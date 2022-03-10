import UIKit

final class AboutAppViewController: UIViewController {
    
    @IBOutlet private weak var about_title1: UILabel!
    @IBOutlet private weak var about_info1: UILabel!
    @IBOutlet private weak var about_title2: UILabel!
    @IBOutlet private weak var about_info2: UILabel!
    @IBOutlet private weak var about_title3: UILabel!
    @IBOutlet private weak var about_info3: UILabel!
    @IBOutlet private weak var about_title4: UILabel!
    @IBOutlet private weak var about_info4: UILabel!
    @IBOutlet private weak var about_title5: UILabel!
    @IBOutlet private weak var about_info5: UILabel!
    
    private var titles:[UILabel]!
    private var informations:[UILabel]!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        titles = [about_title1,about_title2,about_title3,about_title4,about_title5]
        informations = [about_info1,about_info2,about_info3,about_info4,about_info5]
        
        about_title1.text = "about_app_section1_title".localized
        about_title2.text = "about_app_section2_title".localized
        about_title3.text = "about_app_section3_title".localized
        about_title4.text = "about_app_section4_title".localized
        about_title5.text = "about_app_section5_title".localized
        
        about_info1.text = "about_app_section1_description".localized
        about_info2.text = "about_app_section2_description".localized
        about_info3.text = "about_app_section3_description".localized
        about_info4.text = "about_app_section4_description".localized
        about_info5.text = "about_app_section5_description".localized
        
        setFontAccessibility()
    }

    
    func setFontAccessibility() {
        for title in titles {
            title.font = UIFont.scaledFont(name: UIFont.fontNameSemiBold, textStyle: .body)
        }
        
        for information in informations {
            information.font = UIFont.scaledFont(name: UIFont.fontNameRegular, textStyle: .body)
        }
    }
}
