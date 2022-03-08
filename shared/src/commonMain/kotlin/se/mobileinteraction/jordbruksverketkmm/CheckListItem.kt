package se.mobileinteraction.jordbruksverketkmm

class CheckListItem(itemName : String, checked : Boolean, active : Boolean) {
    val title : String = "CheckListItem_" + itemName + "_title"
    val text : String = "CheckListItem_" + itemName + "_text"
    var checked : Boolean =  checked
    var active : Boolean =  active

    fun switchChecked(){
        this.checked = !this.checked
    }

    fun switchActive(){
        this.active = !this.active
    }
}