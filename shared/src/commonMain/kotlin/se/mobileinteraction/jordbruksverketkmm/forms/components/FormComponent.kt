package se.mobileinteraction.jordbruksverketkmm.forms.components
import se.mobileinteraction.jordbruksverketkmm.forms.models.AnswerWithPhoto
import se.mobileinteraction.jordbruksverketkmm.forms.models.QuestionnaireAnswer

enum class ComponentType {
    TITLEBIG,
    TITLESMALL,
    BODY,
    TEXTFIELD,
    TEXTFIELDNOTES,
    BUTTONLIST,
    CHECKLIST,
    IMAGE,
    VIDEO,
    BUTTON,
    QUESTIONNAIRE,
    QUESTIONNAIRERESULT,
    IMAGESGRID,
    TIMEFIELD,
    EMPTYLINE,
    RESULTSINFOBODY,
    RESULTSIMAGES,
    RESULTSREMARKSFACE,

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
    var value: String,
    val placeholder: String,
) : FormComponent

class FormComponentChecklist(
    override val type: ComponentType,
    override val id: String,
    val title: String,
    val options: List<String>,
    var active: Int,
) : FormComponent

class FormComponentQuestionnaire(
    override val type: ComponentType,
    override val id: String,
    val text: List<String>,
    var answer: QuestionnaireAnswer?
) : FormComponent

class FormComponentQuestionnaireResult(
    override val type: ComponentType,
    override val id: String,
    val answers: MutableList<AnswerWithPhoto>?,
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
    val timeLabel: String,
    val start: String,
    val stop: String,
) : FormComponent

class FormComponentLine(
    override val type: ComponentType,
    override val id: String,
    val text: String
) : FormComponent

class FormComponentResultsInfoBody(
    override val type: ComponentType,
    override val id: String,
    val text: String
) : FormComponent

class FormComponentResultsImages(
    override val type: ComponentType,
    override val id: String,
    val imagesTextList: List<String>
) : FormComponent

class FormComponentResultsRemark(
    override val type: ComponentType,
    override val id: String,
    val text: String,
    val image: String,
    val color: String
) : FormComponent

class FormComponentVideo(
    override val type: ComponentType,
    override val id: String,
    val description: String,
    val source: String,
) : FormComponent

class FormComponentImagesGrid(
    override val type: ComponentType,
    override val id: String,
    val image: List<String>,
    val caption: List<String>,
) : FormComponent
