package lt.arturas.androidtopics.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import lt.arturas.androidtopics.R
import lt.arturas.androidtopics.first_fragment.FirstFragment

//ctrl alt l o
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.commit {
            replace(
                R.id.fragmentContainerView,
                FirstFragment.newInstance(),
                "first_fragment"
            )
            setReorderingAllowed(true)
        }
    }
}