package se.mobileinteraction.jordbruksverketkmm.android.forms

import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.view.*
import android.widget.*
import androidx.core.net.toUri
import androidx.core.widget.addTextChangedListener
import androidx.navigation.findNavController
import se.mobileinteraction.jordbruksverketkmm.android.MainApplication
import se.mobileinteraction.jordbruksverketkmm.android.R
import se.mobileinteraction.jordbruksverketkmm.android.databinding.*
import se.mobileinteraction.jordbruksverketkmm.forms.components.*
import se.mobileinteraction.jordbruksverketkmm.forms.models.AnswerWithPhoto
import se.mobileinteraction.jordbruksverketkmm.forms.models.QuestionnaireAnswer

class AndroidFormGenerator(context: Context) :
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
                    mainView.createOrUpdateImage(image.id, image.image, image.caption)
                }
                ComponentType.INFORMATION -> {
                    val information = (component as FormComponentInformation)
                    mainView.createOrUpdateInformation(information.id, information.components)
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
                        buttonList.position,
                        buttonList.placeholder
                    )
                }
                ComponentType.STOMPLEVEL3 -> {
                    val buttonList = (component as FormComponentButtonList)
                    mainView.createOrUpdateLevel3(
                        buttonList.title,
                        buttonList.id,
                        buttonList.list,
                        buttonList.value,
                        buttonList.position,
                        buttonList.placeholder
                    )
                }
                ComponentType.CHECKLIST -> {
                    val checklist = (component as FormComponentChecklist)
                    mainView.createOrUpdateChecklist(
                        checklist.id,
                        checklist.title,
                        checklist.options,
                        checklist.rating
                    )
                }
                ComponentType.QUESTIONNAIRE -> {
                    val questionnaire = (component as FormComponentQuestionnaire)
                    mainView.createOrUpdateQuestionnaire(
                        questionnaire.id,
                        questionnaire.text,
                        questionnaire.answer
                    )
                }

                ComponentType.QUESTIONNAIRERESULT -> {
                    val questionnaireResult = (component as FormComponentQuestionnaireResult)
                    mainView.createOrUpdateQuestionnaireResult(
                        questionnaireResult.id,
                        questionnaireResult.answers
                    )
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
                    mainView.createOrUpdateTextfield(
                        textField.id,
                        textField.text,
                        textField.placeholder
                    )
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
                    mainView.addResultsImages(
                        resultsImages.id,
                        resultsImages.images,
                        resultsImages.imagesTextList
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
    rating: Int
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
                getApplication().formViewModel.setChecklistRating(id, checkedId)
            }
            binding.radioGroup.check(rating)
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
    val binding: FormTextfieldNotesBinding =
        FormTextfieldNotesBinding.inflate(LayoutInflater.from(context))

    this.findViewWithTag(id) ?: binding.textfield.rootView.apply { tag = id }
        .also {
            binding.textfield.setText(text)
            binding.textfield.hint = placeholder
            binding.textfield.addTextChangedListener { editable ->
                if (text != editable.toString()) getApplication().formViewModel.setTextData(
                    id,
                    editable.toString()
                )
            }
            this.addView(it)
        }
    this.findViewWithTag(id) ?: binding.textfield.rootView.apply { tag = id }
        .also {
            binding.textfield.hint = placeholder
            this.addView(it)
        }
}

private fun ViewGroup.createOrUpdateQuestionnaire(
    id: String,
    text: List<String>,
    answer: QuestionnaireAnswer?
) {
    val binding: FormQuestionnaireChecklistBinding =
        FormQuestionnaireChecklistBinding.inflate(LayoutInflater.from(context))

    this.findViewWithTag(id) ?: binding.radioGroup.rootView.apply { tag = id }
        .also {
            binding.radioButtonSad.text = text[0]
            binding.radioButtonIndifferent.text = text[1]
            binding.radioButtonHappy.text = text[2]

            println("Answer when empty: $answer")
            if (answer == null) {
                setQuestionnaireAnswered(false)
            }

            binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    binding.radioButtonSad.id -> {
                        getApplication().formViewModel.setQuestionnaireAnswer(
                            id,
                            QuestionnaireAnswer.Poor,
                            text[0]
                        )
                        setQuestionnaireAnswered(true)
                    }
                    binding.radioButtonIndifferent.id -> {
                        getApplication().formViewModel.setQuestionnaireAnswer(
                            id,
                            QuestionnaireAnswer.Mediocre,
                            text[1]
                        )
                        setQuestionnaireAnswered(true)
                    }
                    binding.radioButtonHappy.id -> {
                        getApplication().formViewModel.setQuestionnaireAnswer(
                            id,
                            QuestionnaireAnswer.Good,
                            text[2]
                        )
                        setQuestionnaireAnswered(true)
                    }
                }
            }
            when (answer) {
                QuestionnaireAnswer.Good -> binding.radioGroup.check(binding.radioButtonHappy.id)
                QuestionnaireAnswer.Mediocre -> binding.radioGroup.check(binding.radioButtonIndifferent.id)
                QuestionnaireAnswer.Poor -> binding.radioGroup.check(binding.radioButtonSad.id)
                else -> {
                    binding.radioGroup.check(-1)
                }
            }
            this.addView(it)
        }
}

