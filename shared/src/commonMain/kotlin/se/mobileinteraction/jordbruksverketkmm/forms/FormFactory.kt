package se.mobileinteraction.jordbruksverketkmm.forms

import se.mobileinteraction.jordbruksverketkmm.forms.forms.FormGeneralQuestions

class FormFactory() {
    fun createForm(): FormGeneralQuestions {
        return FormGeneralQuestions()
    }
}