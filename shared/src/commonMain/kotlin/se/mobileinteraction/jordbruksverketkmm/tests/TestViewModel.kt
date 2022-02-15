package se.mobileinteraction.jordbruksverketkmm.tests

class TestViewModel(val interfaceGenerator: InterfaceGenerator, val test: Test = Test1()) {
    var currentScreen: Int = 0

    fun showScreen(screenNumber: Int = 0) {
        val components = test.screens[screenNumber].components
        components.forEach {
            when (it.type) {
                ComponentType.TITLE -> interfaceGenerator.drawTitleLabel(text = it.text ?: "")
                ComponentType.BODY -> interfaceGenerator.drawBodyLabel(text = it.text ?: "")
            }
        }
    }
}