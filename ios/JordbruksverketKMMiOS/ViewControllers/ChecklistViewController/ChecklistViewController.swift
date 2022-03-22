//
//  ChecklistViewController.swift
//  JordbruksverketKMMiOS
//
//  Created by David Dahlman on 2022-03-21.
//

import UIKit

class ChecklistViewController: UIViewController {

    @IBOutlet weak var testLabel: UILabel!
    var testVar = IOSChecklistViewModel.shared
    override func viewDidLoad() {
        super.viewDidLoad()
        testLabel.text = String(testVar.currentState.checklist.category.categoryName)
        
    }


}
