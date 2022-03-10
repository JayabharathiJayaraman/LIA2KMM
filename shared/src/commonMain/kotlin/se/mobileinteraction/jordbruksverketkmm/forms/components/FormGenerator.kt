package se.mobileinteraction.jordbruksverketkmm.forms.components

interface FormGenerator {
    fun generateInterface(components: List<FormComponent>):Any
    fun createInterface(components: List<FormComponent>): Any
    fun updateInterface(components: List<FormComponent>)
}