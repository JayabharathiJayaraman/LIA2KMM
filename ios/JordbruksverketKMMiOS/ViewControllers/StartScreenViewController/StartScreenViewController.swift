import UIKit

extension UILabel {
    
  func dynamicFontBold(){
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
  func dynamicFontSemiBoldWelcome(){
    guard let customFont = UIFont(name: "OpenSans-SemiBold", size: 16) else {
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

extension UILabel {
  func dynamicFontSemiBold(){
      guard let customFont = UIFont(name: "OpenSans-SemiBold", size: 14) else {
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

extension UILabel {
  func dynamicFontSemiBoldAbout(){
      guard let customFont = UIFont(name: "OpenSans-SemiBold", size: 16.5) else {
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

extension UILabel {
  func dynamicFontRegular(){
    guard let customFont = UIFont(name: "OpenSans-Regular", size: UIFont.labelFontSize) else {
      fatalError("""
        Failed to load the "OpenSans-Regular" font.
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
    @IBOutlet weak var aboutlabel: UILabel!
    @IBOutlet weak var mytestlabel: UILabel!
    @IBOutlet weak var newtestlabel: UILabel!
    @IBOutlet weak var planticonlabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        welcomelabel.dynamicFontSemiBoldWelcome()
        mytestlabel.dynamicFontSemiBold()
        newtestlabel.dynamicFontSemiBold()
        planticonlabel.dynamicFontSemiBold()
        
        func localized(){
            welcomelabel.text = "WelcomeLabel".localized
            appnamelabel.text = "AppNameLabel".localized
            mytestlabel.text = "MyTestLabel".localized
            newtestlabel.text = "NewTestLabel".localized
            planticonlabel.text = "PlantLabel1".localized
        }
    }
    
    override var shouldAutorotate: Bool{
         return false
     }
    
     override var supportedInterfaceOrientations: UIInterfaceOrientationMask{
         return .portrait
     }
}
