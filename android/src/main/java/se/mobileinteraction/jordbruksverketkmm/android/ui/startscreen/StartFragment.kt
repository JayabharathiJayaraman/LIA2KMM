package se.mobileinteraction.jordbruksverketkmm.android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import se.mobileinteraction.jordbruksverketkmm.android.databinding.FragmentStartBinding


class StartFragment : Fragment() {

    private var fragmentStartBinding: FragmentStartBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_start, container, false)

        val binding = FragmentStartBinding.bind(view)
        fragmentStartBinding = binding

        binding.textViewMenu.setOnClickListener{
            view.findNavController().navigate(R.id.navigateToMenuFragment)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentStartBinding = null
    }
}