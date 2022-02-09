//
//  AboutViewController.swift
//  JordbruksverketKMMiOS
//
//  Created by Ghana Chandrasekaran on 2022-02-08.
//

import UIKit

class AboutViewController: UIViewController {

    @IBOutlet weak var about_section1_title: UILabel!
    
    @IBOutlet weak var about_section1_info: UILabel!
    
    @IBOutlet weak var about_section2_title: UILabel!
    
    @IBOutlet weak var about_section2_info: UILabel!
    
   // @IBOutlet weak var about_section3_title: UILabel!
    
   // @IBOutlet weak var about_section3_info: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        about_section1_title.text = "about_app_section1_title".localized
        
        about_section1_info.text = "about_app_section1_description".localized
        
        about_section2_title.text = "about_app_section2_title".localized
        
        about_section2_info.text = "about_app_section2_description".localized
       
       /* about_section3_title.text = "about_app_section3_title".localized
        
        about_section3_info.text = "about_app_section3_description".localized*/
    
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
