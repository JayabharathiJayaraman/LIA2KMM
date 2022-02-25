package se.mobileinteraction.jordbruksverketkmm.tests.`interface`

import se.mobileinteraction.jordbruksverketkmm.tests.TestValueKey

interface InterfaceGenerator {
    fun drawTitleLabel(text: String)
    fun drawBodyLabel(text: String)
    fun drawTextField(text: String, placeholder: String)

    fun clear()
    fun getInterface(): Any
}