package se.mobileinteraction.jordbruksverketkmm.tests.`interface`

import se.mobileinteraction.jordbruksverketkmm.tests.TestValueKey

enum class ComponentType {
    TITLE,
    BODY,
    TEXTFIELD,
}

interface InterfaceComponent {
    val type: ComponentType
}

class InterfaceComponentText(override val type: ComponentType, val text: String): InterfaceComponent {

}

class InterfaceComponentTextAndPlaceholder(override val type: ComponentType, val text: String, val placeholder: String): InterfaceComponent {

}
