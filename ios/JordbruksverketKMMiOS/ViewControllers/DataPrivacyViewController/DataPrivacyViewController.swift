
import UIKit

final class DataPrivacyViewController: UIViewController {

    @IBOutlet private weak var label1: UILabel!
    @IBOutlet private weak var text1: UILabel!
    @IBOutlet private weak var label2: UILabel!
    @IBOutlet private weak var text2: UILabel!

    @IBOutlet private weak var feedBackLabel: UILabel!
    
    @IBOutlet private weak var buttonShape: UIView!

    
    private var titles:[UILabel]!
    private var informations:[UILabel]!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        print("Hello ")
        setUpLabels()
        setFontAccessibility()
    }
    

    @IBAction func feedbackButton(_ sender: UIButton) {
        print("button pressed")
    }
    
    
    func setUpLabels(){
        
        titles = [label1, label2]
        informations = [text1, text2, feedBackLabel]
        
        label1.text = "DataPrivacyPolicyApplikationen".localized
        text1.text = "DataPrivacyPolicyTextPart1".localized
        label2.text = "DataPrivacyPolicyHantering".localized
        text2.text = "DataPrivacyPolicyTextPart2".localized
        feedBackLabel.text = "DataPrivacyPolicyFeedbackButton".localized
    }
    
    func setFontAccessibility(){
                 guard let customTitleFont = UIFont(name: "OpenSans-SemiBold", size: UIFont.labelFontSize)
                 else {
                     fatalError("""
                         Failed to load the "Opensans-SemiBold" font.
                         Make sure the font file is included in the project and the font name is spelled correctly.
                         """)
                 }

                 guard let customInfoFont = UIFont(name: "OpenSans-Regular", size: UIFont.labelFontSize)
                 else {
                     fatalError("""
                         Failed to load the "Opensans-Regular" font.
                         Make sure the font file is included in the project and the font name is spelled correctly.
                         """)
                 }


             for title in titles
             {
                 title.font = UIFontMetrics(forTextStyle: .subheadline).scaledFont(for: customTitleFont)
                 title.adjustsFontForContentSizeCategory = true
                 title.adjustsFontSizeToFitWidth = true
             }

             for information in informations
             {
                 information.font = UIFontMetrics(forTextStyle: .body).scaledFont(for: customInfoFont)
                 information.adjustsFontForContentSizeCategory = true
                 information.adjustsFontSizeToFitWidth = true
             }

         }
    
    
}
