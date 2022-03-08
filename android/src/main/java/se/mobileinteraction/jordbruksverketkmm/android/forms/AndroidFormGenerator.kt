package se.mobileinteraction.jordbruksverketkmm.android.forms

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import se.mobileinteraction.jordbruksverketkmm.android.R
import se.mobileinteraction.jordbruksverketkmm.forms.components.*

class AndroidFormGenerator(val context: Context) : FormGenerator {
    override fun generateInterface(components: List<FormComponent>): View {
        val mainView = LinearLayout(context)
        val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        mainView.layoutParams = params
        mainView.orientation = LinearLayout.VERTICAL

        for (component in components) {
            when (component.type) {
                ComponentType.BODY -> {
                    val body = (component as FormComponentText)
                    mainView.addBodyLabel(body.text)
                }
                ComponentType.IMAGE -> {
                    val image = (component as FormComponentImage)
                    mainView.addImage(image.image, image.caption)
                }
                else -> println("unknown")
            }
        }

        return mainView
    }
}

private fun ViewGroup.addBigTitleLabel(text: String) {
}

private fun ViewGroup.addSmallTitleLabel(text: String) {
}

private fun ViewGroup.addBodyLabel(text: String) {
    val textView = TextView(context)
    textView.text = text

    this.addView(textView)
}

private fun ViewGroup.addTextField(id: String, text: String, placeholder: String) {
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