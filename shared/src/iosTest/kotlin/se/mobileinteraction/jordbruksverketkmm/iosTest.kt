package se.mobileinteraction.jordbruksverketkmm

import kotlin.test.Test
import kotlin.test.assertTrue

class IosGreetingTest {

    @Test
    fun testExample() {
        assertTrue(MainLabel().greeting().contains("iOS"), "Check iOS is mentioned")
    }
}