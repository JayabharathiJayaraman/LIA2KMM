package se.mobileinteraction.jordbruksverketkmm.jvforms.formcomponents

enum class ComponentType {
    TITLEBIG,
    TITLESMALL,
    BODY,
    TEXTFIELD,
    BUTTONLIST,
}

interface FormComponent {
    val type: ComponentType
}

class FormComponentText(override val type: ComponentType, val text: String) :
    FormComponent

class FormComponentTextField(
    override val type: ComponentType,
    val id: String,
    val text: String,
    val placeholder: String,
): FormComponent

class FormComponentButtonList(
    override val type: ComponentType,
    val id: String,
    val title: String,
    val list: List<String>,
    val value: String,
    val placeholder: String,
): FormComponent
