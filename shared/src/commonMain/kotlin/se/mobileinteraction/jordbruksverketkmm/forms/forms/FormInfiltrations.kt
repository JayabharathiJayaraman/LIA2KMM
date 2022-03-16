package se.mobileinteraction.jordbruksverketkmm.forms.forms

import se.mobileinteraction.jordbruksverketkmm.forms.FormViewModel
import se.mobileinteraction.jordbruksverketkmm.forms.components.*
import se.mobileinteraction.jordbruksverketkmm.forms.models.FormData
import se.mobileinteraction.jordbruksverketkmm.forms.models.FormDataInfiltration
import se.mobileinteraction.jordbruksverketkmm.utilities.DateUtils

data class FormInfiltrations(
    override val type: FormType = FormType.Infiltration,
    override val data: FormData = FormDataInfiltration(),
) : Form {
    override val screens: List<FormScreen> = listOf(
        FormScreen
            (
            components = listOf<FormComponent>(
                FormComponentText(
                    id = "beskrivningTitleScreen1",
                    type = ComponentType.TITLESMALL,
                    text = "Beskrivning"
                ),
                FormComponentText(
                    id = "beskrivningBodyScreen1",
                    type = ComponentType.BODY,
                    text = "I detta test mäter du hur vatten infiltrerar i marken. Hur vatten kan röra sig i marken är ett mycket bra mått på strukturen. Testet tar cirka 30 minuter. " +
                            "\n\nMät i första hand i matjorden. Om du vill kan du även mäta i andra skikt, som bearbetningssula och alv. Vägledning för hur du hittar skikten finns i markstrukturtestet."
                ),
                FormComponentText(
                    id = "utrustningTitleScreen1",
                    type = ComponentType.TITLESMALL,
                    text = "Utrustning"
                ),
                FormComponentImage(
                    id = "braGrävspadeImage",
                    type = ComponentType.CAPTIONEDIMAGE,
                    image = "shovel",
                    caption = "Bra grävspade",
                ),
                FormComponentImage(
                    id = "cylinderImage",
                    type = ComponentType.CAPTIONEDIMAGE,
                    image = "cylinder",
                    caption = "Cylinder",
                ),
                FormComponentImage(
                    id = "vattenImage",
                    type = ComponentType.CAPTIONEDIMAGE,
                    image = "waterdrops",
                    caption = "Vatten",
                ),
                FormComponentImage(
                    id = "litermåttImage",
                    type = ComponentType.CAPTIONEDIMAGE,
                    image = "litre",
                    caption = "Litermått",
                ),
                FormComponentImage(
                    id = "tumstockImage",
                    type = ComponentType.CAPTIONEDIMAGE,
                    image = "ruler",
                    caption = "Tumstock",
                ),
                FormComponentText(
                    id = "utrustningBodyScreen1",
                    type = ComponentType.BODY,
                    text = "Räkna med 3 liter vatten för ett test (med en cylinder på cirka 20 cm i diameter). Cylindern tillverkar du enkelt själv. Läs mer under info-knappen."
                ),
                FormComponentText(
                    id = "tipsTitleScreen1",
                    type = ComponentType.TITLESMALL,
                    text = "Tips!"
                ),
                FormComponentText(
                    id = "tipsBodyScreen1",
                    type = ComponentType.BODY,
                    text = "Använd brädlapp och gummiklubba för att få ner cylindern om det är hårt i marken."
                ),
            ),
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
                    id = "hämtaUppgifterButtonTextScreen2",
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
                    text = "Om du har ett stort skifte med stora olikheter i jordat och brukningsegenskaper så kan du dela upp skiftet. Det kan göra det enklare att svara på frågorna i testen, bedöma markstrukturen och möjliga åtgärder."
                ),
            ),
        ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentChecklist(
                    id = "representativeChecklistScreen3",
                    type = ComponentType.CHECKLIST,
                    title = "Testets plats",
                    options = listOf("Representativ", "Bra plats", "Dålig plats", "Annan"),
                    active = (data as? FormDataInfiltration)?.placeAssesment?.rating ?: -1,
                ),
                FormComponentTextField(
                    type = ComponentType.TEXTFIELD,
                    id = ID_ALTERNATE,
                    text = "",
                    placeholder = "Ange annan typ av plats",
                ),
                FormComponentText(
                    id = "väljaPlatsTitleScreen3",
                    type = ComponentType.TITLESMALL,
                    text = "Att välja plats"
                ),
                FormComponentText(
                    id = "väljaPlatsBodyScreen3",
                    type = ComponentType.BODY,
                    text = "Välj i första hand en plats som är representativ för skiftet. Den ger en bild av hur fältet ser ut och fungerar i största allmänhet. " +
                            "\n\nFör att lära dig mer om just din jord så kan du gå vidare och utföra testet på fler platser på fältet. Välj då gärna en plats som är bättre än din representativa plats och eventuellt en plats som är sämre. Då kan du jämföra hur markstrukturen i fältet ser ut i förhållande till hur den kan vara som bäst och som sämst. " +
                            "\n\nBra plats:\n\nBäst struktur är det ofta vid dikeskant, elstolpe, fältkant eller hörn. " +
                            "\n\nDålig plats:\n\nSämst struktur finns vid infart och vändteg. " +
                            "\n\nOm du gör testet på flera platser på skiftet, för att jämföra, så se till att ha så lika förutsättningar som möjligt. Till exempel jordart, markfukt, gröda och tid på året."
                ),
            ),
        ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentText(
                    id = "platsTitleScreen4",
                    type = ComponentType.TITLESMALL,
                    text = "Plats"
                ),
                FormComponentText(
                    id = "platsBodyScreen4",
                    type = ComponentType.BODY,
                    text = "Tryck på kartan för att välja din exakta position"
                ),
            ),
        ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentText(
                    id = "grundförutsättningarTitleScreen5",
                    type = ComponentType.TITLESMALL,
                    text = "Grundförutsättningar"
                ),
                FormComponentButtonList(
                    type = ComponentType.BUTTONLIST,
                    id = ID_SOILTYPE,
                    title = "Jordart",
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
                    value = (data as? FormDataInfiltration)?.soilAssesment?.soilType ?: "",
                    placeholder = "Välj...",
                ),
                FormComponentText(
                    id = "tipsTitleScreen5",
                    type = ComponentType.TITLESMALL,
                    text = "Tips!"
                ),
                FormComponentText(
                    id = "tipsBodyScreen5",
                    type = ComponentType.BODY,
                    text = "Om det finns en markkartering så kan du titta på den för att få en uppfattning om vilken jordart som dominerar på skiftet." +
                            "\n\nEn grov bedömning av jordarten kan göras ute i fält, utifrån jordens utseende och formbarhet. For mineraljordar gör du det genom ett utrullningsprov. Se hur du gör det i info-bubblan uppe till höger."
                ),
            ),
        ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentText(
                    id = "grundförutsättningarTitleScreen6",
                    type = ComponentType.TITLESMALL,
                    text = "Grundförutsättningar"
                ),
                FormComponentButtonList(
                    type = ComponentType.BUTTONLIST,
                    id = ID_SOILTYPE,
                    title = "Gröda",
                    list = listOf(
                        "Vårstråsäd",
                        "Höststråsäd",
                        "Våroljeväxter",
                        "Höstoljeväxter",
                        "Vall",
                        "Potatis",
                        "Majs",
                        "Åkerbönor",
                        "Ärter",
                        "Sockerbetor",
                        "Frilandsgrönsaker",
                        "Frukt och Bär",
                        "Övriga grödor",
                        "Mellangröda Fånggröda",
                        "Bevuxen träda",
                        "Stubb",
                        "Ingen gröda - öppen jord"
                    ),
                    value = (data as? FormDataInfiltration)?.soilAssesment?.crop ?: "",
                    placeholder = "Välj...",
                ),
                FormComponentButtonList(
                    type = ComponentType.BUTTONLIST,
                    id = "CropButtonListScreen6",
                    title = "Förfuktrsgröda",
                    list = listOf(
                        "Vårstråsäd",
                        "Höststråsäd",
                        "Våroljeväxter",
                        "Höstoljeväxter",
                        "Vall",
                        "Potatis",
                        "Majs",
                        "Åkerbönor",
                        "Ärter",
                        "Sockerbetor",
                        "Frilandsgrönsaker",
                        "Frukt och Bär",
                        "Övriga grödor",
                        "Mellangröda Fånggröda",
                        "Bevuxen träda",
                        "Stubb",
                        "Ingen gröda - öppen jord"
                    ),
                    value = (data as? FormDataInfiltration)?.soilAssesment?.precedingCrop ?: "",
                    placeholder = "Välj...",
                ),
                FormComponentButtonList(
                    type = ComponentType.BUTTONLIST,
                    id = "soilBearbetningScreen6",
                    title = "Jordbearbetning",
                    list = listOf("Plöjt", "Reducerad bearbetning", "Direktsådd", "Fräsning"),
                    value = (data as? FormDataInfiltration)?.soilAssesment?.soilHandling ?: "",
                    placeholder = "Välj...",
                ),
            ),
        ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentText(
                    id = "infiltrationsmätningTitleScreen7",
                    type = ComponentType.TITLESMALL,
                    text = "Infiltrationsmätning"
                ),
                FormComponentImage(
                    id = "infiltrationsTestImage",
                    type = ComponentType.IMAGE,
                    image = "infiltrationstest_prep",
                    caption = ""
                ),
                FormComponentText(
                    id = "görSåHärTitleScreen7",
                    type = ComponentType.TITLESMALL,
                    text = "Gör så här"
                ),
                FormComponentText(
                    id = "görSåHärBodyScreen7",
                    type = ComponentType.BODY,
                    text = "1. Tryck ner cylindern 2-3 cm i marken. Undvik stora sprickor." +
                            "\n\n2. Häll försiktigt i vattnet till cirka 10 cm vattenhöjd. Häll mot handen så minskar risken att vattnet slammar igen porer. Titta så det inte läcker längs cylinderns nedre kant." +
                            "\n\n3. Mät avståndet (mm) mellan vattenytan och cylinderns övre kant.  Gör en markering på kanten där du mäter. Avståndet kan variera runt cylindern om den lutar lite, så du måste veta var du mätte först när du ska mäta igen.  Starta tidtagningen, använd timern i din telefon. Mät i cirka 30 minuter." +
                            "\n\n4. Efter cirka 30 minuter, stoppa timern. Mät avståndet (mm) igen mellan vattenytan och cylinderns övre kant (vid markeringen). " +
                            "\n\nOBS! Infiltrationen kan ibland gå mycket fort. Stoppa då timern direkt när allt vatten infiltrerat!" +
                            "\n\nOm infiltrationen går extremt snabbt kan du ha hamnat över en stor spricka. Flytta då cylindern och gör om testet. " +
                            "\n\nOm infiltrationen går mycket långsamt kan du mäta längre än 30 minuter om du vill."
                ),
                FormComponentText(
                    id = "infiltrationsTestTitleScreen7",
                    type = ComponentType.TITLESMALL,
                    text = "Infiltrationstest i flera skikt"
                ),
                FormComponentText(
                    id = "infiltrationsTestBodyScreen7",
                    type = ComponentType.BODY,
                    text = "Om du vill göra infiltrationstestet i flera skikt i marken så gör inte nästa test precis under det föregående. Då blir nya testet påverkat av vattnet från det förra testet. Gräv istället till nästa nivå lite bredvid det förra testet och utför ditt nya test där."
                ),
                FormComponentImage(
                    id = "test3LevelsImage",
                    type = ComponentType.IMAGE,
                    image = "test3_levels",
                    caption = ""
                ),
            ),
        ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentText(
                    id = "infiltrationstestTitleScreen8",
                    type = ComponentType.TITLESMALL,
                    text = "Infiltrationstest"
                ),
                FormComponentButtonList(
                    type = ComponentType.BUTTONLIST,
                    id = ID_SOILTYPE,
                    title = "På vilket skikt mäter du infiltrationen?",
                    list = listOf(
                        "Markyta/Matjord",
                        "Bearbetningssula 1",
                        "Bearbetningssula 2",
                        "Alv"
                    ),
                    value = (data as FormDataInfiltration).infiltrationTest.measurementType ?: "",
                    placeholder = "Välj...",
                ),
                FormComponentTextField(
                    type = ComponentType.TEXTFIELD,
                    id = ID_VATTENYTAN_START,
                    text = "",
                    placeholder = "mm till vattenytan (start)",
                ),
                FormComponentTextField(
                    type = ComponentType.TEXTFIELD,
                    id = ID_VATTENYTAN_STOPP,
                    text = "",
                    placeholder = "mm till vattenytan (stopp)",
                ),
                FormComponentTime(
                    id = "timeScreen8",
                    type = ComponentType.TIMEFIELD,
                    timeLabel = "Tidsåtgång:",
                    start = "",
                    stop = ""
                ),
                FormComponentLine(
                    id = "dividerScreen8",
                    type = ComponentType.EMPTYLINE,
                    text = "-"
                ),
                FormComponentText(
                    id = "nymätningTitleScreen8",
                    type = ComponentType.TITLESMALL,
                    text = "Ny mätning +"
                ),
            ),
        ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentText(
                    id = "noteringarTitleScreen9",
                    type = ComponentType.TITLESMALL,
                    text = "Noteringar och kommentarer"
                ),
                FormComponentTextField(
                    type = ComponentType.TEXTFIELDNOTES,
                    id = "id",
                    text = "Notes",
                    placeholder = "Skriv dina noteringar och kommentarer om frågorna här",
                ),
            ),
        ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentText(
                    id = "resultatTitleScreen10",
                    type = ComponentType.TITLEBIG,
                    text = "Resultat"
                ),
                FormComponentResultsInfoBody(
                    id = "resultatInfoBodyScreen10",
                    type = ComponentType.RESULTSINFOBODY,
                    text = "Genomsläppligheten för vatten bör överstiga 4 mm per timme för att du ska få en god effekt av din dränering. Intensiteten i ett sommarregn är ofta ca 3 mm per timme."
                ),
                FormComponentText(
                    id = "vadSymbolenTitleScreen10",
                    type = ComponentType.TITLESMALL,
                    text = "Vad betyder symbolen?"
                ),
                FormComponentResultsRemark(
                    id = "structureSadRemarkScreen10",
                    type = ComponentType.RESULTSREMARKSFACE,
                    text = "<4 mm/tim" +
                            "\nOj, här behövs det krafttag för att förbättra markstrukturen!",
                    image = "sad_face",
                    color = "red_round_background"
                ),
                FormComponentResultsRemark(
                    id = "structureIndifferentRemarkScreen10",
                    type = ComponentType.RESULTSREMARKSFACE,
                    text = "4-12 mm/tim" +
                            "\nHär finns det en del att göra åt markstrukturen!",
                    image = "indifferent_face",
                    color = "orange_round_background"
                ),
                FormComponentResultsRemark(
                    id = "structureHappyRemarkScreen10",
                    type = ComponentType.RESULTSREMARKSFACE,
                    text = ">12 mm/tim" +
                            "\nMycket bra markstruktur!Vårda den!",
                    image = "happy_face",
                    color = "green_round_background"
                ),
                FormComponentText(
                    id = "tipsTitleScreen10",
                    type = ComponentType.TITLESMALL,
                    text = "Tips!"
                ),
                FormComponentText(
                    id = "tipsBodyScreen10",
                    type = ComponentType.BODY,
                    text = "Gå till 'Mina test' och exportera testet som datafil direkt när du är klar!Då har du ditt arbete tryggt sparat även på annan plats. Annars finns det bara i appen i din mobil."
                ),
                FormComponentText(
                    id = "vadNuTitleScreen10",
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
                ID_SOILTYPE -> (this as FormDataInfiltration).soilAssesment.soilType = text
            }
        }

        (screens[state.currentScreen].components.firstOrNull { it.id == id } as FormComponentTextField).text =
            text

        return state
    }

    override fun setChecklistActive(
        id: String,
        active: Int,
        state: FormViewModel.State
    ): FormViewModel.State {
        (state.form.data as? FormDataInfiltration)?.placeAssesment?.rating = active
        (screens[state.currentScreen].components.firstOrNull { it.id == id } as FormComponentChecklist).active =
            active
        return state
    }

    override fun setButtonlistActive(
        id: String,
        value: String,
        state: FormViewModel.State
    ): FormViewModel.State {
        (state.form.data as? FormDataInfiltration)?.soilAssesment?.crop = value
        (screens[state.currentScreen].components.firstOrNull { it.id == id } as FormComponentButtonList).value =
            value
        return state
    }

    companion object {
        const val ID_FARMNAME = "FARMNAME"
        const val ID_FARMLAND = "FARMLAND"
        const val ID_DATE = "DATE"
        const val ID_SOILTYPE = "SOILTYPE"
        const val ID_ALTERNATE = "ALTERNATE_REMARK"
        const val ID_VATTENYTAN_START = "VATTENYTAN_START"
        const val ID_VATTENYTAN_STOPP = "VATTENYTAN_STOPP"
    }
}