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

    
    func setFontAccessibility()
    {
            guard let customTitleFont = UIFont(name: "OpenSans-SemiBold", size: UIFont.labelFontSize)
            else {
                fatalError("""
                    Failed to load the "Opensans-SemiBold" font.
                    Make sure the font file is included in the project and the font name is spelled correctly.
                    """)
            }
            
            guard let customInfoFont = UIFont(name: "OpenSans-Regular", size: UIFont.labelFontSize)
            else {
                fatalError("""
                    Failed to load the "Opensans-Regular" font.
                    Make sure the font file is included in the project and the font name is spelled correctly.
                    """)
            }
            
            
        for title in titles
        {
            title.font = UIFontMetrics(forTextStyle: .subheadline).scaledFont(for: customTitleFont)
            about_title1.adjustsFontForContentSizeCategory = true
            about_title1.adjustsFontSizeToFitWidth = true
        }
        
        for information in informations
        {
            information.font = UIFontMetrics(forTextStyle: .body).scaledFont(for: customInfoFont)
            information.adjustsFontForContentSizeCategory = true
            information.adjustsFontSizeToFitWidth = true
        }
        
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
