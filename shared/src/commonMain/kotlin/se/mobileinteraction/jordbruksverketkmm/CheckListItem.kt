package se.mobileinteraction.jordbruksverketkmm

class CheckListItem(atgard : String, checked : Boolean, active : Boolean) {
    val title : String
    val text : String
    var checked : Boolean =  checked
    var active : Boolean =  active

    init {
        when(atgard){
            "Huvudavvattning" -> {
                this.title = "dummy"
                this.text = "dummy"
            }
            else -> {
                this.title = "dummy"
                this.text = "dummy"
            }
        }
    }
    fun switchChecked(){
        this.checked = !this.checked
    }
    fun switchactive(){
        this.active = !this.active
    }
}