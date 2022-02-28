package se.mobileinteraction.jordbruksverketkmm.jvtests.jvtestmodels

data class Test3Data(
    override val commonData: Common = Common(),
    val placeAssesment: PlaceAssesment = PlaceAssesment(),
    val coordinates: Coordinates = Coordinates(),
    val soilAssesment: SoilAssesment = SoilAssesment(),
    val infiltrationTest: List<InfiltrationTest>? = null,
    var comment: String? = null,
): TestData

// Only used by test 3

data class InfiltrationTest(
    val measurementType: String? = null,
    val distanceStart: Int? = null,
    val distanceStop: Int? = null,
    val timeElapsed: Int? = null,
)