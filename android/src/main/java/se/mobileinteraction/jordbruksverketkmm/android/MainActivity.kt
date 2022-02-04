package se.mobileinteraction.jordbruksverketkmm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import se.mobileinteraction.jordbruksverketkmm.Greeting
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import se.mobileinteraction.jordbruksverketkmm.android.fragments.AboutAppFragment


fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayAboutApp()
    }


    private fun displayAboutApp(){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val aboutAppFragment = AboutAppFragment()
        fragmentTransaction.add(R.id.frameLayout, aboutAppFragment).commit()
    }
}
