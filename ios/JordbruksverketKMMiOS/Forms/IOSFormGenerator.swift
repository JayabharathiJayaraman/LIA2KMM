import AVFoundation
import AVKit
import Foundation
import shared
import UIKit
import MapKit

class IOSFormGenerator: FormGenerator {
    private var mainView = UIStackView()
    private var currentScreenRendered: Int32 = 0

    var presentingViewController: UIViewController?

    init() {
        mainView.axis = .vertical
        mainView.distribution = .fillProportionally
    }

    func updateInterface(components: [FormComponent], currentScreen: Int32) {
        if currentScreen != currentScreenRendered {
            presentingViewController?.children.forEach { child in
                child.willMove(toParent: nil)
                child.removeFromParent()
            }
            mainView.subviews.forEach { $0.removeFromSuperview() }
            currentScreenRendered = currentScreen
        }

        generateInterface(components: components, currentScreen: currentScreen.asKotlinInt)
    }

    func createInterface(components: [FormComponent], currentScreen: Int32) -> Any {
        generateInterface(components: components, currentScreen: currentScreen.asKotlinInt)

        return mainView
    }

    func generateInterface(components: [FormComponent], currentScreen: KotlinInt?) {
        guard let currentScreen = currentScreen else { return }
        let screenTag = Int(truncating: currentScreen)

        for component in components {
            switch component.type {
            case .body:
                if let body = component as? FormComponentText {
                    mainView.creatBodyLabel(screenTag: screenTag, text: body.text)
                }
            case .titlebig:
                if let title = component as? FormComponentText {
                    mainView.addBigTitleLabel(screenTag: screenTag, text: title.text)
                }
            case .titlesmall:
                if let title = component as? FormComponentText {
                    mainView.addSmallTitleLabel(screenTag: screenTag, text: title.text)
                }
            case .textfield:
                if let textfield = component as? FormComponentTextField {
                    mainView.createOrUpdateTextField(id: textfield.id, text: textfield.text, placeholder: textfield.placeholder)
                }
            case .buttonlist:
                if let buttonlist = component as? FormComponentButtonList {
                    addButtonList(screenTag: screenTag, id: buttonlist.id, title: buttonlist.title, list: buttonlist.list, value: buttonlist.value, placeholder: buttonlist.placeholder)
                }
            case .image:
                if let image = component as? FormComponentImage {
                    mainView.addImage(imageName: image.image, caption: image.caption)
                }
            case .information:
                if let information = component as? FormComponentInformation {
                    addInformationView(screenTag: screenTag, id: information.id, components: information.components)
                }
            case .video:
                if let video = component as? FormComponentVideo {
                    addVideo(source: video.source)
                }
            case .maps:
                if let mapImage = component as? FormComponentMap {
                    mainView.addMap(screenTag: screenTag)
                }
            default:
                print("unknown component")
            }
        }
    }
}

