package lt.arturas.androidtopics.second_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import lt.arturas.androidtopics.R
import lt.arturas.androidtopics.common.FragmentLifecyclesPresentation
import lt.arturas.androidtopics.databinding.FragmentFirstBinding
import lt.arturas.androidtopics.databinding.FragmentSecondBinding
import lt.vcs.demoapp.second_fragment.Item

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        receiveDataFromFirstFragment()
    }

    override fun onResume() {
        super.onResume()
        binding.textView.text = arguments?.getParcelable<Item>(TAG).toString()
    }

    override fun onPause() {
        super.onPause()
        transferDataToFirstFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun transferDataToFirstFragment() {
        val bundle = bundleOf(
            "sf_text" to "text_from_second_fragment",
            "sf_item" to Item(2, "txt01_02", "txt02_02")
        )
        setFragmentResult("second_fragment_result_key", bundle)
    }

    private fun receiveDataFromFirstFragment() {
        setFragmentResultListener("first_fragment_result_key") { requestKey, bundle ->
            val text = bundle.getString("ff_text")
            val item = bundle.getParcelable<Item>("ff_item")
            binding.textView.text = """
                $text
                +++++++++++++++++
                ${item.toString()}
            """.trimIndent()
        }
    }


    companion object {
        const val TAG = "second_fragment"
        //fun newInstance() = SecondFragment()
        fun newInstance(item: Item): SecondFragment{
            val fragment = SecondFragment()
            fragment.arguments = Bundle().apply{putParcelable(TAG, item)}

            return fragment
        }
    }
}