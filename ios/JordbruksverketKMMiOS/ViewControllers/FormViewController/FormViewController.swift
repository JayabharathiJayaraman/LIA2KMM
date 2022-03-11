import shared
import UIKit

class FormViewController: UIViewController {
    
    @IBOutlet private weak var containerView: UIView!

    @IBOutlet weak var progressBarStackView: UIStackView!
    @IBOutlet weak var currentScreenLabel: UILabel!
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
        
        progressBarStackView.layer.cornerRadius = 6.0
        progressBarStackView.clipsToBounds = true
        
        listeningJob = viewModel.wrappedState.onChange { newState in
            print("iOS, new state recieved: \(newState)")
            
            self.updateProgress(totalScreens: Int(newState.totalScreens), currentScreen: Int(newState.currentScreen))

            self.currentScreenLabel.text = "\(newState.currentScreen) " + "av".localized + " \(newState.totalScreens)"
            self.displayComponents(components: newState.components, currentScreen: newState.currentScreen)
        }
    }
        
    @IBAction func nextViewButton(_ sender: Any) {
        viewModel.nextScreen()
    }
    
    @IBAction func previousViewButton(_ sender: Any) {
        viewModel.previousScreen()
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        super.viewDidDisappear(animated)
        
        listeningJob?.close()
    }
}

private extension FormViewController {
    func updateProgress (totalScreens: Int, currentScreen: Int ) {
        progressBarStackView.subviews.forEach { $0.removeFromSuperview() }
        for i in 0...totalScreens - 1{
            let customView = UILabel()
            customView.backgroundColor = i <= currentScreen ? UIColor.Jordbruksverket.progressFilled : UIColor.Jordbruksverket.progressUnfilled
            progressBarStackView.addArrangedSubview(customView)
        }
    }
    
    func displayComponents(components: [FormComponent], currentScreen: Int32) {
        if containerView.subviews.count == 0 {
            guard let mainView = interfaceGenerator.createInterface(components: components, currentScreen: currentScreen) as? UIStackView else { return }
            containerView.addSubview(mainView)
            mainView.translatesAutoresizingMaskIntoConstraints = false
            mainView.topAnchor.constraint(equalTo: containerView.topAnchor).isActive = true
            mainView.bottomAnchor.constraint(equalTo: containerView.bottomAnchor).isActive = true
            mainView.leadingAnchor.constraint(equalTo: containerView.leadingAnchor, constant: 20).isActive = true
            mainView.widthAnchor.constraint(equalTo: containerView.widthAnchor, constant: -40).isActive = true
        } else {
            interfaceGenerator.updateInterface(components: components, currentScreen: currentScreen)
        }
    }
}
