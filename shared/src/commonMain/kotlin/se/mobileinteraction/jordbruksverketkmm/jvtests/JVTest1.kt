package se.mobileinteraction.jordbruksverketkmm.jvtests

import se.mobileinteraction.jordbruksverketkmm.jvtests.formcomponents.*
import se.mobileinteraction.jordbruksverketkmm.jvtests.jvtestmodels.Test1Data
import se.mobileinteraction.jordbruksverketkmm.jvtests.jvtestmodels.TestData

data class JVTest1(
    override val type: TestType = TestType.TEST1,
    override val data: TestData = Test1Data(),
) : JVTest {
    override val screens: List<InterfaceScreen> = listOf(
        InterfaceScreen(
            components = listOf<InterfaceComponent>(
                InterfaceComponentText(
                    type = ComponentType.TITLESMALL,
                    text = "Beskrivning")
                ,
                InterfaceComponentText(
                    type = ComponentType.BODY,
                    text = "Detta test består av allmänna frågor om hur skiftet brukar fungera för din växtodling och om det finns tydliga problem med koppling till markstruktur."
                ),
            )
        ),
        InterfaceScreen(
            components = listOf<InterfaceComponent>(
                InterfaceComponentText(
                    type = ComponentType.TITLESMALL,
                    text = "Uppgifter om gård och skifte"
                ),
                InterfaceComponentTextField(
                    type = ComponentType.TEXTFIELD,
                    id = ID_FARMNAME,
                    text = "",
                    placeholder = "Gårdsnamn",
                ),
                InterfaceComponentTextField(
                    type = ComponentType.TEXTFIELD,
                    id = ID_FARMLAND,
                    text = "",
                    placeholder = "Skifte",
                ),
                InterfaceComponentTextField(
                    type = ComponentType.TEXTFIELD,
                    id = ID_DATE,
                    text = "",
                    placeholder = "Datum",
                ),
                InterfaceComponentText(
                    type = ComponentType.TITLESMALL,
                    text = "Tips!"
                ),
                InterfaceComponentText(
                    type = ComponentType.BODY,
                    text = "Om du har ett stort skifte..."
                ),
            ),
        ),
        InterfaceScreen(
            components = listOf<InterfaceComponent>(
                InterfaceComponentText(
                    type = ComponentType.TITLEBIG,
                    text = "Grundförutsättningar"
                ),
                InterfaceComponentButtonList(
                    type = ComponentType.BUTTONLIST,
                    id = ID_SOILTYPE,
                    title = "Jordart",
                    list = listOf("ett", "två"),
                    value = "ett",
                    placeholder = "Välj...",
                ),
                InterfaceComponentText(
                    type = ComponentType.TITLESMALL,
                    text = "Tips!"
                ),
                InterfaceComponentText(
                    type = ComponentType.BODY,
                    text = "Om det finns en.."
                ),
            ),
        ),
    )

    fun setTextField(id: String, text: String, state: TestViewModel.State): TestViewModel.State {
        with(state.test.data) {
            when (id) {
                ID_FARMNAME -> commonData.farmInformation.farmName = text
                ID_FARMLAND -> commonData.farmInformation.farmLand = text
                ID_DATE -> commonData.date = text
                ID_SOILTYPE -> (this as Test1Data).soilAssesment.soilType = text
            }
        }

        return state
    }

    companion object {
        const val ID_FARMNAME = "FARMNAME"
        const val ID_FARMLAND = "FARMLAND"
        const val ID_DATE = "DATE"
        const val ID_SOILTYPE = "SOILTYPE"
    }
}

