package lt.arturas.androidtopics

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

class SecondActivity2 : ActivityLifecycles() {

    lateinit var idEditText: EditText
    lateinit var text01EditText: EditText
    lateinit var text02EditText: EditText
    lateinit var closeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        idEditText = findViewById(R.id.idEditText)
        text01EditText = findViewById(R.id.text01EditText)
        text02EditText = findViewById(R.id.text02EditText)
        closeButton = findViewById(R.id.closeButton)

        getIntentExtra()
        setClickListenerOfCloseButton()

    }

    private fun getIntentExtra() {
        idEditText.setText(
            intent.getIntExtra(MainActivity.MAIN_ACTIVITY_ITEM_ID, -1).toString()
        )
        text01EditText.setText(
            intent.getStringExtra(MainActivity.MAIN_ACTIVITY_ITEM_TEXT01)
        )
        text02EditText.setText(
            intent.getStringExtra(MainActivity.MAIN_ACTIVITY_ITEM_TEXT02)
        )
    }

    private fun setClickListenerOfCloseButton() {
        closeButton.setOnClickListener {
            val finishIntent = Intent()

            finishIntent.putExtra(SECOND_ACTIVITY_ITEM_ID, (idEditText.text.toString()).toInt())
            finishIntent.putExtra(SECOND_ACTIVITY_ITEM_TEXT01, text01EditText.text.toString())
            finishIntent.putExtra(SECOND_ACTIVITY_ITEM_TEXT02, text02EditText.text.toString())
            setResult(RESULT_OK, finishIntent)
            finish()
        }
    }

    companion object{
        const val   SECOND_ACTIVITY_ITEM_ID = "lt.arturas.androidtopics.secondactivity_item_id"
        const val   SECOND_ACTIVITY_ITEM_TEXT01 = "lt.arturas.androidtopics.secondactivity_item_text01"
        const val   SECOND_ACTIVITY_ITEM_TEXT02 = "lt.arturas.androidtopics.secondactivity_item_text02"
    }
}