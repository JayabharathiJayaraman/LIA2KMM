import shared
import UIKit

class FormViewController: UIViewController {
    
    @IBOutlet private weak var containerView: UIView!
    @IBOutlet weak var CurrentScreenView: UILabel!
    @IBOutlet weak var totalScreensView: UILabel!
    @IBOutlet weak var progressBarStackView: UIStackView!
    @IBOutlet weak var bottomBarContainerView: UIView!
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

            self.setupBottomViewLayout()

            self.progressBarStackView.layer.cornerRadius = 6.0
            self.progressBarStackView.clipsToBounds = true

            self.updateOrGenerateNewComponents(components: newState.components)

            self.updateProgress(totalScreens: Int(newState.totalScreens), currentScreen: Int(newState.currentScreen))

            self.CurrentScreenView.text = "\(newState.currentScreen + 1)"
            self.totalScreensView.text = "\(newState.totalScreens)"
        }
    }
    
    private func setupBottomViewLayout(){
        let bottomViewContainer = UIView()
        bottomViewContainer.backgroundColor = .white
        view.addSubview(bottomViewContainer)
        bottomViewContainer.translatesAutoresizingMaskIntoConstraints = false
        bottomViewContainer.bottomAnchor.constraint(equalTo: view.bottomAnchor).isActive = true
        bottomViewContainer.leftAnchor.constraint(equalTo: view.leftAnchor).isActive = true
        bottomViewContainer.rightAnchor.constraint(equalTo: view.rightAnchor).isActive = true
        bottomViewContainer.heightAnchor.constraint(equalTo: view.heightAnchor, multiplier: 0.12).isActive = true
        bottomViewContainer.addSubview(bottomBarContainerView)
        bottomBarContainerView.centerXAnchor.constraint(equalTo: bottomViewContainer.centerXAnchor).isActive = true
        bottomBarContainerView.centerYAnchor.constraint(equalTo: bottomViewContainer.centerYAnchor).isActive = true
        

    }

    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
    }

    @IBOutlet weak var nextButton: UIButton!
    @IBOutlet weak var nextButtonImageRight: NSLayoutConstraint!
    
    @IBAction func nextViewButton(_ sender: Any) {
        nextScreen()
    }
    
    @IBOutlet weak var previousButton: UIButton!
    @IBOutlet weak var previousButtonImageLeft: UIImageView!

    @IBAction func previousViewButton(_ sender: Any) {
        previousScreen()
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        super.viewDidDisappear(animated)
        
        listeningJob?.close()
    }
    func updateProgress (totalScreens: Int, currentScreen: Int ) {
        progressBarStackView.subviews.forEach { $0.removeFromSuperview() }
        for i in 0...totalScreens - 1{
            let customView = UILabel()
            print("la till")
            customView.backgroundColor = i <= currentScreen ? getUIColor(hex: "#CED7B2") : getUIColor(hex: "#EDF1E2")
            progressBarStackView.addArrangedSubview(customView)
        }
    }
}
private extension FormViewController {
    func updateOrGenerateNewComponents(components: [FormComponent]) {
        let generateNewComponents = true
        
        if let mainView = containerView.subviews.first {
            mainView.subviews.forEach { componentView in
            }
        }
        
        if generateNewComponents {
            displayComponents(components: components)
        }
    }
    
   func displayComponents(components: [FormComponent]) {
        guard let mainView = interfaceGenerator.generateInterface(components: components) as? UIStackView else { return }
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

    func getUIColor(hex: String, alpha: Double = 1.0) -> UIColor? {
        var cleanString = hex.trimmingCharacters(in: .whitespacesAndNewlines).uppercased()

        if (cleanString.hasPrefix("#")) {
            cleanString.remove(at: cleanString.startIndex)
        }

        if ((cleanString.count) != 6) {
            return nil
        }

        var rgbValue: UInt32 = 0
        Scanner(string: cleanString).scanHexInt32(&rgbValue)

        return UIColor(
            red: CGFloat((rgbValue & 0xFF0000) >> 16) / 255.0,
            green: CGFloat((rgbValue & 0x00FF00) >> 8) / 255.0,
            blue: CGFloat(rgbValue & 0x0000FF) / 255.0,
            alpha: CGFloat(1.0)
        )
    }
}
