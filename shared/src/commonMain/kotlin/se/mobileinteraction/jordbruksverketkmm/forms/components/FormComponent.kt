package se.mobileinteraction.jordbruksverketkmm.forms.components

enum class ComponentType {
    TITLEBIG,
    TITLESMALL,
    BODY,
    TEXTFIELD,
    BUTTONLIST,
    BUTTON,
    IMAGE,
}

interface FormComponent {
    val type: ComponentType
}

class FormComponentText(override val type: ComponentType, val text: String) : FormComponent

class FormComponentButton(override val type: ComponentType, val text: String) : FormComponent

class FormComponentTextField(
    override val type: ComponentType,
    val id: String,
    val text: String,
    val placeholder: String,
) : FormComponent

class FormComponentButtonList(
    override val type: ComponentType,
    val id: String,
    val title: String,
    val list: List<String>,
    val value: String,
    val placeholder: String,
) : FormComponent

class FormComponentImage(
    override val type: ComponentType,
    val image: String,
    val caption: String,
) : FormComponent
