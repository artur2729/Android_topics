package lt.arturas.androidtopics.first_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import lt.arturas.androidtopics.R
import lt.arturas.androidtopics.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private val viewModel: FirstFragmentViewModel by viewModels()

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Call from First Fragment
        binding.openButton.setOnClickListener { view ->
            val bundle = bundleOf("name_of_key" to "Some data from First Fragment")
            view.findNavController()
                .navigate(R.id.action_firstFragment_to_secondFragment, bundle)
        }
    }

    companion object {
        fun newInstance() = FirstFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}