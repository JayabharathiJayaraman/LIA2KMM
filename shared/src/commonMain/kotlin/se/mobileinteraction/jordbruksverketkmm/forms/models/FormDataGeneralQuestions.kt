package se.mobileinteraction.jordbruksverketkmm.forms.models

data class FormDataGeneralQuestions(
    override val commonData: Common = Common(),
    val soilAssesment: SoilAssesment = SoilAssesment(),
    val placeAssesment: PlaceAssesment = PlaceAssesment(),
    val questionnaire: Questionnaire = Questionnaire(),
    var comment: String = "",
) : FormData

// Only used by test 1

data class Questionnaire(
    val answers: MutableList<QuestionnaireAnswers> = mutableListOf()
)

data class QuestionnaireAnswers(
    var answer: QuestionnaireAnswer? = null,
    var id: String = "",
)