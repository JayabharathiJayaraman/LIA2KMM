package se.mobileinteraction.jordbruksverketkmm.checklists.models

import se.mobileinteraction.jordbruksverketkmm.forms.models.Common

enum class ChecklistType {
    EDITABLE,
    LIST,
}

data class Checklist(
    val id: String,
    val title: String,
    val description: String,
    val type: ChecklistType,
    //val list: List<ChecklistItem>,
    val commonData: Common,
)