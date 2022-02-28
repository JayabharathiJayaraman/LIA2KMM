package se.mobileinteraction.jordbruksverketkmm.jvtests.formcomponents

interface InterfaceGenerator {
    fun addBigTitleLabel(text: String)
    fun addSmallTitleLabel(text: String)
    fun addBodyLabel(text: String)
    fun addTextField(id: String, text: String, placeholder: String)
    fun addButtonList(id: String, title: String, list: List<String>, value: String, placeholder: String)

    fun clear()
    fun getInterface(components: List<InterfaceComponent>): Any
}