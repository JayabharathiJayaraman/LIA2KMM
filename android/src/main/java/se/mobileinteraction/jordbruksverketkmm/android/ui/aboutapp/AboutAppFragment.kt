package se.mobileinteraction.jordbruksverketkmm.android.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import se.mobileinteraction.jordbruksverketkmm.android.R
import se.mobileinteraction.jordbruksverketkmm.android.databinding.FragmentAboutAppBinding

class AboutAppFragment : Fragment() {

    private var fragmentAboutAppBinding: FragmentAboutAppBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_about_app, container, false)
        val binding = FragmentAboutAppBinding.bind(view)
        fragmentAboutAppBinding = binding


        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentAboutAppBinding = null
    }
}


