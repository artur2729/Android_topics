package lt.arturas.androidtopics.first_fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import lt.arturas.androidtopics.R
import lt.arturas.androidtopics.common.FragmentLifecyclesPresentation
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
            parentFragmentManager.commit {
                replace(
                    R.id.fragmentContainerView,
                    SecondFragment.newInstance(),
                    "second_fragment"
                )
                setReorderingAllowed(true)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = FirstFragment()
    }

}