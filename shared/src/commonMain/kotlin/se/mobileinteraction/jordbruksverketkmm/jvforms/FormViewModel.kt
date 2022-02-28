package se.mobileinteraction.jordbruksverketkmm.jvforms

import se.mobileinteraction.jordbruksverketkmm.ViewModelState
import se.mobileinteraction.jordbruksverketkmm.ViewModelStateImpl
import se.mobileinteraction.jordbruksverketkmm.jvforms.formcomponents.FormComponent
import se.mobileinteraction.jordbruksverketkmm.jvforms.formcomponents.FormComponentTextField

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
        val currentScreen: Int = 2,
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


