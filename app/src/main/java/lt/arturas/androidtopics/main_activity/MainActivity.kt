package lt.arturas.androidtopics.main_activity

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import android.widget.AbsListView.OnScrollListener
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
        onScrollListView()
        setUpObservables()  //ctrl + alt + m


        onItemLongClick()
        setClickOpenItemDetails()
    }

    override fun onResume() {
        super.onResume()
        activityViewModel.fetchItems()
        if (adapter.getMaxId() != -1) {
            binding.itemListView.smoothScrollToPosition(
                activityViewModel.positionListViewStateFlow.value // - 2
            )
        }
    }

    fun setClickOpenSecondActivity(view: View) {
        startActivity(Intent(this, SecondActivity2::class.java))
    }

    private fun setUpListView() {
        adapter = CustomAdapter(this)
        binding.itemListView.adapter = adapter
    }

    private fun onScrollListView() {
        /*   binding.itemListView.setOnScrollListener(
               object : OnScrollListener{
                   override fun onScrollStateChanged(p0: AbsListView?, p1: Int) {}

                   override fun onScroll(p0: AbsListView?, position: Int, p2: Int, p3: Int) {
                       if(activityViewModel.positionListViewStateFlow.value != position){
                           activityViewModel.savePositionListView(position)
                       }
                       //displaySnackBar("First visible item index: $position")
                   }

               }
           )
         */

        binding.itemListView.setOnScrollChangeListener { _, _, _, _, _ ->
            val position = binding.itemListView.firstVisiblePosition
            if (activityViewModel.positionListViewStateFlow.value != position) {
                activityViewModel.savePositionListView(position)
            }
        }
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

        lifecycleScope.launch() {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                activityViewModel.positionListViewStateFlow.collect { firstVisiblePosition ->
                    displaySnackBar("First visible item index: $firstVisiblePosition")
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
