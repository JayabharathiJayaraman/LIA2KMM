package se.mobileinteraction.jordbruksverketkmm.android

import android.app.Application
import se.mobileinteraction.jordbruksverketkmm.Checklist
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
            Checklist.Category.GRUNDFORBATTRINGAR,
            Checklist.ItemStates.GRUNDFORBATTRINGAR.state
        ), 0
    )
    private var odlingsatgarderChecklist: ChecklistViewModel = ChecklistViewModel(
        Checklist(
            Checklist.Category.ODLINGSATGARDER,
            Checklist.ItemStates.ODLINGSATGARDER.state
        ), 0
    )
    private var undvikellerminimerChecklist: ChecklistViewModel = ChecklistViewModel(
        Checklist(
            Checklist.Category.UNDVIKELLERMINIMERA,
            Checklist.ItemStates.UNDVIKELLERMINIMERA.state
        ), 0
    )
    var checklistViewModel: ChecklistViewModel = grundforbattringarChecklist

    fun setChecklistViewModel(category: Checklist.Category) {
        checklistViewModel = when (category) {
            Checklist.Category.GRUNDFORBATTRINGAR -> grundforbattringarChecklist
            Checklist.Category.ODLINGSATGARDER -> odlingsatgarderChecklist
            Checklist.Category.UNDVIKELLERMINIMERA -> undvikellerminimerChecklist
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