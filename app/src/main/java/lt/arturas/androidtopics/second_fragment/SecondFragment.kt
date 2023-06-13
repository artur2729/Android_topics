package lt.arturas.androidtopics.second_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import lt.arturas.androidtopics.R
import lt.arturas.androidtopics.databinding.FragmentFirstBinding
import lt.arturas.androidtopics.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private val viewModel: SecondFragmentViewModel by viewModels()

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    val args: SecondFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView.text = args.sfText.toString()
    }

    companion object {
        fun newInstance() = SecondFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}