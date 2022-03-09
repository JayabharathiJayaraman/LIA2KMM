package se.mobileinteraction.jordbruksverketkmm.android.forms

import android.app.Application
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import se.mobileinteraction.jordbruksverketkmm.android.MainApplication
import se.mobileinteraction.jordbruksverketkmm.android.R
import se.mobileinteraction.jordbruksverketkmm.forms.FormViewModel
import se.mobileinteraction.jordbruksverketkmm.android.databinding.*
import se.mobileinteraction.jordbruksverketkmm.forms.components.*

class AndroidFormGenerator(private val context: Context, private val viewModel: FormViewModel) : FormGenerator {
    private var mainView: LinearLayout = LinearLayout(context).also {
        val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        it.layoutParams = params
        it.orientation = LinearLayout.VERTICAL
    }

    override fun generateInterface(components: List<FormComponent>): View {
        clearScreenIfNecessary(components)

        for (component in components) {
            when (component.type) {
                ComponentType.TITLESMALL -> {
                    val titleSmall = (component as FormComponentText)
                    addSmallTitleLabel(titleSmall.text)
                }
                ComponentType.BODY -> {
                    val body = (component as FormComponentText)
                    mainView.createOrUpdateBodyLabel(body.text, body.id)
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
                ComponentType.IMAGE -> {
                    val image = (component as FormComponentImage)
                    mainView.addImage(image.image, image.caption)
                }
                ComponentType.TEXTFIELD -> {
                    val textField = (component as FormComponentTextField)
                    mainView.addTextField(textField.id, textField.text, textField.placeholder)
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

    private fun clearScreenIfNecessary(components: List<FormComponent>) {
        if (mainView.childCount > 0) {
            println("logg: CHILD COUNT > 0 in clearScreen")
            val shouldClearScreen = components.none { it.id == mainView.getChildAt(0).tag }
            if (shouldClearScreen) mainView.removeAllViews()
        }
    }
}

private fun ViewGroup.addBigTitleLabel(text: String) {
}

private fun ViewGroup.addSmallTitleLabel(text: String) {
    val binding: FormSmallTitleLabelBinding = FormSmallTitleLabelBinding.inflate(LayoutInflater.from(context))
    binding.bigTitleLabelTextview.text = text
    mainView.addView(binding.formSmallTitleLabelContainer.rootView)
}

private fun ViewGroup.createOrUpdateBodyLabel(text: String, id: String) {
    val binding: FormBodyLabelBinding = FormBodyLabelBinding.inflate(LayoutInflater.from(context))
    binding.bodyLabelTextview.text = text
    mainView.addView(binding.formBodyLabelContainer.rootView)
}

private fun ViewGroup.addTextField(id: String, text: String, placeholder: String) {
    println("logg: addTextField: $text")
    val editText = this.findViewWithTag<EditText>(id) ?: EditText(context).apply { tag = id }
        .also {
            it.setText(text)
            it.addTextChangedListener { editable ->
                println("logg: TEXT LISTENER: ${editable.toString()}")
                if (text != editable.toString()) getApplication().formViewModel.setTextData(id, editable.toString())
            }
            this.addView(it)
        }
    val binding: FormTextfieldBinding = FormTextfieldBinding.inflate(LayoutInflater.from(context))
    binding.formTextfield.hint = placeholder
    mainView.addView(binding.formTextfieldContainer.rootView)
}

private  fun viewGroup.addTextFieldNotes(id: String, text: String, placeholder: String) {
    val binding: FormTextfieldBinding = FormTextfieldBinding.inflate(LayoutInflater.from(context))
    binding.formTextfield.hint = placeholder
    mainView.addView(binding.formTextfieldContainer.rootView)
}

private fun ViewGroup.addButtonList(
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

private fun ViewGroup.addImage(imageName: String, caption: String) {
    val imageView = ImageView(context)
    imageView.setImageResource(getImageResource(imageName))

    this.addView(imageView)

    val textView = TextView(context)
    textView.text = caption

    this.addView(textView)
}
private fun ViewGroup.addButton(text: String){
    val binding: FormButtonBinding = FormButtonBinding.inflate(LayoutInflater.from(context))
    binding.button.text = text
    mainView.addView(binding.formButtonContainer.rootView)
}
private fun ViewGroup.addChecklistRemark(text: String, image: String){
    val binding: FormChecklistRemarkBinding = FormChecklistRemarkBinding.inflate(LayoutInflater.from(context))
    binding.textview.text = text
    binding.imageview.setImageResource(getDrawable(image))
    mainView.addView(binding.formChecklistremarkContainer.rootView)
}

private fun ViewGroup.addImageWithCaption(text: String, image: String) {
    val binding: FormImageviewCaptionBinding =
        FormImageviewCaptionBinding.inflate(LayoutInflater.from(context))
    binding.imageview.setImageResource(getDrawable(image))
    binding.textviewCaption.text = text
    mainView.addView(binding.formImageviewCaptionContainer.rootView)
}

override fun clear() {
}

private fun ViewGroup.getImageResource(name: String): Int {
    return context.resources.getIdentifier("drawable/$name", null, context.packageName)
}

private fun ViewGroup.getApplication(): MainApplication {
    return context.applicationContext as MainApplication
}