package se.mobileinteraction.jordbruksverketkmm.android.forms

import android.content.Context
import android.widget.LinearLayout
import android.widget.TextView
import se.mobileinteraction.jordbruksverketkmm.forms.components.ComponentType
import se.mobileinteraction.jordbruksverketkmm.forms.components.FormComponent
import se.mobileinteraction.jordbruksverketkmm.forms.components.FormComponentText
import se.mobileinteraction.jordbruksverketkmm.forms.components.FormGenerator

class AndroidFormGenerator(val context: Context) : FormGenerator {
    var mainView: LinearLayout

    init {
        this.mainView = LinearLayout(context)
    }

    override fun addBigTitleLabel(text: String) {
    }

    override fun addSmallTitleLabel(text: String) {
    }

    override fun addBodyLabel(text: String) {
        val textView = TextView(context)
        textView.text = text

        mainView.addView(textView)
    }

    override fun addTextField(id: String, text: String, placeholder: String) {
    }

    override fun addButtonList(
        id: String,
        title: String,
        list: List<String>,
        value: String,
        placeholder: String
    ) {
    }

    override fun clear() {
    }

    override fun getInterface(components: List<FormComponent>): Any {
        mainView = LinearLayout(context)

        for (component in components) {
            when (component.type) {
                ComponentType.BODY -> {
                    val body = (component as FormComponentText)
                    addBodyLabel(body.text)
                }
                else -> println("unknown")
            }
        }

        return mainView
    }
}