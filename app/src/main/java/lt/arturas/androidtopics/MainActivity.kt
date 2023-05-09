package lt.arturas.androidtopics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import timber.log.Timber

class MainActivity : ActivityLifecycles() {

    lateinit var openSecondActivityButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openSecondActivityButton = findViewById(R.id.button)

        setClickOpenSecondActivity()
    }

    private fun setClickOpenSecondActivity() {
        openSecondActivityButton.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }
}
