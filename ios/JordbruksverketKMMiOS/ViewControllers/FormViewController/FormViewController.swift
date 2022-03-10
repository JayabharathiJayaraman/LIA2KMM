import shared
import UIKit

class FormViewController: UIViewController {
    
    @IBOutlet private weak var containerView: UIView!
    
    @IBOutlet weak var screenCounter: UILabel!
    
    @IBOutlet weak var column1: UIView!
    
    @IBOutlet weak var column2: UIView!
    
    @IBOutlet weak var column3: UIView!
    
    @IBOutlet weak var column4: UIView!
    
    @IBOutlet weak var column5: UIView!
    
    @IBOutlet weak var column6: UIView!
    
    @IBOutlet weak var column7: UIView!
    
    @IBOutlet weak var column8: UIView!
    
    @IBOutlet weak var column9: UIView!
    
    @IBOutlet weak var column10: UIView!
    
    @IBOutlet weak var column11: UIView!

    var count = 1
    
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
            self.updateOrGenerateNewComponents(components: newState.components)
        }
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
    }
    
    
    @IBOutlet weak var nextButton: UIButton!
    
    
    @IBOutlet weak var nextButtonImageRight: NSLayoutConstraint!
    
    @IBAction func nextViewButton(_ sender: Any) {
        count = count + 1
        if (count > 1 ) {
            previousButton.isHidden = false
            previousButtonImageLeft.isHidden = false
        }
        screenCounter.text = "\(count)"
        nextScreen()
        
        if (count == 11 ) {
            nextButton.isHidden = true
        }
        
        if (count == 2 ) {
            column2.isHidden = false
        }
        if (count == 3 ) {
            column3.isHidden = false
        }
        if (count == 4 ) {
            column4.isHidden = false
        }
        if (count == 5 ) {
            column5.isHidden = false
        }
        if (count == 6 ) {
            column6.isHidden = false
        }
        if (count == 7 ) {
            column7.isHidden = false
        }
        if (count == 8 ) {
            column8.isHidden = false
        }
        if (count == 9 ) {
            column9.isHidden = false
        }
        if (count == 10 ) {
            column10.isHidden = false
        }
        if (count == 11 ) {
            column11.isHidden = false
        }
    }
    
    @IBOutlet weak var counterLabel: UILabel!
    
    @IBOutlet weak var previousButton: UIButton!
    
    @IBOutlet weak var previousButtonImageLeft: UIImageView!
    
    
    
    @IBAction func previousViewButton(_ sender: Any) {
        count = count - 1
        if (count == 1 ) {
            previousButtonImageLeft.isHidden = true
            previousButton.isHidden = true
            screenCounter.text = "\(1)"
        }
        screenCounter.text = "\(count)"
        previousScreen()
        
        if (count < 11 ) {
            nextButton.isHidden = false
        }
        if (count < 2 ) {
            column2.isHidden = true
        }
        if (count < 3 ) {
            column3.isHidden = true
        }
        if (count < 4 ) {
            column4.isHidden = true
        }
        if (count < 5 ) {
            column5.isHidden = true
        }
        if (count < 6 ) {
            column6.isHidden = true
        }
        if (count < 7 ) {
            column7.isHidden = true
        }
        if (count < 8 ) {
            column8.isHidden = true
        }
        if (count < 9 ) {
            column9.isHidden = true
        }
        if (count < 10 ) {
            column10.isHidden = true
        }
        if (count < 11 ) {
            column11.isHidden = true
        }
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        super.viewDidDisappear(animated)
        
        listeningJob?.close()
    }
}

private extension FormViewController {
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
}
