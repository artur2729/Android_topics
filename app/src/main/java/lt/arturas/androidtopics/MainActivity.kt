package lt.arturas.androidtopics

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import timber.log.Timber

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import lt.arturas.androidtopics.databinding.ActivityMainBinding

class MainActivity : ActivityLifecycles() {

    private lateinit var adapter: CustomAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = mutableListOf<Item>()
        generateListOfItems(items)

        setUpListView()
        updateAdapter(items)

        setClickOpenItemDetails()
        setClickOpenSecondActivity()
    }

    private fun generateListOfItems(items: MutableList<Item>) {
        for (item in 1..10) {
            items.add(
                Item(
                    item,
                    "Text01_%04x".format(item),
                    "Text02_%06x".format(item)
                )
            )
        }
    }

    private fun setUpListView() {
        adapter = CustomAdapter(this)
        binding.itemListView.adapter = adapter
    }

    private fun updateAdapter(items: MutableList<Item>) {
        adapter.add(items)
        adapter.add(Item(101, "text01", "text02"))
        adapter.add(
            Item(102, "text01", "text02"),
            Item(103, "text01", "text02"),
            Item(104, "text01", "text02"),
            Item(105, "text01", "text02"),
        )
    }

    private fun setClickOpenSecondActivity() {
        binding.button.setOnClickListener {
            val intent = Intent(this, SecondActivity2::class.java)
            val id = adapter.getMaxId().inc()
            intent.putExtra(MAIN_ACTIVITY_ITEM_INTENT_ID, id)

            startActivityForResult.launch(intent)
        }
    }

    private fun setClickOpenItemDetails() {
        binding.itemListView.setOnItemClickListener { adapterView, view, position, l ->
            val item: Item = adapterView.getItemAtPosition(position) as Item

            val itemIntent = Intent(this, SecondActivity2::class.java)
            itemIntent.putExtra(MAIN_ACTIVITY_ITEM_INTENT_OBJECT, item)

            startActivityForResult.launch(itemIntent)
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

    //fun watchYoutubeVideo(context: Context, id: String) {
    //    val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$id"))
    //    val webIntent = Intent(
    //        Intent.ACTION_VIEW,
    //        Uri.parse("http://www.youtube.com/watch?v=$id")
    //    )
    //    try {
    //        context.startActivity(appIntent)
    //    } catch (ex: ActivityNotFoundException) {
    //        context.startActivity(webIntent)
    //    }
    //}

    companion object {
        const val MAIN_ACTIVITY_ITEM_INTENT_OBJECT = "package lt.arturas.androidtopics_item_intent_id"
        const val MAIN_ACTIVITY_ITEM_INTENT_ID = "package lt.arturas.androidtopics_item_intent_object"
    }
}
