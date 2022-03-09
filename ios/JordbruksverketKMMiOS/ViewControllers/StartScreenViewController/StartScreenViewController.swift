import UIKit
import shared

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
        
        welcomelabel.text = "StartScreenWelcomeLabel".localized
        appnamelabel.text = "StartScreenAppNameLabel".localized
        aboutlabel.text = "StartScreenAboutlabel".localized
        mytestlabel.text = "StartScreenMyTestLabel".localized
        newtestlabel.text = "StartScreenNewTestLabel".localized
        planticonlabel.text = "StartScreenPlantLabel".localized
    }
    
    override var shouldAutorotate: Bool{
         return false
     }
    
     override var supportedInterfaceOrientations: UIInterfaceOrientationMask{
         return .portrait
     }
}
