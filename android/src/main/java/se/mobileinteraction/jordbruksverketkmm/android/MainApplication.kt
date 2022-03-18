package se.mobileinteraction.jordbruksverketkmm.android

import android.app.Application
import se.mobileinteraction.jordbruksverketkmm.forms.FormViewModel
import se.mobileinteraction.jordbruksverketkmm.forms.forms.FormGeneralQuestions
import se.mobileinteraction.jordbruksverketkmm.forms.forms.FormInfiltrations
import se.mobileinteraction.jordbruksverketkmm.forms.forms.FormSoilStructure
import se.mobileinteraction.jordbruksverketkmm.forms.forms.FormType

class MainApplication : Application() {
    var formViewModel: FormViewModel = FormViewModel(FormGeneralQuestions(FormType.GeneralQuestions))

    fun setViewModel(formType: FormType) {
        formViewModel = when (formType) {
            FormType.GeneralQuestions -> FormViewModel(FormGeneralQuestions(FormType.GeneralQuestions))
            FormType.SoilStructure -> FormViewModel(FormSoilStructure(FormType.SoilStructure))
            FormType.Infiltration -> FormViewModel(FormInfiltrations(FormType.Infiltration))
        }
    }
}