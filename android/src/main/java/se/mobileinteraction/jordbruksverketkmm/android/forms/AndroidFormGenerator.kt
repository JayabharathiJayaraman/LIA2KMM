package se.mobileinteraction.jordbruksverketkmm.android.forms

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.GridLayout
import android.widget.LinearLayout
import androidx.core.net.toUri
import androidx.core.widget.addTextChangedListener
import androidx.navigation.findNavController
import se.mobileinteraction.jordbruksverketkmm.android.MainApplication
import se.mobileinteraction.jordbruksverketkmm.android.R
import se.mobileinteraction.jordbruksverketkmm.android.databinding.*
import se.mobileinteraction.jordbruksverketkmm.forms.FormViewModel
import se.mobileinteraction.jordbruksverketkmm.forms.components.*

class AndroidFormGenerator(private val context: Context, private val viewModel: FormViewModel) :
    FormGenerator {
    private var mainView: LinearLayout = LinearLayout(context).also {
        val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(40, 0, 40, 0)
        it.layoutParams = params
        it.orientation = LinearLayout.VERTICAL
    }
    private var innerImageLayout: GridLayout = GridLayout(context).also {
        it.columnCount = 3
    }
    private var currentScreenRendered: Int = 0

    override fun generateInterface(components: List<FormComponent>, currentScreen: Int?) {
        for (component in components) {
            when (component.type) {
                ComponentType.TITLEBIG -> {
                    val bigTitle = (component as FormComponentText)
                    mainView.createOrUpdateBigTitleLabel(bigTitle.text, bigTitle.id)
                }
                ComponentType.TITLESMALL -> {
                    val smallTitle = (component as FormComponentText)
                    mainView.createOrUpdateSmallTitleLabel(smallTitle.text, smallTitle.id)
                }
                ComponentType.BODY -> {
                    val body = (component as FormComponentText)
                    mainView.createOrUpdateBodyLabel(body.text, body.id)
                }
                ComponentType.IMAGE -> {
                    val image = (component as FormComponentImage)
                    mainView.createOrUpdateImage(image.image, image.caption)
                }
                ComponentType.CAPTUREIMAGE -> {
                    val capture = (component as FormComponentCaptureImage)
                    mainView.createOrUpdateCaptureImage(
                        capture.imageUri,
                        capture.placeholderImage,
                        capture.title,
                        capture.body,
                        capture.button_text,
                        capture.id
                    )
                }
                ComponentType.VIDEO -> {
                    val video = (component as FormComponentVideo)
                    mainView.createOrUpdateVideo(video.text, video.id)
                }
                ComponentType.BUTTON -> {
                    val button = (component as FormComponentButton)
                    mainView.createOrUpdateButton(button.text, button.id)
                }
                ComponentType.BUTTONLIST -> {
                    val buttonList = (component as FormComponentButtonList)
                    mainView.createOrUpdateButtonList(
                        buttonList.title,
                        buttonList.id,
                        buttonList.list,
                        buttonList.value,
                        buttonList.placeholder
                    )
                }
                ComponentType.CHECKLIST -> {
                    val checklist = (component as FormComponentChecklist)
                    mainView.createOrUpdateChecklist(checklist.text, checklist.id)
                }
                ComponentType.REMARK -> {
                    val remark = (component as FormComponentRemark)
                    mainView.createOrUpdateRemark(remark.text, remark.id, remark.image)
                }

                ComponentType.RESULTATREMARKSFACE -> {
                    val resultatRemarks = (component as FormComponentResultatRemark)
                    mainView.createOrUpdateResultatRemarks(
                        resultatRemarks.text,
                        resultatRemarks.id,
                        resultatRemarks.image,
                        resultatRemarks.color
                    )
                }
                ComponentType.TEXTFIELD -> {
                    val textField = (component as FormComponentTextField)
                    mainView.addTextField(textField.id, textField.text, textField.placeholder)
                }
                ComponentType.TEXTFIELDNOTES -> {
                    val textFieldNotes = (component as FormComponentTextField)
                    mainView.createOrUpdateTextFieldNotes(
                        textFieldNotes.id,
                        textFieldNotes.text,
                        textFieldNotes.placeholder
                    )
                }

                ComponentType.CAPTIONEDIMAGE -> {

                    if (innerImageLayout.parent == null) {
                        mainView.addView(innerImageLayout)
                    }
                    val captionedImage = (component as FormComponentImage)
                    innerImageLayout.createOrUpdateCaptionedImage(
                        captionedImage.id,
                        captionedImage.image,
                        captionedImage.caption
                    )
                }
                ComponentType.TIMEFIELD -> {
                    val timeField = (component as FormComponentTime)
                    mainView.createOrUpdateTimeField(
                        timeField.id,
                        timeField.timeLabel,
                        timeField.start,
                        timeField.stopp
                    )
                }
                ComponentType.RESULTATINFOBODY -> {
                    val resultInfoBody = (component as FormComponentResultatInfoBody)
                    mainView.createOrUpdateResultatInfoBody(resultInfoBody.text, resultInfoBody.id)
                }
                ComponentType.RESULTATIMAGES -> {
                    val resultatImages = (component as FormComponentResultatImages)
                    mainView.addImagesContainer(
                        resultatImages.id,
                        resultatImages.text1,
                        resultatImages.text2,
                        resultatImages.text3,
                        resultatImages.text4
                    )
                }

                else -> println("unknown")
            }
        }
    }

    fun createInterface(components: List<FormComponent>): View {
        generateInterface(components)

        return mainView
    }

    fun updateInterface(components: List<FormComponent>, currentScreen: Int) {
        if (currentScreen != currentScreenRendered) {
            mainView.removeAllViews()
            currentScreenRendered = currentScreen
        }
        generateInterface(components)
    }
}

