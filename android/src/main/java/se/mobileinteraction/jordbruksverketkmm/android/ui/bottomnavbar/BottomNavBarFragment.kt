package se.mobileinteraction.jordbruksverketkmm.android.ui.bottomnavbar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import se.mobileinteraction.jordbruksverketkmm.android.R
import se.mobileinteraction.jordbruksverketkmm.android.databinding.FragmentBottomNavBarBinding


class BottomNavBarFragment : Fragment() {

    private var fragmentBottomNavBarBinding: FragmentBottomNavBarBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_nav_bar, container, false)
        val binding = FragmentBottomNavBarBinding.bind(view)
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentBottomNavBarBinding = null
    }
}