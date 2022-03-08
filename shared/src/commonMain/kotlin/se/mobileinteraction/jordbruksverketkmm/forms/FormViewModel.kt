package se.mobileinteraction.jordbruksverketkmm.forms

import se.mobileinteraction.jordbruksverketkmm.utilities.ViewModelState
import se.mobileinteraction.jordbruksverketkmm.utilities.ViewModelStateImpl
import se.mobileinteraction.jordbruksverketkmm.forms.components.FormComponent
import se.mobileinteraction.jordbruksverketkmm.forms.components.FormComponentTextField
import se.mobileinteraction.jordbruksverketkmm.forms.forms.FormGeneralQuestions
import se.mobileinteraction.jordbruksverketkmm.forms.forms.FormInfiltrations

class FormViewModel(
    //form: FormGeneralQuestions = FormGeneralQuestions()
    form: FormInfiltrations = FormInfiltrations()
) : ViewModelState<FormViewModel.State> by ViewModelStateImpl(State(form)) {
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
        //val form: FormGeneralQuestions,
        val form: FormInfiltrations,
        val currentScreen: Int = 0,
        val counter: Int = 0,
    ) {
        val components: List<FormComponent> = form.screens[currentScreen].components
        val totalScreens: Int = form.screens.size
    }

    fun setTextData(id: String, text: String) {
        state.value.components.firstOrNull {
            it is FormComponentTextField
        }.let {
            updateStateAndSave { form.setText(id, text, state.value).copy(counter = counter + 1) }
        }
    }
}


