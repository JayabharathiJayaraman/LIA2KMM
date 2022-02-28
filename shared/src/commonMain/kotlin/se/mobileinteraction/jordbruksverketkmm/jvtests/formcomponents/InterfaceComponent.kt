package se.mobileinteraction.jordbruksverketkmm.jvtests.formcomponents

enum class ComponentType {
    TITLEBIG,
    TITLESMALL,
    BODY,
    TEXTFIELD,
    BUTTONLIST,
}

interface InterfaceComponent {
    val type: ComponentType
}

class InterfaceComponentText(override val type: ComponentType, val text: String) :
    InterfaceComponent

class InterfaceComponentTextField(
    override val type: ComponentType,
    val id: String,
    val text: String,
    val placeholder: String,
): InterfaceComponent

class InterfaceComponentButtonList(
    override val type: ComponentType,
    val id: String,
    val title: String,
    val list: List<String>,
    val value: String,
    val placeholder: String,
): InterfaceComponent
