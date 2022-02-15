//
//  ScrollViewController.swift
//  JordbruksverketKMMiOS
//
//  Created by Erik Hedrenius on 2022-02-15.
//

import UIKit

class ScrollViewController: UIViewController {

    @IBOutlet weak var label1: UILabel!
    @IBOutlet weak var text1: UILabel!
    @IBOutlet weak var label2: UILabel!
    @IBOutlet weak var text2: UILabel!
    
    private var titles:[UILabel]!
    private var informations:[UILabel]!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        titles = [label1, label2]
        informations = [text1, text2]
        label1.text = "DataPrivacyPolicyApplikationen".localized
        text1.text = "DataPrivacyPolicyTextPart1".localized
        label2.text = "DataPrivacyPolicyHantering".localized
        text2.text = "DataPrivacyPolicyTextPart2".localized
     
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
                 title.adjustsFontForContentSizeCategory = true
                 title.adjustsFontSizeToFitWidth = true
             }

             for information in informations
             {
                 information.font = UIFontMetrics(forTextStyle: .body).scaledFont(for: customInfoFont)
                 information.adjustsFontForContentSizeCategory = true
                 information.adjustsFontSizeToFitWidth = true
             }

         }
    
    
}
