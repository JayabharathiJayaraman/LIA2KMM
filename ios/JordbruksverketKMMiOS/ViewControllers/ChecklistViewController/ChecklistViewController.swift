//
//  ChecklistViewController.swift
//  JordbruksverketKMMiOS
//
//  Created by David Dahlman on 2022-03-21.
//

import UIKit

class ChecklistViewController: UIViewController {

    @IBOutlet weak var TitleLabel: UILabel!
    
    @IBOutlet weak var MainTextLabel: UILabel!
    
    @IBOutlet weak var AnpassaTextLabel: UILabel!
    
    @IBOutlet weak var AtgardlistaLabel: UILabel!
   
    @IBOutlet weak var AtgardslistaTextLabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        TitleLabel.text = String(IOSChecklistViewModel.shared.currentState.checklist.title).localized
        MainTextLabel.text = String(IOSChecklistViewModel.shared.currentState.checklist.text).localized
        AnpassaTextLabel.text = "check_list_text1".localized
        AtgardlistaLabel.text = "check_list_label_atgardslista".localized
        AtgardslistaTextLabel.text = "check_list_text_3".localized
    }


}