private fun ViewGroup.createOrUpdateBigTitleLabel(text: String, id: String) {
    val binding: FormBigTitleLabelBinding =
        FormBigTitleLabelBinding.inflate(LayoutInflater.from(context))
    this.findViewWithTag(id) ?: binding.formBigTitleLabelContainer.rootView.apply { tag = id }
        .also { this.addView(it) }
    binding.bigTitleLabelTextview.text = text
}

private fun ViewGroup.createOrUpdateSmallTitleLabel(text: String, id: String) {
    val binding: FormSmallTitleLabelBinding =
        FormSmallTitleLabelBinding.inflate(LayoutInflater.from(context))

    this.findViewWithTag(id) ?: binding.formSmallTitleLabelContainer.rootView.apply { tag = id }
        .also { this.addView(it) }
    binding.smallTitleLabelTextview.text = text
}

private fun ViewGroup.createOrUpdateButton(text: String, id: String) {
    val binding: FormButtonBinding = FormButtonBinding.inflate(LayoutInflater.from(context))
    this.findViewWithTag(id) ?: binding.formButtonContainer.rootView.apply { tag = id }
        .also { this.addView(it) }
    binding.button.text = text
}

private fun ViewGroup.createOrUpdateChecklist(text: String, id: String) {
    val binding: FormChecklistBinding = FormChecklistBinding.inflate(LayoutInflater.from(context))
    this.findViewWithTag(id) ?: binding.formRadiobtnContainer.rootView.apply { tag = id }
        .also { this.addView(it) }
    binding.checklistButton.text = text
}

private fun ViewGroup.createOrUpdateBodyLabel(text: String, id: String) {
    val binding: FormBodyLabelBinding = FormBodyLabelBinding.inflate(LayoutInflater.from(context))
    this.findViewWithTag(id) ?: binding.formBodyLabelContainer.rootView.apply { tag = id }
        .also { this.addView(it) }
    binding.bodyLabelTextview.text = text
}

private fun ViewGroup.createOrUpdateCaptureImage(
    imageUri: String?,
    placeholderImage: String,
    title: String,
    body: String,
    button_text: String,
    id: String
) {
    val binding: FormCaptureImageBinding =
        FormCaptureImageBinding.inflate(LayoutInflater.from(context))
    this.findViewWithTag(id) ?: binding.formCaptureImageContainer.rootView.apply { tag = id }
        .also { this.addView(it) }
    binding.title.text = title
    binding.body.text = body
    binding.button.text = button_text
    println("imageuri in generator: $imageUri")

    if (imageUri != null) {
        binding.imageview.setImageURI(imageUri.toUri())
    } else {
        binding.imageview.setImageResource(getImageResource(placeholderImage))
    }

    binding.button.setOnClickListener {
        findNavController().navigate(R.id.permissionsFragment)
    }
}

private fun ViewGroup.createOrUpdateTextFieldNotes(id: String, text: String, placeholder: String) {
    println("logg: addTextField: $text")
    val binding: FormTextfieldNotesBinding =
        FormTextfieldNotesBinding.inflate(LayoutInflater.from(context))

    this.findViewWithTag<EditText>(id) ?: binding.formTextfieldnotesContainer.apply { tag = id }
        .also { this.addView(it) }
    binding.textfield.hint = placeholder
}

private fun ViewGroup.createOrUpdateRemark(text: String, id: String, image: String) {
    val binding: FormRemarkBinding = FormRemarkBinding.inflate(LayoutInflater.from(context))
    this.findViewWithTag(id) ?: binding.formRemarkContainer.rootView.apply { tag = id }
        .also { this.addView(it) }
    binding.textview.text = text
    binding.imageview.setImageResource(getImageResource(image))
}

