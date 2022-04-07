package se.mobileinteraction.jordbruksverketkmm.forms.models

data class FormDataInfiltration(
    override val commonData: Common = Common(),
    override val coordinates: Coordinates = Coordinates(),
    val placeAssesment: PlaceAssesment = PlaceAssesment(),
    val soilAssesment: SoilAssesment = SoilAssesment(),
    val infiltrationTest: InfiltrationTest = InfiltrationTest(),
    var comment: String = "",
    override val questionnaireIsAnswered: QuestionnaireIsAnswered = QuestionnaireIsAnswered(),
) : FormData

// Only used by test 3

data class InfiltrationTest(
    var measurementType: String? = null,
    val distanceStart: Int? = null,
    val distanceStop: Int? = null,
    val timeElapsed: Int? = null,
)