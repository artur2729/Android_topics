package lt.arturas.androidtopics

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class SecondActivity2 : ActivityLifecycles() {

    private lateinit var idEditText: TextView
    private lateinit var text01EditText: EditText
    private lateinit var text02EditText: EditText
    private lateinit var closeButton: Button
    private lateinit var saveButton: Button
    private var finishIntentStatus = SECOND_ACTIVITY_ITEM_INTENT_RETURN_UPDATE
    private lateinit var item: Item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        idEditText = findViewById(R.id.idEditText)
        text01EditText = findViewById(R.id.text01EditText)
        text02EditText = findViewById(R.id.text02EditText)
        closeButton = findViewById(R.id.closeButton)
        saveButton = findViewById(R.id.saveButton)

        getIntentExtra()
        setClickListenerOfCloseButton()
        setClickListenerOfSaveButton()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putString(SECOND_ACTIVITY_ITEM_ID, idEditText.text.toString())
            putString(SECOND_ACTIVITY_ITEM_TEXT01, text01EditText.text.toString())
            putString(SECOND_ACTIVITY_ITEM_TEXT02, text02EditText.text.toString())
            putInt(SECOND_ACTIVITY_FINISH_INTENT_STATUS, finishIntentStatus)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        with(savedInstanceState) {
            idEditText.setText(this.getString(SECOND_ACTIVITY_ITEM_ID))
            text01EditText.setText(this.getString(SECOND_ACTIVITY_ITEM_TEXT01))
            text02EditText.setText(this.getString(SECOND_ACTIVITY_ITEM_TEXT02))
            finishIntentStatus = this.getInt(SECOND_ACTIVITY_FINISH_INTENT_STATUS)
        }
    }

    private fun getIntentExtra() {
        if (intent.hasExtra(MainActivity.MAIN_ACTIVITY_ITEM_INTENT_OBJECT)) {

            item = getExtraFromParcelable(
                intent,
                MainActivity.MAIN_ACTIVITY_ITEM_INTENT_OBJECT
            ) ?: Item(-1, "", "")

            idEditText.setText(item.id.toString())
            text01EditText.setText(item.text01)
            text02EditText.setText(item.text02)


        } else if (intent.hasExtra(MainActivity.MAIN_ACTIVITY_ITEM_INTENT_ID)) {

            idEditText.setText(
                intent
                    .getIntExtra(MainActivity.MAIN_ACTIVITY_ITEM_INTENT_ID, -1)
                    .toString()
            )
            finishIntentStatus = SECOND_ACTIVITY_ITEM_INTENT_RETURN_NEW

        } else {

            finishIntentStatus = RESULT_CANCELED
        }
    }

    private fun setClickListenerOfCloseButton() {
        closeButton.setOnClickListener {
            finish()
        }
    }

    private fun setClickListenerOfSaveButton() {
        saveButton.setOnClickListener {

            val finishIntent = Intent()

            when (finishIntentStatus) {
                SECOND_ACTIVITY_ITEM_INTENT_RETURN_NEW -> {
                    val item = Item(
                        idEditText.text.toString().toInt(),
                        text01EditText.text.toString(),
                        text02EditText.text.toString()
                    )
                    finishIntent.putExtra(SECOND_ACTIVITY_ITEM_INTENT_RETURN_OBJECT, item)
                }

                SECOND_ACTIVITY_ITEM_INTENT_RETURN_UPDATE -> {

                    item.text01 = text01EditText.text.toString()
                    item.text02 = text02EditText.text.toString()

                    finishIntent.putExtra(SECOND_ACTIVITY_ITEM_INTENT_RETURN_OBJECT, item)
                }
            }

            if (idEditText.text.toString().toInt() < 0) {
                finishIntentStatus = RESULT_CANCELED
            }

            setResult(finishIntentStatus, finishIntent)
            finish()
        }
    }

    companion object {
        const val SECOND_ACTIVITY_ITEM_INTENT_RETURN_OBJECT =
            "lt.arturas.androidtopics.secondactivity_item_intent_return_object"
        const val SECOND_ACTIVITY_ITEM_ID = "lt.arturas.androidtopics.secondactivity_item_id"
        const val SECOND_ACTIVITY_ITEM_TEXT01 = "lt.arturas.androidtopics.secondactivity_item_text01"
        const val SECOND_ACTIVITY_ITEM_TEXT02 = "lt.arturas.androidtopics.secondactivity_item_text02"
        const val SECOND_ACTIVITY_FINISH_INTENT_STATUS =
            "lt.arturas.androidtopics.secondactivity_finish_intent_status"

        const val SECOND_ACTIVITY_ITEM_INTENT_RETURN_NEW = 101
        const val SECOND_ACTIVITY_ITEM_INTENT_RETURN_UPDATE = 102
    }
}