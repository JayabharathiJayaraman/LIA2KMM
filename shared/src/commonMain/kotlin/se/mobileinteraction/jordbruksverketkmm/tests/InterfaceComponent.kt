package se.mobileinteraction.jordbruksverketkmm.tests

enum class ComponentType {
    TITLE,
    BODY,
}

class InterfaceComponent(
    val type: ComponentType,
    val text: String? = null,
) {

}