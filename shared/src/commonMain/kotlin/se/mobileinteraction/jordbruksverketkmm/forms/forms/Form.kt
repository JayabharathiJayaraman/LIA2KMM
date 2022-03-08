package se.mobileinteraction.jordbruksverketkmm.forms.forms

import se.mobileinteraction.jordbruksverketkmm.forms.components.FormScreen
import se.mobileinteraction.jordbruksverketkmm.forms.models.FormData

interface Form {
    val screens: List<FormScreen>
    val type: FormType
    val data: FormData
}

enum class FormType {
    GeneralQuestions,
    SoilStructure,
    Infiltration,
    NewTest
}
