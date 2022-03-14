package se.mobileinteraction.jordbruksverketkmm.android.ui.bottomnavbar

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
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
        val totalScreens: Int = application.formViewModel.state.value.totalScreens

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

        view.post {
            setContent(binding, totalScreens, currentScreen)
        }

        return view
    }

    private fun setContent(
        binding: FragmentBottomNavBarBinding,
        totalScreens: Int,
        currentScreen: Int
    ) {
        setProgress(binding, totalScreens, currentScreen)
        setText(binding, totalScreens, currentScreen)
    }

    private fun setProgress(
        binding: FragmentBottomNavBarBinding,
        totalScreens: Int,
        currentScreen: Int
    ) {
        binding.progressLayout.removeAllViews()
        val usableContainerWidth = (binding.progressLayout.width * 0.8).toInt()
        for (i in 0 until totalScreens) {
            val progressItem = ImageView(this.requireContext())
            progressItem.layoutParams =
                LinearLayout.LayoutParams(usableContainerWidth / totalScreens, 50)
            (progressItem.layoutParams as LinearLayout.LayoutParams).setMargins(2, 0, 2, 0)
            progressItem.setBackgroundResource(if (i <= currentScreen) R.drawable.bottom_navbar_progress_filled else R.drawable.bottom_navbar_progress_unfilled)
            if (i == 0) {
                progressItem.background = getFirstDrawableWithRadius()
            }
            if (i == totalScreens - 1) {
                if (i == currentScreen) {
                    progressItem.background = getLastColoredDrawableWithRadius()
                } else {
                    progressItem.background = getLastDrawableWithRadius()
                }
            }
            binding.progressLayout.addView(progressItem)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setText(
        binding: FragmentBottomNavBarBinding,
        totalScreens: Int,
        currentScreen: Int
    ) {
        binding.bottomNavbarProgressText.text = "${currentScreen + 1} ${getString(R.string.bottom_navbar_text)} $totalScreens"
    }

    private fun getFirstDrawableWithRadius(): Drawable {
        val gradientDrawable = GradientDrawable()
        gradientDrawable.cornerRadii = floatArrayOf(20f, 20f, 0f, 0f, 0f, 0f, 20f, 20f)
        gradientDrawable.setColor(Color.parseColor("#CED7B2"))
        return gradientDrawable
    }

    private fun getLastDrawableWithRadius(): Drawable {
        val gradientDrawable = GradientDrawable()
        gradientDrawable.cornerRadii = floatArrayOf(0f, 0f, 20f, 20f, 20f, 20f, 0f, 0f)
        gradientDrawable.setColor(Color.parseColor("#EDF1E2"))
        return gradientDrawable
    }

    private fun getLastColoredDrawableWithRadius(): Drawable {
        val gradientDrawable = GradientDrawable()
        gradientDrawable.cornerRadii = floatArrayOf(0f, 0f, 20f, 20f, 20f, 20f, 0f, 0f)
        gradientDrawable.setColor(Color.parseColor("#CED7B2"))
        return gradientDrawable
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentBottomNavBarBinding = null
    }
}