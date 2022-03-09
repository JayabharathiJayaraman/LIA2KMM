package se.mobileinteraction.jordbruksverketkmm.forms.components

enum class ComponentType {
    TITLEBIG,
    TITLESMALL,
    BODY,
    TEXTFIELD,
    TEXTFIELDNOTES,
    BUTTONLIST,
    BUTTON,
    IMAGE,
    CAPTIONEDIMAGE,
    CHECKLIST,
    TIMEFIELD,
    EMPTYLINE,
    FACESBUTTON
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

class FormComponentChecklist(
    override val type: ComponentType,
    val text: String,
    val image: String,
) : FormComponent

class FormComponentTime(
    override val type: ComponentType,
    val start: String,
    val stopp: String,
) : FormComponent

class FormComponentLine(
    override val type: ComponentType, val text: String
): FormComponent

class FormComponentFacesButton(
    override val type: ComponentType,
    val faceImage: String,
    val text: String,
) : FormComponent