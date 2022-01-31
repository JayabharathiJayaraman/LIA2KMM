package se.mobileinteraction.jordbruksverketkmm.android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import se.mobileinteraction.jordbruksverketkmm.android.databinding.FragmentMenuBinding


class MenuFragment : Fragment() {

    private var fragmentMenuBinding: FragmentMenuBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        val binding = FragmentMenuBinding.bind(view)
        fragmentMenuBinding = binding

        binding.textViewClose.setOnClickListener {
            view.findNavController().navigate(R.id.navigateToStartFragment)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentMenuBinding = null
    }
}