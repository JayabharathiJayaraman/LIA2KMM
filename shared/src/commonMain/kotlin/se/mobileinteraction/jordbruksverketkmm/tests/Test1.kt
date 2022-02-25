package se.mobileinteraction.jordbruksverketkmm.tests

import se.mobileinteraction.jordbruksverketkmm.tests.`interface`.*
import se.mobileinteraction.jordbruksverketkmm.tests.testDataModels.Test1Data
import se.mobileinteraction.jordbruksverketkmm.tests.testDataModels.TestData

class Test1(): Test {
    override val type = TestType.TEST1
    override val data: TestData = Test1Data()

    override val screens: List<InterfaceScreen> = listOf(
        InterfaceScreen(components = listOf<InterfaceComponent>(
            InterfaceComponentText(type = ComponentType.TITLE, text = "Titel1"),
            InterfaceComponentText(type = ComponentType.BODY, text = "Body1 Genom olika skogar ska vi visa att man kan gräva olika typer av fält ända ned till helvetet. Om man gräver för långt så kommer djävulen att säga hej!"),
            InterfaceComponentTextAndPlaceholder(type = ComponentType.TEXTFIELD, text = "Text", placeholder = "Placeholder"),
            )
        ),
        InterfaceScreen(components = listOf<InterfaceComponent>(
            InterfaceComponentText(type = ComponentType.TITLE, text = "Titel2"),
            InterfaceComponentText(type = ComponentType.BODY, text = "Body2"),
        ),
        )
    )
}

