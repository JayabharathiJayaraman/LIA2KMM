package se.mobileinteraction.jordbruksverketkmm.android.forms

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import se.mobileinteraction.jordbruksverketkmm.android.R
import se.mobileinteraction.jordbruksverketkmm.forms.components.*


class AndroidFormGenerator(val context: Context) : FormGenerator {
    var mainView: LinearLayout

    init {
        val linearLayout = LinearLayout(context)
        this.mainView = linearLayout
    }

    override fun generateInterface(components: List<FormComponent>): View {
        val linearLayout = LinearLayout(context)
        val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        linearLayout.layoutParams = params
        linearLayout.orientation = LinearLayout.VERTICAL

        mainView = linearLayout

        for (component in components) {
            when (component.type) {
                ComponentType.BODY -> {
                    val body = (component as FormComponentText)
                    addBodyLabel(body.text)
                }
                ComponentType.IMAGE -> {
                    val image = (component as FormComponentImage)
                    addImage(image.image, image.caption)
                }
                else -> println("unknown")
            }
        }

        return mainView
    }

    fun addBigTitleLabel(text: String) {
    }

    fun addSmallTitleLabel(text: String) {
    }

    fun addBodyLabel(text: String) {
        val textView = TextView(context)
        textView.text = text

        mainView.addView(textView)
    }

    fun addTextField(id: String, text: String, placeholder: String) {
    }

    fun addButtonList(
        id: String,
        title: String,
        list: List<String>,
        value: String,
        placeholder: String
    ) {
    }

    fun addImage(imageName: String, caption: String) {
        val imageView = ImageView(context)
        imageView.setImageResource(getImageResource(imageName))

        mainView.addView(imageView)

        val textView = TextView(context)
        textView.text = caption

        mainView.addView(textView)
    }

    fun getImageResource(name: String): Int {
        return context.resources.getIdentifier("drawable/$name", null, context.packageName)
    }

    fun clear() {
    }
}