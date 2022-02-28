package se.mobileinteraction.jordbruksverketkmm.jvtests.jvtestmodels

data class Test1Data(
    override val commonData: Common = Common(),
    val soilAssesment: SoilAssesment = SoilAssesment(),
    val questionaire: Questionaire = Questionaire(),
    var comment: String? = null,
): TestData

// Only used by test 1

data class Questionaire(
    val answers: List<Int>? = null
)