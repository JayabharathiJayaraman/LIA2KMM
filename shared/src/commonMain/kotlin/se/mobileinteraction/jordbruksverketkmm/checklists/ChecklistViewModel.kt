package se.mobileinteraction.jordbruksverketkmm.checklists

import android.util.Log
import se.mobileinteraction.jordbruksverketkmm.Checklist
import se.mobileinteraction.jordbruksverketkmm.utilities.ViewModelState
import se.mobileinteraction.jordbruksverketkmm.utilities.ViewModelStateImpl

class ChecklistViewModel constructor(
    val checklist: Checklist,
    val count: Int
): ViewModelState<ChecklistViewModel.State> by ViewModelStateImpl(State(checklist, count)) {
    data class State(val checklist: Checklist,val count: Int){}

    init {

    }
    fun triggerStateActive(itemName: String) {
        val tmp = checklist
        val count = count
        val tmp2 = tmp.itemList.filter { it.id == itemName }[0].copy(active = !tmp.itemList.filter { it.id == itemName }[0].active)
          
        for (elem in stateFlow.value.stateList) {
            if (elem.itemName == stateFlow.value.stateList.filter { it.itemName == itemName }[0].itemName) {
                newList.add(tmp2)
            } else {
                newList.add(elem)
            }
        }
        _stateFlow.value = stateFlow.value.copy(count = count + 1, stateList = newList)
        val newStateToLog = stateFlow.value
        Log.d("final out", newStateToLog.toString())
    }
}