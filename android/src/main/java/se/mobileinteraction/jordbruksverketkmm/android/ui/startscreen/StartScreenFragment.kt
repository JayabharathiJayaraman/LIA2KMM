package se.mobileinteraction.jordbruksverketkmm.android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView


class StartScreenFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {

        val startScreenFragmentView = inflater.inflate(R.layout.fragment_start_screen, container, false)
        return startScreenFragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuButton: ImageButton = view.findViewById(R.id.image_button_menu)


        val aboutApp: TextView = view.findViewById(R.id.aboutAppText)


        val testDoneButton: ImageButton = view.findViewById(R.id.image_test_done_button)


        val addTestButton: ImageButton = view.findViewById(R.id.image_add_test_button)


        val plantIconButton: ImageButton = view.findViewById(R.id.image_plant_icon_button)


        val welcomeLabel: TextView = view.findViewById(R.id.welcomeText)


        val mainLabel: TextView = view.findViewById(R.id.appNameText)


    }
}