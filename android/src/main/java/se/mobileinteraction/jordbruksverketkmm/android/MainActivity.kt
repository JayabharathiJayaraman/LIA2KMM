package se.mobileinteraction.jordbruksverketkmm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import se.mobileinteraction.jordbruksverketkmm.Greeting
import android.widget.TextView

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        //tv.text = greet()

        displayAboutApp()
    }

    private fun displayAboutApp(){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val aboutAppFragment = AboutAppFragment()
        fragmentTransaction.add(R.id.frameLayout, aboutAppFragment).commit()
    }
}
