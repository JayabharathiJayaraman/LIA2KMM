package se.mobileinteraction.jordbruksverketkmm.android

import android.app.Application
import se.mobileinteraction.jordbruksverketkmm.forms.FormViewModel
import se.mobileinteraction.jordbruksverketkmm.forms.forms.FormGeneralQuestions
import se.mobileinteraction.jordbruksverketkmm.forms.forms.FormInfiltrations
import se.mobileinteraction.jordbruksverketkmm.forms.forms.FormType

class MainApplicationDagger : Application() {
   // val formViewModel: FormViewModel = FormViewModel(FormGeneralQuestions(FormType.GeneralQuestions))
   val formViewModel: FormViewModel = FormViewModel(FormInfiltrations(FormType.Infiltration))

    override fun onCreate() {
        super.onCreate()
    }
}