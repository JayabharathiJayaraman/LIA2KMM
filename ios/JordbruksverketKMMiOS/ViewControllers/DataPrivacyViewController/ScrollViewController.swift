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
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()

        label1.text = "DataPrivacyPolicyApplikationen".localized
        text1.text = "DataPrivacyPolicyTextPart1".localized
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
