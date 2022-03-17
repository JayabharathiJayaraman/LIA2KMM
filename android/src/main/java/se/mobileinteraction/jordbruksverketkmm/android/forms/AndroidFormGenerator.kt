package se.mobileinteraction.jordbruksverketkmm.android.forms

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.addTextChangedListener
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
                ComponentType.VIDEO -> {
                    val video = (component as FormComponentVideo)
                    mainView.createOrUpdateVideo(video.id, video.description, video.source)
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
                    mainView.createOrUpdateChecklist(
                        checklist.id,
                        checklist.title,
                        checklist.options,
                        checklist.active
                    )
                }
                ComponentType.REMARK -> {
                    val remark = (component as FormComponentRemark)
                    mainView.createOrUpdateRemark(remark.text, remark.id, remark.image)
                }

                ComponentType.RESULTSREMARKSFACE -> {
                    val resultsRemark = (component as FormComponentResultsRemark)
                    mainView.createOrUpdateResultsRemarks(
                        resultsRemark.text,
                        resultsRemark.id,
                        resultsRemark.image,
                        resultsRemark.color
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
                ComponentType.IMAGESGRID -> {
                    val imagesGrid = (component as FormComponentImagesGrid)
                    mainView.addInnerImageLayout(
                        imagesGrid.id,
                        imagesGrid.image,
                        imagesGrid.caption
                    )
                }
                ComponentType.TIMEFIELD -> {
                    val timeField = (component as FormComponentTime)
                    mainView.createOrUpdateTimeField(
                        timeField.id,
                        timeField.timeLabel,
                        timeField.start,
                        timeField.stop
                    )
                }
                ComponentType.RESULTSINFOBODY -> {
                    val resultInfoBody = (component as FormComponentResultsInfoBody)
                    mainView.createOrUpdateResultsInfoBody(resultInfoBody.text, resultInfoBody.id)
                }
                ComponentType.RESULTSIMAGES -> {
                    val resultsImages = (component as FormComponentResultsImages)
                    mainView.addImagesContainer(resultsImages.id, resultsImages.imagesTextList)
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
    binding.title.text = text
}

private fun ViewGroup.createOrUpdateButton(text: String, id: String) {
    val binding: FormButtonBinding = FormButtonBinding.inflate(LayoutInflater.from(context))
    this.findViewWithTag(id) ?: binding.formButtonContainer.rootView.apply { tag = id }
        .also { this.addView(it) }
    binding.button.text = text
}

private fun ViewGroup.createOrUpdateChecklist(
    id: String,
    title: String,
    options: List<String>,
    active: Int
) {
    val binding: FormChecklistBinding = FormChecklistBinding.inflate(LayoutInflater.from(context))
    binding.title.text = title

    val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    )
    params.setMargins(0, 10, 0, 10)

    this.findViewWithTag(id) ?: binding.formRadioGroup.rootView.apply { tag = id }
        .also {
            for (i in options.indices) {
                val radiobutton = RadioButton(context)
                radiobutton.setButtonDrawable(R.drawable.checklist_button_states)
                radiobutton.setPadding(20, 0, 0, 0)
                radiobutton.layoutParams = params
                radiobutton.id = i
                radiobutton.text = options[i]
                binding.radioGroup.addView(radiobutton)
            }

            binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
                getApplication().formViewModel.setChecklistActive(id, checkedId)
            }
            binding.radioGroup.check(active)
            this.addView(it)
        }
}