private extension IOSFormGenerator {
    func addButtonList(
        screenTag: Int,
        id: String,
        title: String,
        list: [String],
        value: String,
        placeholder: String
    ) {
        mainView.addSmallTitleLabel(screenTag: screenTag, text: title)

        let verticalSpacing = mainView.getVerticalSpacingView(withHeight: 5)
        mainView.addArrangedSubview(verticalSpacing)

        let button = ButtonWithList()
        button.id = id
        button.list = list

        button.setTitle(placeholder, for: .normal)
        button.setTitleColor(.black, for: .normal)
        button.contentHorizontalAlignment = .left
        button.backgroundColor = .white
        button.contentEdgeInsets = UIEdgeInsets(top: 11, left: 12, bottom: 11, right: 12)
        button.layer.cornerRadius = 4
        button.layer.shadowColor = UIColor.black.cgColor
        button.layer.shadowRadius = 5
        button.layer.shadowOpacity = 0.5
        button.layer.shadowOffset = CGSize(width: 0, height: 3)
        button.titleLabel?.font = UIFont.scaledFont(name: UIFont.fontNameRegular, textStyle: .body)
        button.titleLabel?.adjustsFontForContentSizeCategory = true
        button.addTarget(self, action: #selector(handleButtonListButtonTap), for: .touchUpInside)

        mainView.addArrangedSubview(button)
    }

    @objc
    func handleButtonListButtonTap(_ button: ButtonWithList) {
        guard
            let id = button.id,
            let list = button.list,
            let presentingViewController = presentingViewController
        else { return }

        let buttonListViewController = ButtonListViewController(list: list)
        let modalViewController = ModalViewController(contentViewController: buttonListViewController)

        buttonListViewController.itemSelectionHandler = { [weak modalViewController] item in
            button.setTitle(item, for: .normal)
//            IOSFormViewModel.shared.setTextData(id: id, text: item)
            modalViewController?.dismiss()
        }

        modalViewController.present(using: presentingViewController)
    }

    func addInformationView(screenTag: Int, id: String, components: [FormComponent]) {
        let button = ButtonWithComponents()
        button.components = components
        button.setImage(UIImage(named: "infoIcon"), for: .normal)
        button.setTitleColor(.black, for: .normal)
        button.addTarget(self, action: #selector(handleInformationButtonTap), for: .touchUpInside)

        button.translatesAutoresizingMaskIntoConstraints = false
        button.widthAnchor.constraint(equalToConstant: 53.0).isActive = true
        button.heightAnchor.constraint(equalToConstant: 50.0).isActive = true

        let stackView = UIStackView(arrangedSubviews: [UIView(), button])
        stackView.axis = .horizontal

        mainView.addArrangedSubview(stackView)
    }

    @objc
    func handleInformationButtonTap(_ button: ButtonWithComponents) {
        guard
            let components = button.components,
            let presentingViewController = presentingViewController
        else { return }

        let informationViewController = InformationViewController(components: components)
        let modalViewController = ModalViewController(contentViewController: informationViewController)
        modalViewController.present(using: presentingViewController)
    }

    func addVideo(source: String) {
        guard let url = Bundle.main.url(forResource: source, withExtension: "mp4") else { return }

        let player = AVPlayer(url: url)
        let playerViewController = AVPlayerViewController()
        playerViewController.player = player
        playerViewController.videoGravity = .resizeAspectFill

        playerViewController.view.translatesAutoresizingMaskIntoConstraints = false
        playerViewController.view.heightAnchor.constraint(equalTo: playerViewController.view.widthAnchor).isActive = true

        mainView.addArrangedSubview(playerViewController.view)
        presentingViewController?.addChild(playerViewController)
        playerViewController.didMove(toParent: presentingViewController)
    }
}

extension UIStackView {
    func addImage(imageName: String, caption: String) {
        let imageView = UIImageView()
        imageView.image = UIImage(named: imageName)

        let label = getDefaultLabel()
        label.text = caption
        label.font = UIFont.scaledFont(name: UIFont.fontNameRegular, textStyle: .callout)

        let verticalSpace = getVerticalSpacingView(withHeight: 3)

        self.addArrangedSubview(imageView)

        self.addArrangedSubview(imageView)
        self.addArrangedSubview(verticalSpace)
        self.addArrangedSubview(label)
    }
    func addMap(screenTag: Int) {

           let mapView = MapScreenViewController()

           self.addArrangedSubview(mapView.view)
    }


    func addBigTitleLabel(screenTag: Int,text: String) {
        if self.subviews.first(where: { view in view.tag == screenTag }) == nil {
            let label = getDefaultLabel()
            label.text = text
            label.font = UIFont.scaledFont(name: UIFont.fontNameBold, textStyle: .title2)

            self.addArrangedSubview(label)
        }
    }

    func addSmallTitleLabel(screenTag: Int,text: String) {
        if self.subviews.first(where: { view in view.tag == screenTag }) == nil {
            var verticalSpacing = getVerticalSpacingView(withHeight: 20)
            self.addArrangedSubview(verticalSpacing)

            let label = getDefaultLabel()
            label.text = text
            label.font = UIFont.scaledFont(name: UIFont.fontNameBold, textStyle: .body)

            self.addArrangedSubview(label)

            verticalSpacing = getVerticalSpacingView(withHeight: 3)
            self.addArrangedSubview(verticalSpacing)
        }
    }

    func creatBodyLabel(screenTag: Int, text: String) {
        if self.subviews.first(where: { view in view.tag == screenTag }) == nil {
            let label = getDefaultLabel()
            label.tag = screenTag
            label.text = text
            label.font = UIFont.scaledFont(name: UIFont.fontNameRegular, textStyle: .body)

            self.addArrangedSubview(label)
        }
    }

    func createOrUpdateTextField(id: String, text: String, placeholder: String) {
        if let existingView = (self.subviews.first(where: { view in
            (view as? TextFieldWithId)?.idString == id
        }) as? TextFieldWithId) {
            existingView.text = text
        } else {
            let verticalSpacing = getVerticalSpacingView(withHeight: 10)
            self.addArrangedSubview(verticalSpacing)

            let textField = TextFieldWithId()
            textField.text = text
            textField.placeholder = placeholder
            textField.font = UIFont.scaledFont(name: UIFont.fontNameRegular, textStyle: .body)
            textField.textColor = UIColor.Jordbruksverket.defaultTextColor
            textField.idString = id
            textField.addTarget(self, action: #selector(textFieldChange), for: .editingChanged)

            self.addArrangedSubview(textField)
        }
    }

    @objc
    func textFieldChange(_ sender: TextFieldWithId) {
        guard let id = sender.idString else { return }
        IOSFormViewModel.shared.formViewModel.setTextData(id: id, text: sender.text ?? "")
    }

    func getDefaultLabel() -> UILabel {
        let label = UILabel()
        label.numberOfLines = 0
        label.lineBreakMode = .byWordWrapping
        label.adjustsFontForContentSizeCategory = true
        label.textColor = UIColor.Jordbruksverket.defaultTextColor

        return label
    }

    func getVerticalSpacingView(withHeight height: CGFloat) -> UIView {
        let view = UIView()
        view.translatesAutoresizingMaskIntoConstraints = false
        view.heightAnchor.constraint(equalToConstant: height).isActive = true

        return view
    }
}
