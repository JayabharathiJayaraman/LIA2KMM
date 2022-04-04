package se.mobileinteraction.jordbruksverketkmm.forms

import se.mobileinteraction.jordbruksverketkmm.forms.components.*
import se.mobileinteraction.jordbruksverketkmm.forms.forms.Form
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
    fun setCordinates(id: String, latitude: Double, longitude: Double) = state.value.components.firstOrNull {
        it is FormComponentMap
    }.let {
        updateStateAndSave { form.setCordinates(id, latitude, longitude, state.value).copy(counter = counter + 1) }
    }

    fun setChecklistRating(id: String, rating: Int) = state.value.components.firstOrNull {
        it is FormComponentChecklist
    }.let {
        updateStateAndSave {
            form.setChecklistRating(id, rating, state.value).copy(counter = counter + 1)
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


