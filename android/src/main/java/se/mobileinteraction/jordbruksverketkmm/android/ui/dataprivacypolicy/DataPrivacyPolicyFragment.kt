package se.mobileinteraction.jordbruksverketkmm.android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import se.mobileinteraction.jordbruksverketkmm.android.databinding.FragmentDataPrivacyPolicyBinding


class DataPrivacyPolicyFragment : Fragment(R.layout.fragment_data_privacy_policy) {

    private var _binding: FragmentDataPrivacyPolicyBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDataPrivacyPolicyBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.closeLink.setOnClickListener {

        }

        binding.feedbackButton.setOnClickListener {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}