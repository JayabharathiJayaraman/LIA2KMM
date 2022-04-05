
import UIKit
import shared

class VardaMarkstrukturViewController: UIViewController {

    @IBOutlet weak var Label_1: UILabel!
    
    @IBOutlet weak var MainText: UILabel!
    
    @IBOutlet weak var SecondText: UILabel!
    
    @IBOutlet weak var Label_2: UILabel!
    
  
    @IBOutlet weak var GrundBtn: UILabel!
    
    
    @IBOutlet weak var OdlingsBtn: UILabel!
    
    
    @IBOutlet weak var UndvikBtn: UILabel!
    
    
    @IBOutlet weak var PDF_Btn_label: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        setUpLabels()
    }
    
    @IBAction func GrundforbattringarBtn(_ sender: UIButton) {
        IOSChecklistViewModel.shared = IOSChecklistViewModel.grundforbattringarViewModel
        navigateToChecklistfunNavigateToChecklist()
    }
    
    @IBAction func OdlingsatgarderBtn(_ sender: UIButton) {
        IOSChecklistViewModel.shared = IOSChecklistViewModel.odlingsatgarderrViewModel
        navigateToChecklistfunNavigateToChecklist()
    }
    
    @IBAction func UndvikBtn(_ sender: UIButton) {
        IOSChecklistViewModel.shared = IOSChecklistViewModel.undvikViewModel
        navigateToChecklistfunNavigateToChecklist()
    }
    
    func navigateToChecklistfunNavigateToChecklist(){
        let checklistViewController = ChecklistViewController()
        navigationController?.pushViewController(checklistViewController, animated: true)
    }
    
    func setUpLabels(){
        Label_1.text = "varda_markstruktur_beskrivning".localized
        MainText.text = "varda_markstruktur_main_text".localized
        SecondText.text = "varda_markstruktur_second_text".localized
        Label_2.text = "varda_markstruktur_atgarder".localized
        GrundBtn.text = "varda_markstruktur_grundforbattringar".localized
        OdlingsBtn.text = "varda_markstruktur_odlingsatgarder".localized
        UndvikBtn.text = "varda_markstruktur_undvik_eller_minimera".localized
        PDF_Btn_label.text = "varda_markstruktur_export_button".localized
    }
}
