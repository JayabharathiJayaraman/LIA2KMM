package se.mobileinteraction.jordbruksverketkmm.forms.models

data class FormDataSoilStructure(
    override val commonData: Common = Common(),
    val placeAssesment: PlaceAssesment = PlaceAssesment(),
    val coordinates: Coordinates = Coordinates(),
    val soilAssesment: SoilAssesment = SoilAssesment(),
    val soilCondition: SoilCondition = SoilCondition(),
    val photoData: PhotoData = PhotoData(),
    val stompData: StompData = StompData(),
    val questionnaireWithPhotos: QuestionnaireWithPhotos = QuestionnaireWithPhotos(),
    var comment: String? = null,
) : FormData

// Only used by test 2

data class PhotoData(
    val photoUri: String? = null,
    val photoLines: List<PhotoLine> = emptyList()
)

data class PhotoLine(
    val y: Double? = null,
    val type: String? = null,
    val start: Double? = null,
    val end: Double? = null
)

data class StompData(
    val level1: Int? = null,
    val level2: Int? = null,
    val level3: Int? = null,
    val level4: Int? = null,
    val showLevel3: Boolean = false,
)

data class QuestionnaireWithPhotos(
    val answers: List<AnswerWithPhoto> = emptyList()
)

data class AnswerWithPhoto(
    val answer: QuestionnaireAnswer? = null,
    val photoUri: String? = null,
    val comment: String = "",
)