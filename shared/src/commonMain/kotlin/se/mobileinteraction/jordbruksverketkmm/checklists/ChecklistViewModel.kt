package se.mobileinteraction.jordbruksverketkmm.checklists

import android.util.Log
import se.mobileinteraction.jordbruksverketkmm.Checklist
import se.mobileinteraction.jordbruksverketkmm.checklists.models.ChecklistItem
import se.mobileinteraction.jordbruksverketkmm.utilities.ViewModelState
import se.mobileinteraction.jordbruksverketkmm.utilities.ViewModelStateImpl

class ChecklistViewModel constructor(
    val checklist: Checklist,
    val count: Int
): ViewModelState<ChecklistViewModel.State> by ViewModelStateImpl(State(checklist, count)) {
    data class State(val checklist: Checklist,val count: Int){}

    fun triggerStateActive(itemName: String) {
        val tmp = state.value.checklist
        Log.d("wtf", tmp.itemList.toString())
        val tmp2 = tmp.itemList.filter { it.id == itemName }[0].copy(active = !tmp.itemList.filter { it.id == itemName }[0].active)
        val newStateList = mutableListOf<ChecklistItem>()

        for (elem in tmp.itemList) {
            if (elem.id == checklist.itemList.filter { it.id == itemName }[0].id) {
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