package lt.arturas.androidtopics.second_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import lt.arturas.androidtopics.R
import lt.arturas.androidtopics.common.FragmentLifecyclesPresentation
import lt.arturas.androidtopics.databinding.FragmentFirstBinding
import lt.arturas.androidtopics.databinding.FragmentSecondBinding

class SecondFragment : FragmentLifecyclesPresentation() {

    private val viewModel: SecondFragmentViewModel by viewModels()

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = SecondFragment()
    }
}