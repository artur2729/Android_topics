package lt.arturas.androidtopics.first_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import lt.arturas.androidtopics.R
import lt.arturas.androidtopics.common.FragmentLifecyclesPresentation
import lt.arturas.androidtopics.common.MainActivity
import lt.arturas.androidtopics.databinding.FragmentFirstBinding
import lt.arturas.androidtopics.second_fragment.SecondFragment

class FirstFragment : FragmentLifecyclesPresentation() {

    private val viewModel: FirstFragmentViewModel by viewModels()

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickOpenButton()
    }

    private fun onClickOpenButton() {
        binding.openSecondFragmentButton.setOnClickListener {
            (activity as MainActivity).openFragment(
                SecondFragment.newInstance(),
                SecondFragment.TAG
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "first_fragment"
        fun newInstance() = FirstFragment()
    }

}