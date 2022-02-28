package se.mobileinteraction.jordbruksverketkmm.jvtests

import se.mobileinteraction.jordbruksverketkmm.ViewModelState
import se.mobileinteraction.jordbruksverketkmm.ViewModelStateImpl
import se.mobileinteraction.jordbruksverketkmm.jvtests.formcomponents.InterfaceComponent
import se.mobileinteraction.jordbruksverketkmm.jvtests.formcomponents.InterfaceComponentTextField

class TestViewModel constructor(
    test: JVTest1 = JVTest1()
) : ViewModelState<TestViewModel.State> by ViewModelStateImpl(State(test)) {
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
        val test: JVTest1,
        val currentScreen: Int = 2,
        val counter: Int = 0,
    ) {
        val components: List<InterfaceComponent> = test.screens[currentScreen].components
    }

    fun addTextFieldData(id: String, text: String) {
        state.value.components.firstOrNull {
            it is InterfaceComponentTextField
        }.let {
            updateStateAndSave { test.setTextField(id, text, state.value).copy(counter = counter + 1) }
        }
    }
}


