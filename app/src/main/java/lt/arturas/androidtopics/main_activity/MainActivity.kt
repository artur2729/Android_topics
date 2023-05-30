package lt.arturas.androidtopics.main_activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
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
        activityViewModel.itemsLiveData.observe(
            this,
            Observer { listOfItems ->
                adapter.add(listOfItems)
            }
        )

       // activityViewModel.isLoadingLiveData.observe(this) { isLoading ->
       //     binding.loadingProgressBar.isVisible = isLoading
        //    binding.itemListView.isVisible = !isLoading
       // }
    }

    private fun setClickOpenItemDetails() {
        binding.itemListView.setOnItemClickListener { adapterView, view, position, l ->
            val item: Item = adapterView.getItemAtPosition(position) as Item

            val intent = Intent(this, SecondActivity2::class.java)
            intent.putExtra(MAIN_ACTIVITY_ITEM_INTENT_ID, item.id)

            startActivity(intent)
        }
    }

    companion object {
        const val MAIN_ACTIVITY_ITEM_INTENT_OBJECT =
            "package lt.arturas.androidtopics_item_intent_id"
        const val MAIN_ACTIVITY_ITEM_INTENT_ID =
            "package lt.arturas.androidtopics_item_intent_object"
    }
}
