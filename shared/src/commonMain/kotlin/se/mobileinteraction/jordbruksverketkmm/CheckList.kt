package se.mobileinteraction.jordbruksverketkmm

class CheckList(category : String) {

    val title : String
    val text : String
    val itemList: List<CheckListItem>

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
                else -> listOf<CheckListItem>()
            }
        }else{
            this.title = "dummy"
            this.text = "dummy"
            this.itemList = listOf<CheckListItem>()
        }
    }

    private fun createItemList(listForCategory : List<String>): List<CheckListItem>{
        val listToReturn = mutableListOf<CheckListItem>()
        for(elem in listForCategory){
            val item = CheckListItem(elem, true, true)
            listToReturn.add(item)
        }
        return listToReturn
    }
}