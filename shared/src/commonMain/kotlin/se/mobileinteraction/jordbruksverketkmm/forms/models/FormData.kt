package se.mobileinteraction.jordbruksverketkmm.forms.models

interface FormData {
    val commonData: Common
}

// Used by more than one test

data class Common(
    val farmInformation: FarmInformation = FarmInformation(),
    var date: String? = null,
)

data class FarmInformation(
    var farmName: String? = null,
    var farmLand: String? = null,
)

data class SoilAssesment(
    var soilType: String? = null,
    var crop: String? = null,
    var precedingCrop: String? = null,
    var soilHandling: String? = null,
)

data class PlaceAssesment(
    var rating: Int? = null,
    var other: String? = null,
)

data class Coordinates(
    var longitude: Double? = null,
    var latitude: Double? = null,
)

data class SoilCondition(
    var condition: Int? = null
)