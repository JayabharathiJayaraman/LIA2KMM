package se.mobileinteraction.jordbruksverketkmm.forms.forms

import se.mobileinteraction.jordbruksverketkmm.forms.FormViewModel
import se.mobileinteraction.jordbruksverketkmm.forms.components.FormScreen
import se.mobileinteraction.jordbruksverketkmm.forms.models.FormData

interface Form {
    val screens: List<FormScreen>
    val type: FormType
    val data: FormData

    fun setText(id: String, text: String, state: FormViewModel.State): FormViewModel.State

    fun setChecklistRating(id: String, rating: Int, state: FormViewModel.State): FormViewModel.State

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