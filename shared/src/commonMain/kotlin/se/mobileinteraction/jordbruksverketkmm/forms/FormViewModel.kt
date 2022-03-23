package se.mobileinteraction.jordbruksverketkmm.forms

import se.mobileinteraction.jordbruksverketkmm.forms.components.FormComponent
import se.mobileinteraction.jordbruksverketkmm.forms.components.FormComponentButtonList
import se.mobileinteraction.jordbruksverketkmm.forms.components.FormComponentChecklist
import se.mobileinteraction.jordbruksverketkmm.forms.components.FormComponentTextField
import se.mobileinteraction.jordbruksverketkmm.forms.forms.Form
import se.mobileinteraction.jordbruksverketkmm.forms.forms.FormSoilStructure
import se.mobileinteraction.jordbruksverketkmm.utilities.ViewModelState
import se.mobileinteraction.jordbruksverketkmm.utilities.ViewModelStateImpl


class FormViewModel constructor(
    val form: Form
) : ViewModelState<FormViewModel.State> by ViewModelStateImpl(State(form)) {
    fun nextScreen() {
        if (state.value.currentScreen < state.value.form.screens.size - 1) {
            updateStateAndSave {
                copy(currentScreen = currentScreen + 1)
            }
        }
    }

    fun previousScreen() {
        if (state.value.currentScreen > 0) {
            updateStateAndSave {
                copy(currentScreen = currentScreen - 1)
            }
        }
    }

    private fun updateStateAndSave(state: State.() -> State) {
        updateState(state).also(::save)
    }

    private fun save(state: State) {
        println("Saving state: $state")
    }

    data class State(
        val form: Form,
        val currentScreen: Int = 0,
        val counter: Int = 0,
    ) {
        val components: List<FormComponent> = form.screens[currentScreen].components
        val totalScreens: Int = form.screens.size
    }

    fun setTextData(id: String, text: String) = state.value.components.firstOrNull {
        it is FormComponentTextField
    }.let {
        updateStateAndSave { form.setText(id, text, state.value).copy(counter = counter + 1) }
    }

    fun setChecklistRating(id: String, rating: Int) = state.value.components.firstOrNull {
        it is FormComponentChecklist
    }.let {
        updateStateAndSave {
            form.setChecklistRating(id, rating, state.value).copy(counter = counter + 1)
        }
    }

    fun setSoilStructurePhoto(id: String, imageUri: String) {
        updateStateAndSave {
            (form as? FormSoilStructure)?.setSoilStructurePhoto(id, state.value, imageUri)
                ?.copy(counter = counter + 1)
                ?: this
        }
    }

    fun setButtonListActive(id: String, value: String) = state.value.components.firstOrNull {
        it is FormComponentButtonList
    }.let {
        updateStateAndSave {
            form.setButtonlistActive(id, value, state.value).copy(counter = counter + 1)
        }
    }
}


