package se.mobileinteraction.jordbruksverketkmm

class CheckList(category : String) {
    private val checkListLocal = CheckListLocal()
    val title = checkListLocal.text("test")
}