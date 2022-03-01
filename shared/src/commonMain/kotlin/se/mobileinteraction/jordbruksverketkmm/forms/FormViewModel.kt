package se.mobileinteraction.jordbruksverketkmm.forms

import se.mobileinteraction.jordbruksverketkmm.utilities.ViewModelState
import se.mobileinteraction.jordbruksverketkmm.utilities.ViewModelStateImpl
import se.mobileinteraction.jordbruksverketkmm.forms.components.FormComponent
import se.mobileinteraction.jordbruksverketkmm.forms.components.FormComponentTextField
import se.mobileinteraction.jordbruksverketkmm.forms.forms.FormGeneralQuestions

class FormViewModel constructor(
    test: FormGeneralQuestions = FormGeneralQuestions()
) : ViewModelState<FormViewModel.State> by ViewModelStateImpl(State(test)) {
    fun nextScreen() {
        updateStateAndSave {
            copy(currentScreen = currentScreen + 1)
        }
    }

    fun previousScreen() {
        updateStateAndSave {
            copy(currentScreen = currentScreen - 1)
        }
    }

    private fun updateStateAndSave(state: State.() -> State) {
        updateState(state).also(::save)
    }

    private fun save(state: State) {
        println("Saving state: $state")
    }

    data class State(
        val test: FormGeneralQuestions,
        val currentScreen: Int = 1,
        val counter: Int = 0,
    ) {
        val components: List<FormComponent> = test.screens[currentScreen].components
    }

    fun setTextData(id: String, text: String) {
        state.value.components.firstOrNull {
            it is FormComponentTextField
        }.let {
            updateStateAndSave { test.setText(id, text, state.value).copy(counter = counter + 1) }
        }
    }
}


