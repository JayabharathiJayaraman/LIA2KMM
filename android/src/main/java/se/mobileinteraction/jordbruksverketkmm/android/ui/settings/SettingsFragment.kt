package se.mobileinteraction.jordbruksverketkmm.android.ui.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import se.mobileinteraction.jordbruksverketkmm.android.R
import se.mobileinteraction.jordbruksverketkmm.android.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var fragmentSettingsBinding: FragmentSettingsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        val binding = FragmentSettingsBinding.bind(view)
        fragmentSettingsBinding = binding

        binding.mydetailsSaveBtn.setOnClickListener {

            view.findNavController().navigateUp()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentSettingsBinding = null
    }
}