package se.mobileinteraction.jordbruksverketkmm.forms.components

interface FormGenerator {
    fun addChecklistRemark(text: String, image: String)
    fun addImageWithCaption(text: String, image: String)
    fun addButton(text: String)
    fun addBigTitleLabel(text: String)
    fun addSmallTitleLabel(text: String)
    fun addBodyLabel(text: String)
    fun addTextField(id: String, text: String, placeholder: String)
    fun addTextFieldNotes(id: String, text: String, placeholder: String)
    fun addButtonList(
        id: String,
        title: String,
        list: List<String>,
        value: String,
        placeholder: String
    )

    fun clear()
    fun getInterface(components: List<FormComponent>): Any
}