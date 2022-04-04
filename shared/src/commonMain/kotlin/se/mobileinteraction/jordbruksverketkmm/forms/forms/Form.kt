package se.mobileinteraction.jordbruksverketkmm.forms.forms

import se.mobileinteraction.jordbruksverketkmm.forms.FormViewModel
import se.mobileinteraction.jordbruksverketkmm.forms.components.FormScreen
import se.mobileinteraction.jordbruksverketkmm.forms.models.FormData

interface Form {
    val screens: List<FormScreen>
    val type: FormType
    val data: FormData

    fun setText(id: String, text: String, state: FormViewModel.State): FormViewModel.State

    fun setCoordinates(
        id: String,
        latitude: Double,
        longitude: Double,
        state: FormViewModel.State
    ): FormViewModel.State {
        with(state.form.data) {
            this.coordinates.latitude = latitude
            this.coordinates.longitude = longitude
        }
        return state
    }


    fun setChecklistRating(id: String, rating: Int, state: FormViewModel.State): FormViewModel.State

    fun setButtonlistActive(
        id: String,
        value: String,
        state: FormViewModel.State
    ): FormViewModel.State
}

enum class FormType {
    GeneralQuestions,
    SoilStructure,
    Infiltration,
}