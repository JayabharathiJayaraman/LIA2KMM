package se.mobileinteraction.jordbruksverketkmm.forms.forms

import se.mobileinteraction.jordbruksverketkmm.forms.FormViewModel
import se.mobileinteraction.jordbruksverketkmm.forms.components.*
import se.mobileinteraction.jordbruksverketkmm.forms.information.InformationScreens
import se.mobileinteraction.jordbruksverketkmm.forms.models.AnswerWithPhoto
import se.mobileinteraction.jordbruksverketkmm.forms.models.FormData
import se.mobileinteraction.jordbruksverketkmm.forms.models.FormDataSoilStructure
import se.mobileinteraction.jordbruksverketkmm.forms.models.QuestionnaireAnswer
import se.mobileinteraction.jordbruksverketkmm.utilities.DateUtils

data class FormSoilStructure(
    override val type: FormType = FormType.SoilStructure,
    override val data: FormData = FormDataSoilStructure(),
) : Form {
    override val screens: List<FormScreen> = listOf(
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    id = "beskrivningTitleScreen1",
                    text = "Beskrivning"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    id = "beskrivningBodyScreen1",
                    text = "I detta test ska du gräva en grop och undersöka markstrukturen ute i fältet. Det är bäst och lättast att göra det när jorden är fuktig. " +
                            "Testet tar max en timme. Tiden kan variera beriende på jordart och markfukt. Det tar längre tid att gräva testgropen om det är torrt i marken.\n\n" +
                            "Det här testet ger dig en bra bild av markstrukturen på platsen. Änny mer information får du om du även gör infiltrationstestet och Allmänna " +
                            "frågorna om skiftet."
                ),
                FormComponentText(
                    id = "utrustningTitleScreen1",
                    type = ComponentType.TITLESMALL,
                    text = "Utrustning"
                ),
                FormComponentImagesGrid(
                    id = "braGrävspadeImage",
                    type = ComponentType.IMAGESGRID,
                    image = listOf("shovel", "knife", "ruler"),
                    caption = listOf("Bra grävspade", "Morakniv", "Tumstock")
                ),
                FormComponentText(
                    id = "tipsTitleScreen1",
                    type = ComponentType.TITLESMALL,
                    text = "Tips!"
                ),
                FormComponentText(
                    id = "tipsBodyScreen1",
                    type = ComponentType.BODY,
                    text = "Om du även ska göra infiltrationstestet så kan du göra det för översta nivån (markyta/matjord) samtidigt som du gräver din grop för markstrukturtestet."
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
            )
        ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentChecklist(
                    id = ID_PLACEASSESSMENT,
                    type = ComponentType.CHECKLIST,
                    title = "Testets plats",
                    options = listOf("Representativ", "Bra plats", "Dålig plats", "Annan"),
                    rating = (data as? FormDataSoilStructure)?.placeAssesment?.rating ?: -1,
                ),
                FormComponentTextField(
                    id = "placeTextfieldScreen3",
                    type = ComponentType.TEXTFIELD,
                    text = "",
                    placeholder = "Ange annan typ av plats",
                ),
                FormComponentText(
                    id = "choosingPlaceTitleScreen3",
                    type = ComponentType.TITLESMALL,
                    text = "Att välja plats"
                ),
                FormComponentText(
                    id = "choosingPlaceBodyScreen3",
                    type = ComponentType.BODY,
                    text = "Välj i första hand en plats som är representativ för skiftet. " +
                            "Den ger en bild av hur fältet ser ut och fungerar i största allmänhet." +
                            "\n \nFör att lära dig mer om just din jord så kan du gå vidare och " +
                            "utföra testet på fler platser på fältet. Välj då gärna en plats som " +
                            "är bättre än din representativa plats och eventuellt en plats som är " +
                            "sämre. Då kan du jämföra hur markstrukturen i fältet ser ut i " +
                            "förhållande till hur den kan vara som bäst och som sämst.\n\n" +
                            "Bra plats:\n\nBäst struktur är det ofta vid dikeskant, elstolpe, " +
                            "fältkant eller hörn. \n\nDålig plats:\n\nSämst struktur finns " +
                            "vid infart och vändteg. \n\nOm du gör testet på flera platser på " +
                            "skiftet, för att jämföra, så se till att ha så lika förutsättningar " +
                            "som möjligt. Till exempel jordart, markfukt, gröda och tid på året."
                ),
            )
        ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentInformation(
                    type = ComponentType.INFORMATION,
                    id = "firstInfo",
                    components = InformationScreens().screens.first { it.id == "wormScreen1" }.components
                ),
                FormComponentText(
                    id = "locationTitleScreen4",
                    type = ComponentType.TITLESMALL,
                    text = "Plats"
                ),
                FormComponentText(
                    id = "locationBodyScreen4",
                    type = ComponentType.BODY,
                    text = "Tryck på kartan för att välja din exakta position"
                ),
            )
        ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentText(
                    id = "basicConditionsTitleScreen5",
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
                    value = (data as? FormDataSoilStructure)?.soilAssesment?.soilType ?: "",
                    position = -1,
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
                    text = "Om det finns en markkartering så kan du titta på den för att få en " +
                            "uppfattning om vilken jordart som dominerar på skiftet. \n\n" +
                            "En grov bedömning av jordarten kan göras ute i fält, utifrån jordens " +
                            "utseende och formbarhet. För mineraljordar gör du det genom ett " +
                            "utrullningsprov. Se hur du gör det i info-bubblan uppe till höger."
                ),
            )
        ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentText(
                    id = "basicConditionsTitleScreen6",
                    type = ComponentType.TITLESMALL,
                    text = "Grundförutsättningar"
                ),
                FormComponentButtonList(
                    id = ID_CROP,
                    type = ComponentType.BUTTONLIST,
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
                    value = (data as? FormDataSoilStructure)?.soilAssesment?.crop ?: "",
                    position = -1,
                    placeholder = "Välj...",
                ),
                FormComponentButtonList(
                    id = ID_PRECEDINGCROP,
                    type = ComponentType.BUTTONLIST,
                    title = "Förfruktsgröda",
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
                    value = (data as? FormDataSoilStructure)?.soilAssesment?.precedingCrop ?: "",
                    position = -1,
                    placeholder = "Välj...",
                ),
                FormComponentButtonList(
                    id = ID_SOILHANDLING,
                    type = ComponentType.BUTTONLIST,
                    title = "Jordbearbetning",
                    list = listOf("Plöjt", "Reducerad bearbetning", "Direktsådd", "Fräsning"),
                    value = (data as? FormDataSoilStructure)?.soilAssesment?.soilHandling ?: "",
                    position = -1,
                    placeholder = "Välj...",
                ),
            )
        ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentText(
                    id = "digTitleScreen7",
                    type = ComponentType.TITLESMALL,
                    text = "Gräv din grop!"
                ),
                FormComponentText(
                    id = "digBodyScreen7",
                    type = ComponentType.BODY,
                    text = "Gräv en grop som är minst 40cm djup och minst 50cm bred. " +
                            "Undvik körspår och annat som avviker " +
                            "(om det inte är det du vill titta på)\n\n" +
                            "Det kan vara intressant att gräva djupare om du gjort djupa " +
                            "jordbearbetningar eller det finns rötter som går ner långt, " +
                            "men behövs inte för att" +
                            "genomföra testet."
                ),
                FormComponentImage(
                    id = "digImageScreen7",
                    type = ComponentType.IMAGE,
                    caption = "Gräv din grop.",
                    image = "dig"
                ),
            )
        ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentChecklist(
                    id = ID_CROP,
                    type = ComponentType.CHECKLIST,
                    title = "Markförhållanden",
                    options = listOf("Fuktigt", "Torrt", "Blött"),
                    rating = (data as? FormDataSoilStructure)?.soilCondition?.condition ?: -1,
                ),
            )
        ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentText(
                    id = "groundProfileTitleScreen9",
                    type = ComponentType.TITLESMALL,
                    text = "Markprofilen!"
                ),
                FormComponentText(
                    id = "groundProfileBodyScreen9",
                    type = ComponentType.BODY,
                    text = "Gropväggen är din markprofil, som du nu ska undersöka närmare." +
                            " Kan du hitta olika skikt? Marken består oftast av minst två skikt: matjord och alv." +
                            " Vanligen finns även tätare skikt, som plog- eller bearbetningssula." +
                            " Använd kniven och bänd loss jord så framträder olika markskikt tydligare."
                ),
                FormComponentVideo(
                    id = "videoScreen9",
                    type = ComponentType.VIDEO,
                    description = "Markprofil video",
                    source = "markprofilen"
                ),
                FormComponentText(
                    id = "tipsTitleScreen9",
                    type = ComponentType.TITLESMALL,
                    text = "Tips!"
                ),
                FormComponentText(
                    id = "tipsBodyScreen9",
                    type = ComponentType.BODY,
                    text = "Täta skikt går lättare att upptäcka om man sticker in knivbladet i " +
                            "profilväggen strax under markytan och sedan jobbar sig nedåt, med " +
                            "stick ungefär varannan centimeter, och känner var man stöter på ett " +
                            "större motstånd."
                ),
            )
        ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentCaptureImage(
                    id = ID_SOILSTRUCTUREIMAGE,
                    type = ComponentType.CAPTUREIMAGE,
                    title = "Ta kort på profilen rakt framifrån",
                    body = "Försök att fota så lodrätt som möjligt för att göra det enklare att i " +
                            "nästa steg markera de olika skikten",
                    placeholderImage = "markprofil_illustration",
                    imageUri = (data as? FormDataSoilStructure)?.photoData?.photoUri,
                    button_text = "Fota markprofilen"
                )
            )
        ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentText(
                    id = "countShovelTitleScreen11",
                    type = ComponentType.TITLESMALL,
                    text = "Räkna spadtramp"
                ),
                FormComponentText(
                    id = "tryEarthResistanceBodyScreen11",
                    type = ComponentType.BODY,
                    text = "Testa jordmotståndet i marken genom att räkna spadtramp. " +
                            "Börja med matjorden. Placera spaden på markytan. " +
                            "Räkna hur många gånger du behöver stampa på spaden " +
                            "för att få ner den till cirka 10-15 cm djup.\n\n" +
                            "Upprepa gärna testet i bearbetningssula och alv för att jämföra."
                ),

                FormComponentButtonList(
                    type = ComponentType.BUTTONLIST,
                    id = ID_STOMPLEVEL1,
                    title = "Markyta/Markjord",
                    list = listOf("1", "2", "3", "4", "5", "6 eller fler"),
                    value = (data as? FormDataSoilStructure)?.stompData?.level1 ?: "",
                    position = -1,
                    placeholder = "Välj...",
                ),
                FormComponentButtonList(
                    type = ComponentType.BUTTONLIST,
                    id = ID_STOMPLEVEL2,
                    title = "Bearbetningssula",
                    list = listOf("1", "2", "3", "4", "5", "6 eller fler"),
                    value = (data as? FormDataSoilStructure)?.stompData?.level2 ?: "",
                    position = -1,
                    placeholder = "Välj...",
                ),
                /*FormComponentButtonList(
                    type = ComponentType.BUTTONLIST,
                    id = "subSoil2ButtonListScreen11",
                    title = "Bearbetningssula 2",
                    list = listOf("1", "2", "3", "4", "5", "6 eller fler"),
                    value = (data as? FormDataSoilStructure)?.stompData?.level3 ?: "",
                    placeholder = "Välj...",
                ),*/
                FormComponentButtonList(
                    type = ComponentType.BUTTONLIST,
                    id = ID_STOMPLEVEL4,
                    title = "Alv",
                    list = listOf("1", "2", "3", "4", "5", "6 eller fler"),
                    value = (data as? FormDataSoilStructure)?.stompData?.level4 ?: "",
                    position = -1,
                    placeholder = "Välj...",
                ),
            )
        ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentText(
                    id = "structureRemarkTitleScreen12",
                    type = ComponentType.TITLESMALL,
                    text = "Har matjorden bra struktur?"
                ),
                FormComponentQuestionnaire(
                    id = ID_QUESTIONNAIRESTRUCTUREANSWER,
                    type = ComponentType.QUESTIONNAIRE,
                    text = listOf(
                        "Matjorden har kokig, pulvrig, massiv, skiktad eller enkelkornsstruktur. Släta brottytor om man bryter isär jordklump",
                        "Matjorden har skarpkantade aggregat som kan falla sönder i mindre, kompakta aggregat. Brottytor lite ojämna.",
                        "Matjorden har avrundade aggregat som faller sönder i mindre porösa aggregat. Skrovliga brottytor."
                    ),
                    answer = (data as? FormDataSoilStructure)?.questionnaireWithPhotos?.answers?.firstOrNull {
                        it.id == ID_QUESTIONNAIRESTRUCTUREANSWER
                    }?.answer
                ),

                FormComponentText(
                    id = "instructionsTitleScreen12",
                    type = ComponentType.TITLESMALL,
                    text = "Gör så här"
                ),
                FormComponentText(
                    id = "instructionsBodyScreen12",
                    type = ComponentType.BODY,
                    text = "För att bedöma strukturen kan du peta och bryta loss jord med kniven " +
                            "från gropväggen, bryta isär jordklumpar, trycka sönder större " +
                            "aggregat i handen o.s.v. Om jorden har aggregatstruktur, " +
                            "försök avgöra om aggregaten är avrundade och porösa eller " +
                            "skarpkantade och kompakta.\n\nDu kan också ta en jordklump, stor som en" +
                            " knytnäve, bryta den i två delar och titta på brottytorna. Om " +
                            "brottytan är ojämn och skrovlig är strukturen bra.  " +
                            "Om ytan är slät med få ojämnheter är strukturen generellt sämre."
                ),
                FormComponentImage(
                    id = "soilStructureImageScreen12",
                    type = ComponentType.IMAGE,
                    caption = "Från vänster till höger: " +
                            "Dålig struktur med skarpkantade kokor, " +
                            "halvbra struktur, och bra struktur med " +
                            "avrundande aggregat i olika storlekar.",
                    image = "soil_structure"
                ),
                FormComponentText(
                    id = "notesTitleScreen12",
                    type = ComponentType.TITLESMALL,
                    text = "Noteringar"
                ),
                FormComponentTextField(
                    id = ID_COMMENT,
                    type = ComponentType.TEXTFIELDNOTES,
                    text = (data as? FormDataSoilStructure)?.comment ?: "",
                    placeholder = "Skriv dina anteckningar här",
                ),
                FormComponentButton(
                    id = "photoButtonScreen12",
                    type = ComponentType.BUTTON,
                    text = "Ta foto",
                ),
            )
        ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentText(
                    id = "topsoilTitleScreen13",
                    type = ComponentType.TITLESMALL,
                    text = "Har matjorden hög mullhalt?"
                ),
                FormComponentQuestionnaire(
                    id = ID_QUESTIONNAIREHIGHSOIL,
                    type = ComponentType.QUESTIONNAIRE,
                    text = listOf(
                        "Matjorden har låg mullhalt - samma ljusa färg som alven.",
                        "Matjorden är något mörkare än alven.",
                        "Matjorden är mycket mörkare än alven. Eller hela markprofilen har hög mullhalt (mörk färg).",
                    ),
                    answer = (data as? FormDataSoilStructure)?.questionnaireWithPhotos?.answers?.firstOrNull {
                        it.id == ID_QUESTIONNAIREHIGHSOIL
                    }?.answer
                ),

                FormComponentText(
                    id = "instructionsTitleScreen13",
                    type = ComponentType.TITLESMALL,
                    text = "Gör så här"
                ),
                FormComponentText(
                    id = "instructionsBodyScreen13",
                    type = ComponentType.BODY,
                    text = "Bedöm matjordens färg i förhållande till alven. " +
                            "Färgen på jorden är en enkel bedömning av mullhalt. " +
                            "En mer exakt bedömning av mullhalten kan vara svår att göra i fält."
                ),
                FormComponentImage(
                    id = "highSoilContentImageScreen13",
                    type = ComponentType.IMAGE,
                    caption = "Image_mullhalt_bagge",
                    image = "mullhalt_bagge"
                ),
                FormComponentText(
                    id = "notesTitleScreen13",
                    type = ComponentType.TITLESMALL,
                    text = "Noteringar"
                ),
                FormComponentTextField(
                    id = ID_COMMENT,
                    type = ComponentType.TEXTFIELDNOTES,
                    text = (data as? FormDataSoilStructure)?.comment ?: "",
                    placeholder = "Skriv dina anteckningar här",
                ),
                FormComponentButton(
                    id = "photoButtonScreen13",
                    type = ComponentType.BUTTON,
                    text = "Ta foto",
                ),
            )
        ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentText(
                    id = "layerTitleScreen14",
                    type = ComponentType.TITLESMALL,
                    text = "Har jorden täta skikt?"
                ),
                FormComponentQuestionnaire(
                    id = ID_QUESTIONNAIREDENSESOIL,
                    type = ComponentType.QUESTIONNAIRE,
                    text = listOf(
                        "Ett eller flera täta skikt. Tydlig bearbetningssula. Rötterna böjer av.",
                        "Det finns kompakta skikt. Rottillväxt lite hämmad.",
                        "Saknar tydliga täta skikt. Det går att sticka kniven i gropväggen utan större motstånd.",
                    ),
                    answer = (data as? FormDataSoilStructure)?.questionnaireWithPhotos?.answers?.firstOrNull {
                        it.id == ID_QUESTIONNAIREDENSESOIL
                    }?.answer
                ),

                FormComponentText(
                    id = "instructionsTitleScreen14",
                    type = ComponentType.TITLESMALL,
                    text = "Gör så här"
                ),
                FormComponentText(
                    id = "instructionsBodyScreen14",
                    type = ComponentType.BODY,
                    text = "Stick kniven rakt in i gropväggen för att bedöma var det kan finnas ett" +
                            " tätare skikt (bearbetningssula) och hur utvecklad den verkar vara: " +
                            "kraftigt, medel eller svagt. Börja från markytan och jobba dig neråt " +
                            "genom att sticka in kniven ungefär varannan centimeter."
                ),
                FormComponentImage(
                    id = "soilWallImageScreen14",
                    type = ComponentType.IMAGE,
                    caption = "",
                    image = "soil_wall"
                ),
                FormComponentText(
                    id = "notesTitleScreen14",
                    type = ComponentType.TITLESMALL,
                    text = "Noteringar"
                ),
                FormComponentTextField(
                    id = ID_COMMENT,
                    type = ComponentType.TEXTFIELDNOTES,
                    text = (data as? FormDataSoilStructure)?.comment ?: "",
                    placeholder = "Skriv dina anteckningar här",
                ),
                FormComponentButton(
                    id = "photoButtonScreen14",
                    type = ComponentType.BUTTON,
                    text = "Ta foto",
                ),
            )
        ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentText(
                    id = "plantStructureTitleScreen15",
                    type = ComponentType.TITLESMALL,
                    text = "Hur ser växtresterna ut?"
                ),
                FormComponentQuestionnaire(
                    id = ID_QUESTIONNAIREPLANTSTRUCTURE,
                    type = ComponentType.QUESTIONNAIRE,
                    text = listOf(
                        "Växtresterna är dåligt omsatta, jorden luktar unket.",
                        "Växtresterna är varken bra eller dåligt omsatta.",
                        "Växtresterna är väl förmultnade, jorden har en frisk doft.",
                    ),
                    answer = (data as? FormDataSoilStructure)?.questionnaireWithPhotos?.answers?.firstOrNull {
                        it.id == ID_QUESTIONNAIREPLANTSTRUCTURE
                    }?.answer
                ),
                FormComponentText(
                    id = "instructionsTitleScreen15",
                    type = ComponentType.TITLESMALL,
                    text = "Gör så här"
                ),
                FormComponentText(
                    id = "instructionsBodyScreen15",
                    type = ComponentType.BODY,
                    text = "Ta en allmän titt på profilen och lukta på jorden. " +
                            "Beskriv växtresternas utseende och bedöm om jorden " +
                            "luktar unket eller friskt."
                ),
                FormComponentImage(
                    id = "plantStructureImageScreen15",
                    type = ComponentType.IMAGE,
                    caption = "",
                    image = "vaxtrester"
                ),
                FormComponentText(
                    id = "notesTitleScreen15",
                    type = ComponentType.TITLESMALL,
                    text = "Noteringar"
                ),
                FormComponentTextField(
                    id = ID_COMMENT,
                    type = ComponentType.TEXTFIELDNOTES,
                    text = (data as? FormDataSoilStructure)?.comment ?: "",
                    placeholder = "Skriv dina anteckningar här",
                ),
                FormComponentButton(
                    id = "photoButtonScreen15",
                    type = ComponentType.BUTTON,
                    text = "Ta foto",
                ),
            )
        ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentText(
                    id = "goodRootsTitleScreen16",
                    type = ComponentType.TITLESMALL,
                    text = "Har ser växtrötterna ut?"
                ),
                FormComponentQuestionnaire(
                    id = ID_QUESTIONNAIREROOTS,
                    type = ComponentType.QUESTIONNAIRE,
                    text = listOf(
                        "Rötterna finns bara i stora porer och sprickor, böjer ofta av. Gulbruna rötter. Förtjockade rotspetsar.",
                        "En del finrötter, de flesta friska. Rötter mest mellan större aggregat. Böjer av ibland.",
                        "Frodiga, friska, vita rotsystem, jämnt fördelade.",
                    ),
                    answer = (data as? FormDataSoilStructure)?.questionnaireWithPhotos?.answers?.firstOrNull {
                        it.id == ID_QUESTIONNAIREROOTS
                    }?.answer
                ),
                FormComponentText(
                    id = "instructionsTitleScreen16",
                    type = ComponentType.TITLESMALL,
                    text = "Gör så här"
                ),
                FormComponentText(
                    id = "instructionsBodyScreen16",
                    type = ComponentType.BODY,
                    text = "Titta på hela profilen och studera rotsystemets utseende. " +
                            "Kan rötterna breda ut sig i matjorden och ta sig nedåt till djupare" +
                            " skikt? Är rötterna förtjockade eller har knyckar på grund av att" +
                            " de har svårt att ta sig fram i vissa markskikt?"
                ),
                FormComponentText(
                    id = "tipsTitleScreen16",
                    type = ComponentType.TITLESMALL,
                    text = "Tips"
                ),
                FormComponentText(
                    id = "tipsBodyScreen16",
                    type = ComponentType.BODY,
                    text = "Bästa tiden för att studera rotutvecklingen är sensommaren." +
                            " Gör du testet på våren får du titta på det du hittar av" +
                            " fjolårets rötter."
                ),
                FormComponentImage(
                    id = "damagedSoilImageScreen16",
                    type = ComponentType.IMAGE,
                    caption = "",
                    image = "damaged_soil_roots"
                ),
                FormComponentText(
                    id = "notesTitleScreen16",
                    type = ComponentType.TITLESMALL,
                    text = "Noteringar"
                ),
                FormComponentTextField(
                    id = ID_COMMENT,
                    type = ComponentType.TEXTFIELDNOTES,
                    text = (data as? FormDataSoilStructure)?.comment ?: "",
                    placeholder = "Skriv dina anteckningar här",
                ),
                FormComponentButton(
                    id = "photoButtonScreen16",
                    type = ComponentType.BUTTON,
                    text = "Ta foto",
                ),
            )
        ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentText(
                    id = "wormsTitleScreen17",
                    type = ComponentType.TITLESMALL,
                    text = "Finns det daggmaskar?"
                ),
                FormComponentQuestionnaire(
                    id = ID_QUESTIONNAIREWORMS,
                    type = ComponentType.QUESTIONNAIRE,
                    text = listOf(
                        "Varken maskar eller maskgångar finns.",
                        "Enstaka maskar eller maskgångar.",
                        "Flera maskar eller maskgångar.",
                    ),
                    answer = (data as? FormDataSoilStructure)?.questionnaireWithPhotos?.answers?.firstOrNull {
                        it.id == ID_QUESTIONNAIREWORMS
                    }?.answer
                ),
                FormComponentText(
                    id = "instructionsTitleScreen17",
                    type = ComponentType.TITLESMALL,
                    text = "Gör så här",
                ),
                FormComponentText(
                    id = "instructionsBodyScreen17",
                    type = ComponentType.BODY,
                    text = "Ta en spadfull matjord och leta mask. " +
                            "Är det torrt kommer du inte hitta några. " +
                            "Ta då istället en jordklump, stor som en knytnäve ungefär. " +
                            "Bryt isär den och leta maskgångar (porer större än 2 mm)" +
                            " på ena brottytan."
                ),
                FormComponentImage(
                    id = "wormSoilImageScreen17",
                    type = ComponentType.IMAGE,
                    caption = "Jordklump med mask i por",
                    image = "worm_soil",
                ),
                FormComponentImage(
                    id = "wormNewImageScreen17",
                    type = ComponentType.IMAGE,
                    caption = "",
                    image = "daggmaskar_ny",
                ),
                FormComponentText(
                    id = "notesScreen17",
                    type = ComponentType.TITLESMALL,
                    text = "Noteringar",
                ),
                FormComponentTextField(
                    id = ID_COMMENT,
                    type = ComponentType.TEXTFIELDNOTES,
                    text = (data as? FormDataSoilStructure)?.comment ?: "",
                    placeholder = "Skriv dina anteckningar här",
                ),
                FormComponentButton(
                    id = "photoButtonScreen17",
                    type = ComponentType.BUTTON,
                    text = "Ta foto",
                ),
            )
        ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentText(
                    id = "notesTitleScreen18",
                    type = ComponentType.TITLESMALL,
                    text = "Noteringar och kommentarer",
                ),
                FormComponentTextField(
                    id = ID_COMMENT,
                    type = ComponentType.TEXTFIELDNOTES,
                    text = (data as? FormDataSoilStructure)?.comment ?: "",
                    placeholder = "Skriv dina anteckningar här",
                ),
            )
        ),
        FormScreen(
            components = listOf<FormComponent>(
                FormComponentText(
                    id = "resultTitleBigScreen19",
                    type = ComponentType.TITLEBIG,
                    text = "Resultat",
                ),
                FormComponentText(
                    id = "groundProfileAppearanceTitleScreen19",
                    type = ComponentType.TITLESMALL,
                    text = "Hur ser markprofilen ut?",
                ),
                FormComponentQuestionnaireResult(
                    id = ID_QUESTIONNAIRERESULT,
                    type = ComponentType.QUESTIONNAIRERESULT,
                    answers = (data as? FormDataSoilStructure)?.questionnaireWithPhotos?.answers,
                ),
                FormComponentText(
                    id = "dominationgSymbolTitleScreen19",
                    type = ComponentType.TITLESMALL,
                    text = "Vilken symbol dominerar?",
                ),
                FormComponentResultsRemark(
                    id = "structureSadRemarkScreen19",
                    type = ComponentType.RESULTSREMARKSFACE,
                    text = "<4 mm/tim" +
                            "\nOj, här behövs det krafttag för att förbättra markstrukturen!",
                    image = "sad_face",
                    color = "red_round_background"
                ),
                FormComponentResultsRemark(
                    id = "structureIndifferentRemarkScreen19",
                    type = ComponentType.RESULTSREMARKSFACE,
                    text = "4-12 mm/tim" +
                            "\nHär finns det en del att göra åt markstrukturen!",
                    image = "indifferent_face",
                    color = "orange_round_background"
                ),
                FormComponentResultsRemark(
                    id = "structureHappyRemarkScreen19",
                    type = ComponentType.RESULTSREMARKSFACE,
                    text = ">12 mm/tim" +
                            "\nMycket bra markstruktur!Vårda den!",
                    image = "happy_face",
                    color = "green_round_background"
                ),
                FormComponentText(
                    id = "tipsTitleScreen19",
                    type = ComponentType.TITLESMALL,
                    text = "Tips!",
                ),
                FormComponentText(
                    id = "tipsBodyScreen19",
                    type = ComponentType.BODY,
                    text = "Gå till 'Mina Test' och exportera testet som datafil direkt när du" +
                            " är klar! Då har du ditt arbete tryggt sparat även på annan plats." +
                            " Annars finns det bara i appen i din mobil.",
                ),
                FormComponentText(
                    id = "whatNowTitleScreen19",
                    type = ComponentType.TITLESMALL,
                    text = "Vad vill du göra nu?",
                ),
                FormComponentResultsImages(
                    id = "vadNuImagesScreen19",
                    type = ComponentType.RESULTSIMAGES,
                    images = listOf("add_test_icon", "plant_icon", "check"),
                    imagesTextList = listOf("Nytt test", "Vårda", "markstruktur", "klar")
                ),
            )
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
                ID_SOILTYPE -> (this as FormDataSoilStructure).soilAssesment.soilType = text
                ID_COMMENT -> (this as FormDataSoilStructure).comment = text
            }
        }

        (screens[state.currentScreen].components.firstOrNull { it.id == id } as FormComponentTextField).text =
            text

        return state
    }

    override fun setChecklistRating(
        id: String,
        rating: Int,
        state: FormViewModel.State
    ): FormViewModel.State {
        with(state.form.data) {
            when (id) {
                ID_PLACEASSESSMENT -> (this as? FormDataSoilStructure)?.placeAssesment?.rating =
                    rating
                ID_CROP -> (this as? FormDataSoilStructure)?.soilCondition?.condition = rating
            }
        }
        (screens[state.currentScreen].components.firstOrNull {
            it.id == id
        } as FormComponentChecklist).rating = rating

        return state
    }

    override fun setQuestionnaireAnswer(
        id: String,
        answer: QuestionnaireAnswer,
        text: String,
        state: FormViewModel.State
    ): FormViewModel.State {
        with(state.form.data as? FormDataSoilStructure) {
            val existingAnswer = this?.questionnaireWithPhotos?.answers?.firstOrNull { it.id == id }

            if (existingAnswer != null) {
                val newAnswer = existingAnswer.copy(answer = answer)
                val index = this?.questionnaireWithPhotos?.answers?.indexOf(existingAnswer)
                index?.let { this?.questionnaireWithPhotos?.answers?.set(index, newAnswer) }
            } else {
                val newAnswer = AnswerWithPhoto(answer, id, text)
                this?.questionnaireWithPhotos?.answers?.add(newAnswer)
            }
        }

        (screens[state.currentScreen].components.firstOrNull { it.id == id } as FormComponentQuestionnaire).answer =
            answer

        return state
    }

    override fun setButtonlistData(
        id: String,
        selected: String,
        position: Int,
        state: FormViewModel.State
    ): FormViewModel.State {
        with(state.form.data) {
            when (id) {
                ID_SOILTYPE -> (this as? FormDataSoilStructure)?.soilAssesment?.soilType = selected
                ID_CROP -> (this as? FormDataSoilStructure)?.soilAssesment?.crop = selected
                ID_PRECEDINGCROP -> (this as? FormDataSoilStructure)?.soilAssesment?.precedingCrop =
                    selected
                ID_SOILHANDLING -> (this as? FormDataSoilStructure)?.soilAssesment?.soilHandling =
                    selected
                ID_STOMPLEVEL1 -> (this as? FormDataSoilStructure)?.stompData?.level1 = selected
                ID_STOMPLEVEL2 -> (this as? FormDataSoilStructure)?.stompData?.level2 = selected
                ID_STOMPLEVEL4 -> (this as? FormDataSoilStructure)?.stompData?.level4 = selected
            }
        }
        println("Logg active: $position")
        (screens[state.currentScreen].components.firstOrNull { it.id == id } as FormComponentButtonList).position =
            position

        return state
    }

    fun setSoilStructurePhoto(
        id: String,
        state: FormViewModel.State,
        imageUri: String
    ): FormViewModel.State {
        (state.form.data as? FormDataSoilStructure)?.photoData?.photoUri = imageUri
        (screens[state.currentScreen].components.firstOrNull { it.id == id } as FormComponentCaptureImage).imageUri =
            imageUri

        return state

    }

    companion object {
        const val ID_FARMNAME = "FARMNAME"
        const val ID_FARMLAND = "FARMLAND"
        const val ID_DATE = "DATE"
        const val ID_SOILTYPE = "SOILTYPE"
        const val ID_SOILSTRUCTUREIMAGE = "SOILSTRUCTUREIMAGE"
        const val ID_COMMENT = "COMMENT"
        const val ID_PLACEASSESSMENT = "PLACEASSESSMENT"
        const val ID_CROP = "CROP"
        const val ID_PRECEDINGCROP = "PRECEDINGCROP"
        const val ID_SOILHANDLING = "SOILHANDLING"
        const val ID_STOMPLEVEL1 = "STOMPLEVEL1"
        const val ID_STOMPLEVEL2 = "STOMPLEVEL2"
        const val ID_STOMPLEVEL4 = "STOMPLEVEL4"
        const val ID_QUESTIONNAIRESTRUCTUREANSWER = "QUESTIONNAIRESTRUCTURE"
        const val ID_QUESTIONNAIREHIGHSOIL = "QUESTIONNAIREHIGHSOIL"
        const val ID_QUESTIONNAIREDENSESOIL = "QUESTIONNAIREDENSESOIL"
        const val ID_QUESTIONNAIREPLANTSTRUCTURE = "QUESTIONNAIREPLANTSTRUCTURE"
        const val ID_QUESTIONNAIREROOTS = "QUESTIONNAIREROOTS"
        const val ID_QUESTIONNAIREWORMS = "QUESTIONNAIREWORMS"
        const val ID_QUESTIONNAIRERESULT = "QUESTIONNAIRERESULT"
    }
}