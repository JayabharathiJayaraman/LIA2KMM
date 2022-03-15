package se.mobileinteraction.jordbruksverketkmm

import se.mobileinteraction.jordbruksverketkmm.checklists.models.ChecklistState

data class Checklist(val category : String, val stateList: List<ChecklistState>) {

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



    init {
        if(categoryList.contains(category)){
            this.title = "CheckList_" + category + "_title"
            this.text = "CheckList_" + category + "_text"

            this.itemList = when(category){
                categoryList[0] -> createItemList(catgoryListGrundforbattringar, stateList)
                categoryList[1] -> createItemList(catgoryListOdlingsatgarder, stateList)
                categoryList[2] -> createItemList(catgoryListUndvikEllerMinimera, stateList)
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