private fun ViewGroup.createOrUpdateBodyLabel(text: String, id: String) {
    val binding: FormBodyLabelBinding = FormBodyLabelBinding.inflate(LayoutInflater.from(context))
    this.findViewWithTag(id) ?: binding.formBodyLabelContainer.rootView.apply { tag = id }
        .also { this.addView(it) }
    binding.bodyLabelTextview.text = text
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

private fun ViewGroup.createOrUpdateResultsRemarks(
    text: String,
    id: String,
    image: String,
    color: String
) {
    val binding: FormResultsRemarkfaceBinding =
        FormResultsRemarkfaceBinding.inflate(LayoutInflater.from(context))
    this.findViewWithTag(id) ?: binding.formResultsRemarkContainer.rootView.apply { tag = id }
        .also { this.addView(it) }
    binding.textview.text = text
    binding.imageview.setImageResource(getImageResource(image))
    binding.imageview.setBackgroundResource(getFaceBackgroundColor(color))
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

private fun ViewGroup.addInnerImageLayout(id: String, images: List<String>, caption: List<String>)
{
    val binding: FormCaptionedimageGridlayoutBinding = FormCaptionedimageGridlayoutBinding.inflate(LayoutInflater.from(context))
    this.findViewWithTag(id) ?: binding.formImageGridContainer.rootView.apply { tag = id }
        .also { this.addView(it) }

    for(i in images.indices){
        val imageLayout:FormImageviewCaptionBinding = FormImageviewCaptionBinding.inflate(
            LayoutInflater.from(context))
        imageLayout.imageview.setImageResource(getImageResource(images[i]))
        imageLayout.textviewCaption.text = caption[i]
        binding.formImageGridContainer.addView(imageLayout.formImageviewCaptionContainer)
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
        .also {

            val dataAdapter: ArrayAdapter<String> =
                ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, list)

            binding.spinner.adapter = dataAdapter
            binding.textView.text = title

            binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    pos: Int,
                    spinnerId: Long
                ) {
                    val itemId = parent.getItemIdAtPosition(pos)
                    getApplication().formViewModel.setButtonListActive(id, itemId.toString())
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }

            println("BUTTON LIST INFILTRATION: $value")
            if (value != "") {
                binding.spinner.setSelection(value.toInt())
            }
            this.addView(it)
        }
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
    stop: String
) {
    val binding: FormTimeTextviewBinding =
        FormTimeTextviewBinding.inflate(LayoutInflater.from(context))
    this.findViewWithTag(id) ?: binding.timeViewContainer.rootView.apply { tag = id }
        .also { this.addView(it) }
    binding.timeLabelTextview.text = timeLabel
}

private fun ViewGroup.addImagesContainer(id: String, imagesTextList: List<String>) {
    val binding: FormResultsImageviewsBinding = FormResultsImageviewsBinding.inflate(
        LayoutInflater.from(context)
    )
    this.findViewWithTag(id) ?: binding.whatNextImagesContainer.rootView.apply { tag = id }
        .also { this.addView(it) }

    binding.newTestText.text = imagesTextList[0]
    binding.vardaText.text = imagesTextList[1]
    binding.markstrukturText.text = imagesTextList[2]
    binding.klarText.text = imagesTextList[3]
}

private fun ViewGroup.createOrUpdateVideo(id: String, description: String, source: String) {
    val binding: FormVideoBinding = FormVideoBinding.inflate(LayoutInflater.from(context))
    this.findViewWithTag(id) ?: binding.formVideoviewContainer.rootView.apply { tag = id }
        .also { this.addView(it) }

    val mc = MediaController(context)
    binding.videoview.setMediaController(mc)
    binding.videoview.setVideoURI(Uri.parse(getVideoPath(source)))
    binding.videoview.seekTo(1)
}

private fun ViewGroup.createOrUpdateCaptionedImage(id: String, imageName: String, caption: String) {
    val binding: FormImageviewCaptionBinding =
        FormImageviewCaptionBinding.inflate(LayoutInflater.from(context))
    this.findViewWithTag(id) ?: binding.formImageviewCaptionContainer.rootView.apply { tag = id }
        .also { this.addView(it) }

    binding.imageview.setImageResource(getImageResource(imageName))
    binding.textviewCaption.text = caption

}

private fun ViewGroup.createOrUpdateResultsInfoBody(text: String, id: String) {
    val binding: FormResultsInfoBodyBinding =
        FormResultsInfoBodyBinding.inflate(LayoutInflater.from(context))
    this.findViewWithTag(id) ?: binding.formResultsInfoContainer.rootView.apply { tag = id }
        .also { this.addView(it) }
    binding.resultsBodyTextview.text = text
}

private fun ViewGroup.getImageResource(name: String): Int {
    return context.resources.getIdentifier("drawable/$name", null, context.packageName)
}

private fun ViewGroup.getVideoPath(source: String): String {
    val videoResource = resources.getIdentifier(source, "raw", context.packageName)
    return "android.resource://" + context.packageName.toString() + "/" + videoResource
}

private fun ViewGroup.getFaceBackgroundColor(colorName: String): Int {
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