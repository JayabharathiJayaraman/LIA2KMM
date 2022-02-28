package se.mobileinteraction.jordbruksverketkmm.jvtests.jvtestmodels

data class Test2Data(
    override val commonData: Common = Common(),
    val placeAssesment: PlaceAssesment = PlaceAssesment(),
    val coordinates: Coordinates = Coordinates(),
    val soilAssesment: SoilAssesment = SoilAssesment(),
    val soilCondition: SoilCondition = SoilCondition(),
    val photoData: PhotoData = PhotoData(),
    val stompData: StompData = StompData(),
    val questionaireWithPhotos: QuestionaireWithPhotos = QuestionaireWithPhotos(),
    var comment: String? = null,
): TestData

// Only used by test 2

data class PhotoData(
    val photoUri: String? = null,
    val photoLines: List<PhotoLine>? = null
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

data class QuestionaireWithPhotos(
    val answers: List<AnswerWithPhoto>? = null,
)

data class AnswerWithPhoto(
    val answer: Int? = null,
    val photoUri: String? = null,
    val comment: String? = null,
)