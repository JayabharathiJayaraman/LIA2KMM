package se.mobileinteraction.jordbruksverketkmm.forms.components

enum class ComponentType {
    TITLEBIG,
    TITLESMALL,
    BODY,
    CAPTUREIMAGE,
    TEXTFIELD,
    TEXTFIELDNOTES,
    BUTTONLIST,
    CHECKLIST,
    IMAGE,
    VIDEO,
    BUTTON,
    REMARK,
    CAPTIONEDIMAGE,
    TIMEFIELD,
    EMPTYLINE,
    RESULTATINFOBODY,
    RESULTATIMAGES,
    RESULTATREMARKSFACE,
}

interface FormComponent {
    val type: ComponentType
    val id: String
}

class FormComponentText(
    override val type: ComponentType,
    override val id: String,
    val text: String
) :
    FormComponent

class FormComponentButton(
    override val type: ComponentType,
    override val id: String,
    val text: String,
) : FormComponent

class FormComponentCaptureImage(
    override val type: ComponentType,
    override val id: String,
    val title: String,
    val body: String,
    var placeholderImage: String,
    var imageUri: String?,
    val button_text: String
) : FormComponent

class FormComponentTextField(
    override val type: ComponentType,
    override val id: String,
    var text: String,
    val placeholder: String,
) : FormComponent

class FormComponentButtonList(
    override val type: ComponentType,
    override val id: String,
    val title: String,
    val list: List<String>,
    val value: String,
    val placeholder: String,
) : FormComponent

class FormComponentChecklist(
    override val type: ComponentType,
    override val id: String,
    val text: String,
) : FormComponent

class FormComponentRemark(
    override val type: ComponentType,
    override val id: String,
    val text: String,
    val image: String,
) : FormComponent

class FormComponentImage(
    override val type: ComponentType,
    override val id: String,
    val image: String,
    val caption: String,
) : FormComponent

class FormComponentTime(
    override val type: ComponentType,
    override val id: String,
    val timeLabel:String,
    val start: String,
    val stopp: String,
) : FormComponent

class FormComponentLine(
    override val type: ComponentType,
    override val id: String,
   val text: String
): FormComponent

class FormComponentResultatInfoBody(
    override val type: ComponentType,
    override val id: String,
    val text: String) : FormComponent

class FormComponentResultatImages(
    override val type: ComponentType,
    override val id: String,
    val text1: String,
    val text2: String,
    val text3: String,
    val text4: String
): FormComponent

class FormComponentResultatRemark(
    override val type: ComponentType,
    override val id: String,
    val text: String,
    val image: String,
    val color: String
) : FormComponent

class FormComponentVideo(
    override val type: ComponentType,
    override val id: String,
    val text: String,
) : FormComponent
