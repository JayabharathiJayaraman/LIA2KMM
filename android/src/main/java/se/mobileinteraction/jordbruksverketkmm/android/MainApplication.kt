package se.mobileinteraction.jordbruksverketkmm.android

import android.app.Application
import se.mobileinteraction.jordbruksverketkmm.Checklist
import se.mobileinteraction.jordbruksverketkmm.checklists.ChecklistViewModel
import se.mobileinteraction.jordbruksverketkmm.checklists.models.ChecklistItem
import se.mobileinteraction.jordbruksverketkmm.forms.FormViewModel
import se.mobileinteraction.jordbruksverketkmm.forms.forms.FormGeneralQuestions
import se.mobileinteraction.jordbruksverketkmm.forms.forms.FormType

class MainApplication : Application() {
    var formViewModel: FormViewModel =
        FormViewModel(FormGeneralQuestions(FormType.GeneralQuestions))

    private val grundforbattringarState = listOf<ChecklistItem>(
        ChecklistItem("Huvudavvattning", true, true),
        ChecklistItem("OrganiskaMaterial", true, true),
        ChecklistItem("Detaljdränering", true, true),
        ChecklistItem("Strukturkalkning", true, true),
        ChecklistItem("Alvluckring", true, true)
    )

    private val odlingsatgarderState = listOf<ChecklistItem>(
        ChecklistItem("GynnaDaggmaskarna", true, true),
        ChecklistItem("GrödorMedBraRotsystem", true, true),
        ChecklistItem("LämnaOrganisktMaterial", true, true),
        ChecklistItem("PlaneraKörningen", true, true),
        ChecklistItem("MinskaBelastningenPåMarken", true, true),
        ChecklistItem("BeväxtMarkÅretOm", true, true)
    )

    private val undvikEllerMinimeraState = listOf<ChecklistItem>(
        ChecklistItem("UndvikTungaMaskiner", true, true),
        ChecklistItem("UndvikKörningVidVåtaMarkförhållanden", true, true),
        ChecklistItem("MinimeraAntaletÖverfarter", true, true),
        ChecklistItem("MinimeraAndelenBarMark", true, true)
    )

    private var grundforbattringarChecklist: ChecklistViewModel = ChecklistViewModel(
        Checklist(
            Checklist.Category.GRUNDFORBATTRINGAR,
            grundforbattringarState
        ), 0
    )
    private var odlingsatgarderChecklist: ChecklistViewModel = ChecklistViewModel(
        Checklist(
            Checklist.Category.ODLINGSATGARDER,
            odlingsatgarderState
        ), 0
    )
    private var undvikellerminimerChecklist: ChecklistViewModel = ChecklistViewModel(
        Checklist(
            Checklist.Category.UNDVIKELLERMINIMERA,
            undvikEllerMinimeraState
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
            FormType.SoilStructure -> FormViewModel(FormGeneralQuestions(FormType.SoilStructure))
            FormType.Infiltration -> FormViewModel(FormGeneralQuestions(FormType.Infiltration))
        }
    }
}