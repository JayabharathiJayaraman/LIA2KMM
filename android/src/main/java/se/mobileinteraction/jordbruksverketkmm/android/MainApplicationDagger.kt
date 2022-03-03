package se.mobileinteraction.jordbruksverketkmm.android

import android.app.Application
import se.mobileinteraction.jordbruksverketkmm.forms.FormViewModel
import se.mobileinteraction.jordbruksverketkmm.forms.forms.FormGeneralQuestions
import se.mobileinteraction.jordbruksverketkmm.forms.forms.FormType

class MainApplicationDagger : Application() {
    val formViewModel: FormViewModel = FormViewModel(FormGeneralQuestions(FormType.GeneralQuestions))

    override fun onCreate() {
        super.onCreate()
    }
}