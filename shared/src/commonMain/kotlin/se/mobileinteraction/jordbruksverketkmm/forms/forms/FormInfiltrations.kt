package se.mobileinteraction.jordbruksverketkmm.forms.forms

import se.mobileinteraction.jordbruksverketkmm.forms.FormViewModel
import se.mobileinteraction.jordbruksverketkmm.forms.components.*
import se.mobileinteraction.jordbruksverketkmm.forms.models.FormData
import se.mobileinteraction.jordbruksverketkmm.forms.models.FormDataGeneralQuestions
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
                    type = ComponentType.TITLESMALL,
                    text = "Beskrivning"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    text = "I detta test mäter du hur vatten infiltrerar i marken. Hur vatten kan röra sig i marken är ett mycket bra mått på strukturen. Testet tar cirka 30 minuter. " +
                            "\n\nMät i första hand i matjorden. Om du vill kan du även mäta i andra skikt, som bearbetningssula och alv. Vägledning för hur du hittar skikten finns i markstrukturtestet."
                ),
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    text = "Utrustning"
                ),
                FormComponentImage(
                    type = ComponentType.CAPTIONEDIMAGE,
                    image = "shovel",
                    caption = "Bra grävspade",
                ),
                FormComponentImage(
                    type = ComponentType.CAPTIONEDIMAGE,
                    image = "cylinder",
                    caption = "Cylinder",
                ),
                FormComponentImage(
                    type = ComponentType.CAPTIONEDIMAGE,
                    image = "waterdrops",
                    caption = "Vatten",
                ),
                FormComponentImage(
                    type = ComponentType.CAPTIONEDIMAGE,
                    image = "litre",
                    caption = "Litermått",
                ),
                FormComponentImage(
                    type = ComponentType.CAPTIONEDIMAGE,
                    image = "ruler",
                    caption = "Tumstock",
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    text = "Räkna med 3 liter vatten för ett test (med en cylinder på cirka 20 cm i diameter). Cylindern tillverkar du enkelt själv. Läs mer under info-knappen."
                ),
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    text = "Tips!"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    text = "Använd brädlapp och gummiklubba för att få ner cylindern om det är hårt i marken."
                ),
            ),
        ),

        FormScreen(
            components = listOf<FormComponent>(
                FormComponentText(
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
                    type = ComponentType.TITLESMALL,
                    text = "Tips!"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    text = "Om du har ett stort skifte med stora olikheter i jordat och brukningsegenskaper så kan du dela upp skiftet. Det kan göra det enklare att svara på frågorna i testen, bedöma markstrukturen och möjliga åtgärder."
                ),
            ),
        ),

        FormScreen(
            components = listOf<FormComponent>(
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    text = "Grundförutsättningar"
                ),
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    text = "Jordat"
                ),
                FormComponentButtonList(
                    type = ComponentType.BUTTONLIST,
                    id = FormGeneralQuestions.ID_SOILTYPE,
                    title = "Jordat",
                    list = listOf("Sand, grovmo", "Finmo, mjäla", "Leriga jordar (5-15%)", "Lättlera (15-25%)", "Mellanlera (25-40%)", "Styv lera (40-60%)", "Mycket styv lera (>60%)",
                        "Moränlera", "Mulljord (torvjord under)", "Mulljord (gyttjejord under)"),
                    value = "ett",
                    placeholder = "Välj...",
                ),
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    text = "Tips!"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    text = "Om det finns en markkartering så kan du titta på den för att få en uppfattning om vilken jordart som dominerar på skiftet."
                ),
            ),
        ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    text = "Testets plats"
                ),
                FormComponentChecklist(
                    type = ComponentType.CHECKLIST,
                    text = "Representativ",
                    image = "src"
                ),
                FormComponentChecklist(
                    type = ComponentType.CHECKLIST,
                    text = "Bra plats",
                    image = "src"
                ),
                FormComponentChecklist(
                    type = ComponentType.CHECKLIST,
                    text = "Dålig plats",
                    image = "src"
                ),
                FormComponentChecklist(
                    type = ComponentType.CHECKLIST,
                    text = "Annan",
                    image = "src"
                ),
                FormComponentTextField(
                    type = ComponentType.TEXTFIELD,
                    id = ID_ALTERNATE,
                    text = "",
                    placeholder = "Ange annan typ av plats",
                ),
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    text = "Att välja plats"
                ),
                FormComponentText(
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
                    type = ComponentType.TITLESMALL,
                    text = "Plats"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    text = "Tryck på kartan för att välja din exakta position"
                ),
            ),
        ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    text = "Grundförutsättningar"
                ),
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    text = "Jordat"
                ),
                FormComponentButtonList(
                    type = ComponentType.BUTTONLIST,
                    id = FormGeneralQuestions.ID_SOILTYPE,
                    title = "Jordat",
                    list = listOf("Sand, grovmo", "Finmo, mjäla", "Leriga jordar (5-15%)", "Lättlera (15-25%)", "Mellanlera (25-40%)", "Styv lera (40-60%)", "Mycket styv lera (>60%)",
                        "Moränlera", "Mulljord (torvjord under)", "Mulljord (gyttjejord under)"),
                    value = "ett",
                    placeholder = "Välj...",
                ),
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    text = "Tips!"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    text = "Om det finns en markkartering så kan du titta på den för att få en uppfattning om vilken jordart som dominerar på skiftet." +
                            "\n\nEn grov bedömning av jordarten kan göras ute i fält, utifrån jordens utseende och formbarhet. For mineraljordar gör du det genom ett utrullningsprov. Se hur du gör det i info-bubblan uppe till höger."
                ),
            ),
        ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    text = "Grundförutsättningar"
                ),
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    text = "Gröda"
                ),
                FormComponentButtonList(
                    type = ComponentType.BUTTONLIST,
                    id = FormGeneralQuestions.ID_SOILTYPE,
                    title = "Gröda",
                    list = listOf("Vårstråsäd", "Höststråsäd", "Våroljeväxter", "Höstoljeväxter", "Vall", "Potatis", "Majs",
                        "Åkerbönor", "Ärter", "Sockerbetor", "Frilandsgrönsaker", "Frukt och Bär", "Övriga grödor",
                        "Mellangröda Fånggröda", "Bevuxen träda", "Stubb", "Ingen gröda - öppen jord"),
                    value = "ett",
                    placeholder = "Välj...",
                ),
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    text = "Förfuktrsgröda"
                ),
                FormComponentButtonList(
                    type = ComponentType.BUTTONLIST,
                    id = FormGeneralQuestions.ID_SOILTYPE,
                    title = "Förfuktrsgröda",
                    list = listOf("Vårstråsäd", "Höststråsäd", "Våroljeväxter", "Höstoljeväxter", "Vall", "Potatis", "Majs",
                        "Åkerbönor", "Ärter", "Sockerbetor", "Frilandsgrönsaker", "Frukt och Bär", "Övriga grödor",
                        "Mellangröda Fånggröda", "Bevuxen träda", "Stubb", "Ingen gröda - öppen jord"),
                    value = "ett",
                    placeholder = "Välj...",
                ),
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    text = "Jordbearbetning"
                ),
                FormComponentButtonList(
                    type = ComponentType.BUTTONLIST,
                    id = FormGeneralQuestions.ID_SOILTYPE,
                    title = "Jordbearbetning",
                    list = listOf("Plöjt", "Reducerad bearbetning", "Direktsådd", "Fräsning"),
                    value = "ett",
                    placeholder = "Välj...",
                ),
            ),
        ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    text = "Infiltrationsmätning"
                ),
                FormComponentImage(
                    type = ComponentType.IMAGE,
                    image = "infiltrationstest_prep",
                    caption = ""
                ),
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    text = "Gör så här"
                ),
                FormComponentText(
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
                    type = ComponentType.TITLESMALL,
                    text = "Infiltrationstest i flera skikt"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    text = "Om du vill göra infiltrationstestet i flera skikt i marken så gör inte nästa test precis under det föregående. Då blir nya testet påverkat av vattnet från det förra testet. Gräv istället till nästa nivå lite bredvid det förra testet och utför ditt nya test där."
                ),
                FormComponentImage(
                    type = ComponentType.IMAGE,
                    image = "test3_levels",
                    caption = ""
                ),
            ),
        ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    text = "Infiltrationsmätning"
                ),
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    text = "På vilket skikt mäter du infiltrationen?"
                ),
                FormComponentButtonList(
                    type = ComponentType.BUTTONLIST,
                    id = FormGeneralQuestions.ID_SOILTYPE,
                    title = "På vilket skikt mäter du infiltrationen?",
                    list = listOf("Markyta/Matjord", "Bearbetningssula 1", "Bearbetningssula 2", "Alv"),
                    value = "ett",
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
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    text = "Tidsåtgång:"
                ),
               FormComponentTime(
                   type = ComponentType.TIMEFIELD,
                   start = "",
                   stopp = ""
               ),
                FormComponentLine(
                    type = ComponentType.EMPTYLINE,
                    text = "-"
                ),
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    text = "Ny mätning +"
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
                    placeholder = "Skriv dina noteringar och kommentarer om frågorna här",
                ),
            ),
        ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentText(
                    type = ComponentType.TITLEBIG,
                    text = "Resultat"
                ),
            ),
        ),
    )

        fun setText(id: String, text: String, state: FormViewModel.State): FormViewModel.State
        {
            with(state.form.data) {
                when (id) {
                    FormGeneralQuestions.ID_FARMNAME -> commonData.farmInformation.farmName = text
                    FormGeneralQuestions.ID_FARMLAND -> commonData.farmInformation.farmLand = text
                    FormGeneralQuestions.ID_SOILTYPE -> (this as FormDataGeneralQuestions).soilAssesment.soilType = text
                }
            }
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