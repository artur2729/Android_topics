package lt.arturas.androidtopics

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import timber.log.Timber

import android.app.Activity
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : ActivityLifecycles() {

    lateinit var openSecondActivityButton: Button
    lateinit var adapter: CustomAdapter
    lateinit var itemListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openSecondActivityButton = findViewById(R.id.button)
        itemListView = findViewById(R.id.itemListView)

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
        itemListView.adapter = adapter
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
        openSecondActivityButton.setOnClickListener {
//            startActivity(Intent(this, SecondActivity::class.java))
            startActivityForResult.launch(Intent(this, SecondActivity2::class.java))
        }
    }

    private fun setClickOpenItemDetails() {
        itemListView.setOnItemClickListener { adapterView, view, position, l ->
            val item: Item = adapterView.getItemAtPosition(position) as Item

            val itemIntent = Intent(this, SecondActivity2::class.java)
            itemIntent.putExtra(MAIN_ACTIVITY_ITEM_ID, item.id)
            itemIntent.putExtra(MAIN_ACTIVITY_ITEM_TEXT01, item.text01)
            itemIntent.putExtra(MAIN_ACTIVITY_ITEM_TEXT02, item.text02)
            startActivity(itemIntent)
        }
    }

    private val startActivityForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resul ->
            when(resul.resultCode){
                Activity.RESULT_OK -> {
                    val item = Item(
                        id = resul.data
                            ?.getIntExtra(SecondActivity2.SECOND_ACTIVITY_ITEM_ID, 0) ?: 0 ,
                        text01 = resul.data
                            ?.getStringExtra(SecondActivity2.SECOND_ACTIVITY_ITEM_TEXT01) ?:"",
                        text02 = resul.data
                            ?.getStringExtra(SecondActivity2.SECOND_ACTIVITY_ITEM_TEXT02) ?:""
                    )

                    adapter.add(item)
                }
            }
        }

    companion object {
        const val MAIN_ACTIVITY_ITEM_ID = "package lt.arturas.androidtopics.androidtopics_item_id"
        const val MAIN_ACTIVITY_ITEM_TEXT01 = "package lt.arturas.androidtopicsandroidtopics_item_text01"
        const val MAIN_ACTIVITY_ITEM_TEXT02 = "package lt.arturas.androidtopics.androidtopics_item_text02"
    }
}
