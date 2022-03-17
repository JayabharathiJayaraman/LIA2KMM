package se.mobileinteraction.jordbruksverketkmm.android.ui.dataprivacypolicy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import se.mobileinteraction.jordbruksverketkmm.android.R
import se.mobileinteraction.jordbruksverketkmm.android.databinding.FragmentDataPrivacyPolicyBinding

class DataPrivacyPolicyFragment : Fragment() {

    private var fragmentDataPrivacyPolicyBinding: FragmentDataPrivacyPolicyBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_data_privacy_policy, container, false)

        val binding = FragmentDataPrivacyPolicyBinding.bind(view)
        fragmentDataPrivacyPolicyBinding = binding

        binding.closeLink.setOnClickListener {
            view.findNavController().navigateUp()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentDataPrivacyPolicyBinding = null
    }
}