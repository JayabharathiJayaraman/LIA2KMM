package se.mobileinteraction.jordbruksverketkmm.forms.models

data class FormDataGeneralQuestions(
    override val commonData: Common = Common(),
    override val coordinates: Coordinates = Coordinates(),
    val soilAssesment: SoilAssesment = SoilAssesment(),
    val placeAssesment: PlaceAssesment = PlaceAssesment(),
    override val questionnaireIsAnswered: QuestionnaireIsAnswered = QuestionnaireIsAnswered(),
    val questionnaire: Questionnaire = Questionnaire(),
    var comment: String = "",
) : FormData

// Only used by test 1

data class Questionnaire(
    val answers: MutableList<AnswerWithPhoto> = mutableListOf()
)
