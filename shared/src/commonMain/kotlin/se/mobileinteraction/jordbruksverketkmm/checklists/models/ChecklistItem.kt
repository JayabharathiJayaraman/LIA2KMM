package se.mobileinteraction.jordbruksverketkmm

data class ChecklistItem(val id : String,val checked : Boolean,val active : Boolean) {
    val title : String = "CheckListItem_" + id + "_title"
    val text : String = "CheckListItem_" + id + "_text"
}