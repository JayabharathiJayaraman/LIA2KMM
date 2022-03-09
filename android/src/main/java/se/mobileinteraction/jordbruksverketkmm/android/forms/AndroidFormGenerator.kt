package se.mobileinteraction.jordbruksverketkmm.android.forms
import android.R
import android.app.Application
import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.*
import androidx.core.widget.addTextChangedListener
import se.mobileinteraction.jordbruksverketkmm.android.MainApplication
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

    override fun generateInterface(components: List<FormComponent>) {
        for (component in components) {
            when (component.type) {
                ComponentType.TITLESMALL -> {
                    val titleSmall = (component as FormComponentText)
                    mainView.addSmallTitleLabel(titleSmall.text)
                }
                ComponentType.BODY -> {
                    val body = (component as FormComponentText)
                    mainView.createOrUpdateBodyLabel(body.text, body.id)
                }
                ComponentType.TEXTFIELD ->{
                    val textField = (component as FormComponentTextField)
                    mainView.addTextField(textField.id, textField.text, textField.placeholder)
                }
                ComponentType.TEXTFIELDNOTES -> {
                    val textFieldNotes = (component as FormComponentTextField)
                    mainView.addTextFieldNotes(textFieldNotes.id, textFieldNotes.text, textFieldNotes.placeholder)
                }
                ComponentType.BUTTON -> {
                    val button = (component as FormComponentButton)
                    mainView.addButton(button.text)
                }
                ComponentType.BUTTONLIST -> {
                    val buttonList = (component as FormComponentButtonList)
                    mainView.addButtonList(buttonList.id, buttonList.title, buttonList.list,buttonList.value,buttonList.placeholder)
                }
                ComponentType.REMARK -> {
                    val remark = (component as FormComponentChecklist)
                    mainView.addChecklistRemark(remark.text, remark.image)
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
                    mainView.addImageWithCaption(imageWithCaption.text, imageWithCaption.image)
                }
                else -> println("unknown")
            }
        }
    }

    override fun createInterface(components: List<FormComponent>): View {
        generateInterface(components)

        return mainView
    }

    override fun updateInterface(components: List<FormComponent>, currentScreen: Int) {
        if (currentScreen != currentScreenRendered) {
            mainView.removeAllViews()
            currentScreenRendered = currentScreen
        }
        generateInterface(components)
    }
}

private fun ViewGroup.addBigTitleLabel(text: String) {
}

private fun ViewGroup.addSmallTitleLabel(text: String) {
    val binding: FormSmallTitleLabelBinding =
        FormSmallTitleLabelBinding.inflate(LayoutInflater.from(context))
    this.findViewWithTag(id) ?: binding.formSmallTitleLabelContainer.rootView.apply { tag = id }
        .also { this.addView(it) }
    binding.smallTitleLabelTextview.text = text
}

private fun ViewGroup.createOrUpdateBodyLabel(text: String, id: String) {
    val binding: FormBodyLabelBinding = FormBodyLabelBinding.inflate(LayoutInflater.from(context))
    this.findViewWithTag(id) ?: binding.formBodyLabelContainer.rootView.apply { tag = id }
    binding.bodyLabelTextview.text = text
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
    this.findViewWithTag(id) ?: binding.formTextfieldContainer.rootView.apply { tag = id }
    binding.formTextfield.hint = placeholder
}

private  fun ViewGroup.addTextFieldNotes(id: String, text: String, placeholder: String) {
    val binding: FormTextfieldNotesBinding =
        FormTextfieldNotesBinding.inflate(LayoutInflater.from(context))
    this.findViewWithTag(id) ?: binding.formTextfieldnotesContainer.rootView.apply { tag = id }
    binding.textfield.hint = placeholder
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
    this.findViewWithTag(id) ?: binding.formButtonlistContainer.rootView.apply { tag = id }
    binding.spinner.adapter = dataAdapter
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
    this.findViewWithTag(id) ?: binding.formButtonContainer.rootView.apply { tag = id }
    binding.button.text = text
}
private fun ViewGroup.addChecklistRemark(text: String, image: String){
    val binding: FormChecklistRemarkBinding = FormChecklistRemarkBinding.inflate(LayoutInflater.from(context))
    binding.textview.text = text
    binding.imageview.setImageResource(getImageResource(image))
    this.findViewWithTag(id) ?: binding.formChecklistremarkContainer.rootView.apply { tag = id }
}

private fun ViewGroup.addImageWithCaption(text: String, image: String) {
    val binding: FormImageviewCaptionBinding =
        FormImageviewCaptionBinding.inflate(LayoutInflater.from(context))
    binding.imageview.setImageResource(getImageResource(image))
    this.findViewWithTag(id) ?: binding.formImageviewCaptionContainer.rootView.apply { tag = id }
    binding.textviewCaption.text = text
}

private fun ViewGroup.getImageResource(name: String): Int {
    return context.resources.getIdentifier("drawable/$name", null, context.packageName)
}

private fun ViewGroup.getApplication(): MainApplication {
    return context.applicationContext as MainApplication
}