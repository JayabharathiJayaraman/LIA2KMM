package se.mobileinteraction.jordbruksverketkmm.forms.forms

import se.mobileinteraction.jordbruksverketkmm.forms.FormViewModel
import se.mobileinteraction.jordbruksverketkmm.forms.components.*
import se.mobileinteraction.jordbruksverketkmm.forms.models.FormData
import se.mobileinteraction.jordbruksverketkmm.forms.models.FormDataGeneralQuestions
import se.mobileinteraction.jordbruksverketkmm.utilities.DateUtils

data class FormGeneralQuestions(
    override val type: FormType = FormType.GeneralQuestions,
    override val data: FormData = FormDataGeneralQuestions(),
) : Form {
    override var screens: List<FormScreen> = listOf(
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
                    id = "getRecordsButtonScreen2",
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
                    text = "Om du har ett stort skifte med stora olikheter i jordart och brukningsegenskaper så kan du dela upp skiftet. Det kan göra det enklare att svara " +
                            "på frågorna i testen, bedöma markstrukturen och möjliga åtgärder."
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
                    list = listOf(
                        "Sand, grovmo",
                        "Finmo, mjäla",
                        "Leriga jordar (5-15%)",
                        "Lättlera (15-25%)",
                        "Mellanlera (25-40%)",
                        "Styv lera (40-60%)",
                        "Mycket styv lera (>60%)",
                        "Moränlera",
                        "Mulljord (torvjord under)",
                        "Mulljord (gyttjejord under)"
                    ),
                    title = "Jordart",
                    value = (data as? FormDataGeneralQuestions)?.soilAssesment?.soilType ?: "",
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
                    id = "bearbetaJordenTitleScreen4",
                    type = ComponentType.TITLESMALL,
                    text = "Är det lätt att bearbeta jorden?"
                ),
                FormComponentRemark(
                    id = "sadFaceRemarkScreen4",
                    type = ComponentType.REMARK,
                    text = "Svårbearbetad jord som kräver många överfarter." +
                            "Stort dragkraftsbehov",
                    image = "sad_face"
                ),
                FormComponentRemark(
                    id = "indifferentFaceRemarkScreen4",
                    type = ComponentType.REMARK,
                    text = "Jordbearbetningen kräver ibland många överfarter." +
                            "Relativt stort dragkraftsbehov",
                    image = "indifferent_face"
                ),
                FormComponentRemark(
                    id = "happyFaceRemarkScreen4",
                    type = ComponentType.REMARK,
                    text = "Lättbearbetad jord, litet dragkraftsbehov",
                    image = "happy_face"
                ),
            ),
        ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentText(
                    id = "grödansEtableringTitleScreen5",
                    type = ComponentType.TITLESMALL,
                    text = "Är grödans etablering god?"
                ),
                FormComponentRemark(
                    id = "sadFaceRemarkScreen5",
                    type = ComponentType.REMARK,
                    text = "Ojämn uppkomst och luckiga bestånd.",
                    image = "sad_face"
                ),
                FormComponentRemark(
                    id = "indifferentFaceRemarkScreen5",
                    type = ComponentType.REMARK,
                    text = "Något ojämn uppkomst och etablering av grödan.",
                    image = "indifferent_face"
                ),
                FormComponentRemark(
                    id = "happyFaceRemarkScreen5",
                    type = ComponentType.REMARK,
                    text = "Jämn och snabb uppkomst. Jämnhöga bestånd",
                    image = "happy_face"
                ),
            ),

            ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentText(
                    id = "grödanFriskTitleScreen6",
                    type = ComponentType.TITLESMALL,
                    text = "Är grödan frisk och frodig och konkurrerar väl med ogräsen?"
                ),
                FormComponentRemark(
                    id = "sadFaceRemarkScreen6",
                    type = ComponentType.REMARK,
                    text = "Hämmad tillväxt," +
                            "missfärgning, eller stora" +
                            "ogräsproblem.",
                    image = "sad_face"
                ),
                FormComponentRemark(
                    id = "indifferentFaceRemarkScreen6",
                    type = ComponentType.REMARK,
                    text = "Något ojämn tillväxt, lite" +
                            "missfärgning, eller vissa" +
                            "ogräsproblem.",
                    image = "indifferent_face"
                ),
                FormComponentRemark(
                    id = "happyFaceRemarkScreen6",
                    type = ComponentType.REMARK,
                    text = "Frisk och frodig gröda," +
                            "och mycket små" +
                            "ogräsproblem.",
                    image = "happy_face"
                ),
            ),
        ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentText(
                    id = "InfiltrerarTitleScreen7",
                    type = ComponentType.TITLESMALL,
                    text = "Infiltrerar vatten snabbt?"
                ),
                FormComponentRemark(
                    id = "sadFaceRemarkScreen7",
                    type = ComponentType.REMARK,
                    text = "Stående vatten kvar länge," +
                            "efter kraftiga regn eller" +
                            "bevattning.",
                    image = "sad_face"
                ),
                FormComponentRemark(
                    id = "indifferentFaceRemarkScreen7",
                    type = ComponentType.REMARK,
                    text = "Vattnet rinner undan" +
                            "sakta, lite pölar.",
                    image = "indifferent_face"
                ),
                FormComponentRemark(
                    id = "happyFaceRemarkScreen7",
                    type = ComponentType.REMARK,
                    text = "Vanligen inget vatten" + "stående kvar efter" +
                            "kraftiga regn eller" + "bevattning.",
                    image = "happy_face"
                ),
            ),
        ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentText(
                    id = "förekommerTitleScreen8",
                    type = ComponentType.TITLESMALL,
                    text = "Förekommer skorpbildning?"
                ),
                FormComponentRemark(
                    id = "sadFaceRemarkScreen8",
                    type = ComponentType.REMARK,
                    text = "Skorpa bildas ofta, även" +
                            "efter lätta regn",
                    image = "sad_face"
                ),
                FormComponentRemark(
                    id = "indifferentFaceRemarkScreen8",
                    type = ComponentType.REMARK,
                    text = "Skorpa förekommer ibland, särskilt " +
                            "efter kraftigt regn eller bevattning bevattning.",
                    image = "indifferent_face"
                ),
                FormComponentRemark(
                    id = "happyFaceRemarkScreen8",
                    type = ComponentType.REMARK,
                    text = "Skorpa bildas aldrig.",
                    image = "happy_face"
                ),
                FormComponentText(
                    id = "skorpbildningTittleScreen8",
                    type = ComponentType.TITLESMALL,
                    text = "Skorpbildning"
                ),
                FormComponentText(
                    id = "skorpaBodyScreen8",
                    type = ComponentType.BODY,
                    text = "Problem med skorpa är framförallt knutet till vissa mjäla- och lerjordar."
                ),
            ),
        ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentText(
                    id = "skördenivåernaTitleScreen9",
                    type = ComponentType.TITLESMALL,
                    text = "Är skördenivåerna stabila?"
                ),
                FormComponentRemark(
                    id = "sadFaceRemarkScreen9",
                    type = ComponentType.REMARK,
                    text = "Stor skördevariation" + "inom fältet och mellan"
                            + "åren.",
                    image = "sad_face"
                ),
                FormComponentRemark(
                    id = "indifferentFaceRemarkScreen9",
                    type = ComponentType.REMARK,
                    text = "Viss skördevariation inom" +
                            "fält och mellan år.",
                    image = "indifferent_face"
                ),
                FormComponentRemark(
                    id = "happyFaceRemarkScreen9",
                    type = ComponentType.REMARK,
                    text = "Jämna och - för området" +
                            "och jordarten - goda" + "skordar.",
                    image = "happy_face"
                ),
            ),
        ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentText(
                    id = "noteringarTitleScreen10",
                    type = ComponentType.TITLESMALL,
                    text = "Noteringar och kommentarer"
                ),
                FormComponentTextField(
                    id = FormGeneralQuestions.ID_COMMENT,
                    type = ComponentType.TEXTFIELDNOTES,
                    text = (data as? FormDataGeneralQuestions)?.comment ?: "",
                    placeholder = "Skriv dina anteckningar här",
                ),
            ),
        ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentText(
                    id = "resultatTitleScreen11",
                    type = ComponentType.TITLEBIG,
                    text = "Resultat",
                ),
                FormComponentText(
                    id = "hurFungerarTitleScreen11",
                    type = ComponentType.TITLESMALL,
                    text = "Hur fungerar skiftet för min växtodling?"
                ),
                FormComponentText(
                    id = "symbolTitleScreen11",
                    type = ComponentType.TITLESMALL,
                    text = "vilken symbol dominerar?"
                ),
                FormComponentResultsRemark(
                    id = "structureSadRemarkScreen10",
                    type = ComponentType.RESULTSREMARKSFACE,
                    text = "Oj, här behövs det krafttag för att förbättra markstrukturen!",
                    image = "sad_face",
                    color = "red_round_background"
                ),
                FormComponentResultsRemark(
                    id = "structureIndifferentRemarkScreen10",
                    type = ComponentType.RESULTSREMARKSFACE,
                    text = "Här finns det en del att göra åt markstrukturen!",
                    image = "indifferent_face",
                    color = "orange_round_background"
                ),
                FormComponentResultsRemark(
                    id = "structureHappyRemarkScreen10",
                    type = ComponentType.RESULTSREMARKSFACE,
                    text = "Mycket bra markstruktur!Vårda den!",
                    image = "happy_face",
                    color = "green_round_background"
                ),
                FormComponentText(
                    id = "NoteringarTitleScreen11",
                    type = ComponentType.TITLESMALL,
                    text = "Noteringar"
                ),
                FormComponentText(
                    id = "TipsTitleScreen11",
                    type = ComponentType.TITLESMALL,
                    text = "Tips"
                ),
                FormComponentText(
                    id = "exporteraTestetBodyScreen11",
                    type = ComponentType.BODY,
                    text = "Gå till Mina test och exportera testet som datafil direkt när du är klar! Då har du ditt arbete tryggt sparat även på annan plats. Annars finns det bara i appen i din mobil."
                ),
                FormComponentText(
                    id = "vadGöraNuTitleScreen11",
                    type = ComponentType.TITLESMALL,
                    text = "Vad vill du göra nu?"
                ),
                FormComponentResultsImages(
                    id = "vadNuImagesScreen10",
                    type = ComponentType.RESULTSIMAGES,
                    imagesTextList = listOf("Nytt test", "Vårda", "markstruktur", "klar")
                ),
            ),
        ),
    )

    override fun setText(
        id: String,
        text: String,
        state: FormViewModel.State
    ): FormViewModel.State {
        println("logg: FORMDEF $text")
        with(state.form.data) {
            when (id) {
                ID_FARMNAME -> commonData.farmInformation.farmName = text
                ID_FARMLAND -> commonData.farmInformation.farmLand = text
                ID_SOILTYPE -> (this as FormDataGeneralQuestions).soilAssesment.soilType = text
                ID_COMMENT -> (this as FormDataGeneralQuestions).comment = text
            }
        }

        println("ID $id")
        (screens[state.currentScreen].components.firstOrNull { it.id == id } as FormComponentTextField).text =
            text

        return state
    }

    override fun setChecklistRating(
        id: String,
        rating: Int,
        state: FormViewModel.State
    ): FormViewModel.State {
        when (id) {
            FormSoilStructure.ID_PLACEASSESSMENT -> (state.form.data as? FormDataGeneralQuestions)?.placeAssesment?.rating =
                rating
        }

        (screens[state.currentScreen].components.firstOrNull { it.id == id } as FormComponentChecklist).rating =
            rating

        return state
    }

    override fun setButtonlistActive(
        id: String,
        value: String,
        state: FormViewModel.State
    ): FormViewModel.State {
        (state.form.data as? FormDataGeneralQuestions)?.soilAssesment?.crop = value
        (screens[state.currentScreen].components.firstOrNull { it.id == id } as FormComponentButtonList).value =
            value
        return state
    }

    companion object {
        const val ID_FARMNAME = "FARMNAME"
        const val ID_FARMLAND = "FARMLAND"
        const val ID_DATE = "DATE"
        const val ID_SOILTYPE = "SOILTYPE"
        const val ID_COMMENT = "COMMENT"
    }
}

