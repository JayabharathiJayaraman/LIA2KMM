package se.mobileinteraction.jordbruksverketkmm

import se.mobileinteraction.jordbruksverketkmm.checklists.models.ChecklistState

data class Checklist(val category : String, val stateList: List<ChecklistState>?) {

    val title : String
    val text : String
    val id : String = category
    val itemList: List<ChecklistItem>

    private val categoryList = listOf("Grundförbättringar",
        "Odlingsåtgärder", "UndvikEllerMinimera")

    private val catgoryListGrundforbattringar = listOf("Huvudavvattning",
        "OrganiskaMaterial", "Detaljdränering", "Strukturkalkning", "Alvluckring")

    private val catgoryListOdlingsatgarder = listOf("GynnaDaggmaskarna",
        "GrödorMedBraRotsystem", "LämnaOrganisktMaterial", "PlaneraKörningen",
        "MinskaBelastningenPåMarken", "BeväxtMarkÅretOm")


    private val catgoryListUndvikEllerMinimera = listOf("UndvikTungaMaskiner",
        "UndvikKörningVidVåtaMarkförhållanden", "MinimeraAntaletÖverfarter",
        "MinimeraAndelenBarMark",)

    private val grundforbattringarState = listOf<ChecklistState>(
        ChecklistState("Huvudavvattning", true, true),
        ChecklistState("OrganiskaMaterial", true, true),
        ChecklistState("Detaljdränering", true, true),
        ChecklistState("Strukturkalkning", true, true),
        ChecklistState("Alvluckring", true, true)
    )

    private val odlingsatgarderState = listOf<ChecklistState>(
        ChecklistState("GynnaDaggmaskarna", true, true),
        ChecklistState("GrödorMedBraRotsystem", true, true),
        ChecklistState("LämnaOrganisktMaterial", true, true),
        ChecklistState("PlaneraKörningen", true, true),
        ChecklistState("MinskaBelastningenPåMarken", true, true),
        ChecklistState("BeväxtMarkÅretOm", true, true)
    )

    private val undvikEllerMinimeraState = listOf<ChecklistState>(
        ChecklistState("UndvikTungaMaskiner", true, true),
        ChecklistState("UndvikKörningVidVåtaMarkförhållanden", true, true),
        ChecklistState("MinimeraAntaletÖverfarter", true, true),
        ChecklistState("MinimeraAndelenBarMark", true, true)
    )

    init {
        if(categoryList.contains(category)){
            this.title = "CheckList_" + category + "_title"
            this.text = "CheckList_" + category + "_text"
           val newGrundforbattringarState : List<ChecklistState>
           val newOdlingsatgarderState : List<ChecklistState>
           val newUndvikEllerMinimeraState : List<ChecklistState>

            if(stateList.isNullOrEmpty()){
                newGrundforbattringarState = grundforbattringarState
                newOdlingsatgarderState = odlingsatgarderState
                newUndvikEllerMinimeraState = undvikEllerMinimeraState
            }else{
                newGrundforbattringarState = stateList
                newOdlingsatgarderState = stateList
                newUndvikEllerMinimeraState = stateList
            }

            this.itemList = when(category){
                categoryList[0] -> createItemList(catgoryListGrundforbattringar, newGrundforbattringarState)
                categoryList[1] -> createItemList(catgoryListOdlingsatgarder, newOdlingsatgarderState)
                categoryList[2] -> createItemList(catgoryListUndvikEllerMinimera, newUndvikEllerMinimeraState)
                else -> listOf<ChecklistItem>()
            }
        }else{
            this.title = "dummy"
            this.text = "dummy"
            this.itemList = listOf<ChecklistItem>()
        }
    }

    private fun createItemList(listForCategory : List<String>, stateList: List<ChecklistState>): List<ChecklistItem>{
        val listToReturn = mutableListOf<ChecklistItem>()
        for(elem in listForCategory){
            val tmpState = stateList.filter { it.id == elem }
            val item = ChecklistItem(elem, tmpState[0].checked, tmpState[0].active)
            listToReturn.add(item)
        }
        return listToReturn
    }
}