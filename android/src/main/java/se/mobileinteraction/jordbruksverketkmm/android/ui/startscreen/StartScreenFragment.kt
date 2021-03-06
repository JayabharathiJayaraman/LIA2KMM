package se.mobileinteraction.jordbruksverketkmm.android.ui.startscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import se.mobileinteraction.jordbruksverketkmm.android.R
import se.mobileinteraction.jordbruksverketkmm.android.databinding.FragmentStartScreenBinding

class StartScreenFragment : Fragment() {

    private var fragmentStartScreenBinding: FragmentStartScreenBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_start_screen, container, false)

        val binding = FragmentStartScreenBinding.bind(view)
        fragmentStartScreenBinding = binding

        binding.imageButtonMenu.setOnClickListener{
            view.findNavController().navigate(R.id.navigateToMenu)
        }
        binding.imagePlantIconButton.setOnClickListener{
            view.findNavController().navigate(R.id.navigate_startScreenFragment_to_vardaMarkstrukturFragment)
        }

        binding.imageNewTestButton?.setOnClickListener {
            view.findNavController().navigate(R.id.navigate_from_startScreenFragment_to_newTestFragment)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentStartScreenBinding = null
    }
}