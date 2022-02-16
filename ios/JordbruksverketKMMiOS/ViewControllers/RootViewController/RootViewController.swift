import shared
import UIKit

class RootViewController: UIViewController {
    
    @IBOutlet weak var testLabel: UILabel!
    override func viewDidLoad() {
        super.viewDidLoad()

        print("Hello world!")
        testLabel.text = CheckList(category:"test").title
        print("\(CheckList(category:"test").title)")
        print("HEJ".localized)
    }
}
