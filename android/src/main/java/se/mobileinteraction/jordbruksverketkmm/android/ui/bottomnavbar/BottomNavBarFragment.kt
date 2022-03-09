package se.mobileinteraction.jordbruksverketkmm.android.ui.bottomnavbar

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import se.mobileinteraction.jordbruksverketkmm.android.MainApplication
import se.mobileinteraction.jordbruksverketkmm.android.R
import se.mobileinteraction.jordbruksverketkmm.android.databinding.FragmentBottomNavBarBinding

class BottomNavBarFragment : Fragment() {

    private var fragmentBottomNavBarBinding: FragmentBottomNavBarBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_nav_bar, container, false)
        val binding = FragmentBottomNavBarBinding.bind(view)

        val application = (activity?.application as MainApplication)
        var currentScreen: Int = application.formViewModel.state.value.currentScreen
        var totalScreens:Int = application.formViewModel.state.value.totalScreens

        setContent(binding, totalScreens, currentScreen)

        binding.bottomNavbarBack.setOnClickListener {

            application.formViewModel.previousScreen()
            currentScreen = application.formViewModel.state.value.currentScreen
            setContent(binding, totalScreens, currentScreen)
        }

        binding.bottomNavbarForward.setOnClickListener {
            application.formViewModel.nextScreen()
            currentScreen = application.formViewModel.state.value.currentScreen
            setContent(binding, totalScreens, currentScreen)
        }

        return view
    }

    private fun setContent(binding: FragmentBottomNavBarBinding, totalScreens: Int, currentScreen: Int){
        setProgress(binding, totalScreens, currentScreen)
        setText(binding, totalScreens, currentScreen)
    }

    private fun setProgress(binding: FragmentBottomNavBarBinding, totalScreens: Int, currentScreen: Int){
        binding.progressLayout.removeAllViews()
        for (i in 0 until totalScreens){
            val progressItem = ImageView(this.requireContext())
//            progressItem.layoutParams = LinearLayout.LayoutParams(progressItem.layoutParams.width/totalScreens, R.dimen.form_progressbar_height)
            progressItem.setBackgroundResource(if (i <= currentScreen) R.drawable.bottom_navbar_progress_filled else R.drawable.bottom_navbar_progress_unfilled)
            binding.progressLayout.addView(progressItem)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setText(binding: FragmentBottomNavBarBinding, totalScreens: Int, currentScreen: Int){
        binding.bottomNavbarProgressText.text = "${currentScreen + 1} av $totalScreens"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentBottomNavBarBinding = null
    }
}