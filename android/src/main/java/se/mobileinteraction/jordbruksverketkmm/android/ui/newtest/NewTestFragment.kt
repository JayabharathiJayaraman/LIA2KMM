package se.mobileinteraction.jordbruksverketkmm.android.ui.newtest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import se.mobileinteraction.jordbruksverketkmm.android.R
import se.mobileinteraction.jordbruksverketkmm.android.databinding.FragmentNewTestBinding

class NewTestFragment : Fragment() {

    private var fragmentNewTestBinding: FragmentNewTestBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_new_test, container, false)
        val binding = FragmentNewTestBinding.bind(view)

        binding.gobackBtn.setOnClickListener {
            view.findNavController().navigateUp()
        }

        binding.test1Container.setOnClickListener {

        }

        binding.test2Container.setOnClickListener {

        }

        binding.test3Container.setOnClickListener {

        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentNewTestBinding = null
    }
}