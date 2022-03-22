
import shared

class IOSChecklistViewModel {

    static let shared = ChecklistViewModel( checklist: Checklist(category: ChecklistCategory.grundforbattringar,itemList: ChecklistItemStates.grundforbattringar.state ),  count: 0)
    
}
