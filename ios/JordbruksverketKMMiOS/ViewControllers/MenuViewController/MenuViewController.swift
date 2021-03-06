
import UIKit

final class MenuViewController: UIViewController {
    
    @IBOutlet private weak var label_settings: UILabel!
    @IBOutlet private weak var label_knowledgebank: UILabel!
    @IBOutlet private weak var label_aboutapp: UILabel!
    @IBOutlet private weak var label_privacy: UILabel!
    private var menu: [UILabel]!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        label_knowledgebank.text = "menu_knowledgebank".localized
        label_aboutapp.text = "menu_about".localized
        label_privacy.text = "menu_data".localized
        label_settings.text = "menu_settings".localized
        
        menu = [label_settings, label_aboutapp, label_privacy, label_knowledgebank]
        
        changeFontForScaling()
    }
    
    private func changeFontForScaling() {
        for item in menu {
            item.font = UIFont.scaledFont(name: UIFont.fontNameSemiBold, textStyle: .body)
        }
    }

    @IBAction func closeButtonTapped(_ sender: Any) {
        self.dismiss(animated: true)
    }
    
    @IBAction func settingsButtonTapped(_ sender: Any) {
        let settingsViewController = SettingsViewController()
        navigationController?.pushViewController(settingsViewController, animated: true)
    }
    
    @IBAction func knowledgebankButtonTapped(_ sender: Any) {
    }
    
    @IBAction func aboutButtonTapped(_ sender: Any) {
        let aboutViewController = AboutAppViewController()
        navigationController?.pushViewController(aboutViewController, animated: true)
    }
    
    @IBAction func dataButonTapped(_ sender: Any) {
        let dataPolicyViewController = DataPrivacyViewController()
        navigationController?.pushViewController(dataPolicyViewController, animated: true)
    }
}
