package se.mobileinteraction.jordbruksverketkmm.tests

class Test1: Test {
    override val screens: List<InterfaceScreen> = listOf(
        InterfaceScreen(components = listOf<InterfaceComponent>(
            InterfaceComponent(type = ComponentType.TITLE, text = "Titel1"),
            InterfaceComponent(type = ComponentType.BODY, text = "Body1"),
            )
        ),
        InterfaceScreen(components = listOf<InterfaceComponent>(
            InterfaceComponent(type = ComponentType.TITLE, text = "Titel2"),
            InterfaceComponent(type = ComponentType.BODY, text = "Body2"),
        ),
        )
    )
}