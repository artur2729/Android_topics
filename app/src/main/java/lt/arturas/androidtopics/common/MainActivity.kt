package lt.arturas.androidtopics.common

import android.os.Bundle
import lt.arturas.androidtopics.R

//ctrl alt l o
class MainActivity : ActivityLifecyclesPresentation() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}