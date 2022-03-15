package se.mobileinteraction.jordbruksverketkmm.android

import android.app.Application
import se.mobileinteraction.jordbruksverketkmm.Checklist
import se.mobileinteraction.jordbruksverketkmm.checklists.ChecklistViewModel
import se.mobileinteraction.jordbruksverketkmm.forms.FormViewModel
import se.mobileinteraction.jordbruksverketkmm.forms.forms.FormGeneralQuestions
import se.mobileinteraction.jordbruksverketkmm.forms.forms.FormType

class MainApplication : Application() {
    var formViewModel: FormViewModel = FormViewModel(FormGeneralQuestions(FormType.GeneralQuestions))

    var grundforbattringarViewModel: ChecklistViewModel = ChecklistViewModel(Checklist("Grundförbättringar", null), 0)
    var odlingsatgarderViewModel: ChecklistViewModel = ChecklistViewModel(Checklist("Odlingsåtgärder", null), 0)
    var undvikEllerMinimeraViewModel: ChecklistViewModel = ChecklistViewModel(Checklist("UndvikEllerMinimera", null), 0)

    fun setViewModel(formType: FormType) {
        formViewModel = when (formType) {
            FormType.GeneralQuestions -> FormViewModel(FormGeneralQuestions(FormType.GeneralQuestions))
            FormType.SoilStructure -> FormViewModel(FormGeneralQuestions(FormType.SoilStructure))
            FormType.Infiltration -> FormViewModel(FormGeneralQuestions(FormType.Infiltration))
        }
    }
}