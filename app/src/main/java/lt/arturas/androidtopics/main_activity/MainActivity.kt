package lt.arturas.androidtopics.main_activity

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import lt.arturas.androidtopics.ActivityLifecycles
import lt.arturas.androidtopics.R
import lt.arturas.androidtopics.repository.Item
import lt.arturas.androidtopics.second_activity.SecondActivity2
import lt.arturas.androidtopics.databinding.ActivityMainBinding

class MainActivity : ActivityLifecycles() {

    private lateinit var adapter: CustomAdapter
    private lateinit var binding: ActivityMainBinding
    private val activityViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainActivity = this
        binding.viewModel = activityViewModel
        binding.lifecycleOwner = this

        setUpListView()

        setUpObservables()  //ctrl + alt + m

        onItemLongClick()
        setClickOpenItemDetails()
    }

    override fun onResume() {
        super.onResume()
        activityViewModel.fetchItems()
    }

    fun setClickOpenSecondActivity(view: View) {
        startActivity(Intent(this, SecondActivity2::class.java))
    }

    private fun setUpListView() {
        adapter = CustomAdapter(this)
        binding.itemListView.adapter = adapter
    }

    private fun setUpObservables() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                activityViewModel.uiState.collect { uiState ->
                    adapter.add(uiState.items)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                activityViewModel.isDeletedUiState.collect { displayUIState ->
                    if (displayUIState.isDeleted) {
                        displaySnackBar("Item was deleted from repository")
                        adapter.remove(displayUIState.item)  //fix
                    } else {
                        displaySnackBar("Item wasn't deleted from repository")
                    }
                }
            }
        }
    }

    private fun onItemLongClick() {
        binding.itemListView.setOnItemLongClickListener { adapterView, view, position, l ->
            val item = adapterView.getItemAtPosition(position) as Item
            displayDeleteItemAlertDialog(item)
            true
        }
    }

    private fun setClickOpenItemDetails() {
        binding.itemListView.setOnItemClickListener { adapterView, view, position, l ->
            val item: Item = adapterView.getItemAtPosition(position) as Item

            val intent = Intent(this, SecondActivity2::class.java)
            intent.putExtra(MAIN_ACTIVITY_ITEM_INTENT_ID, item.id)

            startActivity(intent)
        }
    }

    private fun displayDeleteItemAlertDialog(item: Item) {
        AlertDialog.Builder(this)
            .setTitle("Delete")
            .setMessage("Do you really want to delete this item?")
            .setIcon(R.drawable.ic_clear_24)
            .setPositiveButton("Yes") { _, _ ->
                activityViewModel.deleteItem(item)
                adapter.remove(item)
            }
            .setNegativeButton("No", null)
            .show()
    }

    private fun displaySnackBar(message: String) {
        Snackbar
            .make(
                binding.button,
                message,
                Snackbar.LENGTH_LONG
            )
            .setAnchorView(binding.button)
            .show()
    }

    companion object {
        const val MAIN_ACTIVITY_ITEM_INTENT_OBJECT =
            "package lt.arturas.androidtopics_item_intent_id"
        const val MAIN_ACTIVITY_ITEM_INTENT_ID =
            "package lt.arturas.androidtopics_item_intent_object"
    }
}
