package se.mobileinteraction.jordbruksverketkmm.checklists

import android.util.Log
import se.mobileinteraction.jordbruksverketkmm.Checklist
import se.mobileinteraction.jordbruksverketkmm.ChecklistItem
import se.mobileinteraction.jordbruksverketkmm.checklists.models.ChecklistState
import se.mobileinteraction.jordbruksverketkmm.forms.FormViewModel
import se.mobileinteraction.jordbruksverketkmm.utilities.ViewModelState
import se.mobileinteraction.jordbruksverketkmm.utilities.ViewModelStateImpl

class ChecklistViewModel constructor(
    val checklist: Checklist,
    val count: Int
): ViewModelState<ChecklistViewModel.State> by ViewModelStateImpl(State(checklist, count)) {
    data class State(val checklist: Checklist,val count: Int){}

    fun triggerStateActive(itemName: String) {
        val tmp = state.value.checklist
        val count = count
        Log.d("wtf", tmp.stateList.toString())
        val tmp2 = tmp.stateList.filter { it.id == itemName }[0].copy(active = !tmp.stateList.filter { it.id == itemName }[0].active)
        val newStateList = mutableListOf<ChecklistState>()

        for (elem in tmp.stateList) {
            if (elem.id == checklist.stateList.filter { it.id == itemName }[0].id) {
                newStateList.add(tmp2)
                Log.d("this change",tmp2.toString())
            } else {
                newStateList.add(elem)
                Log.d("this haven't change",elem.toString())
            }
        }
        updateStateAndSave { copy(checklist = Checklist(checklist.category, newStateList), count =  state.value.count + 1) }
    }

    private fun updateStateAndSave(state: ChecklistViewModel.State.() -> ChecklistViewModel.State) {
        updateState(state).also(::save)
    }

    private fun save(state: ChecklistViewModel.State) {
        println("Saving state: $state")
    }
}