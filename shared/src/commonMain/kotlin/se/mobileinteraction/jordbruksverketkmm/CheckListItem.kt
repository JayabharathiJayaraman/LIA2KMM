package se.mobileinteraction.jordbruksverketkmm

class CheckListItem(itemName : String, checked : Boolean, active : Boolean) {
    val title : String
    val text : String
    var checked : Boolean =  checked
    var active : Boolean =  active

    init {
        this.title = "CheckListItem_" + itemName + "_title"
        this.text = "CheckListItem_" + itemName + "_text"
    }
    fun switchChecked(){
        this.checked = !this.checked
    }
    fun switchactive(){
        this.active = !this.active
    }
}