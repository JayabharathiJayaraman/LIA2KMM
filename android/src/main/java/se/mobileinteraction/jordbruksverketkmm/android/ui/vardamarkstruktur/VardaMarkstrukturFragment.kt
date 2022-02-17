package se.mobileinteraction.jordbruksverketkmm.android.ui.vardamarkstruktur

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import se.mobileinteraction.jordbruksverketkmm.android.R
import se.mobileinteraction.jordbruksverketkmm.android.databinding.FragmentVardaMarkstrukturBinding


class VardaMarkstrukturFragment : Fragment() {

    private var fragmentVardaMarkstrukturBinding: FragmentVardaMarkstrukturBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_varda_markstruktur, container, false)
        val binding = FragmentVardaMarkstrukturBinding.bind(view)
        fragmentVardaMarkstrukturBinding = binding
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentVardaMarkstrukturBinding = null
    }
}