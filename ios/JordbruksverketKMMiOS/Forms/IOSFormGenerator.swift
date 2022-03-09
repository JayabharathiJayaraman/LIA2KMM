import Foundation
import shared
import UIKit

class IOSFormGenerator: FormGenerator {
    private var mainView = UIStackView()
    
    init() {
        mainView.axis = .vertical
        mainView.distribution = .fillProportionally
    }
    
    func updateInterface(components: [FormComponent]) {
        generateInterface(components: components)
    }
    
    func createInterface(components: [FormComponent]) -> Any {
        generateInterface(components: components)
        
        return mainView
    }
    
    func generateInterface(components: [FormComponent]) {
        clearScreenIfNecessary(components: components)
        
        if mainView.subviews.count == 0 {
            for component in components {
                switch component.type {
                case .body:
                    if let body = component as? FormComponentText {
                        mainView.addBodyLabel(id: body.id, text: body.text)
                    }
                case .titlebig:
                    if let title = component as? FormComponentText {
                        mainView.addBigTitleLabel(id: title.id, text: title.text)
                    }
                case .titlesmall:
                    if let title = component as? FormComponentText {
                        mainView.addSmallTitleLabel(id: title.id, text: title.text)
                    }
                case .textfield:
                    if let textfield = component as? FormComponentTextField {
                        mainView.createOrUpdateTextField(id: textfield.id, text: textfield.text, placeholder: textfield.placeholder)
                    }
                case .buttonlist:
                    if let buttonlist = component as? FormComponentButtonList {
                        mainView.addButtonList(id: buttonlist.id, title: buttonlist.title, list: buttonlist.list, value: buttonlist.value, placeholder: buttonlist.placeholder)
                    }
                case .image:
                    if let image = component as? FormComponentImage {
                        mainView.addImage(imageName: image.image, caption: image.caption)
                    }
                default:
                    print("unknown component")
                }
            }
        }
    }
    
    private func clearScreenIfNecessary(components: [FormComponent]) {
        var shouldClearScreen = true
        
        if mainView.subviews.count > 0 {
        outerLoop: for component in components {
        innerLoop: for view in mainView.subviews {
            if let view = view as? LabelWithId {
                if view.idString == component.id {
                    shouldClearScreen = false
                    break innerLoop
                }
            }
        }
            break outerLoop
        }
        }
        
        if shouldClearScreen { mainView.subviews.forEach { $0.removeFromSuperview() }}
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
        self.addArrangedSubview(verticalSpace)
        self.addArrangedSubview(label)
    }
    
    func addBigTitleLabel(id: String, text: String) {
        let label = getDefaultLabel()
        label.idString = id
        label.text = text
        label.font = UIFont.scaledFont(name: UIFont.fontNameBold, textStyle: .title2)
        
        self.addArrangedSubview(label)
    }
    
    func addSmallTitleLabel(id: String, text: String) {
        var verticalSpacing = getVerticalSpacingView(withHeight: 20)
        self.addArrangedSubview(verticalSpacing)
        
        let label = getDefaultLabel()
        label.idString = id
        label.text = text
        label.font = UIFont.scaledFont(name: UIFont.fontNameBold, textStyle: .body)
        
        self.addArrangedSubview(label)
        
        verticalSpacing = getVerticalSpacingView(withHeight: 3)
        self.addArrangedSubview(verticalSpacing)
    }
    
    func addBodyLabel(id: String, text: String) {
        let label = getDefaultLabel()
        label.idString = id
        label.text = text
        label.font = UIFont.scaledFont(name: UIFont.fontNameRegular, textStyle: .body)
        
        self.addArrangedSubview(label)
    }
    
    func createOrUpdateTextField(id: String, text: String, placeholder: String) {
        let existingView = self.subviews.first { view in
            (view as? TextFieldWithId)?.idString == id
        } as? TextFieldWithId
        
        var textField = TextFieldWithId()
        
        if let existingView = existingView {
            textField = existingView
        } else {
            let verticalSpacing = getVerticalSpacingView(withHeight: 10)
            self.addArrangedSubview(verticalSpacing)
            
            textField.placeholder = placeholder
            textField.font = UIFont.scaledFont(name: UIFont.fontNameRegular, textStyle: .body)
            textField.idString = id
            textField.addTarget(self, action: #selector(textFieldChange), for: .editingChanged)
            
            self.addArrangedSubview(textField)
        }
        
        textField.text = text
    }
    
    @objc
    func textFieldChange(_ sender: TextFieldWithId) {
        guard let id = sender.idString else { return }
        IOSFormViewModel.shared.setTextData(id: id, text: sender.text ?? "")
    }
    
    func addButtonList(id: String, title: String, list: [String], value: String, placeholder: String) {
        addSmallTitleLabel(id: id, text: title)
        
        let verticalSpacing = getVerticalSpacingView(withHeight: 5)
        self.addArrangedSubview(verticalSpacing)
        
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
        button.titleLabel?.font = UIFont.scaledFont(name: UIFont.fontNameRegular, textStyle: .body)
        button.titleLabel?.adjustsFontForContentSizeCategory = true
        
        self.addArrangedSubview(button)
    }
    
    func getDefaultLabel() -> LabelWithId {
        let label = LabelWithId()
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
