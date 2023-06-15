package lt.arturas.androidtopics.second_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import lt.arturas.androidtopics.R
import lt.arturas.androidtopics.databinding.FragmentFirstBinding
import lt.arturas.androidtopics.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchUsers((1..12).random())

        observeItemStateFlow()
    }

    private fun observeItemStateFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.itemsStateFlow.collect { response ->
                    val user = response?.data
                    if (user != null) {
                        binding.textView.text = user.toString()
                    }
                }
            }
        }
    }

    companion object {
        fun newInstance() = SecondFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}