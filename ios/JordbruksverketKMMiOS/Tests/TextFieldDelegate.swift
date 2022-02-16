import UIKit

typealias TextFieldCompletion = ((String) -> Void)

class TextFieldDelegate: UIViewController, UITextFieldDelegate {
    let completion: TextFieldCompletion
    
    init(completion: @escaping TextFieldCompletion) {
        self.completion = completion
        
        super.init()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    func textFieldDidEndEditing(_ textField: UITextField, reason: UITextField.DidEndEditingReason) {
        completion(textField.text ?? "")
    }
}