private fun ViewGroup.createOrUpdateResultatRemarks(
    text: String,
    id: String,
    image: String,
    color: String
) {
    val binding: FormResultatRemarkfaceBinding =
        FormResultatRemarkfaceBinding.inflate(LayoutInflater.from(context))
    this.findViewWithTag(id) ?: binding.formResultatRemarkContainer.rootView.apply { tag = id }
        .also { this.addView(it) }
    binding.textview.text = text
    binding.imageview.setImageResource(getImageResource(image))
    //binding.imageview.setBackgroundColor(getResultatFaceColor(color))
    binding.imageview.setBackgroundResource(getbackgroundFaceColor(color))
}

private fun ViewGroup.addTextField(id: String, text: String, placeholder: String) {
    println("logg: addTextField: $text")
    val editText = this.findViewWithTag<EditText>(id) ?: EditText(context).apply { tag = id }
        .also {
            it.setText(text)
            it.hint = placeholder
            it.addTextChangedListener { editable ->
                println("logg: TEXT LISTENER: ${editable.toString()}")
                if (text != editable.toString()) getApplication().formViewModel.setTextData(
                    id,
                    editable.toString()
                )
            }
            this.addView(it)
        }
}

private fun ViewGroup.createOrUpdateButtonList(
    title: String,
    id: String,
    list: List<String>,
    value: String,
    placeholder: String
) {
    val binding: FormButtonListBinding = FormButtonListBinding.inflate(LayoutInflater.from(context))
    this.findViewWithTag(id) ?: binding.formButtonlistContainer.rootView.apply { tag = id }
        .also { this.addView(it) }
    val dataAdapter: ArrayAdapter<String> =
        ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, list)

    binding.spinner.adapter = dataAdapter
    binding.textView.text = title
}

private fun ViewGroup.createOrUpdateImage(imageName: String, caption: String) {
    val binding: FormImageBinding = FormImageBinding.inflate(LayoutInflater.from(context))
    this.findViewWithTag(id) ?: binding.formImageviewContainer.rootView.apply { tag = id }
        .also { this.addView(it) }
    binding.imageview.setImageResource(getImageResource(imageName))
    binding.textView.text = caption
}

private fun ViewGroup.createOrUpdateTimeField(
    id: String,
    timeLabel: String,
    start: String,
    stopp: String
) {
    val binding: FormTimeTextviewBinding =
        FormTimeTextviewBinding.inflate(LayoutInflater.from(context))
    this.findViewWithTag(id) ?: binding.timeViewContainer.rootView.apply { tag = id }
        .also { this.addView(it) }
    binding.timeLabelTextview.text = timeLabel
}

private fun ViewGroup.addImagesContainer(
    id: String,
    text1: String,
    text2: String,
    text3: String,
    text4: String
) {
    val binding: FormResultatImageviewsBinding = FormResultatImageviewsBinding.inflate(
        LayoutInflater.from(context)
    )
    this.findViewWithTag(id) ?: binding.whatNextImagesContainer.rootView.apply { tag = id }
        .also { this.addView(it) }
    binding.newTestText.text = text1
    binding.vardaText.text = text2
    binding.markstrukturText.text = text3
    binding.klarText.text = text4
}

private fun ViewGroup.createOrUpdateVideo(id: String, text: String) {
    val binding: FormVideoBinding = FormVideoBinding.inflate(LayoutInflater.from(context))
    this.findViewWithTag(id) ?: binding.formVideoviewContainer.rootView.apply { tag = id }
        .also { this.addView(it) }
}

private fun ViewGroup.createOrUpdateCaptionedImage(id: String, imageName: String, caption: String) {
    val binding: FormImageviewCaptionBinding =
        FormImageviewCaptionBinding.inflate(LayoutInflater.from(context))
    this.findViewWithTag(id) ?: binding.formImageviewCaptionContainer.rootView.apply { tag = id }
        .also { this.addView(it) }

    binding.imageview.setImageResource(getImageResource(imageName))
    binding.textviewCaption.text = caption

}

private fun ViewGroup.createOrUpdateResultatInfoBody(text: String, id: String) {
    val binding: FormResultatInfoBodyBinding =
        FormResultatInfoBodyBinding.inflate(LayoutInflater.from(context))
    this.findViewWithTag(id) ?: binding.formResultatInfoContainer.rootView.apply { tag = id }
        .also { this.addView(it) }
    binding.resultatBodyTextview.text = text
}

private fun ViewGroup.getImageResource(name: String): Int {
    return context.resources.getIdentifier("drawable/$name", null, context.packageName)
}

private fun ViewGroup.getbackgroundFaceColor(colorName: String): Int {
    var resourceId: Int = 0
    if (colorName == "red_round_background") {
        resourceId =
            context.resources.getIdentifier("drawable/$colorName", null, context.packageName)
    }
    if (colorName == "orange_round_background") {
        resourceId =
            context.resources.getIdentifier("drawable/$colorName", null, context.packageName)
    }
    if (colorName == "green_round_background") {
        resourceId =
            context.resources.getIdentifier("drawable/$colorName", null, context.packageName)
    }
    return resourceId
}

private fun ViewGroup.getApplication(): MainApplication {
    return context.applicationContext as MainApplication
}