package se.mobileinteraction.jordbruksverketkmm.android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast


class StartScreenFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {

        val startScreenFragmentView = inflater.inflate(R.layout.fragment_start_screen, container, false)
        return startScreenFragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuButton: ImageButton = view.findViewById(R.id.image_button_menu)

        menuButton.setOnClickListener { Toast.makeText(view.context, "Test: Meny", Toast.LENGTH_SHORT).show() }

        val aboutApp: TextView = view.findViewById(R.id.textView3)

        aboutApp.setOnClickListener { Toast.makeText(view.context, "Test: aboutApp", Toast.LENGTH_SHORT).show() }

        val testDoneButton: ImageButton = view.findViewById(R.id.image_test_done_button)

        testDoneButton.setOnClickListener { Toast.makeText(view.context, "Test: test done", Toast.LENGTH_SHORT).show() }

        val addTestButton: ImageButton = view.findViewById(R.id.image_add_test_button)

        addTestButton.setOnClickListener { Toast.makeText(view.context, "Test: add test", Toast.LENGTH_SHORT).show() }

        val plantIconButton: ImageButton = view.findViewById(R.id.image_plant_icon_button)

        plantIconButton.setOnClickListener { Toast.makeText(view.context, "Test: plant icon", Toast.LENGTH_SHORT).show() }

        val welcomeLabel: TextView = view.findViewById(R.id.text_view1)
        welcomeLabel.text = welcome()

        val mainLabel: TextView = view.findViewById(R.id.text_view)
        mainLabel.text = appName()

    }
}