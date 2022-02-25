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

final class StartScreenViewController: UIViewController {
    
    @IBOutlet private weak var welcomelabel: UILabel!
    @IBOutlet private weak var appnamelabel: UILabel!
    @IBOutlet private weak var aboutlabel: UILabel!
    @IBOutlet private weak var mytestlabel: UILabel!
    @IBOutlet private weak var newtestlabel: UILabel!
    @IBOutlet private weak var planticonlabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        initView()
    }
    
    func initView(){
        welcomelabel.dynamicFontSemiBoldWelcome()
        aboutlabel.dynamicFontRegular()
        mytestlabel.dynamicFontSemiBold()
        newtestlabel.dynamicFontSemiBold()
        planticonlabel.dynamicFontSemiBold()
        
        welcomelabel.text = "WelcomeLabel".localized
        appnamelabel.text = "AppNameLabel".localized
        aboutlabel.text = "Aboutlabel".localized
        mytestlabel.text = "MyTestLabel".localized
        newtestlabel.text = "NewTestLabel".localized
        planticonlabel.text = "PlantLabel".localized
    }
    
    override var shouldAutorotate: Bool{
         return false
     }
    
     override var supportedInterfaceOrientations: UIInterfaceOrientationMask{
         return .portrait
     }
}
