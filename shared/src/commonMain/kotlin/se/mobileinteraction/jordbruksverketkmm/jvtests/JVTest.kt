package se.mobileinteraction.jordbruksverketkmm.jvtests

import se.mobileinteraction.jordbruksverketkmm.jvtests.formcomponents.InterfaceScreen
import se.mobileinteraction.jordbruksverketkmm.jvtests.jvtestmodels.TestData

interface JVTest {
    val screens: List<InterfaceScreen>
    val type: TestType
    val data: TestData
}

enum class TestType {
    TEST1,
    TEST2,
    TEST3,
}
