package se.mobileinteraction.jordbruksverketkmm.tests

import se.mobileinteraction.jordbruksverketkmm.tests.`interface`.ComponentType
import se.mobileinteraction.jordbruksverketkmm.tests.`interface`.InterfaceComponentText
import se.mobileinteraction.jordbruksverketkmm.tests.`interface`.InterfaceComponentTextAndPlaceholder
import se.mobileinteraction.jordbruksverketkmm.tests.`interface`.InterfaceGenerator

class TestViewModel(val interfaceGenerator: InterfaceGenerator, val test: Test) {
    var currentScreen: Int = 0

    fun drawCurrentScreen() {
        interfaceGenerator.clear()
        val components = test.screens[currentScreen].components
        components.forEach {
            when (it.type) {
                ComponentType.TITLE -> interfaceGenerator.drawTitleLabel(text = (it as InterfaceComponentText).text)
                ComponentType.BODY -> interfaceGenerator.drawBodyLabel(text = (it as InterfaceComponentText).text)
                ComponentType.TEXTFIELD -> interfaceGenerator.drawTextField(
                    text = (it as InterfaceComponentTextAndPlaceholder).text,
                    placeholder = (it as InterfaceComponentTextAndPlaceholder).placeholder)
            }
        }
    }

    fun nextScreen() {
        if (currentScreen < test.screens.size) currentScreen += 1
    }

    fun previousScreen() {
        if (currentScreen > 0) currentScreen -= 1
    }
}