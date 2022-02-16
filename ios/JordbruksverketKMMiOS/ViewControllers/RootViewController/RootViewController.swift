import shared
import UIKit

class RootViewController: UIViewController {
    @IBOutlet private weak var containerView: UIView!
    
    private var viewModel: TestViewModel
    private var interfaceGenerator: IOSInterfaceGenerator
    
    init() {
        let interfaceGenerator = IOSInterfaceGenerator()
        let test1 = Test1()
        let viewModel = TestViewModel(interfaceGenerator: interfaceGenerator, test: test1)
        self.viewModel = viewModel
        interfaceGenerator.setValueHandler = { key, value in
            viewModel.setValue(key: key, value: value)
        }
        self.interfaceGenerator = interfaceGenerator
        
        let nibName = String(describing: RootViewController.self)
        super.init(nibName: nibName, bundle: nil)
    }

    required init?(coder: NSCoder) {
        return nil
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        
        displayCurrentScreen()
    }
}

private extension RootViewController {
    func displayCurrentScreen() {
        viewModel.drawCurrentScreen()
        guard let mainView = interfaceGenerator.getInterface() as? UIStackView else { return }
        containerView.addSubview(mainView)
        mainView.translatesAutoresizingMaskIntoConstraints = false
        mainView.topAnchor.constraint(equalTo: containerView.topAnchor).isActive = true
        mainView.bottomAnchor.constraint(equalTo: containerView.bottomAnchor).isActive = true
        mainView.leadingAnchor.constraint(equalTo: containerView.leadingAnchor, constant: 20).isActive = true
        mainView.widthAnchor.constraint(equalTo: containerView.widthAnchor, constant: -40).isActive = true
    }
    
    func nextScreen() {
        viewModel.nextScreen()
        displayCurrentScreen()
    }
    
    func previousScreen() {
        viewModel.previousScreen()
        displayCurrentScreen()
    }
}
