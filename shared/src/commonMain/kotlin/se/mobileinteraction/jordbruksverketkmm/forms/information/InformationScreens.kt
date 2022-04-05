package se.mobileinteraction.jordbruksverketkmm.forms.information

import se.mobileinteraction.jordbruksverketkmm.forms.components.ComponentType
import se.mobileinteraction.jordbruksverketkmm.forms.components.FormComponentImage
import se.mobileinteraction.jordbruksverketkmm.forms.components.FormComponentText

class InformationScreens {
    val screens: List<InformationScreen> = listOf(
        InformationScreen(
            title = "Daggmaskarna och markstrukturen",
            id = "wormScreen1",
            components = listOf(
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    id = "wormsTitle1",
                    text = "Daggmaskarna och markstrukturen"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    id = "wormsBody1",
                    text = "Många maskar tyder på stor biologisk aktivitet i marken och är " +
                            "positivt för markstrukturen. Maskarnas framfart skapar gångar för " +
                            "andra markdjur och de stora porerna är viktiga för infiltrationen " +
                            "av vatten och luftutbytet i marken. Maskarna finfördelar växtmaterial " +
                            "och för det djupare ner i marken. Många av de större porerna i " +
                            "marken är maskhål. Rötterna följer gärna maskgångarna nedåt i " +
                            "marken."
                ),
            )
        ),
        InformationScreen(
            title = "Infiltrationen och markstrukturen",
            id = "InfiltrationScreen2",
            components = listOf(
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    id = "infiltrationTitle2",
                    text = "Infiltrationen och markstrukturen"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    id = "infiltrationBody2",
                    text = "Markens vattenledande förmåga är ett bra mått på markens " +
                            "strukturtillstånd och dränerbarhet. Genomsläppligheten för " +
                            "vatten bör överstiga 4 mm per timme för att du ska få en god " +
                            "effekt av din dränering. Intensiteten i ett sommarregn är ofta " +
                            "ca 3 mm per timme.\n\nGenomsläppligheten i åkerjord varierar " +
                            "inom vida gränser beroende på jordart och markens brukande. Från " +
                            "mycket låg på en tät lera till hög på en sandjord eller gyttjelera." +
                            " Jordbearbetningen kan verka luckrande men också förtätande genom " +
                            "tung maskindrift."
                ),
            )
        ),
        InformationScreen(
            title = "Jordartsbestämning i fält",
            id = "conditionsScreen3",
            components = listOf(
                FormComponentText(
                    id = "conditionsTitle",
                    type = ComponentType.TITLESMALL,
                    text = "Jordartsbedömning i fält"
                ),
                FormComponentText(
                    id = "conditionsBody",
                    type = ComponentType.BODY,
                    text = "Jordarten i mineraljordar kan grovt bedömas genom ett enkelt " +
                            "utrullningsprov: \n\nFukta lite jord och knåda den i handen " +
                            "tills den slutar klibba. Rulla sedan den snabbt och med " +
                            "jämnt tryck mot ett plant underlag eller i handen till en " +
                            "så tunn tråd som möjligt. Trådens tjocklek när den börjar " +
                            "brista indikerar jordarten (se tabell nedan). \n\n" +
                            "För organogena jordar såsom torvjordar och gyttjejordar " +
                            "bestäms jordart efter egenskaper som klibbighet, brottyta " +
                            "o.s.v.\nVägledning finns på sidan 28 i rapporten: " +
                            "https://pub.epsilon.slu.se/3375/1/Rapport8.pdf\n"
                ),
                FormComponentImage(
                    id = "tableImage",
                    type = ComponentType.IMAGE,
                    image = "jordartsbedomning_table",
                    caption = ""
                ),
                FormComponentImage(
                    id = "fingersImage",
                    type = ComponentType.IMAGE,
                    image = "jordartsbedomning_fingers",
                    caption = ""
                ),
            )
        ),
        InformationScreen(
            title = "Markprofilen",
            id = "groundProfileScreen4",
            components = listOf(
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    id = "groundProfileTitle4",
                    text = "Markprofilen"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    id = "groundProfileBody4",
                    text = "Markprofilen är ett tvärsnitt (i djupled) av marken. Dess utseende, " +
                            "med matjord, eventuella täta skikt (till exempel bearbetningssula) " +
                            "och alv, varierar beroende på markens grundförutsättningar " +
                            "(såsom jordart) och odlingssystemets utformning. \n\nGenom att " +
                            "undersöka markprofilen kan man få en överblick över markens " +
                            "aktuella förutsättningar som växtplats. Nedan visas exempel " +
                            "på några olika odlingsjordar med typiska egenskaper."
                ),
                FormComponentImage(
                    id = "infoImage1Screen4",
                    type = ComponentType.IMAGE,
                    image = "markprofil_info_1_sandjord",
                    caption = ""
                ),
                FormComponentImage(
                    id = "infoImage2Screen4",
                    type = ComponentType.IMAGE,
                    image = "markprofil_info_2_lerjord",
                    caption = ""
                ),
                FormComponentImage(
                    id = "infoImage3Screen4",
                    type = ComponentType.IMAGE,
                    image = "markprofil_info_3_mjallera",
                    caption = ""
                ),
                FormComponentImage(
                    id = "infoImage4Screen4",
                    type = ComponentType.IMAGE,
                    image = "markprofil_info_4_mulljord",
                    caption = ""
                ),
            )
        ),
        InformationScreen(
            title = "Markstruktur",
            id = "groundStructureScreen5",
            components = listOf(
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    id = "groundStructureTitle5",
                    text = "Markstruktur"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    id = "groundStructureBody5",
                    text = "Markstrukturen är uppbyggd av enskilda partiklar (t.ex. sand), " +
                            "aggregat (sammansatt av flera partiklar), mull och döda växtrester." +
                            " Man skiljer på: \n\n• Enkelkornstruktur: Jordens partiklar som " +
                            "t.ex. sand, mo, mjäla… sitter inte ihop i aggregat. \n\n" +
                            "• Aggregatstruktur: Flera partiklar håller ihop och bildar " +
                            "aggregat. \n\n• Massiv struktur: Hela jorden hänger samman utan " +
                            "sprickor. \n\n• Kokig struktur: Jorden har ältats och packats " +
                            "samman till stora kokor. \n\nGenom att beskriva strukturen i " +
                            "matjorden får man en första bild av markens förutsättningar som " +
                            "växtplats."
                ),
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    id = "goodStructureTitle5",
                    text = "Vad är bra struktur"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    id = "goodStructureBody5",
                    text = "Aggregaten i en jord har olika storlek, form och stabilitet. " +
                            "Mindre, porösa aggregat är oftast bra, men de ska också tåla " +
                            "regn och belastning. På sandjordar faller porösa aggregat lätt " +
                            "isär till enkelkorn. Där kan lite större och mer kompakta aggregat " +
                            "vara stabilare och mer fördelaktiga. På gyttjejordar bildas stabila" +
                            " skarpkantade pelare naturligt i alven och ger god dränering. " +
                            "På alla jordar är skorpbildning dåligt.\n"
                ),
            )
        ),
        InformationScreen(
            title = "Markstruktur - Film",
            id = "groundStructureMovieScreen5",
            components = listOf(
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    id = "groundStructureMovieTitle5",
                    text = "Markstruktur - Film"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    id = "groundStructureMovieBody5",
                    text = "Filmen \"vårda markens struktur\" är gjord i projektet Goodla " +
                            "och är 5:30 lång."
                ),
                /* TODO CREATE LINK COMPONENT
                   TODO https://www.slu.se/institutioner/mark-miljo/samverkan/goodla/filmer/varda-markens-struktur */
            )
        ),
        InformationScreen(
            title = "Markstruktur - Åtgärdstips",
            id = "groundStructureTipsScreen5",
            components = listOf(
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    id = "groundStructureTipsTitle5",
                    text = "Markstruktur - Åtgärdstips"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    id = "groundStructureTipsBody5",
                    text = "På Greppa Näringens hemsida kan du, bland annat, läsa om " +
                            "åtgärder för att vårda markstrukturen och undvika att förstöra den. " +
                            "Här finns till exempel broschyrer med praktiska råd om dränering, " +
                            "strukturkalkning, bördighet och att undvika markpackning. \n\n" +
                            "Greppa Näringen har även kurser och enskild rådgivning för " +
                            "lantbrukare kostnadsfritt. Projektet är finansierat av EUs " +
                            "landsbygdsprogram.\n"
                ),
                /* TODO CREATE LINK COMPONENT
                   TODO https://www.greppa.nu/ */
            )
        ),
        InformationScreen(
            title = "Markstruktur - Rapport SLU",
            id = "groundStructureReportScreen5",
            components = listOf(
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    id = "groundStructureReportTitle5",
                    text = "Markstruktur - Rapport SLU"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    id = "groundStructureReportBody5",
                    text = "Denna app är utvecklad utifrån SLUs rapport " +
                            "\"Markstrukturtest i fält\". Här kan du bland annat läsa mer" +
                            " om testerna som ingår i appen.\n"
                ),
                /* TODO CREATE LINK COMPONENT
                   TODO https://pub.epsilon.slu.se/3375/1/Rapport8.pdf */
            )
        ),
        InformationScreen(
            title = "Mullhalten och markstrukturen",
            id = "topSoilStructureScreen5",
            components = listOf(
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    id = "topSoilStructureTitle5",
                    text = "Mullhalten och markstrukturen"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    id = "topSoilStructureBody5",
                    text = "Mullhalten har stor betydelse för markstrukturen. Bland annat ökar " +
                            "stabiliteten hos strukturen och aggregaten i jorden med stigande " +
                            "ler- och mullhalt. \n\nMan kan bedöma mullhalten efter jordens " +
                            "färg; ju mörkare jord desto högre mullhalt. En finare jord " +
                            "(t.ex. lera) ser dock alltid mörkare ut än en grövre (t.ex. sand)" +
                            " vid samma mullhalt. Man kan även få ledtrådar i topografin, till " +
                            "exempel är mullhalten ofta högre i svackor. Vanligtvis har matjorden" +
                            " tydligt högre mullinnehåll än alven. Även markfukt ger jorden en " +
                            "mörkare nyans.\n"
                ),
            )
        ),

        InformationScreen(
            title = "Spadtrampen och markstrukturen",
            id = "shovelStructureScreen5",
            components = listOf(
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    id = "shovelStructureTitle5",
                    text = "Spadtrampen och markstrukturen"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    id = "shovelStructureBody5",
                    text = "Att räkna spadtramp är ett sätt att testa jordmotståndet i marken. " +
                            "Ett tramp tyder på lucker struktur och sex tramp mycket tät. \n\n" +
                            "Det bör vara fuktigt i marken för att det ska bli rättvisande. " +
                            "Är marken mycket torr vid tillfället kan det vara stenhårt att " +
                            "gräva även i jordar med bra struktur. \n\nGenom att upprepa " +
                            "testet på fler nivåer kan man få en bild av hur motstånden varierar" +
                            " i markprofilen. För att resultaten ska vara jämförbara måste det " +
                            "vara samma person som stampar, med samma spade och till samma djup.\n"
                ),
            )
        ),
        InformationScreen(
            title = "Täta skikt och markstrukturen",
            id = "layerStructureScreen5",
            components = listOf(
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    id = "layerStructureTitle5",
                    text = "Täta skikt och markstrukturen"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    id = "layerStructureBody5",
                    text = "Det är viktigt att lägga märke till tätare skikt i marken, " +
                            "till exempel såbäddsbotten eller bearbetningssula, och hur " +
                            "svårt det är för rötterna att tränga igenom dessa. Om ett tätare " +
                            "skikt stoppar rötternas framfart spelar det ingen roll hur bra " +
                            "marken är under. Ett annat problem med täta skikt är att vatten " +
                            "inte kan rinna undan och rötterna kan få syrebrist.\n"
                ),
            )
        ),
        InformationScreen(
            title = "Växtresterna och markstrukturen",
            id = "plantStructureScreen5",
            components = listOf(
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    id = "plantStructureTitle5",
                    text = "Växtresterna och markstrukturen"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    id = "plantStructureBody5",
                    text = "Om markstrukturen är bra med god luftväxling så finns det växtrester " +
                            "i alla nedbrytningsstadier och de har en frisk doft. Dåligt omsatta " +
                            "växtrester är en indikation på att marken kan vara vattensjuk på " +
                            "grund av dålig dränering, eller har täta skikt.\n"
                ),
            )
        ),
        InformationScreen(
            title = "Växtrötterna och markstrukturen",
            id = "rootStructureScreen5",
            components = listOf(
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    id = "rootStructureTitle5",
                    text = "Växtrötterna och markstrukturen"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    id = "rootStructureBody5",
                    text = "Väl utvecklat, regelbundet rotsystem är ofta förenat med god struktur. " +
                            "I en jord med dålig struktur har grödan måttligt eller svagt utvecklat" +
                            " rotsystem, där rötterna främst växer i sprickor och maskgångar. " +
                            "Lokala förgreningar tyder på täta skikt och markpackning. Ju finare " +
                            "rotsystemet är och ju djupare det går, desto mer kan växten prestera " +
                            "och desto större blir skörden.\n"
                ),
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    id = "differentRootStructureTitle5",
                    text = "Olika slags rotsystem"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    id = "differentRootStructureBody5",
                    text = "Olika grödor har olika typer av rotsystem. Huvudsakligen särskiljs två" +
                            " typer: Enhjärtbladiga växter (gräs, spannmål) som har två rotsystem, " +
                            "djupgående frörötter och ytliga kronrötter, och tvåhjärtbladiga " +
                            "(oljeväxter, betor) som har en kraftig djupgående pålrot.\n"
                ),
                FormComponentImage(
                    id = "grassRootStructureImage5",
                    type = ComponentType.IMAGE,
                    image = "grassroots",
                    caption = "Gräsrötter"
                ),
                FormComponentImage(
                    id = "tapRootStructureImage5",
                    type = ComponentType.IMAGE,
                    image = "taproot",
                    caption = "Pålrot"
                ),
            )
        ),
    )
}