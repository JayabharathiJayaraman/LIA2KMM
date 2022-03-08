package se.mobileinteraction.jordbruksverketkmm.android.forms

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
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
                ComponentType.TITLEBIG -> {
                    val bigTitle = (component as FormComponentText)
                    addBigTitleLabel(bigTitle.text)
                }
                ComponentType.TITLESMALL -> {
                    val smallTitle = (component as FormComponentText)
                    addSmallTitleLabel(smallTitle.text)
                }
                ComponentType.BODY -> {
                    val body = (component as FormComponentText)
                    addBodyLabel(body.text)
                }
                ComponentType.TEXTFIELD -> {
                    val textField = (component as FormComponentTextField)
                    addTextField(textField.id, textField.text, textField.placeholder)
                }
                ComponentType.BUTTON -> {
                    val button = (component as FormComponentButton)
                    addButton(button.text)
                }
                ComponentType.IMAGE -> {
                    val image = (component as FormComponentImage)
                    //addImage(image.image, image.caption)
                    addImageWithCaption(image.image,image.caption)
                }
                else -> println("unknown")
            }
        }

        return mainView
    }

    override fun addBigTitleLabel(text: String) {
        val binding: FormBigTitleLabelBinding = FormBigTitleLabelBinding.inflate(LayoutInflater.from(context))
        binding.bigTitleLabelTextview.text = text
        mainView.addView(binding.formBigTitleLabelContainer.rootView)
    }

    override fun addSmallTitleLabel(text: String) {
        val binding: FormSmallTitleLabelBinding = FormSmallTitleLabelBinding.inflate(LayoutInflater.from(context))
        binding.bigTitleLabelTextview.text = text
        mainView.addView(binding.formBigTitleLabelContainer.rootView)
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

     fun addButton(text: String){
        val binding: FormButtonBinding = FormButtonBinding.inflate(LayoutInflater.from(context))
        binding.button.text = text
        mainView.addView(binding.formButtonContainer.rootView)
    }

    override fun addButtonList(
        id: String,
        title: String,
        list: List<String>,
        value: String,
        placeholder: String
    ) {
    }

    fun addImage(imageName: String, caption: String) {
      /*  val imageView = ImageView(context)
        imageView.setImageResource(getImageResource(imageName))

        mainView.addView(imageView)

        val textView = TextView(context)
        textView.text = caption

        mainView.addView(textView)*/

        val binding: FormImageViewBinding = FormImageViewBinding.inflate(LayoutInflater.from(context))
        binding.imageview.setImageResource(getDrawable(imageName))
        mainView.addView(binding.formImageviewContainer.rootView)
    }

    fun addImageWithCaption(imageName: String, caption: String)
    {
      /*  val binding: FormImageviewCaptionBinding = FormImageviewCaptionBinding.inflate(
            LayoutInflater.from(context))

        val smallImage  = binding.imageview.setImageResource(getDrawable(imageName))
        binding.textviewCaption.text = caption

        if(binding.imageview.parent != null){
            (binding.imageview.parent as ViewGroup).removeView(binding.imageview)
        }
        binding.linearLayout.addView(smallImage as View)

        if (binding.textviewCaption.parent != null) {
            (binding.textviewCaption.parent as ViewGroup).removeView(binding.textviewCaption) // <- fix
        }
        binding.linearLayout.addView(binding.textviewCaption)
        binding.formImageviewCaptionContainer.addView(binding.linearLayout)
        mainView.addView(binding.formImageviewCaptionContainer.rootView)*/

        val binding: FormImageviewCaptionBinding = FormImageviewCaptionBinding.inflate(
            LayoutInflater.from(context))
        binding.imageview.setImageResource(getDrawable(imageName))
        binding.textviewCaption.text = caption
        mainView.addView(binding.formImageviewCaptionContainer.rootView)
    }

    fun getImageResource(name: String): Int {
        return context.resources.getIdentifier("drawable/$name", null, context.packageName)
    }

    override fun clear() {
    }
}