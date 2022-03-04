import Foundation
import shared
import UIKit

class IOSFormGenerator: FormGenerator {
    var mainView: UIStackView
    var viewModel: FormViewModel?
    
    init() {
        mainView = UIStackView()
        mainView.axis = .vertical
        mainView.distribution = .fillProportionally
    }
    
    func getInterface(components: [FormComponent]) -> Any {
        if mainView.subviews.count == 0 {
            for component in components {
                switch component.type {
                case .body:
                    if let body = component as? FormComponentText {
                        addBodyLabel(text: body.text)
                    }
                case .titlebig:
                    if let title = component as? FormComponentText {
                        addBigTitleLabel(text: title.text)
                    }
                case .titlesmall:
                    if let title = component as? FormComponentText {
                        addSmallTitleLabel(text: title.text)
                    }
                case .textfield:
                    if let textfield = component as? FormComponentTextField {
                        addTextField(id: textfield.id, text: textfield.text, placeholder: textfield.placeholder)
                    }
                case .buttonlist:
                    if let buttonlist = component as? FormComponentButtonList {
                        addButtonList(id: buttonlist.id, title: buttonlist.title, list: buttonlist.list, value: buttonlist.value, placeholder: buttonlist.placeholder)
                    }
                case .image:
                    if let image = component as? FormComponentImage {
                        addImage(imageName: image.image, caption: image.caption)
                    }
                default:
                    print("unknown component")
                }
            }
        }
        
        return mainView
    }
    
    func clear() {
        mainView.subviews.forEach { $0.removeFromSuperview() }
    }
    
    func addImage(imageName: String, caption: String) {
        let imageView = UIImageView()
        imageView.image = UIImage(named: imageName)
        
        let label = getDefaultLabel()
        label.text = caption
        label.font = UIFont.scaledFont(name: LocalConstants.fontNameRegular, textStyle: .callout)
        
        let verticalSpace = getVerticalSpacingView(withHeight: 3)
        
        mainView.addArrangedSubview(imageView)
        mainView.addArrangedSubview(verticalSpace)
        mainView.addArrangedSubview(label)
    }
    
    func addBigTitleLabel(text: String) {
        let label = getDefaultLabel()
        label.text = text
        label.font = UIFont.scaledFont(name: LocalConstants.fontNameBold, textStyle: .title2)
        
        mainView.addArrangedSubview(label)
    }
    
    func addSmallTitleLabel(text: String) {
        var verticalSpacing = getVerticalSpacingView(withHeight: 20)
        mainView.addArrangedSubview(verticalSpacing)
        
        let label = getDefaultLabel()
        label.text = text
        label.font = UIFont.scaledFont(name: LocalConstants.fontNameBold, textStyle: .body)
        
        mainView.addArrangedSubview(label)
        
        verticalSpacing = getVerticalSpacingView(withHeight: 3)
        mainView.addArrangedSubview(verticalSpacing)
    }
    
    func addBodyLabel(text: String) {
        let label = getDefaultLabel()
        label.text = text
        label.font = UIFont.scaledFont(name: LocalConstants.fontNameRegular, textStyle: .body)
        
        mainView.addArrangedSubview(label)
    }
    
    func addTextField(id: String, text: String, placeholder: String) {
        let verticalSpacing = getVerticalSpacingView(withHeight: 10)
        mainView.addArrangedSubview(verticalSpacing)
        
        let textField = TextFieldWithId()
        textField.text = text
        textField.placeholder = placeholder
        textField.font = UIFont.scaledFont(name: LocalConstants.fontNameRegular, textStyle: .body)
        textField.idString = id
        textField.addTarget(self, action: #selector(textFieldChange), for: .allEditingEvents)
        
        mainView.addArrangedSubview(textField)
    }
    
    @objc
    func textFieldChange(_ sender: TextFieldWithId) {
        guard let viewModel = viewModel, let id = sender.idString else { return }
        viewModel.setTextData(id: id, text: sender.text ?? "")
    }
    
    func addButtonList(id: String, title: String, list: [String], value: String, placeholder: String) {
        addSmallTitleLabel(text: title)
        
        let verticalSpacing = getVerticalSpacingView(withHeight: 5)
        mainView.addArrangedSubview(verticalSpacing)
        
        let button = UIButton()
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
        button.titleLabel?.font = UIFont.scaledFont(name: LocalConstants.fontNameRegular, textStyle: .body)
        button.titleLabel?.adjustsFontForContentSizeCategory = true
        
        mainView.addArrangedSubview(button)
    }
}

private extension IOSFormGenerator {
    func getDefaultLabel() -> UILabel {
        let label = UILabel()
        label.numberOfLines = 0
        label.lineBreakMode = .byWordWrapping
        label.adjustsFontForContentSizeCategory = true
        label.textColor = UIColor(red: 58/255, green: 84/255, blue: 40/255, alpha: 1.0)

        return label
    }
    
    func getVerticalSpacingView(withHeight height: CGFloat) -> UIView {
        let view = UIView()
        view.translatesAutoresizingMaskIntoConstraints = false
        view.heightAnchor.constraint(equalToConstant: height).isActive = true
        
        return view
    }
}

private extension IOSFormGenerator {
    struct LocalConstants {
        static let fontNameRegular = "OpenSans-Regular"
        static let fontNameBold = "OpenSans-Bold"
    }
}
