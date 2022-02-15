import shared
import UIKit

class RootViewController: UIViewController {
    private var viewModel: TestViewModel
    
    init() {
        let interfaceGenerator = IOSInterfaceGenerator()
        self.viewModel = TestViewModel()
        let nibName = String(describing: RootViewController.self)
        super.init(nibName: nibName, bundle: nil)
    }

    required init?(coder: NSCoder) {
        return nil
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()


    }
}
