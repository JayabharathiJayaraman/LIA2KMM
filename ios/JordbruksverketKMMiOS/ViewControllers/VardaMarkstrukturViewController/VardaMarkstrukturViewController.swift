//
//  VardaMarkstrukturViewController.swift
//  JordbruksverketKMMiOS
//
//  Created by David Dahlman on 2022-03-18.
//

import UIKit

class VardaMarkstrukturViewController: UIViewController {

    @IBOutlet weak var Label_1: UILabel!
    
    @IBOutlet weak var MainText: UILabel!
    
    @IBOutlet weak var SecondText: UILabel!
    
    @IBOutlet weak var Label_2: UILabel!
    
  
    @IBOutlet weak var PDF_Btn_label: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        setUpLabels()
    }

    func setUpLabels(){
        Label_1.text = "varda_markstruktur_beskrivning".localized
        MainText.text = "varda_markstruktur_main_text".localized
        SecondText.text = "varda_markstruktur_second_text".localized
        Label_2.text = "varda_markstruktur_atgarder".localized
        PDF_Btn_label.text = "varda_markstruktur_export_button".localized
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
