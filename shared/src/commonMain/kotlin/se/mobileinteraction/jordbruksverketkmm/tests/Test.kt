package se.mobileinteraction.jordbruksverketkmm.tests

import se.mobileinteraction.jordbruksverketkmm.tests.`interface`.InterfaceScreen
import se.mobileinteraction.jordbruksverketkmm.tests.testDataModels.TestData

interface Test {
    val screens: List<InterfaceScreen>
    val type: TestType
    var data: TestData

    fun setValue(key: TestValueKey, value: Any)
}

enum class TestType {
    TEST1,
    TEST2,
    TEST3,
}

enum class TestValueKey {
    FARM_NAME,
    FARM_LAND,
    DATE,
}

data class KeyValue(val key: TestValueKey, val value: Any) {
}