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
                    type = ComponentType.IMAGE,
                    image = "shovel",
                    caption = "Bra grävspade",
                ),
                FormComponentImage(
                    type = ComponentType.IMAGE,
                    image = "cylinder",
                    caption = "Cylinder",
                ),
                FormComponentImage(
                    type = ComponentType.IMAGE,
                    image = "waterdrops",
                    caption = "Vatten",
                ),
                FormComponentImage(
                    type = ComponentType.IMAGE,
                    image = "litre",
                    caption = "Litermått",
                ),
                FormComponentImage(
                    type = ComponentType.IMAGE,
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
                    text = "Om du har ett stort skifte med stora olikheter i jordart och brukningsegenskaper så kan du dela upp skiftet. Det kan göra det enklare att svara " +
                            "på frågorna i testen, bedöma markstrukturen och möjliga åtgärder."
                ),
            )
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
        }
}