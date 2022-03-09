package se.mobileinteraction.jordbruksverketkmm.forms.forms

import se.mobileinteraction.jordbruksverketkmm.forms.FormViewModel
import se.mobileinteraction.jordbruksverketkmm.forms.components.*
import se.mobileinteraction.jordbruksverketkmm.forms.models.FormDataGeneralQuestions
import se.mobileinteraction.jordbruksverketkmm.forms.models.FormData
import se.mobileinteraction.jordbruksverketkmm.utilities.DateUtils

data class FormGeneralQuestions(
    override val type: FormType = FormType.GeneralQuestions,
    override val data: FormData = FormDataGeneralQuestions(),
) : Form {
    override var screens: List<FormScreen> = loadScreens()

    fun loadScreens(): List<FormScreen> {
        return listOf(
            FormScreen(
                components = listOf<FormComponent>(
                    FormComponentText(
                        id = "beskrivningTitleScreen1",
                        type = ComponentType.TITLESMALL,
                        text = "Beskrivning"
                    ),
                    FormComponentText(
                        id = "beskrivningBodyScreen1",
                        type = ComponentType.BODY,
                        text = "Detta test består av allmänna frågor om hur skiftet brukar fungera för din växtodling och om det finns tydliga problem med koppling till markstruktur."
                    ),
                    FormComponentImage(
                        id = "exempelImage",
                        type = ComponentType.IMAGE,
                        image = "plant_icon",
                        caption = "Jordbruksverket",
                    ),
                )
            ),
            FormScreen(
                components = listOf<FormComponent>(
                    FormComponentText(
                        id = "uppgifterTitleScreen2",
                        type = ComponentType.TITLESMALL,
                        text = "Uppgifter om gård och skifte"
                    ),
                    FormComponentTextField(
                        type = ComponentType.TEXTFIELD,
                        id = ID_FARMNAME,
                        text = data.commonData.farmInformation.farmName ?: "",
                        placeholder = "Gårdsnamn",
                    ),
                    FormComponentTextField(
                        type = ComponentType.TEXTFIELD,
                        id = ID_FARMLAND,
                        text = data.commonData.farmInformation.farmLand ?: "",
                        placeholder = "Skifte",
                    ),
                    FormComponentTextField(
                        type = ComponentType.TEXTFIELD,
                        id = ID_DATE,
                        text = DateUtils().instantToString(data.commonData.date),
                        placeholder = "Datum",
                    ),
                    FormComponentButton(
                        type = ComponentType.BUTTON,
                        text = "Hämta uppgifter från annat test",
                    ),
                    FormComponentText(
                        id = "tipsTitleScreen2",
                        type = ComponentType.TITLESMALL,
                        text = "Tips!"
                    ),
                    FormComponentText(
                        id = "tipsBodyScreen2",
                        type = ComponentType.BODY,
                        text = "Om du har ett stort skifte med stora olikheter i jordart och brukningsegenskaper så kan du dela upp skiftet. Det kan göra det enklare att svara på frågorna i testen, bedäma markstrukturen och mökliga årgärder."
                    ),
                ),
            ),
            FormScreen(
                components = listOf<FormComponent>(
                    FormComponentText(
                        id = "grundförutsättningarTitleScreen3",
                        type = ComponentType.TITLESMALL,
                        text = "Grundförutsättningar"
                    ),
                    FormComponentButtonList(
                        type = ComponentType.BUTTONLIST,
                        id = ID_SOILTYPE,
                        title = "Jordart",
                        list = listOf("Sand, grovmo", "Finmo,mjäla", "Leriga jordar (5-15%)", "Lättlera (15-25%)", "Mellanlera (25-40%)", "Styv lera (40-60%)", "Mucket Styv lera (>60%)",
                            "Moränlera", "Mulljord (torvjord under)", "Mulljord (gyttjejord under)"),
                        value = "ett",
                        placeholder = "Välj...",
                    ),
                    FormComponentText(
                        id = "tipsTitleScreen3",
                        type = ComponentType.TITLESMALL,
                        text = "Tips!"
                    ),
                    FormComponentText(
                        id = "tipsBodyScreen3",
                        type = ComponentType.BODY,
                        text = "Om det finns en markkartering så kan du titta på den för att få en uppfattning om vilken jordart som dominerar på skiftet."
                    ),
                ),
            ),
            FormScreen(
                components = listOf<FormComponent>(
                    FormComponentText(
                        type = ComponentType.TITLESMALL,
                        text = "Är det lätt att bearbeta jorden?"
                    ),
                    FormComponentChecklist(
                        type = ComponentType.REMARK,
                        text = "Svårbearbetad jord som kräver många överfarter." +
                                "Stort dragkraftsbehov",
                        image = "sad_face"
                    ),
                    FormComponentChecklist(
                        type = ComponentType.REMARK,
                        text = "Jordbearbetningen kräver ibland många överfarter." +
                                "Relativt stort dragkraftsbehov",
                        image = "indifferent_face"
                    ),
                    FormComponentChecklist(
                        type = ComponentType.REMARK,
                        text = "Lättbearbetad jord, litet dragkraftsbehov",
                        image = "happy_face"
                    ),
                ),
            ),
            FormScreen(
                components = listOf<FormComponent>(
                    FormComponentText(
                        type = ComponentType.TITLESMALL,
                        text = "Är grödans etablering god?"
                    ),
                    FormComponentChecklist(
                        type = ComponentType.REMARK,
                        text = "Ojämn uppkomst och luckiga bestånd.",
                        image = "sad_face"
                    ),
                    FormComponentChecklist(
                        type = ComponentType.REMARK,
                        text = "Något ojämn uppkomst och etablering av grödan.",
                        image = "indifferent_face"
                    ),
                    FormComponentChecklist(
                        type = ComponentType.REMARK,
                        text = "Jämn och snabb uppkomst. Jämnhöga bestånd",
                        image = "happy_face"
                    ),
                ),

                ),
            FormScreen(
                components = listOf<FormComponent>(
                    FormComponentText(
                        type = ComponentType.TITLESMALL,
                        text = "Är grödan frisk och frodig och konkurrerar väl med ogräsen?"
                    ),
                    FormComponentChecklist(
                        type = ComponentType.REMARK,
                        text = "Hämmad tillväxt," +
                                "missfärgning, eller stora"+
                                "ogräsproblem.",
                        image = "sad_face"
                    ),
                    FormComponentChecklist(
                        type = ComponentType.REMARK,
                        text = "Något ojämn tillväxt, lite" +
                                "missfärgning, eller vissa"+
                                "ogräsproblem.",
                        image = "indifferent_face"
                    ),
                    FormComponentChecklist(
                        type = ComponentType.REMARK,
                        text = "Frisk och frodig gröda," +
                                "och mycket små"+
                                "ogräsproblem.",
                        image = "happy_face"
                    ),
                ),
            ),
            FormScreen(
                components = listOf<FormComponent>(
                    FormComponentText(
                        type = ComponentType.TITLESMALL,
                        text = "Infiltrerar vatten snabbt?"
                    ),
                    FormComponentChecklist(
                        type = ComponentType.REMARK,
                        text = "Stående vatten kvar länge," +
                                "efter kraftiga regn eller"+
                                "bevattning.",
                        image = "sad_face"
                    ),
                    FormComponentChecklist(
                        type = ComponentType.REMARK,
                        text = "Vattnet rinner undan" +
                                "sakta, lite pölar.",
                        image = "indifferent_face"
                    ),
                    FormComponentChecklist(
                        type = ComponentType.REMARK,
                        text = "Vanligen inget vatten" + "stående kvar efter"+
                                "kraftiga regn eller" + "bevattning.",
                        image = "happy_face"
                    ),
                ),
            ),
            FormScreen(
                components = listOf<FormComponent>(
                    FormComponentText(
                        type = ComponentType.TITLESMALL,
                        text = "Förekommer skorpbildning?"
                    ),
                    FormComponentChecklist(
                        type = ComponentType.REMARK,
                        text = "Skorpa bildas ofta, även" +
                                "efter lätta regn",
                        image = "sad_face"
                    ),
                    FormComponentChecklist(
                        type = ComponentType.REMARK,
                        text = "Skoråa förekommer" + "ibland, särskilt efter"+
                                "ibland, särskilt efter" +"bevattning.",
                        image = "indifferent_face"
                    ),
                    FormComponentChecklist(
                        type = ComponentType.REMARK,
                        text = "Skorpa bildas aldrig.",
                        image = "happy_face"
                    ),
                    FormComponentText(
                        type = ComponentType.TITLESMALL,
                        text = "Skorpbildning"
                    ),
                    FormComponentText(
                        type = ComponentType.BODY,
                        text = "Problem med skorpa är framförallt knutet till vissa mjäla- och lerjordar."
                    ),
                ),
            ),
            FormScreen(
                components = listOf<FormComponent>(
                    FormComponentText(
                        type = ComponentType.TITLESMALL,
                        text = "Är skördenivåerna stabila?"
                    ),
                    FormComponentChecklist(
                        type = ComponentType.REMARK,
                        text = "Stor skördevariation" + "inom fältet och mellan"
                                + "åren.",
                        image = "sad_face"
                    ),
                    FormComponentChecklist(
                        type = ComponentType.REMARK,
                        text = "Viss skördevariation inom"+
                                "fält och mellan år.",
                        image = "indifferent_face"
                    ),
                    FormComponentChecklist(
                        type = ComponentType.REMARK,
                        text = "Jämna och - för området"+
                                "och jordarten - goda" + "skordar.",
                        image = "happy_face"
                    ),
                ),
            ),
            FormScreen(
                components = listOf<FormComponent>(
                    FormComponentText(
                        type = ComponentType.TITLESMALL,
                        text = "Noteringar och kommentarer"
                    ),
                    FormComponentTextField(
                        type = ComponentType.TEXTFIELDNOTES,
                        id = "id",
                        text = "Notes",
                        placeholder = "Skriv dina noteringar och kommentarer om frågorna här.",
                    ),
                    FormComponentButton(
                        type = ComponentType.BUTTON,
                        text = "Ta foto",
                    ),
                ),
            ),
            FormScreen(
                components = listOf<FormComponent>(
                    FormComponentText(
                        type = ComponentType.TITLEBIG,
                        text = "Resultat",
                    ),
                    FormComponentText(
                        type = ComponentType.TITLESMALL,
                        text = "Hur fungerar skiftet för min växtodling?"
                    ),
                    FormComponentText(
                        type = ComponentType.TITLESMALL,
                        text = "vilken symbol dominerar?"
                    ),
                    FormComponentText(
                        type = ComponentType.BODY,
                        text = "oj, här behövs det krafttag för att förbättra ,markstrukturen!"
                    ),
                    FormComponentText(
                        type = ComponentType.BODY,
                        text = "Här finns det en del att göra åt markstrukturen!"
                    ),
                    FormComponentText(
                        type = ComponentType.BODY,
                        text = "Mycket bra markstruktur! Vårda den!"
                    ),
                    FormComponentText(
                        type = ComponentType.TITLESMALL,
                        text = "Noteringar"
                    ),
                    FormComponentText(
                        type = ComponentType.TITLESMALL,
                        text = "Tips"
                    ),
                    FormComponentText(
                        type = ComponentType.BODY,
                        text = "Gå till Mina test och exportera testet som datafil direkt när du är klar! Då har du ditt arbete tryggt sparat även på annan plats. Annars finns det bara i appen i din mobil."
                    ),
                    FormComponentText(
                        type = ComponentType.TITLESMALL,
                        text = "Vad vill du göra nu?"
                    ),
                    FormComponentImageCaption(
                        type = ComponentType.IMAGECAPTION,
                        text = "Nytt test",
                        image = "add_test_icon"
                    ),
                    FormComponentImageCaption(
                        type = ComponentType.IMAGECAPTION,
                        text = "Vårda markstruktur",
                        image = "plant_icon"
                    ),
                    FormComponentImageCaption(
                        type = ComponentType.IMAGECAPTION,
                        text = "klar",
                        image = "switch_checked"
                    ),
                    FormComponentImageCaption(
                        type = ComponentType.IMAGECAPTION,
                        text = "klar",
                        image = "switch_checked.png"
                    ),
                ),
            ),
        )
    }

    fun setText(id: String, text: String, state: FormViewModel.State): FormViewModel.State {
        println("logg: FORMDEF $text")
        with(state.form.data) {
            when (id) {
                ID_FARMNAME -> commonData.farmInformation.farmName = text
                ID_FARMLAND -> commonData.farmInformation.farmLand = text
                ID_SOILTYPE -> (this as FormDataGeneralQuestions).soilAssesment.soilType = text
            }
        }

        state.form.screens = loadScreens()

        return state
    }

    companion object {
        const val ID_FARMNAME = "FARMNAME"
        const val ID_FARMLAND = "FARMLAND"
        const val ID_DATE = "DATE"
        const val ID_SOILTYPE = "SOILTYPE"
    }
}

