package se.mobileinteraction.jordbruksverketkmm.forms.models

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

interface FormData {
    val commonData: Common
    val coordinates: Coordinates
    val questionnaireIsAnswered: QuestionnaireIsAnswered
}

// Used by more than one test

data class Common(
    val farmInformation: FarmInformation = FarmInformation(),
    var date: Instant = Clock.System.now(),
)

data class FarmInformation(
    var farmName: String = "",
    var farmLand: String = "",
)

data class SoilAssesment(
    var soilType: String = "",
    var crop: String = "",
    var precedingCrop: String = "",
    var soilHandling: String = "",
)

data class PlaceAssesment(
    var rating: Int? = null,
    var other: String = "",
)

data class Coordinates(
    var longitude: Double? = null,
    var latitude: Double? = null,
)

data class SoilCondition(
    var condition: Int? = null
)

data class QuestionnaireIsAnswered(
    var answered: Boolean? = null,
)

enum class QuestionnaireAnswer {
    Good,
    Mediocre,
    Poor,
}