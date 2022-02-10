import shared
import UIKit

class RootViewController: UIViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()

        print("Hello world!")
        print("\(Greeting().greeting())")
        print("HEJ".localized)
    }
    
}
