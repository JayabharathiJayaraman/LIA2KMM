import UIKit

class NewTestViewController: UIViewController {

    @IBOutlet weak var generalQuestionsTitleLabel: UILabel!
    @IBOutlet weak var generalQuestionsLabel: UILabel!
    @IBOutlet weak var soilStructureTitleLabel: UILabel!
    @IBOutlet weak var soilStructureLabel: UILabel!
    @IBOutlet weak var infiltrationTestTitleLabel: UILabel!
    @IBOutlet weak var infiltrationTestLabel: UILabel!
    @IBOutlet weak var bodyTextTitleLabel: UILabel!
    @IBOutlet weak var bodyTextLabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        setupLabels()
    }
    
    private func setupLabels() {
        let smallTitles = [generalQuestionsTitleLabel, soilStructureTitleLabel, infiltrationTestTitleLabel]
        let bigTitles = [generalQuestionsLabel, soilStructureLabel, infiltrationTestLabel]
        smallTitles.forEach { $0?.font = UIFont.scaledFont(name: UIFont.fontNameRegular, textStyle: .caption1)}
        bigTitles.forEach { $0?.font = UIFont.scaledFont(name: UIFont.fontNameBold, textStyle: .body)}
        
        bodyTextTitleLabel.font = UIFont.scaledFont(name: UIFont.fontNameBold, textStyle: .body)
        bodyTextLabel.font = UIFont.scaledFont(name: UIFont.fontNameRegular, textStyle: .body)
        
        
    }
}
