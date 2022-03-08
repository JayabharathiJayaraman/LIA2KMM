package se.mobileinteraction.jordbruksverketkmm.android.forms

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import se.mobileinteraction.jordbruksverketkmm.android.R
import se.mobileinteraction.jordbruksverketkmm.android.databinding.*
import se.mobileinteraction.jordbruksverketkmm.forms.components.*


class AndroidFormGenerator(val context: Context) : FormGenerator {
    var mainView: LinearLayout

    init {
        val linearLayout = LinearLayout(context)
        this.mainView = linearLayout
    }

    private fun getDrawable(image: String): Int {
        return context.resources.getIdentifier(image, "drawable", context.packageName)
    }

    override fun getInterface(components: List<FormComponent>): Any {
        val linearLayout = LinearLayout(context)
        val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        linearLayout.layoutParams = params
        linearLayout.orientation = LinearLayout.VERTICAL

        mainView = linearLayout

        for (component in components) {
            when (component.type) {
                ComponentType.TITLESMALL -> {
                    val titleSmall = (component as FormComponentText)
                    addSmallTitleLabel(titleSmall.text)
                }
                ComponentType.BODY -> {
                    val body = (component as FormComponentText)
                    addBodyLabel(body.text)
                }
                ComponentType.TEXTFIELD ->{
                    val textField = (component as FormComponentTextField)
                    addTextField(textField.id, textField.text, textField.placeholder)
                }
                ComponentType.TEXTFIELDNOTES -> {
                    val textFieldNotes = (component as FormComponentTextField)
                    addTextFieldNotes(textFieldNotes.id, textFieldNotes.text, textFieldNotes.placeholder)
                }
                ComponentType.BUTTON -> {
                    val button = (component as FormComponentButton)
                    addButton(button.text)
                }
                ComponentType.BUTTONLIST -> {
                    val buttonList = (component as FormComponentButtonList)
                    addButtonList(buttonList.id, buttonList.title, buttonList.list,buttonList.value,buttonList.placeholder)
                }
                ComponentType.REMARK -> {
                    val remark = (component as FormComponentChecklist)
                    addChecklistRemark(remark.text, remark.image)
                }
                ComponentType.IMAGECAPTION -> {
                    val imageWithCaption = (component as FormComponentImageCaption)
                    addImageWithCaption(imageWithCaption.text, imageWithCaption.image)
                }
                else -> println("unknown")
            }
        }

        return mainView
    }

    override fun addBigTitleLabel(text: String) {
    }

    override fun addSmallTitleLabel(text: String) {
        val binding: FormSmallTitleLabelBinding = FormSmallTitleLabelBinding.inflate(LayoutInflater.from(context))
        binding.bigTitleLabelTextview.text = text
        mainView.addView(binding.formSmallTitleLabelContainer.rootView)
    }

    override fun addBodyLabel(text: String) {
        val binding: FormBodyLabelBinding = FormBodyLabelBinding.inflate(LayoutInflater.from(context))
        binding.bodyLabelTextview.text = text
        mainView.addView(binding.formBodyLabelContainer.rootView)
    }

    override fun addTextField(id: String, text: String, placeholder: String) {
        val binding: FormTextfieldBinding = FormTextfieldBinding.inflate(LayoutInflater.from(context))
        binding.formTextfield.hint = placeholder
        mainView.addView(binding.formTextfieldContainer.rootView)
    }

    override  fun addTextFieldNotes(id: String, text: String, placeholder: String) {
        val binding: FormTextfieldBinding = FormTextfieldBinding.inflate(LayoutInflater.from(context))
        binding.formTextfield.hint = placeholder
        mainView.addView(binding.formTextfieldContainer.rootView)
    }

    override fun addButtonList(
        id: String,
        title: String,
        list: List<String>,
        value: String,
        placeholder: String
    ) {
        val binding: FormButtonListBinding = FormButtonListBinding.inflate(LayoutInflater.from(context))
        val dataAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, list)
        binding.spinner.adapter = dataAdapter
        mainView.addView(binding.formButtonlistContainer.rootView)
    }
    override fun addButton(text: String){
        val binding: FormButtonBinding = FormButtonBinding.inflate(LayoutInflater.from(context))
        binding.button.text = text
        mainView.addView(binding.formButtonContainer.rootView)
    }
    override fun addChecklistRemark(text: String, image: String){
        val binding: FormChecklistRemarkBinding = FormChecklistRemarkBinding.inflate(LayoutInflater.from(context))
        binding.textview.text = text
        binding.imageview.setImageResource(getDrawable(image))
        mainView.addView(binding.formChecklistremarkContainer.rootView)
    }

    override fun addImageWithCaption(text: String, image: String) {
        val binding: FormImageviewCaptionBinding =
            FormImageviewCaptionBinding.inflate(LayoutInflater.from(context))
        binding.imageview.setImageResource(getDrawable(image))
        binding.textviewCaption.text = text
        mainView.addView(binding.formImageviewCaptionContainer.rootView)
    }

    override fun clear() {
    }
}