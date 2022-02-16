package se.mobileinteraction.jordbruksverketkmm

class CheckListItem(atgard : String, checked : Boolean, active : Boolean) {
    val title : String
    val text : String
    var checked : Boolean =  checked
    var active : Boolean =  active

    init {
        this.title = "CheckList_" + atgard + "_title"
        this.text = "CheckList_" + atgard + "_text"
    }
    fun switchChecked(){
        this.checked = !this.checked
    }
    fun switchactive(){
        this.active = !this.active
    }
}