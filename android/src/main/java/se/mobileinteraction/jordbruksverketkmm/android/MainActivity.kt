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
        tv.text = greet()

        val settingsFragment = SettingsFragment()

        val transaction = supportFragmentManager.beginTransaction()

        transaction.replace(R.id.main_view, settingsFragment, "settingsFragment")
        transaction.commit()

    }
}
