//
//  AboutAppViewController.swift
//  JordbruksverketKMMiOS
//
//  Created by Ghana Chandrasekaran on 2022-02-12.
//

import UIKit

class AboutAppViewController: UIViewController {
    
    @IBOutlet weak var about_title1: UILabel!
    @IBOutlet weak var about_info1: UILabel!
    @IBOutlet weak var about_title2: UILabel!
    @IBOutlet weak var about_info2: UILabel!
    @IBOutlet weak var about_title3: UILabel!
    @IBOutlet weak var about_info3: UILabel!
    @IBOutlet weak var about_title4: UILabel!
    @IBOutlet weak var about_info4: UILabel!
    @IBOutlet weak var about_title5: UILabel!
    @IBOutlet weak var about_info5: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        about_title1.text = "about_app_section1_title".localized
        
        about_info1.text = "about_app_section1_description".localized
        
        about_title2.text = "about_app_section2_title".localized
        
        about_info2.text = "about_app_section2_description".localized
        
        about_title3.text = "about_app_section3_title".localized
        
        about_info3.text = "about_app_section3_description".localized
        
        about_title4.text = "about_app_section4_title".localized
        
        about_info4.text = "about_app_section4_description".localized
        
        about_title5.text = "about_app_section5_title".localized
        
        about_info5.text = "about_app_section5_description".localized
        
        adjustFontAccessibility()
        
    }
    
   /* override func traitCollectionDidChange(_ previousTraitCollection: UITraitCollection?) {
        
        let pointSizeTitle = UIFontDescriptor.preferredFontDescriptor(withTextStyle: .subheadline).pointSize
        about_title1.font = about_title1.font.withSize(pointSizeTitle)
        about_title2.font = about_title2.font.withSize(pointSizeTitle)
        about_title3.font = about_title3.font.withSize(pointSizeTitle)
        about_title4.font = about_title4.font.withSize(pointSizeTitle)
        about_title5.font = about_title5.font.withSize(pointSizeTitle)
        
        let pointSizeInfo = UIFontDescriptor.preferredFontDescriptor(withTextStyle: .body).pointSize
        about_info1.font = about_info1.font.withSize(pointSizeInfo)
        about_info2.font = about_info2.font.withSize(pointSizeInfo)
        about_info3.font = about_info3.font.withSize(pointSizeInfo)
        about_info4.font = about_info4.font.withSize(pointSizeInfo)
        about_info5.font = about_info5.font.withSize(pointSizeInfo)
        
        adjustFontAccessibility()
    }*/
    
    func adjustFontAccessibility(){
            guard let customTitleFont = UIFont(name: "OpenSans-SemiBold", size: UIFont.labelFontSize) else {
                fatalError("""
                    Failed to load the "Opensans-SemiBold" font.
                    Make sure the font file is included in the project and the font name is spelled correctly.
                    """
                )
            }
            
            guard let customBodyFont = UIFont(name: "OpenSans-Regular", size: UIFont.labelFontSize) else {
                fatalError("""
                    Failed to load the "Opensans-Regular" font.
                    Make sure the font file is included in the project and the font name is spelled correctly.
                    """
                )
            }
            
            about_title1.font = UIFontMetrics(forTextStyle: .subheadline).scaledFont(for: customTitleFont)
            about_title1.adjustsFontForContentSizeCategory = true
            about_title1.adjustsFontSizeToFitWidth = true
        
        about_title2.font = UIFontMetrics(forTextStyle: .subheadline).scaledFont(for: customTitleFont)
        about_title2.adjustsFontForContentSizeCategory = true
        about_title2.adjustsFontSizeToFitWidth = true
        
        about_title3.font = UIFontMetrics(forTextStyle: .subheadline).scaledFont(for: customTitleFont)
        about_title3.adjustsFontForContentSizeCategory = true
        about_title3.adjustsFontSizeToFitWidth = true
        
        about_title4.font = UIFontMetrics(forTextStyle: .subheadline).scaledFont(for: customTitleFont)
        about_title4.adjustsFontForContentSizeCategory = true
        about_title4.adjustsFontSizeToFitWidth = true
        
        about_title5.font = UIFontMetrics(forTextStyle: .subheadline).scaledFont(for: customTitleFont)
        about_title5.adjustsFontForContentSizeCategory = true
        about_title5.adjustsFontSizeToFitWidth = true
            
        about_info1.font = UIFontMetrics(forTextStyle: .body).scaledFont(for: customBodyFont)
        about_info1.adjustsFontForContentSizeCategory = true
        about_info1.adjustsFontSizeToFitWidth = true
        
        about_info2.font = UIFontMetrics(forTextStyle: .body).scaledFont(for: customBodyFont)
        about_info2.adjustsFontForContentSizeCategory = true
        about_info2.adjustsFontSizeToFitWidth = true
        
        about_info3.font = UIFontMetrics(forTextStyle: .body).scaledFont(for: customBodyFont)
        about_info3.adjustsFontForContentSizeCategory = true
        about_info3.adjustsFontSizeToFitWidth = true
        
        about_info4.font = UIFontMetrics(forTextStyle: .body).scaledFont(for: customBodyFont)
        about_info4.adjustsFontForContentSizeCategory = true
        about_info4.adjustsFontSizeToFitWidth = true
        
        about_info5.font = UIFontMetrics(forTextStyle: .body).scaledFont(for: customBodyFont)
        about_info5.adjustsFontForContentSizeCategory = true
        about_info5.adjustsFontSizeToFitWidth = true
            
        }
    


    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
