package se.mobileinteraction.jordbruksverketkmm.forms.information

import se.mobileinteraction.jordbruksverketkmm.forms.components.ComponentType
import se.mobileinteraction.jordbruksverketkmm.forms.components.FormComponentImage
import se.mobileinteraction.jordbruksverketkmm.forms.components.FormComponentText

class InformationScreens {
    val screens: List<InformationScreen> = listOf(
        InformationScreen(
            title = "Daggmaskarna och markstrukturen",
            id = "wormScreen",
            components = listOf(
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    id = "wormsTitle",
                    text = "Daggmaskarna och markstrukturen"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    id = "wormsBody",
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
            id = "infiltrationScreen",
            components = listOf(
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    id = "infiltrationTitle",
                    text = "Infiltrationen och markstrukturen"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    id = "infiltrationBody",
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
            id = "conditionsScreen",
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
                            "o.s.v.\n\nVägledning finns på sidan 28 i rapporten: "

                    /* TODO CREATE LINK COMPONENT
                       TODO https://pub.epsilon.slu.se/3375/1/Rapport8.pdf */
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
            id = "groundProfileScreen",
            components = listOf(
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    id = "groundProfileTitle4",
                    text = "Markprofilen"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    id = "groundProfileBody",
                    text = "Markprofilen är ett tvärsnitt (i djupled) av marken. Dess utseende, " +
                            "med matjord, eventuella täta skikt (till exempel bearbetningssula) " +
                            "och alv, varierar beroende på markens grundförutsättningar " +
                            "(såsom jordart) och odlingssystemets utformning. \n\nGenom att " +
                            "undersöka markprofilen kan man få en överblick över markens " +
                            "aktuella förutsättningar som växtplats. Nedan visas exempel " +
                            "på några olika odlingsjordar med typiska egenskaper."
                ),
                FormComponentImage(
                    id = "infoImage1",
                    type = ComponentType.IMAGE,
                    image = "markprofil_info_1_sandjord",
                    caption = ""
                ),
                FormComponentImage(
                    id = "infoImage2",
                    type = ComponentType.IMAGE,
                    image = "markprofil_info_2_lerjord",
                    caption = ""
                ),
                FormComponentImage(
                    id = "infoImage3",
                    type = ComponentType.IMAGE,
                    image = "markprofil_info_3_mjallera",
                    caption = ""
                ),
                FormComponentImage(
                    id = "infoImage4",
                    type = ComponentType.IMAGE,
                    image = "markprofil_info_4_mulljord",
                    caption = ""
                ),
            )
        ),
        InformationScreen(
            title = "Markstruktur",
            id = "groundStructureScreen",
            components = listOf(
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    id = "groundStructureTitle",
                    text = "Markstruktur"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    id = "groundStructureBody",
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
                    id = "goodStructureTitle",
                    text = "Vad är bra struktur"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    id = "goodStructureBody",
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
            id = "groundStructureMovieScreen",
            components = listOf(
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    id = "groundStructureMovieTitle",
                    text = "Markstruktur - Film"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    id = "groundStructureMovieBody",
                    text = "Filmen \"vårda markens struktur\" är gjord i projektet Goodla " +
                            "och är 5:30 lång."
                ),
                /* TODO CREATE LINK COMPONENT
                   TODO https://www.slu.se/institutioner/mark-miljo/samverkan/goodla/filmer/varda-markens-struktur */
            )
        ),
        InformationScreen(
            title = "Markstruktur - Åtgärdstips",
            id = "groundStructureTipsScreen",
            components = listOf(
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    id = "groundStructureTipsTitle",
                    text = "Markstruktur - Åtgärdstips"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    id = "groundStructureTipsBody",
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
            id = "groundStructureReportScreen",
            components = listOf(
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    id = "groundStructureReportTitle",
                    text = "Markstruktur - Rapport SLU"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    id = "groundStructureReportBody",
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
            id = "topSoilStructureScreen",
            components = listOf(
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    id = "topSoilStructureTitle",
                    text = "Mullhalten och markstrukturen"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    id = "topSoilStructureBody",
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
            id = "shovelStructureScreen",
            components = listOf(
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    id = "shovelStructureTitle",
                    text = "Spadtrampen och markstrukturen"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    id = "shovelStructureBody",
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
            id = "layerStructureScreen",
            components = listOf(
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    id = "layerStructureTitle",
                    text = "Täta skikt och markstrukturen"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    id = "layerStructureBody",
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
            id = "plantStructureScreen",
            components = listOf(
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    id = "plantStructureTitle",
                    text = "Växtresterna och markstrukturen"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    id = "plantStructureBody",
                    text = "Om markstrukturen är bra med god luftväxling så finns det växtrester " +
                            "i alla nedbrytningsstadier och de har en frisk doft. Dåligt omsatta " +
                            "växtrester är en indikation på att marken kan vara vattensjuk på " +
                            "grund av dålig dränering, eller har täta skikt.\n"
                ),
            )
        ),
        InformationScreen(
            title = "Växtrötterna och markstrukturen",
            id = "rootStructureScreen",
            components = listOf(
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    id = "rootStructureTitle",
                    text = "Växtrötterna och markstrukturen"
                ),
                FormComponentText(
                    type = ComponentType.BODY,
                    id = "rootStructureBody",
                    text = "Väl utvecklat, regelbundet rotsystem är ofta förenat med god struktur. " +
                            "I en jord med dålig struktur har grödan måttligt eller svagt utvecklat" +
                            " rotsystem, där rötterna främst växer i sprickor och maskgångar. " +
                            "Lokala förgreningar tyder på täta skikt och markpackning. Ju finare " +
                            "rotsystemet är och ju djupare det går, desto mer kan växten prestera " +
                            "och desto större blir skörden.\n"
                ),
                FormComponentText(
                    type = ComponentType.TITLESMALL,
                    id = "differentRootStructureTitle",
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
                    id = "grassRootStructureImage",
                    type = ComponentType.IMAGE,
                    image = "grassroots",
                    caption = "Gräsrötter"
                ),
                FormComponentImage(
                    id = "tapRootStructureImage",
                    type = ComponentType.IMAGE,
                    image = "taproot",
                    caption = "Pålrot"
                ),
            )
        ),
        InformationScreen(
            title = "Beskrivning",
            id = "descriptionScreen",
            components = listOf(
                FormComponentText(
                    id = "descriptionTitle",
                    type = ComponentType.TITLESMALL,
                    text = "Cylinder för mätning av infiltration"
                ),
                FormComponentText(
                    id = "descriptionBody",
                    type = ComponentType.BODY,
                    text = "En cylinder för infiltrationsmätning kan du lätt " +
                            "tillverka själv. Den bör vara cirka 15-20 cm i diameter, " +
                            "och 10-15 cm hög. \n\nAnvänd till exempel en bit av " +
                            "ett avloppsrör, eller kapa av botten på en kastrull eller " +
                            "en gammal plåtburk. Inte alltför tjockt gods, men inte " +
                            "heller för tunt. Kanten i botten bör vara vass, men stark, " +
                            "så den går att trycka ner i jorden. \n\nGör en tydlig, " +
                            "vattenbeständig, markering i cylinderns överkant. I testet " +
                            "är det viktigt att vattennivån mäts vid samma ställe på " +
                            "cylindern, både vid start och slut. Då är denna markering " +
                            "till hjälp."
                ),
                FormComponentText(
                    id = "descriptionTipsTitle",
                    type = ComponentType.TITLESMALL,
                    text = "Tips!"
                ),
                FormComponentText(
                    id = "descriptionTipsBody",
                    type = ComponentType.BODY,
                    text = "Det kan vara praktiskt med flera cylindrar (2-3 stycken) om " +
                            "man vet att man vill mäta i flera skikt på samma plats. " +
                            "Det ger möjlighet att ha flera mätningar igång parallellt."
                ),
                FormComponentImage(
                    id = "cylinderImage",
                    type = ComponentType.IMAGE,
                    image = "cylindrar_exempel",
                    caption = "Exempel på cylinder för infiltrationsmätning: Kastrull med" +
                            " avkapad botten och avloppsrör med avfasad kant."
                ),
            )
        ),
        InformationScreen(
            title = "Plats",
            id = "locationScreen",
            components = listOf(
                FormComponentText(
                    type = ComponentType.BODY,
                    id = "locationBody",
                    text = "Platsen för testet sparas i appen tillsammans med resultaten " +
                            "av de test du utfört. Dels som hjälp för minnet, för att du " +
                            "lättare ska kunna veta var du var. Dels för att du ska kunna " +
                            "återkomma till samma plats senare, för att göra nya test och " +
                            "följa upp dina åtgärder."
                ),
            )
        ),
    )
}

val knowledgeBankIds: List<String> = listOf(
    "wormScreen",
    "infiltrationScreen",
    "conditionsScreen",
    "groundProfileScreen",
    "groundStructureScreen",
    "groundStructureMovieScreen",
    "groundStructureTipsScreen",
    "groundStructureReportScreen",
    "topSoilStructureScreen",
    "shovelStructureScreen",
    "layerStructureScreen",
    "plantStructureScreen",
    "rootStructureScreen",
)

