package se.mobileinteraction.jordbruksverketkmm.checklists

import se.mobileinteraction.jordbruksverketkmm.Checklist
import se.mobileinteraction.jordbruksverketkmm.checklists.models.ChecklistItem
import se.mobileinteraction.jordbruksverketkmm.utilities.ViewModelState
import se.mobileinteraction.jordbruksverketkmm.utilities.ViewModelStateImpl

class ChecklistViewModel constructor(
    val checklist: Checklist,
    private val count: Int
) : ViewModelState<ChecklistViewModel.State> by ViewModelStateImpl(State(checklist, count)) {
    data class State(val checklist: Checklist, val count: Int)

    fun triggerStateActive(itemName: String) {
        val updatedChecklistItem = state.value.checklist.itemList
            .first { it.id == itemName }
            .copy(active = !state.value.checklist.itemList
                .first { it.id == itemName }.active
            )
        val newChecklistItemList: MutableList<ChecklistItem> = mutableListOf<ChecklistItem>()

        for (elem in state.value.checklist.itemList) {
            if (elem.id == checklist.itemList.first { it.id == itemName }.id
            ) {
                newChecklistItemList.add(updatedChecklistItem)
            } else {
                newChecklistItemList.add(elem)
            }
        }
        updateStateAndSave {
            copy(
                checklist = Checklist(checklist.category, newChecklistItemList),
                count = state.value.count + 1
            )
        }
    }

    private fun updateStateAndSave(state: State.() -> State) {
        updateState(state).also(::save)
    }

    private fun save(state: State) {
        println("Saving state: $state")
    }
}