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
    
    @IBOutlet weak var EjAktullaLabel: UILabel!
  
    @IBOutlet weak var EjAktuellaTextLabel: UILabel!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
       setLabels()
    }


    func setLabels(){
        TitleLabel.text = String(IOSChecklistViewModel.shared.currentState.checklist.title).localized
        MainTextLabel.text = String(IOSChecklistViewModel.shared.currentState.checklist.text).localized
        AnpassaTextLabel.text = "check_list_text1".localized
        AtgardlistaLabel.text = "check_list_label_atgardslista".localized
        AtgardslistaTextLabel.text = "check_list_text_3".localized
        EjAktullaLabel.text = "check_list_label_ej_aktuella_atgarder".localized
        EjAktuellaTextLabel.text = "check_list_text_4".localized
        
        if(IOSChecklistViewModel.shared.currentState.checklist.category == IOSChecklistViewModel.undvikViewModel.checklist.category){
            EjAktullaLabel.isHidden = true
            EjAktuellaTextLabel.isHidden = true
        }
    }
}
