package se.mobileinteraction.jordbruksverketkmm.android.ui.knowledebank

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import se.mobileinteraction.jordbruksverketkmm.android.R
import se.mobileinteraction.jordbruksverketkmm.android.databinding.DialogBinding
import se.mobileinteraction.jordbruksverketkmm.android.databinding.FragmentKnowledgeBankBinding
import se.mobileinteraction.jordbruksverketkmm.android.databinding.KnowledgebankButtonBinding
import se.mobileinteraction.jordbruksverketkmm.android.forms.AndroidFormGenerator
import se.mobileinteraction.jordbruksverketkmm.forms.components.FormComponent
import se.mobileinteraction.jordbruksverketkmm.forms.information.InformationScreens

class KnowledgeBank : Fragment() {

    private var bindingKnowledgeBank: FragmentKnowledgeBankBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_knowledge_bank, container, false)
        val binding = FragmentKnowledgeBankBinding.bind(view)
        bindingKnowledgeBank = binding

        displayButtons(binding)
        return view
    }

    private fun displayButtons(binding: FragmentKnowledgeBankBinding) {
        val list = InformationScreens().screens
        val usedLetters = arrayListOf("")

        list.forEach {
            val buttonBinding: KnowledgebankButtonBinding =
                KnowledgebankButtonBinding.inflate(LayoutInflater.from(context))

            if (!usedLetters.contains(it.title.first().toString())) {
                usedLetters.add(it.title.first().toString())
                buttonBinding.letter.text = it.title.first().toString()

            } else {
                buttonBinding.letter.visibility = View.GONE
            }

            buttonBinding.button.text = it.title
            handleButtonClick(buttonBinding, it.components)

            binding.linearLayoutContainer.addView(buttonBinding.buttonContainer)
        }
    }

    private fun handleButtonClick(
        binding: KnowledgebankButtonBinding, components: List<FormComponent>
    ) {
        binding.button.setOnClickListener {
            val formGenerator = AndroidFormGenerator(requireContext())
            val componentsView = formGenerator.createInterface(components)
            createDialog(componentsView)
        }
    }

    private fun createDialog(componentsView: View) {
        val dialogBinding = DialogBinding.inflate(LayoutInflater.from(context))
        dialogBinding.componentScrollview.addView(componentsView)

        val customDialog = AlertDialog.Builder(context).create()
        customDialog.setView(dialogBinding.root)

        val params = WindowManager.LayoutParams()
        val height = (resources.displayMetrics.heightPixels * 0.85).toInt()
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.height = height
        params.gravity = Gravity.TOP

        customDialog.show()
        customDialog.window?.attributes = params

        dialogBinding.closeButton.setOnClickListener {
            customDialog.dismiss()
        }
    }
}