package lt.arturas.androidtopics.MainActivity

import android.content.Intent
import android.os.Bundle
import android.view.View

import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import lt.arturas.androidtopics.ActivityLifecycles
import lt.arturas.androidtopics.Repository.Item
import lt.arturas.androidtopics.SecondActivity.SecondActivity2
import lt.arturas.androidtopics.databinding.ActivityMainBinding
import lt.arturas.androidtopics.getExtraFromParcelable

class MainActivity : ActivityLifecycles() {

    private lateinit var adapter: CustomAdapter
    private lateinit var binding: ActivityMainBinding
    private val activityViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setUpListView()

        activityViewModel.itemsLiveData.observe(
            this,
            Observer { listOfItems->
                adapter.add(listOfItems)
            }
        )

        activityViewModel.fetchItems()

        setClickOpenItemDetails()
    }

    private fun setUpListView() {
        adapter = CustomAdapter(this)
        binding.itemListView.adapter = adapter
    }

    private fun setClickOpenSecondActivity(view: View) {
            startActivity(this, SecondActivity2::class.java)
    }

    private fun setClickOpenItemDetails() {
        binding.itemListView.setOnItemClickListener { adapterView, view, position, l ->
            val item: Item = adapterView.getItemAtPosition(position) as Item

            val intent = Intent(this, SecondActivity2::class.java)
            intent.putExtra(MAIN_ACTIVITY_ITEM_INTENT_ID, item.id)

            startActivityForResult.launch(intent)
        }
    }

    private val startActivityForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resul ->

            val item: Item?

            when (resul.resultCode) {
                SecondActivity2.SECOND_ACTIVITY_ITEM_INTENT_RETURN_NEW -> {
                    item = getExtraFromParcelable(
                        resul.data,
                        SecondActivity2.SECOND_ACTIVITY_ITEM_INTENT_RETURN_OBJECT
                    )

                    if (item != null) {
                        adapter.add(item)
                    }
                }

                SecondActivity2.SECOND_ACTIVITY_ITEM_INTENT_RETURN_UPDATE -> {
                    item = getExtraFromParcelable(
                        resul.data,
                        SecondActivity2.SECOND_ACTIVITY_ITEM_INTENT_RETURN_OBJECT
                    )
                    adapter.update(item)
                }
            }
        }


    companion object {
        const val MAIN_ACTIVITY_ITEM_INTENT_OBJECT = "package lt.arturas.androidtopics_item_intent_id"
        const val MAIN_ACTIVITY_ITEM_INTENT_ID = "package lt.arturas.androidtopics_item_intent_object"
    }
}
