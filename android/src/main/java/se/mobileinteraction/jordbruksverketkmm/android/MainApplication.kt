package se.mobileinteraction.jordbruksverketkmm.android

import android.app.Application
import se.mobileinteraction.jordbruksverketkmm.Checklist
import se.mobileinteraction.jordbruksverketkmm.checklists.ChecklistViewModel
import se.mobileinteraction.jordbruksverketkmm.checklists.models.ChecklistState
import se.mobileinteraction.jordbruksverketkmm.forms.FormViewModel
import se.mobileinteraction.jordbruksverketkmm.forms.forms.FormGeneralQuestions
import se.mobileinteraction.jordbruksverketkmm.forms.forms.FormType

class MainApplication : Application() {
    var formViewModel: FormViewModel = FormViewModel(FormGeneralQuestions(FormType.GeneralQuestions))

    private val grundforbattringarState = listOf<ChecklistState>(
        ChecklistState("Huvudavvattning", true, true),
        ChecklistState("OrganiskaMaterial", true, true),
        ChecklistState("Detaljdränering", true, true),
        ChecklistState("Strukturkalkning", true, true),
        ChecklistState("Alvluckring", true, true)
    )

    private val odlingsatgarderState = listOf<ChecklistState>(
        ChecklistState("GynnaDaggmaskarna", true, true),
        ChecklistState("GrödorMedBraRotsystem", true, true),
        ChecklistState("LämnaOrganisktMaterial", true, true),
        ChecklistState("PlaneraKörningen", true, true),
        ChecklistState("MinskaBelastningenPåMarken", true, true),
        ChecklistState("BeväxtMarkÅretOm", true, true)
    )

    private val undvikEllerMinimeraState = listOf<ChecklistState>(
        ChecklistState("UndvikTungaMaskiner", true, true),
        ChecklistState("UndvikKörningVidVåtaMarkförhållanden", true, true),
        ChecklistState("MinimeraAntaletÖverfarter", true, true),
        ChecklistState("MinimeraAndelenBarMark", true, true)
    )

    var checklistViewModel: ChecklistViewModel = ChecklistViewModel(Checklist(Checklist.Category.GRUNDFORBATTRINGAR, grundforbattringarState), 0)

    fun setChecklistViewModel(category: String){
        when(category){
            "Grundförbättringar" -> checklistViewModel = ChecklistViewModel(Checklist(Checklist.Category.GRUNDFORBATTRINGAR, grundforbattringarState), 0)
            "Odlingsåtgärder" -> checklistViewModel = ChecklistViewModel(Checklist(Checklist.Category.ODLINGSATGARDER, odlingsatgarderState), 0)
            "UndvikEllerMinimera" -> checklistViewModel = ChecklistViewModel(Checklist(Checklist.Category.UNDVIKELLERMINIMERA, undvikEllerMinimeraState), 0)
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