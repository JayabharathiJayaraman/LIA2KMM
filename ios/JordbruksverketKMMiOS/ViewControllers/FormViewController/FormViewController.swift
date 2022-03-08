import shared
import UIKit

class FormViewController: UIViewController {
    @IBOutlet private weak var containerView: UIView!
    
    private var viewModel = IOSFormViewModel.shared
    private let interfaceGenerator: IOSFormGenerator
    private var listeningJob: Closeable?
    
    init() {
        let interfaceGenerator = IOSFormGenerator()
        self.interfaceGenerator = interfaceGenerator
        
        let nibName = String(describing: FormViewController.self)
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
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        super.viewDidDisappear(animated)
        
        listeningJob?.close()
    }
}

private extension FormViewController {
    func displayComponents(components: [FormComponent]) {
        guard let mainView = interfaceGenerator.generateInterface(components: components) as? UIStackView else { return }
        containerView.addSubview(mainView)
        mainView.translatesAutoresizingMaskIntoConstraints = false
        mainView.topAnchor.constraint(equalTo: containerView.topAnchor).isActive = true
        mainView.bottomAnchor.constraint(equalTo: containerView.bottomAnchor).isActive = true
        mainView.leadingAnchor.constraint(equalTo: containerView.leadingAnchor, constant: 20).isActive = true
        mainView.widthAnchor.constraint(equalTo: containerView.widthAnchor, constant: -40).isActive = true
    }
    
    func nextScreen() {
        viewModel.nextScreen()
    }
    
    func previousScreen() {
        viewModel.previousScreen()
    }
}
