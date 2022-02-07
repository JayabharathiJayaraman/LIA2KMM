package se.mobileinteraction.jordbruksverketkmm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import se.mobileinteraction.jordbruksverketkmm.MainLabel
import android.widget.TextView
import android.widget.Toast
import se.mobileinteraction.jordbruksverketkmm.WelcomeLabel


fun welcome() : String {
    return WelcomeLabel().welcomeLabel()
}

fun appName(): String {
    return MainLabel().mainLabel()
}



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayStartScreen()


    }

    private fun displayStartScreen(){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val startScreenFragment = StartScreenFragment()
        fragmentTransaction.add(R.id.frameLayout, startScreenFragment).commit()
    }
}
