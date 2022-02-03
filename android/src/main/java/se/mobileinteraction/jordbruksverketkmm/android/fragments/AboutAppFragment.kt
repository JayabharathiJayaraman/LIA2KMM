package se.mobileinteraction.jordbruksverketkmm.android.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import se.mobileinteraction.jordbruksverketkmm.android.R

class AboutAppFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {

        val aboutAppFragmentView = inflater.inflate(R.layout.fragment_about_app, container, false)
        return aboutAppFragmentView
    }
}