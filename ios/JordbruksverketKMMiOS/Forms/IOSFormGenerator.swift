import Foundation
import shared
import UIKit

class IOSFormGenerator: FormGenerator {
    private var mainView = UIStackView()
    private var currentScreenRendered: Int32 = 0
    
    init() {
        mainView.axis = .vertical
        mainView.distribution = .fillProportionally
    }
    
    func updateInterface(components: [FormComponent], currentScreen: Int32) {
        if currentScreen != currentScreenRendered {
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
                    mainView.createBodyLabel(screenTag: screenTag, text: body.text)
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
                    mainView.addButtonList(screenTag: screenTag, id: buttonlist.id, title: buttonlist.title, list: buttonlist.list, value: buttonlist.value, placeholder: buttonlist.placeholder)
                }
            case .button:
                if let button = component as? FormComponentButton {
                    mainView.addButton(screenTag: screenTag, id: button.id, text: button.text)
                }
            case .remark:
                if let remark = component as? FormComponentRemark {
                    mainView.addRemark(screenTag: screenTag, text: remark.text, id: remark.id, image: remark.image)
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
    
    func addBigTitleLabel(screenTag: Int,text: String) {
        if self.subviews.first(where: { view in view.tag == screenTag }) == nil {
            let label = getDefaultLabel()
            label.text = text
            label.font = UIFont.scaledFont(name: UIFont.fontNameBold, textStyle: .title2)
            label.textColor = hexStringToUIColor(hex: "#2e651a")
            self.addArrangedSubview(label)
        }
    }
    
    func addSmallTitleLabel(screenTag: Int,text: String) {
        if self.subviews.first(where: { view in view.tag == screenTag }) == nil {
            var verticalSpacing = getVerticalSpacingView(withHeight: 20)
            self.addArrangedSubview(verticalSpacing)
            
            let label = getDefaultLabel()
            label.text = text
            label.textColor = hexStringToUIColor(hex: "#2e651a")
            label.font = UIFont.scaledFont(name: UIFont.fontNameBold, textStyle: .body)
            
            self.addArrangedSubview(label)
            
            verticalSpacing = getVerticalSpacingView(withHeight: 3)
            self.addArrangedSubview(verticalSpacing)
        }
    }
    
    func createBodyLabel(screenTag: Int, text: String) {
        if self.subviews.first(where: { view in view.tag == screenTag }) == nil {
            var verticalSpacing = getVerticalSpacingView(withHeight: 20)
            self.addArrangedSubview(verticalSpacing)
            
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
        IOSFormViewModel.shared.setTextData(id: id, text: sender.text ?? "")
    }
    
    func addButtonList(screenTag: Int, id: String, title: String, list: [String], value: String, placeholder: String) {
        addSmallTitleLabel(screenTag: screenTag, text: title)
                let verticalSpacing = getVerticalSpacingView(withHeight: 5)
                self.addArrangedSubview(verticalSpacing)
                let button = UIButton()
                let buttonListPlaceholerColor = hexStringToUIColor(hex: "#4d4d4d")
                button.setTitle(placeholder, for: .normal)
                button.setTitleColor(buttonListPlaceholerColor, for: .normal)
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
    
    func addButton(screenTag: Int, id: String, text: String){
        let verticalSpacing = getVerticalSpacingView(withHeight: 30)
        self.addArrangedSubview(verticalSpacing)
        let buttonTitleColor = hexStringToUIColor(hex: "#2e651a")
        let button = UIButton()
        
        let widthConstraint = button.widthAnchor.constraint(equalToConstant: 30.0)
        let heightConstraint = button.heightAnchor.constraint(equalToConstant: 30.0)
        NSLayoutConstraint.activate([widthConstraint, heightConstraint])
        heightConstraint.constant = 80
        button.setTitle(text, for: .normal)
        button.titleLabel!.lineBreakMode = .byWordWrapping
        button.setTitleColor(buttonTitleColor, for: .normal)
        button.contentHorizontalAlignment = .center
        button.backgroundColor = .white
        button.titleLabel?.font = UIFont.scaledFont(name: UIFont.fontNameRegular, textStyle: .body)
        button.titleLabel?.adjustsFontForContentSizeCategory = true
        self.addArrangedSubview(button)
    }
    
    func addRemark(screenTag: Int, text: String, id: String, image: String) {
        if self.subviews.first(where: { view in view.tag == screenTag }) == nil {
            let imageView = UIImageView()
            imageView.image = UIImage(named: image)
            imageView.frame = CGRect(x: 20, y: 40, width: 60, height: 60)
            
            let paddedView = UIView()
            paddedView.addSubview(imageView)
            paddedView.widthAnchor.constraint(equalToConstant: 100).isActive = true
            paddedView.heightAnchor.constraint(equalToConstant: 100).isActive = true
           
            let label = getDefaultLabel()
            label.textAlignment = .left
            label.text = text
            //label.addTextWithImage(text: text, image:  UIImage(named: image)!, imageBehindText: false, keepPreviousText: false)
           // label.heightAnchor.constraint(equalToConstant: 150).isActive = true
            label.font = UIFont.scaledFont(name: UIFont.fontNameRegular, textStyle: .caption2)
            let verticalSpace = getVerticalSpacingView(withHeight: 10)
            let stackView = UIStackView()
            stackView.axis = .horizontal
            stackView.backgroundColor = .white
            self.addArrangedSubview(stackView)
            stackView.heightAnchor.constraint(equalToConstant: 140).isActive = true
            stackView.cornerRadius = 10
          //  stackView.addArrangedSubview(imageView)
            stackView.addArrangedSubview(paddedView)
            stackView.addArrangedSubview(label)
            
            //self.addArrangedSubview(imageView)
            self.addArrangedSubview(verticalSpace)
           //self.addArrangedSubview(label)
    }
    
    }
        
    func getDefaultLabel() -> UILabel {
        let label = UILabel()
        label.numberOfLines = 0
        label.lineBreakMode = .byWordWrapping
        label.adjustsFontForContentSizeCategory = true
        label.textColor = UIColor.Jordbruksverket.defaultTextColor
        
        
        return label
    }
    
    func hexStringToUIColor (hex:String) -> UIColor {
        var cString:String = hex.trimmingCharacters(in: .whitespacesAndNewlines).uppercased()

        if (cString.hasPrefix("#")) {
            cString.remove(at: cString.startIndex)
        }

        if ((cString.count) != 6) {
            return UIColor.gray
        }

        var rgbValue:UInt64 = 0
        Scanner(string: cString).scanHexInt64(&rgbValue)

        return UIColor(
            red: CGFloat((rgbValue & 0xFF0000) >> 16) / 255.0,
            green: CGFloat((rgbValue & 0x00FF00) >> 8) / 255.0,
            blue: CGFloat(rgbValue & 0x0000FF) / 255.0,
            alpha: CGFloat(1.0)
        )
    }
    func getVerticalSpacingView(withHeight height: CGFloat) -> UIView {
        let view = UIView()
        view.translatesAutoresizingMaskIntoConstraints = false
        view.heightAnchor.constraint(equalToConstant: height).isActive = true
        
        return view
    }
}
