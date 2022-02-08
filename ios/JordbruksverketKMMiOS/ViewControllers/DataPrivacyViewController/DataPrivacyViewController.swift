//
//  DataPrivacyViewController.swift
//  JordbruksverketKMMiOS
//
//  Created by David Dahlman on 2022-02-08.
//

import UIKit

class DataPrivacyViewController: UIViewController {
    @IBOutlet weak var goBack: UILabel!
    @IBOutlet weak var pageLabel: UILabel!
    @IBOutlet weak var contentLabelApplikationen: UILabel!
    @IBOutlet weak var textPart1: UILabel!
    @IBOutlet weak var feedBackButton: UIButton!
    @IBOutlet weak var contentLabelHanterin: UILabel!
    @IBOutlet weak var textPart2: UILabel!    
    @IBOutlet weak var textPart3: UILabel!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()

        goBack.text = "DataPrivacyPolicyTILLBAKA".localized
        pageLabel.text = "DataPrivacyPolicyPageLabel".localized
        contentLabelApplikationen.text = "DataPrivacyPolicyApplikationen".localized
        textPart1.text = "DataPrivacyPolicyTextPart1".localized
       // feedBackButton.titleLabel = "DataPrivacyPolicyFeedbackButton".localized
        
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
