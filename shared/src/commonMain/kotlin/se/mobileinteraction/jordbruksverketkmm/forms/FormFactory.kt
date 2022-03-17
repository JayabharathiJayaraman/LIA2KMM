package se.mobileinteraction.jordbruksverketkmm.forms

import se.mobileinteraction.jordbruksverketkmm.forms.forms.FormGeneralQuestions
import se.mobileinteraction.jordbruksverketkmm.forms.forms.FormInfiltrations

class FormFactory() {
    fun createForm(): FormInfiltrations {
        return FormInfiltrations()
    }
}