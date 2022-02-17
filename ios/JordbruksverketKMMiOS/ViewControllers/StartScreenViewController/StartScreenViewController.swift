import UIKit


extension UILabel {
  func dynamicFont(){
    guard let customFont = UIFont(name: "OpenSans-Bold", size: UIFont.labelFontSize) else {
      fatalError("""
        Failed to load the "OpenSans-Bold" font.
        Make sure the font file is included in the project and the font name is spelled correctly.
        """
      )
    }
    self.font = UIFontMetrics(forTextStyle: .headline).scaledFont(for: customFont)
    self.adjustsFontForContentSizeCategory = true
  }
}


extension UILabel {
  func dynamicFont2(){
    guard let customFont = UIFont(name: "OpenSans-SemiBold", size: UIFont.labelFontSize) else {
      fatalError("""
        Failed to load the "OpenSans-SemiBold" font.
        Make sure the font file is included in the project and the font name is spelled correctly.
        """
      )
    }
    self.font = UIFontMetrics(forTextStyle: .headline).scaledFont(for: customFont)
    self.adjustsFontForContentSizeCategory = true

  }
}

class StartScreenViewController: UIViewController {
    
    @IBOutlet weak var welcomelabel: UILabel!
    
    @IBOutlet weak var appnamelabel: UILabel!
            
    @IBOutlet weak var mytestlabel: UILabel!
    
    @IBOutlet weak var newtestlabel: UILabel!
    
    
    @IBOutlet weak var planticonlabel: UILabel!
    
    
    
  
    override func viewDidLoad() {
        super.viewDidLoad()
        welcomelabel.dynamicFont2()
        mytestlabel.dynamicFont()
        newtestlabel.dynamicFont()
        planticonlabel.dynamicFont()

    
        func localized(){
        
            welcomelabel.text = "WelcomeLabel".localized
            appnamelabel.text = "AppNameLabel".localized
            mytestlabel.text = "MyTestLabel".localized
            newtestlabel.text = "NewTestLabel".localized
            planticonlabel.text = "PlantLabel1".localized

        }

    }
    
}

