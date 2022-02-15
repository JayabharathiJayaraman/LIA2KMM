import UIKit

class StartScreenViewController: UIViewController {
    
    @IBOutlet weak var welcomelabel: UILabel!
    
    @IBOutlet weak var appnamelabel: UILabel!
        
    @IBOutlet weak var myteslabel: UILabel!
    
    @IBOutlet weak var newtestlabel: UILabel!
    
    @IBOutlet weak var planticonlabel1: UILabel!
    
    @IBOutlet weak var planticonlabel2: UILabel!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        welcomelabel.text = "WelcomeLabel".localized
        appnamelabel.text = "AppNameLabel".localized
        myteslabel.text = "MyTestLabel".localized
        newtestlabel.text = "NewTestLabel".localized
        planticonlabel1.text = "PlantLabel1".localized
        planticonlabel2.text = "PlantLabel2".localized
    

    }
    
    }

