package se.mobileinteraction.jordbruksverketkmm.forms.information

import se.mobileinteraction.jordbruksverketkmm.forms.components.ComponentType
import se.mobileinteraction.jordbruksverketkmm.forms.components.FormComponentText

class InformationScreens {
    val screens: List<InformationScreen> = listOf(
        InformationScreen(
            title = "Daggmaskarna och markstrukturen",
            id = "wormScreen1",
            letter = "D",
            components = listOf(
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    id = "wormsTitle1",
                    text = "Daggmaskarna och markstrukturen"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    id = "wormsBody1",
                    text = "Lorem ipsum, lorem ipsum."
                ),
            )
        ),
        InformationScreen(
            title = "Infiltrationen och markstrukturen",
            id = "InfiltrationScreen2",
            letter = "I",
            components = listOf(
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    id = "infiltrationTitle2",
                    text = "Lorem ipsum title"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    id = "infiltrationBody2",
                    text = "Lorem ipsum body"
                ),
            )
        ),
        InformationScreen(
            title = "Jordartsbestämning i fält",
            id = "soiltypeScreen3",
            letter = "J",
            components = listOf(
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    id = "soiltypeTitleScreen3",
                    text = "Lorem ipsum title"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    id = "soiltypeBodyScreen3",
                    text = "Lorem ipsum body"
                ),
            )
        ),
        InformationScreen(
            title = "Markprofilen",
            id = "groundProfileScreen4",
            letter = "M",
            components = listOf(
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    id = "groundProfileTitle4",
                    text = "Lorem ipsum title"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    id = "groundProfileBody4",
                    text = "Lorem ipsum body"
                ),
            )
        ),
        InformationScreen(
            title = "Markstruktur",
            id = "groundStructureScreen5",
            letter = "",
            components = listOf(
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    id = "groundStructureTitle5",
                    text = "Lorem ipsum title"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    id = "groundStructureBody5",
                    text = "Lorem ipsum body"
                ),
            )
        ),
    )
}