package se.mobileinteraction.jordbruksverketkmm.forms.models

data class FormDataGeneralQuestions(
    override val commonData: Common = Common(),
    val soilAssesment: SoilAssesment = SoilAssesment(),
    val questionnaire: Questionnaire = Questionnaire(),
    var comment: String? = null,
) : FormData

// Only used by test 1

data class Questionnaire(
    val answers: List<QuestionnaireAnswer>? = null
)