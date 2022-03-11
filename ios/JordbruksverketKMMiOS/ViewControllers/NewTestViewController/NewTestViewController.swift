import UIKit

class NewTestViewController: UIViewController {

    @IBOutlet weak var generalQuestionsTitleLabel: DynamicLabel!
    @IBOutlet weak var generalQuestionsLabel: DynamicLabel!
    @IBOutlet weak var soilStructureTitleLabel: DynamicLabel!
    @IBOutlet weak var soilStructureLabel: DynamicLabel!
    @IBOutlet weak var infiltrationTestTitleLabel: DynamicLabel!
    @IBOutlet weak var infiltrationTestLabel: DynamicLabel!
    @IBOutlet weak var bodyTextTitleLabel: DynamicLabel!
    @IBOutlet weak var bodyTextLabel: DynamicLabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        setupLabels()
    }
    
    private func setupLabels() {
        let smallTitles = [generalQuestionsTitleLabel, soilStructureTitleLabel, infiltrationTestTitleLabel]
        let bigTitles = [generalQuestionsLabel, soilStructureLabel, infiltrationTestLabel]
        smallTitles.forEach {
            $0?.font = UIFont.scaledFont(name: UIFont.fontNameRegular, textStyle: .caption1)
        }
        bigTitles.forEach {
            $0?.font = UIFont.scaledFont(name: UIFont.fontNameBold, textStyle: .body)
        }
        
        bodyTextTitleLabel.font = UIFont.scaledFont(name: UIFont.fontNameBold, textStyle: .body)
        bodyTextLabel.font = UIFont.scaledFont(name: UIFont.fontNameRegular, textStyle: .body)
        
        generalQuestionsTitleLabel.text = "new_test_test1".localized.uppercased()
        soilStructureTitleLabel.text = "new_test_test2".localized.uppercased()
        infiltrationTestTitleLabel.text = "new_test_test3".localized.uppercased()
        
        generalQuestionsLabel.text = "new_test_general_questions".localized
        soilStructureLabel.text = "new_test_soil_structure".localized
        infiltrationTestLabel.text = "new_test_infiltration".localized
        
        bodyTextTitleLabel.text = "new_test_about".localized
        bodyTextLabel.text = "new_test_about_body".localized
    }
}
