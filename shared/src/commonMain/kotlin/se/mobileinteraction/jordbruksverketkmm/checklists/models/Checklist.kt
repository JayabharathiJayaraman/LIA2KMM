package se.mobileinteraction.jordbruksverketkmm

data class Checklist(val category : String) {

    val title : String
    val text : String
    val id : String = category
    val itemList: List<ChecklistItem>

    private val categoryList = listOf("Grundförbättringar",
        "Odlingsåtgärder", "UndvikEllerMinimera")

    private val catgoryListGrundförbättringar = listOf("Huvudavvattning",
        "OrganiskaMaterial", "Detaljdränering", "Strukturkalkning", "Alvluckring")

    private val catgoryListOdlingsåtgärder = listOf("GynnaDaggmaskarna",
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
                categoryList[0] -> createItemList(catgoryListGrundförbättringar)
                categoryList[1] -> createItemList(catgoryListOdlingsåtgärder)
                categoryList[2] -> createItemList(catgoryListUndvikEllerMinimera)
                else -> listOf<ChecklistItem>()
            }
        }else{
            this.title = "dummy"
            this.text = "dummy"
            this.itemList = listOf<ChecklistItem>()
        }
    }

    private fun createItemList(listForCategory : List<String>): List<ChecklistItem>{
        val listToReturn = mutableListOf<ChecklistItem>()
        for(elem in listForCategory){
            val item = ChecklistItem(elem, true, true)
            listToReturn.add(item)
        }
        return listToReturn
    }
}