package se.mobileinteraction.jordbruksverketkmm.checklists.models

data class ChecklistItem(val id: String, val active: Boolean, val checked: Boolean ) {
    val title : String = "CheckListItem_" + id + "_title"
    val text : String = "CheckListItem_" + id + "_text"
}