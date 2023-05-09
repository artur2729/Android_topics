package lt.arturas.androidtopics

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

class SecondActivity2 : ActivityLifecycles() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second2)
        timber("onCreate")
    }
}