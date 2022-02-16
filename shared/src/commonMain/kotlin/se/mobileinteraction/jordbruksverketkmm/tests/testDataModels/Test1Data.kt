package se.mobileinteraction.jordbruksverketkmm.tests.testDataModels

data class Test1Data(
    override val commonData: Common = Common(),
    val soilAssesment: SoilAssesment = SoilAssesment(),
    val questionaire: Questionaire = Questionaire(),
    val comment: String = ""
): TestData

// Only used by test 1

data class Questionaire(
    val answers: List<Int>? = null
)