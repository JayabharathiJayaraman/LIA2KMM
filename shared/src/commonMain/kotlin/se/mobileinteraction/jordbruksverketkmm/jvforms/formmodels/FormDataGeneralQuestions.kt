package se.mobileinteraction.jordbruksverketkmm.jvforms.formmodels

data class FormDataGeneralQuestions(
    override val commonData: Common = Common(),
    val soilAssesment: SoilAssesment = SoilAssesment(),
    val questionaire: Questionaire = Questionaire(),
    var comment: String? = null,
): FormData

// Only used by test 1

data class Questionaire(
    val answers: List<Int>? = null
)