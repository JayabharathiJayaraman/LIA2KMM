package se.mobileinteraction.jordbruksverketkmm.forms.components

interface FormGenerator {
    fun generateInterface(components: List<FormComponent>): Any
}