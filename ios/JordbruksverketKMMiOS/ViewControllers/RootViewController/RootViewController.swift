import shared
import UIKit

class RootViewController: UIViewController {
    @IBOutlet private weak var containerView: UIView!
    
    private var viewModel: FormViewModel
    private var interfaceGenerator: IOSFormGenerator
    private var listeningJob: Closeable?
    
    init() {
        let interfaceGenerator = IOSFormGenerator()
        let form = FormFactory().createForm()
        let viewModel = FormViewModel(form: form)
        self.viewModel = viewModel
        self.interfaceGenerator = interfaceGenerator
        interfaceGenerator.viewModel = viewModel
        
        let nibName = String(describing: RootViewController.self)
        super.init(nibName: nibName, bundle: nil)
    }

    required init?(coder: NSCoder) {
        return nil
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        listeningJob = viewModel.wrappedState.onChange { newState in
            print("iOS, new state recieved: \(newState)")
            self.displayComponents(components: newState.components)
        }
    }
    
    @IBAction func previousButton(_ sender: Any) {
        previousScreen()
    }
    
    @IBAction func nextButton(_ sender: Any) {
        nextScreen()
        
    }
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        print("viewDidAppear")
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        super.viewDidDisappear(animated)
        
        listeningJob?.close()
        print("viewDidDisAppear")
    }
}

private extension RootViewController {
    func displayComponents(components: [FormComponent]) {
        guard let mainView = interfaceGenerator.getInterface(components: components) as? UIStackView else { return }
        containerView.willRemoveSubview(mainView)
        containerView.addSubview(mainView)
        mainView.translatesAutoresizingMaskIntoConstraints = false
        mainView.topAnchor.constraint(equalTo: containerView.topAnchor).isActive = true
        mainView.bottomAnchor.constraint(equalTo: containerView.bottomAnchor).isActive = true
        mainView.leadingAnchor.constraint(equalTo: containerView.leadingAnchor, constant: 20).isActive = true
        mainView.widthAnchor.constraint(equalTo: containerView.widthAnchor, constant: -40).isActive = true
    }
    
    func nextScreen() {
        print("iOS, new state recieved: nextScreen")
        viewModel.nextScreen()
       // navigationController?.pushViewController(nextScreen(), animated: true)
    }
    
    func previousScreen() {
        viewModel.previousScreen()
       // navigationController?.pushViewController(viewModel.previousScreen(), animated: true)
        print("iOS, new state recieved: previousScreen")
    }
}
