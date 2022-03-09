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
            self.updateOrGenerateNewComponents(components: newState.components)
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
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        super.viewDidDisappear(animated)
        
        listeningJob?.close()
    }
}

private extension RootViewController {
    func updateOrGenerateNewComponents(components: [FormComponent]) {
            var generateNewComponents = true
            
            if let mainView = containerView.subviews.first {
                mainView.subviews.forEach { componentView in
                    
                }
            }
            
            if generateNewComponents {
                displayComponents(components: components)
            }
        }
    
    func displayComponents(components: [FormComponent]) {
        guard let mainView = interfaceGenerator.getInterface(components: components) as? UIStackView else { return }
        mainView.tag = 100
        containerView.addSubview(mainView)
        mainView.translatesAutoresizingMaskIntoConstraints = false
        mainView.topAnchor.constraint(equalTo: containerView.topAnchor).isActive = true
        mainView.bottomAnchor.constraint(equalTo: containerView.bottomAnchor).isActive = true
        mainView.leadingAnchor.constraint(equalTo: containerView.leadingAnchor, constant: 20).isActive = true
        mainView.widthAnchor.constraint(equalTo: containerView.widthAnchor, constant: -40).isActive = true
    }
    
    func nextScreen() {
        if let viewWithTag = self.view.viewWithTag(100) {
                       viewWithTag.removeFromSuperview()
                   }else{
                       print("")
                   }
        viewModel.nextScreen()
    }
    
    func previousScreen() {
        if let viewWithTag = self.view.viewWithTag(100) {
                       viewWithTag.removeFromSuperview()
                   }else{
                       print("")
                   }
        viewModel.previousScreen()
    }
}
