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
import se.mobileinteraction.jordbruksverketkmm.android.MainApplication
import se.mobileinteraction.jordbruksverketkmm.android.R
import se.mobileinteraction.jordbruksverketkmm.android.databinding.FragmentFormBinding
import se.mobileinteraction.jordbruksverketkmm.android.forms.AndroidFormGenerator
import se.mobileinteraction.jordbruksverketkmm.forms.FormViewModel
import se.mobileinteraction.jordbruksverketkmm.forms.components.FormComponent
import se.mobileinteraction.jordbruksverketkmm.forms.forms.FormInfiltrations

class FormFragment : Fragment() {

    private val viewModel: FormViewModel = FormViewModel(form = FormInfiltrations())
    private var binding: FragmentFormBinding? = null
    private var formGenerator: AndroidFormGenerator? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val viewModel = (activity?.application as MainApplication).formViewModel
        //this.formGenerator = AndroidFormGenerator(context)
        this.formGenerator = AndroidFormGenerator(context, viewModel)
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

        val application = (activity?.application as MainApplication)
        val viewModel = application.formViewModel

        lifecycleScope.launchWhenStarted {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect(::updateView)
            }
        }
        binding?.btnNextScreen?.setOnClickListener {
            viewModel.nextScreen()
        }

        binding?.btnPreviousScreen?.setOnClickListener {
            viewModel.previousScreen()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

    private fun updateView(state: FormViewModel.State) {
        println("StateJV: $state")
        //addForm(state.components)
        displayComponents(state.components)
    }

    private fun displayComponents(components: List<FormComponent>) {
        if (binding?.scrollView?.childCount == 0) {
            binding?.scrollView?.addView(formGenerator?.createInterface(components))
        } else {
            formGenerator?.updateInterface(components)
        }
    private fun addForm(components: List<FormComponent>) {
        val mainView = formGenerator?.getInterface(components) as LinearLayout
        binding?.scrollView?.removeAllViewsInLayout()
        binding?.scrollView?.addView(mainView)
    }
}