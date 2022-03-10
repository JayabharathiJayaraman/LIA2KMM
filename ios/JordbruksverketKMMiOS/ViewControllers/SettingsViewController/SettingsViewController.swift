import UIKit

final class SettingsViewController: UIViewController {
    @IBOutlet private weak var myDetails: UILabel!
    @IBOutlet private weak var nameTextField: TextFieldWithId!
    @IBOutlet private weak var emailTextField: TextFieldWithId!
    @IBOutlet private weak var saveButton: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        localized()
        
        saveButton.layer.cornerRadius = 20
        nameTextField.font = UIFont.scaledFont(name: UIFont.fontNameRegular, textStyle: .body)
        emailTextField.font = UIFont.scaledFont(name: UIFont.fontNameRegular, textStyle: .body)
        myDetails.font = UIFont.scaledFont(name: UIFont.fontNameSemiBold, textStyle: .body)
    }
    
    private func localized(){
        myDetails.text = "settings_mina_uppgifter".localized;
        nameTextField.placeholder = "settings_namn".localized;
        emailTextField.placeholder = "settings_emailadress".localized;
        saveButton.setTitle("settings_spara".localized, for: .normal)
    }
}
