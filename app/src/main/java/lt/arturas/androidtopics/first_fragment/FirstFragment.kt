package lt.arturas.androidtopics.first_fragment

import android.os.Bundle
import android.util.Log
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
import java.util.Collections.list

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

//        viewModel.fetchUsers()
        viewModel.fetchTopNews()
//        userStateFlow()
        topNewsStateFlow()
    }

    private fun userStateFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.itemsStateFlow.collect { response ->
                    Log.i(TAG, "onViewCreated: ${response?.userList}")
                    val list = response?.userList
                    //var myText = " "
                    if (list != null) {
                        //for (item in list) {              //suletina nes sukuria objektus po 1
                        //    myText += "${item}\n\n"
                        //}
                        val stringBuilder = buildString {  //prideda o ne kuria, todel geresnis
                            list.forEach { append("$it\n\n") }
                        }
                        binding.textView.text = stringBuilder
                    }
                }
            }
        }
    }

    private fun topNewsStateFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.topNewsStateFlow.collect { response ->
//                    Log.i(TAG, "onViewCreated: ${response?.userList}")
                    val list = response?.articles
                    if (list != null) {
                        val stringBuilder = buildString {
                            list.forEach { append("$it\n\n") }
                        }
                        binding.textView.text = stringBuilder
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val TAG = "my_first_fragment"
        fun newInstance() = FirstFragment()
    }
}