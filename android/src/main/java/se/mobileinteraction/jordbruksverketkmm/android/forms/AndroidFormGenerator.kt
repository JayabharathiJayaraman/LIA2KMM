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

class AndroidFormGenerator(private val context: Context) : FormGenerator {
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
                ComponentType.BODY -> {
                    val body = (component as FormComponentText)
                    mainView.createOrUpdateBodyLabel(body.text, body.id)
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
}

private fun ViewGroup.createOrUpdateBodyLabel(text: String, id: String) {
    val textView = this.findViewWithTag<TextView>(id) ?: TextView(context).apply { tag = id }
        .also { this.addView(it) }
    textView.text = text
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