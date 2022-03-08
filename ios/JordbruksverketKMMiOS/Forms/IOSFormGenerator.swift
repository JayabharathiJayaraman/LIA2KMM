import Foundation
import shared
import UIKit

class IOSFormGenerator: FormGenerator {
    func generateInterface(components: [FormComponent]) -> Any {
        let mainView = UIStackView()
        mainView.axis = .vertical
        mainView.distribution = .fillProportionally
        
        if mainView.subviews.count == 0 {
            for component in components {
                switch component.type {
                case .body:
                    if let body = component as? FormComponentText {
                        mainView.addBodyLabel(text: body.text)
                    }
                case .titlebig:
                    if let title = component as? FormComponentText {
                        mainView.addBigTitleLabel(text: title.text)
                    }
                case .titlesmall:
                    if let title = component as? FormComponentText {
                        mainView.addSmallTitleLabel(text: title.text)
                    }
                case .textfield:
                    if let textfield = component as? FormComponentTextField {
                        mainView.addTextField(id: textfield.id, text: textfield.text, placeholder: textfield.placeholder)
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
        
        return mainView
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
    
    func addBigTitleLabel(text: String) {
        let label = getDefaultLabel()
        label.text = text
        label.font = UIFont.scaledFont(name: UIFont.fontNameBold, textStyle: .title2)
        
        self.addArrangedSubview(label)
    }
    
    func addSmallTitleLabel(text: String) {
        var verticalSpacing = getVerticalSpacingView(withHeight: 20)
        self.addArrangedSubview(verticalSpacing)
        
        let label = getDefaultLabel()
        label.text = text
        label.font = UIFont.scaledFont(name: UIFont.fontNameBold, textStyle: .body)
        
        self.addArrangedSubview(label)
        
        verticalSpacing = getVerticalSpacingView(withHeight: 3)
        self.addArrangedSubview(verticalSpacing)
    }
    
    func addBodyLabel(text: String) {
        let label = getDefaultLabel()
        label.text = text
        label.font = UIFont.scaledFont(name: UIFont.fontNameRegular, textStyle: .body)
        
        self.addArrangedSubview(label)
    }
    
    func addTextField(id: String, text: String, placeholder: String) {
        let verticalSpacing = getVerticalSpacingView(withHeight: 10)
        self.addArrangedSubview(verticalSpacing)
        
        let textField = TextFieldWithId()
        textField.text = text
        textField.placeholder = placeholder
        textField.font = UIFont.scaledFont(name: UIFont.fontNameRegular, textStyle: .body)
        textField.idString = id
        textField.addTarget(self, action: #selector(textFieldChange), for: .allEditingEvents)
        
        self.addArrangedSubview(textField)
    }
    
    @objc
    func textFieldChange(_ sender: TextFieldWithId) {
        guard let id = sender.idString else { return }
        IOSFormViewModel.shared.setTextData(id: id, text: sender.text ?? "")
    }
    
    func addButtonList(id: String, title: String, list: [String], value: String, placeholder: String) {
        addSmallTitleLabel(text: title)
        
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
