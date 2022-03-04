package se.mobileinteraction.jordbruksverketkmm.android.ui.bottomnavbar

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import se.mobileinteraction.jordbruksverketkmm.android.MainApplicationDagger
import se.mobileinteraction.jordbruksverketkmm.android.R
import se.mobileinteraction.jordbruksverketkmm.android.databinding.FragmentBottomNavBarBinding


class BottomNavBarFragment : Fragment() {

    private var fragmentBottomNavBarBinding: FragmentBottomNavBarBinding? = null
    private val tmpInt1 = 1
    private val tmpInt2 = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_nav_bar, container, false)
        val binding = FragmentBottomNavBarBinding.bind(view)

        val application = (activity?.application as MainApplicationDagger)

        lifecycleScope.launchWhenStarted {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                //application.formViewModel.state.collect(::updateView)
            }
        }

        setContent(binding)

        binding.bottomNavbarBack.setOnClickListener {
            setContent(binding)
        }

        binding.bottomNavbarForward.setOnClickListener {
            setContent(binding)
        }

        return view
    }

    private fun setContent(binding: FragmentBottomNavBarBinding){
        setProgress(binding)
        setText(binding)
    }

    private fun setProgress(binding: FragmentBottomNavBarBinding){
        binding.progressLayout.removeAllViews()
        for (i in 0 until tmpInt2){
            val progressItem = ImageView(this.requireContext())
            progressItem.layoutParams = LinearLayout.LayoutParams(500/tmpInt2, 50)
            if(i <= tmpInt1){
                progressItem.setBackgroundResource(R.drawable.bottom_navbar_progress_filed)
            } else{
                progressItem.setBackgroundResource(R.drawable.bottom_navbar_progress_unfiled)
            }
            binding.progressLayout.addView(progressItem)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setText(binding: FragmentBottomNavBarBinding){
        binding.bottomNavbarProgressText.text = "$tmpInt1 av $tmpInt2"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentBottomNavBarBinding = null
    }
}