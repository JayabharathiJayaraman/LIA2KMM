package se.mobileinteraction.jordbruksverketkmm.forms.forms

import se.mobileinteraction.jordbruksverketkmm.forms.FormViewModel
import se.mobileinteraction.jordbruksverketkmm.forms.components.FormScreen
import se.mobileinteraction.jordbruksverketkmm.forms.models.FormData
import se.mobileinteraction.jordbruksverketkmm.forms.models.QuestionnaireAnswer

interface Form {
    val screens: List<FormScreen>
    val type: FormType
    val data: FormData
    fun setText(id: String, text: String, state: FormViewModel.State): FormViewModel.State

    fun setCoordinates(
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

    fun setQuestionnaireAnswer(
        id: String,
        answer: QuestionnaireAnswer,
        text: String,
        state: FormViewModel.State
    ): FormViewModel.State
    
    fun setButtonlistData(
        id: String,
        selected: String,
        position: Int,
        state: FormViewModel.State
    ): FormViewModel.State
}

enum class FormType {
    GeneralQuestions,
    SoilStructure,
    Infiltration,
}