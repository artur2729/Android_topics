package lt.arturas.androidtopics.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import lt.arturas.androidtopics.R
import lt.arturas.androidtopics.first_fragment.FirstFragment
import lt.arturas.androidtopics.second_fragment.SecondFragment

//ctrl alt l o
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            openFirstFragment()
        }
    }

    fun openSecondFragment() {
        setCurrentFragment(SecondFragment.newInstance(), SecondFragment.TAG, true)
    }

    private fun openFirstFragment() {
        setCurrentFragment(FirstFragment.newInstance(), FirstFragment.TAG)
    }

    private fun setCurrentFragment(fragment: Fragment, tag: String, addBackStack: Boolean = false) {
        supportFragmentManager.commit {
            replace(
                R.id.fragmentContainerView,
                fragment,
                tag
            )

            setReorderingAllowed(true)

            if (addBackStack){
                addToBackStack(tag)
            }
        }
    }
}