package se.mobileinteraction.jordbruksverketkmm.android.ui.vardamarkstruktur

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
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

        binding.tempBack.setOnClickListener {
            view.findNavController().navigateUp()
        }

        binding.tempForward.setOnClickListener {
            val amount = "Grundförbättringar"
            val bundle = bundleOf("amount" to amount)
            view.findNavController().navigate(R.id.navigateTocheckList, bundle)
        }

        binding.grunforbattringarutton.setOnClickListener {
            val amount = "Grundförbättringar"
            val bundle = bundleOf("amount" to amount)
            view.findNavController().navigate(R.id.navigateTocheckList, bundle)
        }

        binding.odlingsatgarderButton.setOnClickListener {
            val amount = "Odlingsåtgärder"
            val bundle = bundleOf("amount" to amount)
            view.findNavController().navigate(R.id.navigateTocheckList, bundle)
        }

        binding.undvikEllerMinimeraButton.setOnClickListener {
            val amount = "UndvikEllerMinimera"
            val bundle = bundleOf("amount" to amount)
            view.findNavController().navigate(R.id.navigateTocheckList, bundle)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentVardaMarkstrukturBinding = null
    }
}