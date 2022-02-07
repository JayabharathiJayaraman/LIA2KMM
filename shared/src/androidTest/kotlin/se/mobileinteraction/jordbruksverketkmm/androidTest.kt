package se.mobileinteraction.jordbruksverketkmm

import org.junit.Assert.assertTrue
import org.junit.Test

class AndroidGreetingTest {

    @Test
    fun testExample() {
        assertTrue("Check Android is mentioned", MainLabel().greeting().contains("Android"))
    }
}