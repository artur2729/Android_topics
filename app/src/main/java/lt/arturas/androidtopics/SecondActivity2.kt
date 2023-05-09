package lt.arturas.androidtopics

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

class SecondActivity2 : ActivityLifecycles() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val id = intent.getIntExtra(MainActivity.MAIN_ACTIVITY_ITEM_ID, -1)
        val text01 = intent.getStringExtra(MainActivity.MAIN_ACTIVITY_ITEM_TEXT01)
        val text02 = intent.getStringExtra(MainActivity.MAIN_ACTIVITY_ITEM_TEXT02)
    }
}