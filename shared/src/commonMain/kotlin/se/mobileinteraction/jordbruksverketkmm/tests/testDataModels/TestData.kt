package se.mobileinteraction.jordbruksverketkmm.tests.testDataModels

interface TestData {
    val commonData: Common
}

// Used by more than one test

data class Common(
    val farmInformation: FarmInformation = FarmInformation(),
    val date: String = ""
)

data class FarmInformation(
    val farmName: String = "",
    val farmLand: String = ""
)

data class SoilAssesment(
    val soilType: String = "",
    val crop: String = "",
    val precedingCrop: String = "",
    val soilHandling: String = ""
)

data class PlaceAssesment(
    val rating: Int? = null,
    val other: String = ""
)

data class Coordinates(
    val longitude: String = "",
    val latitude: String = ""
)

data class SoilCondition(
    val condition: Int? = null
)