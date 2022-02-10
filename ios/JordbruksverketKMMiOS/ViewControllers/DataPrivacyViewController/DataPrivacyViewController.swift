//
//  DataPrivacyViewController.swift
//  JordbruksverketKMMiOS
//
//  Created by David Dahlman on 2022-02-08.
//

import UIKit

class DataPrivacyViewController: UIViewController {
 
    
    @IBOutlet weak var label1: UILabel!
    @IBOutlet weak var text1: UITextView!
    @IBOutlet weak var feedbackButton: UIButton!
    @IBOutlet weak var label2: UILabel!
    @IBOutlet weak var text2: UITextView!
    
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()


        label1.text = "DataPrivacyPolicyApplikationen".localized
        text1.text = "DataPrivacyPolicyTextPart1".localized
       // feedBackButton.titleLabel = "DataPrivacyPolicyFeedbackButton".localized
      label2.text = "DataPrivacyPolicyHantering".localized
        text2.text = "DataPrivacyPolicyTextPart2".localized
       
        
        
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
