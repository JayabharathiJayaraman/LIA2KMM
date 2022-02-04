package se.mobileinteraction.jordbruksverketkmm

import kotlin.test.Test
import kotlin.test.assertTrue

class CommonGreetingTest {

    @Test
    fun testExample() {
        assertTrue(MainLabel().greeting().contains("Hello"), "Check 'Hello' is mentioned")
    }
}