package se.mobileinteraction.jordbruksverketkmm.forms.forms

import se.mobileinteraction.jordbruksverketkmm.forms.FormViewModel
import se.mobileinteraction.jordbruksverketkmm.forms.components.*
import se.mobileinteraction.jordbruksverketkmm.forms.models.FormData
import se.mobileinteraction.jordbruksverketkmm.forms.models.FormDataGeneralQuestions
import se.mobileinteraction.jordbruksverketkmm.forms.models.FormDataSoilStructure
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
                FormComponentImage(
                    id = "braGrävspadeImage",
                    type = ComponentType.CAPTIONEDIMAGE,
                    image = "shovel",
                    caption = "Bra grävspade",
                ),
                FormComponentImage(
                    id = "cylinderImage",
                    type = ComponentType.CAPTIONEDIMAGE,
                    image = "knife",
                    caption = "Morakniv",
                ),
                FormComponentImage(
                    id = "vattenImage",
                    type = ComponentType.CAPTIONEDIMAGE,
                    image = "ruler",
                    caption = "Tumstock",
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
                    id = "representativeChecklistScreen3",
                    type = ComponentType.CHECKLIST,
                    title = "Testets plats",
                    options = listOf("Representativ", "Bra plats", "Dålig plats", "Annan"),
                    active = (data as? FormDataSoilStructure)?.placeAssesment?.rating ?: -1,
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
                    id = FormGeneralQuestions.ID_SOILTYPE,
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
                    value = "ett",
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
                    id = "cropChecklistScreen6",
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
                    value = "ett",
                    placeholder = "Välj...",
                ),
                FormComponentButtonList(
                    id = "preFruitChecklistScreen6",
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
                    value = "ett",
                    placeholder = "Välj...",
                ),
                FormComponentButtonList(
                    id = "tillageChecklistScreen6",
                    type = ComponentType.BUTTONLIST,
                    title = "Jordbearbetning",
                    list = listOf("Plöjt", "Reducerad bearbetning", "Direktsådd", "Fräsning"),
                    value = "ett",
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
                    id = "moistChecklisScreen8",
                    type = ComponentType.CHECKLIST,
                    title = "Markförhållanden",
                    options = listOf("Fuktigt", "Torrt", "Blött"),
                    active = (data as? FormDataSoilStructure)?.placeAssesment?.rating ?: -1,
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
                    text = "Gräv en grop som är minst 40cm djup och minst 50cm bred." +
                            " Undvik körspår och annat som avviker" +
                            " (om det inte är det du vill titta på)\n\n" +
                            "Det kan vara intressant att gräva djupare om du gjort djupa" +
                            " jordbearbetningar eller det finns rötter som går ner långt," +
                            " men behövs inte för att genomföra testet."
                ),
                FormComponentVideo(
                    id = "videoScreen9",
                    type = ComponentType.VIDEO,
                    text = "Video..."
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
                FormComponentText(
                    id = "photoTitleScreen10",
                    type = ComponentType.TITLESMALL,
                    text = "Ta kort på profilen rakt framifrån!"
                ),
                FormComponentText(
                    id = "photoBodyScreen10",
                    type = ComponentType.BODY,
                    text = "Försök att fota så lodrätt som möjligt för att göra det" +
                            " enklare att i nästa steg markera de olika skikten."
                ),
                FormComponentImage(
                    id = "groundProfileImageScreen10",
                    type = ComponentType.IMAGE,
                    caption = "Markprofil illustration",
                    image = "markprofil_illustration"
                ),
                FormComponentButton(
                    id = "photoButtonScreen10",
                    type = ComponentType.BUTTON,
                    text = "Fota markprofilen."
                ),
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
                    id = "groundSurfaceButtonListScreen11",
                    title = "Markyta/Markjord",
                    list = listOf("1", "2", "3", "4", "5", "6 eller fler"),
                    value = "ett",
                    placeholder = "Välj...",
                ),
                FormComponentButtonList(
                    type = ComponentType.BUTTONLIST,
                    id = "MachiningsoleButtonListScreen11",
                    title = "Bearbetningssula",
                    list = listOf("1", "2", "3", "4", "5", "6 eller fler"),
                    value = "ett",
                    placeholder = "Välj...",
                ),
                FormComponentButtonList(
                    type = ComponentType.BUTTONLIST,
                    id = "subSoilButtonListScreen11",
                    title = "Alv",
                    list = listOf("1", "2", "3", "4", "5", "6 eller fler"),
                    value = "ett",
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
                FormComponentRemark(
                    id = "structureSadRemarkScreen12",
                    type = ComponentType.REMARK,
                    text = "Matjorden har kokig, pulvrig, massiv, skiktad eller enkelkornsstruktur." +
                            " Släta brottytor om man bryter isär jordklump",
                    image = "sad_face"
                ),
                FormComponentRemark(
                    id = "structureIndifferentRemarkScreen12",
                    type = ComponentType.REMARK,
                    text = "Matjorden har skarpkantade aggregat som kan falla sönder i mindre, " +
                            "kompakta aggregat. Brottytor lite ojämna.",
                    image = "indifferent_face"
                ),
                FormComponentRemark(
                    id = "structureHappyRemarkScreen12",
                    type = ComponentType.REMARK,
                    text = "Matjorden har avrundade aggregat sim faller sömder i mindre, " +
                            "porösa, aggregat. Skrovliga brottytor.",
                    image = "happy_face"
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
                    id = "notesTextfieldScreen12",
                    type = ComponentType.TEXTFIELDNOTES,
                    text = "Notes",
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
                FormComponentRemark(
                    id = "topsoilSadRemarkScreen13",
                    type = ComponentType.REMARK,
                    text = "Matjorden har låg mullhalt - samma ljusa färg som alven.",
                    image = "sad_face"
                ),
                FormComponentRemark(
                    id = "topsoilIndifferentRemarkScreen13",
                    type = ComponentType.REMARK,
                    text = "Matjorden är något mörkare än alven.",
                    image = "indifferent_face"
                ),
                FormComponentRemark(
                    id = "topsoilHappyRemarkScreen13",
                    type = ComponentType.REMARK,
                    text = "Matjorden är mycket mörkare än alven. " +
                            "Eller hela markprofilen har hög mullhalt (mörk färg).",
                    image = "happy_face"
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
                    id = "notesTextFieldScreen13",
                    type = ComponentType.TEXTFIELDNOTES,
                    text = "Notes",
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
                FormComponentRemark(
                    id = "layerSadRemarkScreen14",
                    type = ComponentType.REMARK,
                    text = "Ett eller flera täta skikt. Tydlig bearbetningssula. " +
                            "Rötterna böjer av.",
                    image = "sad_face"
                ),
                FormComponentRemark(
                    id = "layerIndifferentRemarkScreen14",
                    type = ComponentType.REMARK,
                    text = "Det finns kompakta skikt. Rottillväxt lite hämmad.",
                    image = "indifferent_face"
                ),
                FormComponentRemark(
                    id = "layerHappyRemarkScreen14",
                    type = ComponentType.REMARK,
                    text = "Saknar tydliga täta skikt. Det går att sticka kniven i gropväggen " +
                            "utan större motstånd.",
                    image = "happy_face"
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
                    id = "textFieldNotesScreen14",
                    type = ComponentType.TEXTFIELDNOTES,
                    text = "Notes",
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
                FormComponentRemark(
                    id = "plantStructureSadRemarkScreen15",
                    type = ComponentType.REMARK,
                    text = "Växtresterna är dåligt omsatta, jorden luktar unket.",
                    image = "sad_face"
                ),
                FormComponentRemark(
                    id = "plantStructureIndifferentRemarkScreen15",
                    type = ComponentType.REMARK,
                    text = "Växtresterna är varken bra eller dåligt omsatta.",
                    image = "indifferent_face"
                ),
                FormComponentRemark(
                    id = "plantStructureHappyRemarkScreen15",
                    type = ComponentType.REMARK,
                    text = "Växtresterna är väl förmultnade, jorden har en frisk doft.",
                    image = "happy_face"
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
                    id = "textFieldNotesScreen15",
                    type = ComponentType.TEXTFIELDNOTES,
                    text = "Notes",
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
                FormComponentRemark(
                    id = "goodRootsSadRemarkScreen16",
                    type = ComponentType.REMARK,
                    text = "Rötterna finns bara i stora porer och sprickor, böjer ofta av. Gulbruna rötter. Förtjockade rotspetsar.",
                    image = "sad_face"
                ),
                FormComponentRemark(
                    id = "goodRootsIndifferentRemarkScreen16",
                    type = ComponentType.REMARK,
                    text = "En del finrötter, de flesta friska. Rötter mest mellan större aggregat. Böjer av ibland.",
                    image = "indifferent_face"
                ),
                FormComponentRemark(
                    id = "goodRootsHappyRemarkScreen16",
                    type = ComponentType.REMARK,
                    text = "Frodiga, friska, vita rotsystem, jämnt fördelade.",
                    image = "happy_face"
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
                    id = "textFieldNotesScreen16",
                    type = ComponentType.TEXTFIELDNOTES,
                    text = "Notes",
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
                FormComponentRemark(
                    id = "wormsSadRemarkScreen17",
                    type = ComponentType.REMARK,
                    text = "Varken maskar eller maskgångar finns.",
                    image = "sad_face"
                ),
                FormComponentRemark(
                    id = "wormsIndifferentRemarkScreen17",
                    type = ComponentType.REMARK,
                    text = "Enstaka maskar eller maskgångar.",
                    image = "indifferent_face",
                ),
                FormComponentRemark(
                    id = "wormsHappyRemarkScreen17",
                    type = ComponentType.REMARK,
                    text = "Flera maskar eller maskgångar.",
                    image = "happy_face",
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
                    id = "textFieldNotesScreen17",
                    type = ComponentType.TEXTFIELDNOTES,
                    text = "Notes",
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
                    id = "textFieldNotesScreen18",
                    type = ComponentType.TEXTFIELDNOTES,
                    text = "Notes",
                    placeholder = "Skriv dina noteringar och kommentarer om frågorna här.",
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
                    id = "groundProfileTitleScreen19",
                    type = ComponentType.TITLESMALL,
                    text = "Markprofil",
                ),
                FormComponentImage(
                    id = "remarkImageScreen19",
                    type = ComponentType.IMAGE,
                    caption = "Image with remark results",
                    image = "",
                ),
                FormComponentText(
                    id = "groundProfileAppearanceTitleScreen19",
                    type = ComponentType.TITLESMALL,
                    text = "Hur ser markprofilen ut?",
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
                    id = "vadNuImagesScreen10",
                    type = ComponentType.RESULTSIMAGES,
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
                ID_SOILTYPE -> (this as FormDataGeneralQuestions).soilAssesment.soilType = text
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
        (state.form.data as? FormDataSoilStructure)?.placeAssesment?.rating = active
        (screens[state.currentScreen].components.firstOrNull { it.id == id } as FormComponentChecklist).active =
            active
        return state
    }

    companion object {
        const val ID_FARMNAME = "FARMNAME"
        const val ID_FARMLAND = "FARMLAND"
        const val ID_DATE = "DATE"
        const val ID_SOILTYPE = "SOILTYPE"
    }
}

