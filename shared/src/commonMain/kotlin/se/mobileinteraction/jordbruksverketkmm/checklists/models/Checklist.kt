package se.mobileinteraction.jordbruksverketkmm

import se.mobileinteraction.jordbruksverketkmm.checklists.models.ChecklistItem

data class Checklist(val category: Category, val itemList: List<ChecklistItem>) {

    val title: String = "CheckList_" + category.categoryName + "_title"
    val text: String = "CheckList_" + category.categoryName + "_text"
    val id: Category = category

    enum class Category(val categoryName: String) {
        GRUNDFORBATTRINGAR("Grundförbättringar"),
        ODLINGSATGARDER("Odlingsåtgärder"),
        UNDVIKELLERMINIMERA("UndvikEllerMinimera")
    }

    enum class ItemStates(val state: List<ChecklistItem>) {
        GRUNDFORBATTRINGAR(
            listOf<ChecklistItem>(
                ChecklistItem("Huvudavvattning", true, true),
                ChecklistItem("OrganiskaMaterial", true, true),
                ChecklistItem("Detaljdränering", true, true),
                ChecklistItem("Strukturkalkning", true, true),
                ChecklistItem("Alvluckring", true, true)
            )
        ),
        ODLINGSATGARDER(
            listOf<ChecklistItem>(
                ChecklistItem("GynnaDaggmaskarna", true, true),
                ChecklistItem("GrödorMedBraRotsystem", true, true),
                ChecklistItem("LämnaOrganisktMaterial", true, true),
                ChecklistItem("PlaneraKörningen", true, true),
                ChecklistItem("MinskaBelastningenPåMarken", true, true),
                ChecklistItem("BeväxtMarkÅretOm", true, true)
            )
        ),
        UNDVIKELLERMINIMERA(
            listOf<ChecklistItem>(
                ChecklistItem("UndvikTungaMaskiner", true, true),
                ChecklistItem("UndvikKörningVidVåtaMarkförhållanden", true, true),
                ChecklistItem("MinimeraAntaletÖverfarter", true, true),
                ChecklistItem("MinimeraAndelenBarMark", true, true)
            )
        )
    }
}