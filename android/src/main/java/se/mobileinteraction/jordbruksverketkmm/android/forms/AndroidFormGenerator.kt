package se.mobileinteraction.jordbruksverketkmm.android.forms

import android.app.Application
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import se.mobileinteraction.jordbruksverketkmm.android.MainApplication
import se.mobileinteraction.jordbruksverketkmm.android.R
import se.mobileinteraction.jordbruksverketkmm.forms.FormViewModel
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
    private var currentScreenRendered: Int = 0

    override fun generateInterface(components: List<FormComponent>) {
        for (component in components) {
            when (component.type) {
                ComponentType.BODY -> {
                    val body = (component as FormComponentText)
                    mainView.createOrUpdateBodyLabel(body.text, body.id)
                }
                ComponentType.IMAGE -> {
                    val image = (component as FormComponentImage)
                    mainView.addImage(image.image, image.caption)
                }
                ComponentType.TEXTFIELD -> {
                    val textField = (component as FormComponentTextField)
                    mainView.addTextField(textField.id, textField.text, textField.placeholder)
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
}

private fun ViewGroup.createOrUpdateBodyLabel(text: String, id: String) {
    val textView = this.findViewWithTag<TextView>(id) ?: TextView(context).apply { tag = id }
        .also { this.addView(it) }
    textView.text = text
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
}

private fun ViewGroup.addButtonList(
    id: String,
    title: String,
    list: List<String>,
    value: String,
    placeholder: String
) {
}

private fun ViewGroup.addImage(imageName: String, caption: String) {
    val imageView = ImageView(context)
    imageView.setImageResource(getImageResource(imageName))

    this.addView(imageView)

    val textView = TextView(context)
    textView.text = caption

    this.addView(textView)
}

private fun ViewGroup.getImageResource(name: String): Int {
    return context.resources.getIdentifier("drawable/$name", null, context.packageName)
}

private fun ViewGroup.getApplication(): MainApplication {
    return context.applicationContext as MainApplication
}