package se.mobileinteraction.jordbruksverketkmm.checklists

import android.util.Log
import se.mobileinteraction.jordbruksverketkmm.Checklist
import se.mobileinteraction.jordbruksverketkmm.ChecklistItem
import se.mobileinteraction.jordbruksverketkmm.checklists.models.ChecklistState
import se.mobileinteraction.jordbruksverketkmm.forms.FormViewModel
import se.mobileinteraction.jordbruksverketkmm.forms.forms.Form
import se.mobileinteraction.jordbruksverketkmm.utilities.ViewModelState
import se.mobileinteraction.jordbruksverketkmm.utilities.ViewModelStateImpl

class ChecklistViewModel constructor(
    val checklist: Checklist,
    val count: Int
): ViewModelState<ChecklistViewModel.StateChecklist> by ViewModelStateImpl(StateChecklist(checklist, count)) {
    data class StateChecklist(val checklist: Checklist,val count: Int){}
    fun triggerStateActive(itemName: String) {
        val tmp = checklist
        val count = count
        Log.d("wtf", tmp.stateList.toString())
        val tmp2 = tmp.stateList.filter { it.id == itemName }[0].copy(active = !tmp.itemList.filter { it.id == itemName }[0].active)
        val newStateList = mutableListOf<ChecklistState>()

        for (elem in checklist.stateList) {
            if (elem.id == checklist.stateList.filter { it.id == itemName }[0].id) {
                newStateList.add(tmp2)
            } else {
                newStateList.add(elem)
            }
        }
        updateStateAndSave { copy(checklist = Checklist(checklist.category, newStateList), count = state.value.count + 1) }
    }

    private fun updateStateAndSave(state: StateChecklist.() -> StateChecklist) {
        updateState(state).also(::save)
    }

    private fun save(state: StateChecklist) {
        println("Saving state: $state")
    }
}