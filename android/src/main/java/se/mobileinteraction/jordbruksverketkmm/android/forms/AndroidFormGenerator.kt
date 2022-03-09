package se.mobileinteraction.jordbruksverketkmm.android.forms

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.widget.*
import se.mobileinteraction.jordbruksverketkmm.android.R
import se.mobileinteraction.jordbruksverketkmm.android.databinding.*
import se.mobileinteraction.jordbruksverketkmm.forms.components.*


class AndroidFormGenerator(val context: Context) : FormGenerator {
    var mainView: LinearLayout
    val innerImageLayout = LinearLayout(context)

    init {
        val linearLayout = LinearLayout(context)
        this.mainView = linearLayout
    }

    private fun getDrawable(image: String): Int {
        return context.resources.getIdentifier(image, "drawable", context.packageName)
    }

    @SuppressLint("ResourceAsColor")
    override fun getInterface(components: List<FormComponent>): Any {
        val linearLayout = LinearLayout(context)
        val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        linearLayout.layoutParams = params
        linearLayout.orientation = LinearLayout.VERTICAL
        mainView = linearLayout

        val innerImageLayoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        innerImageLayout.layoutParams = innerImageLayoutParams
        innerImageLayout.orientation = LinearLayout.HORIZONTAL
        innerImageLayout.weightSum= 3.0F

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
                ComponentType.BUTTONLIST -> {
                    val buttonList = (component as FormComponentButtonList)
                    addButtonList(buttonList.id, buttonList.title, buttonList.list,buttonList.value,buttonList.placeholder)
                }
                ComponentType.IMAGE -> {
                    val image = (component as FormComponentImage)
                    addImage(image.image, image.caption)
                }
                ComponentType.CAPTIONEDIMAGE -> {
                    val image = (component as FormComponentImage)
                    addImageWithCaption(image.image,image.caption)
                }
                ComponentType.CHECKLIST -> {
                    val checklist = (component as FormComponentChecklist)
                    addCheckListItem(checklist.text, checklist.image)
                }
                ComponentType.TIMEFIELD -> {
                    val time = (component as FormComponentTime)
                    addTimeComponent(time.start,time.stopp)
                }
                ComponentType.EMPTYLINE -> {
                    val line = (component as FormComponentLine)
                    addDividerLine(line.text)
                }
                ComponentType.TEXTFIELDNOTES -> {
                    val textFieldNotes = (component as FormComponentTextField)
                    addTextFieldNotes(
                        textFieldNotes.id,
                        textFieldNotes.text,
                        textFieldNotes.placeholder
                    )
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
       // val binding: FormButtonBinding = FormButtonBinding.inflate(LayoutInflater.from(context))
         val binding: FormButtonArrowBinding = FormButtonArrowBinding.inflate(LayoutInflater.from(context))
        binding.button.text = text
        mainView.addView(binding.formButtonContainer.rootView)
    }

    override fun addButtonList(id: String, title: String, list: List<String>, value: String, placeholder: String)
    {
        val binding: FormButtonListBinding = FormButtonListBinding.inflate(LayoutInflater.from(context))
        val dataAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, list)
        binding.spinner.adapter = dataAdapter
        mainView.addView(binding.formButtonlistContainer.rootView)
    }

    fun addCheckListItem(text: String, image: String){
        val binding: FormChecklistitemBinding = FormChecklistitemBinding.inflate(LayoutInflater.from(context))
        binding.formRadiobtn.text = text
        mainView.addView(binding.formRadiobtnContainer.rootView)
    }

    fun addImage(imageName: String, caption: String) {
        val binding: FormImageViewBinding = FormImageViewBinding.inflate(LayoutInflater.from(context))
        binding.imageview.setImageResource(getDrawable(imageName))
        mainView.addView(binding.formImageviewContainer.rootView)
    }

    fun addTimeComponent(start: String, stop: String){
        val binding: FormTimeTextviewBinding = FormTimeTextviewBinding.inflate(LayoutInflater.from(context))
        mainView.addView(binding.timeViewContainer.rootView)
    }

    fun addDividerLine(text:String){
        val binding: FormDividerTextviewBinding = FormDividerTextviewBinding.inflate(LayoutInflater.from(context))
        binding.dividerLine.text = text
        mainView.addView(binding.dividerLineContainer.rootView)
    }

    fun addTextFieldNotes(id: String, text: String, placeholder: String) {
        val binding: FormTextfieldNotesBinding =
            FormTextfieldNotesBinding.inflate(LayoutInflater.from(context))
        binding.textfield.hint = placeholder
        mainView.addView(binding.formTextfieldnotesContainer.rootView)
    }

    @SuppressLint("ResourceAsColor")
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



//        val binding: FormImageviewCaptionBinding = FormImageviewCaptionBinding.inflate(
//            LayoutInflater.from(context))
//

        val innerVerticalLayout = LinearLayout(context)
        val innerParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        innerParams.width = 0
        innerParams.weight = 1.0F

        innerVerticalLayout.layoutParams = innerParams
        innerVerticalLayout.orientation = LinearLayout.VERTICAL
        innerVerticalLayout.setBackgroundColor(R.color.colorPrimary)
        val imageView = ImageView(context)
        imageView.layoutParams = LinearLayout.LayoutParams(150,150)
        imageView.setImageResource(getDrawable(imageName))

        innerVerticalLayout.addView(imageView)

        val imageCaption= TextView(context)
        imageCaption.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        imageCaption.text = caption
        innerVerticalLayout.addView(imageCaption)
        innerImageLayout.addView(innerVerticalLayout)
        if(innerImageLayout.parent == null) {
            Log.d("???","Parent is  null")
            mainView.addView(innerImageLayout)
        }

//        binding.imageview.setImageResource(getDrawable(imageName))
//        binding.textviewCaption.text = caption
//        mainView.addView(binding.formImageviewCaptionContainer.rootView)
    }

    override fun clear() {
    }
}