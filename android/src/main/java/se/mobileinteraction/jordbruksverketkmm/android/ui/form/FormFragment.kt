package se.mobileinteraction.jordbruksverketkmm.android.ui.form

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import se.mobileinteraction.jordbruksverketkmm.android.MainApplicationDagger
import se.mobileinteraction.jordbruksverketkmm.android.R
import se.mobileinteraction.jordbruksverketkmm.android.databinding.FragmentFormBinding
import se.mobileinteraction.jordbruksverketkmm.android.forms.AndroidFormGenerator
import se.mobileinteraction.jordbruksverketkmm.android.ui.menu.MenuFragment
import se.mobileinteraction.jordbruksverketkmm.forms.forms.FormGeneralQuestions
import se.mobileinteraction.jordbruksverketkmm.forms.FormViewModel
import se.mobileinteraction.jordbruksverketkmm.forms.components.FormComponent

class FormFragment : Fragment() {
    private var binding: FragmentFormBinding? = null
    private var formGenerator: AndroidFormGenerator? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        this.formGenerator = AndroidFormGenerator(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_form, container, false)
        binding = FragmentFormBinding.bind(view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = (activity?.application as MainApplicationDagger)

        lifecycleScope.launchWhenStarted {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                application.formViewModel.state.collect(::updateView)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

    private fun updateView(state: FormViewModel.State) {
        println("StateJV: $state")

        displayComponents(state.components)
    }

    private fun displayComponents(components: List<FormComponent>) {
        val mainView = formGenerator?.getInterface(components) as LinearLayout
        binding?.scrollView?.addView(mainView)
    }
}