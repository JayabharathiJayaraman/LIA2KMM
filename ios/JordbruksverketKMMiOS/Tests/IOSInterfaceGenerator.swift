import Foundation
import shared
import UIKit

typealias SetValueHandler = ((TestValueKey, Any) -> Void)

class IOSInterfaceGenerator: InterfaceGenerator {
    var mainView: UIStackView
    var setValueHandler: SetValueHandler?
    
    init() {
        mainView = UIStackView()
        mainView.axis = .vertical
        mainView.spacing = 10
    }
    
    func getInterface() -> Any {
        return mainView
    }
    
    func clear() {
        mainView.subviews.forEach { $0.removeFromSuperview() }
    }
    
    func drawTitleLabel(text: String) {
        let label = getDefaultLabel()
        label.text = text
        label.font = UIFont.scaledFont(name: LocalConstants.fontNameBold, textStyle: .title2)
        
        mainView.addArrangedSubview(label)
    }
    
    func drawBodyLabel(text: String) {
        let label = getDefaultLabel()
        label.text = text
        label.font = UIFont.scaledFont(name: LocalConstants.fontNameRegular, textStyle: .body)
        
        mainView.addArrangedSubview(label)
    }
    
    func drawTextField(text: String, placeholder: String, key: TestValueKey) {
        let textField = UITextField()
        textField.placeholder = placeholder
        textField.font = UIFont.scaledFont(name: LocalConstants.fontNameRegular, textStyle: .body)
        
        mainView.addArrangedSubview(textField)
    }
}

private extension IOSInterfaceGenerator {
    func getDefaultLabel() -> UILabel {
        let label = UILabel()
        label.numberOfLines = 0
        label.lineBreakMode = .byWordWrapping
        label.adjustsFontForContentSizeCategory = true

        return label
    }
}

private extension IOSInterfaceGenerator {
    struct LocalConstants {
        static let fontNameRegular = "OpenSans-Regular"
        static let fontNameBold = "OpenSans-Bold"
    }
}
