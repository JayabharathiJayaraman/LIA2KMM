package se.mobileinteraction.jordbruksverketkmm.jvforms

import se.mobileinteraction.jordbruksverketkmm.jvforms.formcomponents.FormScreen
import se.mobileinteraction.jordbruksverketkmm.jvforms.formmodels.FormData

interface Form {
    val screens: List<FormScreen>
    val type: TestType
    val data: FormData
}

enum class TestType {
    TEST1,
    TEST2,
    TEST3,
}
