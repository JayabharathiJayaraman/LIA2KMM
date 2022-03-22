
import shared

class IOSChecklistViewModel {
    
    static let grundforbattringarViewModel = ChecklistViewModel( checklist: Checklist(category: ChecklistCategory.grundforbattringar,itemList: ChecklistItemStates.grundforbattringar.state ),  count: 0)
    
    static let odlingsatgarderrViewModel = ChecklistViewModel( checklist: Checklist(category: ChecklistCategory.odlingsatgarder,itemList: ChecklistItemStates.odlingsatgarder.state ),  count: 0)
    
    static let undvikViewModel = ChecklistViewModel( checklist: Checklist(category: ChecklistCategory.undvikellerminimera,itemList: ChecklistItemStates.undvikellerminimera.state ),  count: 0)
    
    static var shared = grundforbattringarViewModel
    
/*    func setCklistViewModel(val num : IOSChecklistViewModel.CompassPoint){
        switch num{
        case north : do {
            IOSChecklistViewModel.shared = IOSChecklistViewModel.grundforbattringarViewModel}
        case 2 : do {
            IOSChecklistViewModel.shared = IOSChecklistViewModel.odlingsatgarderrViewModel}
        case 3 : do {
            IOSChecklistViewModel.shared = IOSChecklistViewModel.undvikViewModel}
        default:
            do {
                IOSChecklistViewModel.shared = IOSChecklistViewModel.grundforbattringarViewModel}
        }
    }*/
}
