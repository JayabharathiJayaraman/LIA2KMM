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
    fun setChecklistActive(id: String, active: Int, state: FormViewModel.State): FormViewModel.State
    fun setQuestionnaireAnswer(
        id: String,
        answer: QuestionnaireAnswer,
        state: FormViewModel.State
    ): FormViewModel.State

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