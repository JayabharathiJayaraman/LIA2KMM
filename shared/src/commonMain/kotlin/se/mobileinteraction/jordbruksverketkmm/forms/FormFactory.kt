package se.mobileinteraction.jordbruksverketkmm.forms

import se.mobileinteraction.jordbruksverketkmm.forms.forms.*

class FormFactory() {
    fun createForm(type: FormType): Form {
        return when (type) {
            FormType.GeneralQuestions -> FormGeneralQuestions()
            FormType.SoilStructure -> FormSoilStructure()
            FormType.Infiltration -> FormInfiltrations()
        }
    }
}