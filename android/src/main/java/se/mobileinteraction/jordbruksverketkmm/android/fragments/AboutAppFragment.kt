package se.mobileinteraction.jordbruksverketkmm.android.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import se.mobileinteraction.jordbruksverketkmm.android.R
import se.mobileinteraction.jordbruksverketkmm.android.databinding.CustomToolbarBinding
import se.mobileinteraction.jordbruksverketkmm.android.databinding.FragmentAboutAppBinding

class AboutAppFragment : Fragment() {

    private var aboutAppBinding : FragmentAboutAppBinding? = null
    private val binding get() = aboutAppBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {

        aboutAppBinding = FragmentAboutAppBinding.inflate(inflater,container,false)
        val aboutAppFragmentView = binding.root

        displayToolbar()
        return aboutAppFragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        aboutAppBinding!!.topAppBar.toolbarNavigationText.setOnClickListener {
            Toast.makeText(context,"Back clicked",Toast.LENGTH_LONG).show()
        }
    }

    private fun displayToolbar()
    {
        aboutAppBinding!!.topAppBar.customMenuItemToolbar.setTitle("")
        aboutAppBinding!!.topAppBar.toolbarTitleText.setText(getString(R.string.about_app_fragment_title))
        aboutAppBinding!!.topAppBar.toolbarNavigationText.setText(getString(R.string.toolbar_navigation_back))
        (context as AppCompatActivity).setSupportActionBar(aboutAppBinding!!.topAppBar.customMenuItemToolbar)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        aboutAppBinding = null
    }
}