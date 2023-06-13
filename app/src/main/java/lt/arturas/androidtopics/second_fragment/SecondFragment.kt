package lt.arturas.androidtopics.second_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import lt.arturas.androidtopics.R

class SecondFragment : Fragment() {

    private val viewModel: SecondFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    companion object {
        fun newInstance() = SecondFragment()
    }
}