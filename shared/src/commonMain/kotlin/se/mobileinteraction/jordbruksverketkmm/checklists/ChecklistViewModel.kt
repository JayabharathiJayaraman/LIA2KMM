package se.mobileinteraction.jordbruksverketkmm.checklists

import se.mobileinteraction.jordbruksverketkmm.Checklist
import se.mobileinteraction.jordbruksverketkmm.utilities.ViewModelState
import se.mobileinteraction.jordbruksverketkmm.utilities.ViewModelStateImpl

class ChecklistViewModel constructor(
    val checklist: Checklist
): ViewModelState<ChecklistViewModel.State> by ViewModelStateImpl(State(checklist)) {
    data class State(val checklist: Checklist){}
}