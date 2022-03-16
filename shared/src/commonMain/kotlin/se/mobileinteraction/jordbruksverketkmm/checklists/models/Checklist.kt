package se.mobileinteraction.jordbruksverketkmm

import se.mobileinteraction.jordbruksverketkmm.checklists.models.ChecklistState

data class Checklist(val category: Category, val stateList: List<ChecklistState>) {

    val title : String = "CheckList_" + category.categoryName + "_title"
    val text : String = "CheckList_" + category.categoryName + "_text"
    val id : Category = category

    enum class Category(val categoryName: String) {
        GRUNDFORBATTRINGAR("Grundförbättringar"),
        ODLINGSATGARDER("Odlingsåtgärder"),
        UNDVIKELLERMINIMERA("UndvikEllerMinimera")
    }

}