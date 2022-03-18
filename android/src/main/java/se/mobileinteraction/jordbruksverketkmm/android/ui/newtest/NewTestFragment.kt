package se.mobileinteraction.jordbruksverketkmm.android.ui.newtest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import se.mobileinteraction.jordbruksverketkmm.android.MainApplication
import se.mobileinteraction.jordbruksverketkmm.android.R
import se.mobileinteraction.jordbruksverketkmm.android.databinding.FragmentNewTestBinding
import se.mobileinteraction.jordbruksverketkmm.forms.forms.FormType

class NewTestFragment : Fragment() {

    private var fragmentNewTestBinding: FragmentNewTestBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_new_test, container, false)
        val binding = FragmentNewTestBinding.bind(view)
        val application = (activity?.application as MainApplication)

        binding.gobackBtn.setOnClickListener {
            view.findNavController().navigateUp()
        }

        binding.test1Container.setOnClickListener {
            application.setViewModel(formType = FormType.GeneralQuestions)
            view.findNavController().navigate(R.id.navigateToFormFragment)
        }

        binding.test2Container.setOnClickListener {
            application.setViewModel(formType = FormType.SoilStructure)
            view.findNavController().navigate(R.id.navigateToFormFragment)
        }

        binding.test3Container.setOnClickListener {
            application.setViewModel(formType = FormType.Infiltration)
            view.findNavController().navigate(R.id.navigateToFormFragment)
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentNewTestBinding = null
    }
}