private fun ViewGroup.createOrUpdateQuestionnaireResult(
    id: String,
    answers: MutableList<AnswerWithPhoto>?,
) {
    val binding: FormGroundProfileResultBinding =
        FormGroundProfileResultBinding.inflate(LayoutInflater.from(context))
    this.findViewWithTag(id) ?: binding.questionnaireResultContainer.rootView.apply { tag = id }
        .also {
            if (answers != null) {
                for (i in 0 until answers.size) {
                    val imageLayout: FormQuestionnaireResultItemBinding =
                        FormQuestionnaireResultItemBinding.inflate(
                            LayoutInflater.from(context)
                        )

                    when (answers[i].answer) {
                        QuestionnaireAnswer.Good -> {
                            imageLayout.tableRow.setBackgroundResource(R.drawable.questionnaire_happy_selected)
                            imageLayout.imageView.setImageResource(getImageResource("happy_face"))
                        }
                        QuestionnaireAnswer.Mediocre -> {
                            imageLayout.tableRow.setBackgroundResource(R.drawable.questionnaire_indifferent_selected)
                            imageLayout.imageView.setImageResource(getImageResource("indifferent_face"))
                        }
                        QuestionnaireAnswer.Poor -> {
                            imageLayout.tableRow.setBackgroundResource(R.drawable.questionnaire_sad_selected)
                            imageLayout.imageView.setImageResource(getImageResource("sad_face"))
                        }
                        null -> println("Null")
                    }

                    imageLayout.textView.text = answers[i].text
                    binding.questionnaireResultContainer.addView(imageLayout.questionnaireResultItem)
                }
            }
            this.addView(it)
        }
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

    if (imageUri != null) {
        binding.imageview.setImageURI(imageUri.toUri())
        binding.imageview.adjustViewBounds = true
        binding.imageview.layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT

    } else {
        binding.imageview.setImageResource(getImageResource(placeholderImage))
    }

    binding.button.setOnClickListener {
        findNavController().navigate(R.id.navigateFromFormFragmentToPermissionsFragment)
    }
}

private fun ViewGroup.createOrUpdateTextfield(id: String, text: String, placeholder: String) {
    this.findViewWithTag<EditText>(id) ?: EditText(context).apply { tag = id }
        .also {
            it.setText(text)
            it.hint = placeholder
            it.addTextChangedListener { editable ->
                if (text != editable.toString()) getApplication().formViewModel.setTextData(
                    id,
                    editable.toString()
                )
            }
            this.addView(it)
        }
}

private fun ViewGroup.addInnerImageLayout(id: String, images: List<String>, caption: List<String>) {
    val binding: FormCaptionedimageGridlayoutBinding = FormCaptionedimageGridlayoutBinding.inflate(LayoutInflater.from(context))
    this.findViewWithTag(id) ?: binding.formImageGridContainer.rootView.apply { tag = id }
        .also { this.addView(it) }

    for (i in images.indices) {
        val imageLayout: FormImageviewCaptionBinding = FormImageviewCaptionBinding.inflate(
            LayoutInflater.from(context)
        )
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
    position: Int,
    placeholder: String
) {
    val binding: FormButtonListBinding = FormButtonListBinding.inflate(LayoutInflater.from(context))

    this.findViewWithTag(id) ?: binding.formButtonlistContainer.rootView.apply { tag = id }
        .also { it ->
            val dataAdapter: ArrayAdapter<String> =
                ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, list)

            binding.spinner.adapter = dataAdapter
            binding.textView.text = title
            binding.textView.hint = placeholder
            binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    pos: Int,
                    spinnerId: Long
                ) {
                    val selectedItem = parent.getItemAtPosition(pos).toString()
                    val itemPosition = parent.getItemIdAtPosition(pos).toInt()
                    getApplication().formViewModel.setButtonListData(id, selectedItem, itemPosition)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
            if (position != -1) {
                binding.spinner.setSelection(position)
            }
            this.addView(it)
        }
}


