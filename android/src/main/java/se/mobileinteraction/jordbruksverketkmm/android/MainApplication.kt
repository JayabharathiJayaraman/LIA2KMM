package se.mobileinteraction.jordbruksverketkmm.android

import android.app.Application
import se.mobileinteraction.jordbruksverketkmm.Checklist
import se.mobileinteraction.jordbruksverketkmm.ChecklistCategory
import se.mobileinteraction.jordbruksverketkmm.ChecklistItemStates
import se.mobileinteraction.jordbruksverketkmm.checklists.ChecklistViewModel
import se.mobileinteraction.jordbruksverketkmm.checklists.models.ChecklistItem
import se.mobileinteraction.jordbruksverketkmm.forms.FormViewModel
import se.mobileinteraction.jordbruksverketkmm.forms.forms.FormGeneralQuestions
import se.mobileinteraction.jordbruksverketkmm.forms.forms.FormInfiltrations
import se.mobileinteraction.jordbruksverketkmm.forms.forms.FormSoilStructure
import se.mobileinteraction.jordbruksverketkmm.forms.forms.FormType

class MainApplication : Application() {
    var formViewModel: FormViewModel =
        FormViewModel(FormGeneralQuestions(FormType.GeneralQuestions))

    private var grundforbattringarChecklist: ChecklistViewModel = ChecklistViewModel(
        Checklist(
            ChecklistCategory.GRUNDFORBATTRINGAR,
            ChecklistItemStates.GRUNDFORBATTRINGAR.state
        ), 0
    )
    private var odlingsatgarderChecklist: ChecklistViewModel = ChecklistViewModel(
        Checklist(
            ChecklistCategory.ODLINGSATGARDER,
            ChecklistItemStates.ODLINGSATGARDER.state
        ), 0
    )
    private var undvikellerminimerChecklist: ChecklistViewModel = ChecklistViewModel(
        Checklist(
            ChecklistCategory.UNDVIKELLERMINIMERA,
            ChecklistItemStates.UNDVIKELLERMINIMERA.state
        ), 0
    )
    var checklistViewModel: ChecklistViewModel = grundforbattringarChecklist

    fun setChecklistViewModel(category: ChecklistCategory) {
        checklistViewModel = when (category) {
            ChecklistCategory.GRUNDFORBATTRINGAR -> grundforbattringarChecklist
            ChecklistCategory.ODLINGSATGARDER -> odlingsatgarderChecklist
            ChecklistCategory.UNDVIKELLERMINIMERA -> undvikellerminimerChecklist
        }
    }

    fun setViewModel(formType: FormType) {
        formViewModel = when (formType) {
            FormType.GeneralQuestions -> FormViewModel(FormGeneralQuestions(FormType.GeneralQuestions))
            FormType.SoilStructure -> FormViewModel(FormSoilStructure(FormType.SoilStructure))
            FormType.Infiltration -> FormViewModel(FormInfiltrations(FormType.Infiltration))
        }
    }
}