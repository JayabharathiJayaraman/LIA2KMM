package se.mobileinteraction.jordbruksverketkmm

class CheckList(category : String) {

    val title : String
    val text : String

    init {
        when(category){
           "Grundförbättringar" -> {
                this.title = "CheckList_Grundförbättringar_title"
                this.text = "CheckList_Grundförbättringar_text"
           }
            "Odlingsåtgärder" -> {
                this.title = "CheckList_Odlingsåtgärder_title"
                this.text = "CheckList_Odlingsåtgärder_text"
            }
            "Undvikellerminimera" -> {
                this.title = "CheckList_Undvikellerminimera_title"
                this.text = "CheckList_Undvikellerminimera_text"
            }
            else -> {
                this.title = "dummy"
                this.text = "dummy"
            }
        }
    }
}