private fun ViewGroup.createOrUpdateLevel3(
    title: String,
    id: String,
    list: List<String>,
    value: String,
    position: Int,
    placeholder: String
) {
    val binding: FormStompLevel3Binding =
        FormStompLevel3Binding.inflate(LayoutInflater.from(context))

    val dataAdapter: ArrayAdapter<String> =
        ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, list)

    this.findViewWithTag(id) ?: binding.stompLevel3Container.rootView.apply { tag = id }
        .also {

            binding.textView.setOnClickListener {
                binding.textView.visibility = View.GONE
                binding.spinner.visibility = View.VISIBLE
                binding.title.visibility = View.VISIBLE
                binding.title.text = title
            }

            binding.spinner.adapter = dataAdapter
            binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    pos: Int,
                    spinnerId: Long
                ) {
                    val selectedItem = parent.getItemAtPosition(pos).toString()
                    val itemPosition = parent.getItemIdAtPosition(pos).toInt()
                    getApplication().formViewModel.setButtonListData(id, selectedItem, itemPosition)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
            if (position != -1) {
                binding.textView.visibility = View.GONE
                binding.title.visibility = View.VISIBLE
                binding.spinner.visibility = View.VISIBLE
                binding.title.text = title
                binding.spinner.setSelection(position)
            }
            this.addView(it)
        }
}

private fun ViewGroup.createOrUpdateImage(id: String, imageName: String, caption: String) {
    val binding: FormImageBinding = FormImageBinding.inflate(LayoutInflater.from(context))

    this.findViewWithTag(id) ?: binding.formImageviewContainer.rootView.apply { tag = id }
        .also { this.addView(it) }

    binding.imageview.setImageResource(getImageResource(imageName))
    binding.textView.text = caption
}

private fun ViewGroup.createOrUpdateInformation(id: String, components: List<FormComponent>) {

    val binding: FormInformationComponentBinding =
        FormInformationComponentBinding.inflate(LayoutInflater.from(context))

    this.findViewWithTag(id) ?: binding.informationContainer.rootView.apply { tag = id }
        .also { view ->

            binding.infoButton.setOnClickListener {
                val formGenerator = AndroidFormGenerator(context)
                val componentsView = formGenerator.createInterface(components)

                val dialogBinding = DialogBinding.inflate(LayoutInflater.from(context))
                dialogBinding.componentScrollview.addView(componentsView)

                val customDialog = AlertDialog.Builder(context).create()
                customDialog.setView(dialogBinding.root)

                val params = WindowManager.LayoutParams()
                val height = (resources.displayMetrics.heightPixels * 0.85).toInt()
                params.width = WindowManager.LayoutParams.MATCH_PARENT
                params.height = height
                params.gravity = Gravity.TOP

                customDialog.show()
                customDialog.window?.attributes = params

                dialogBinding.closeButton.setOnClickListener {
                    customDialog.dismiss()
                }

            }
            this.addView(view)
        }
}

private fun ViewGroup.createOrUpdateTimeField(
    id: String,
    timeLabel: String,
    start: String,
    stop: String
) {
    val binding: FormTimeTextviewBinding = FormTimeTextviewBinding.inflate(LayoutInflater.from(context))
    this.findViewWithTag(id) ?: binding.timeViewContainer.rootView.apply { tag = id }
        .also { this.addView(it) }

    binding.timeLabelTextview.text = timeLabel
}

private fun ViewGroup.addResultsImages(
    id: String,
    images: List<String>,
    imagesTextList: List<String>
) {
    val binding: FormResultsImageviewsBinding = FormResultsImageviewsBinding.inflate(
        LayoutInflater.from(context)
    )
    this.findViewWithTag(id) ?: binding.whatNextImagesContainer.rootView.apply { tag = id }
        .also {
            binding.newTestImage.setImageResource(getImageResource(images[0]))
            binding.markstrukturImage.setImageResource(getImageResource(images[1]))
            binding.checkImage.setImageResource(getImageResource(images[2]))
            binding.newTestText.text = imagesTextList[0]
            binding.vardaText.text = imagesTextList[1]
            binding.klarText.text = imagesTextList[2]

            this.addView(it)
             }
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

private fun ViewGroup.createOrUpdateResultsInfoBody(text: String, id: String) {
    val binding: FormResultsInfoBodyBinding = FormResultsInfoBodyBinding.inflate(LayoutInflater.from(context))
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

private fun ViewGroup.setQuestionnaireAnswered(isAnswered: Boolean) {
    getApplication().formViewModel.form.data.questionnaireIsAnswered.answered = isAnswered
}

private fun ViewGroup.getApplication(): MainApplication {
    return context.applicationContext as MainApplication
}