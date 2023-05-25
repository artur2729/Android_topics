package lt.arturas.androidtopics.SecondActivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import lt.arturas.androidtopics.ActivityLifecycles
import lt.arturas.androidtopics.MainActivity.MainActivity
import lt.arturas.androidtopics.MainActivity.MainActivityViewModel
import lt.arturas.androidtopics.R
import lt.arturas.androidtopics.Repository.Item
import lt.arturas.androidtopics.databinding.ActivitySecondBinding
import lt.arturas.androidtopics.getExtraFromParcelable

class SecondActivity2 : ActivityLifecycles() {

    private lateinit var binding: ActivitySecondBinding
    private var finishIntentStatus = SECOND_ACTIVITY_ITEM_INTENT_RETURN_UPDATE
    private val activityViewModel: SecondActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

//        binding = DataBin .setContentView(this,R.layout.activity_second)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_second)
        binding.secondActivity = this

        activityViewModel.itemLiveData.observe(
            this,
            Observer { item ->
                binding.item = item
            }
        )

        activityViewModel.fetchItem()

        getIntentExtra()
        //just for merging purposes: commit02
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            //putString(SECOND_ACTIVITY_ITEM_ID, idEditText.text.toString())
            //putString(SECOND_ACTIVITY_ITEM_TEXT01, text01EditText.text.toString())
            //putString(SECOND_ACTIVITY_ITEM_TEXT02, text02EditText.text.toString())
            putParcelable(SECOND_ACTIVITY_ITEM_SAVE_INSTANCE_STATE, binding.item)
            putInt(SECOND_ACTIVITY_FINISH_INTENT_STATUS, finishIntentStatus)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        with(savedInstanceState) {
            binding.item = getParcelable(SECOND_ACTIVITY_ITEM_SAVE_INSTANCE_STATE)
            finishIntentStatus = this.getInt(SECOND_ACTIVITY_FINISH_INTENT_STATUS)
        }
    }

    private fun getIntentExtra() = intent.getIntExtra(MainActivity.MAIN_ACTIVITY_ITEM_INTENT_ID, -1)

    fun onClickCloseButton(view: View) {
            finish()
    }

    fun onClickSaveButton() {

            val finishIntent = Intent()

            finishIntent.putExtra(SECOND_ACTIVITY_ITEM_INTENT_RETURN_OBJECT, binding.item)

            if (binding.idEditText.text.toString().toInt() < 0) {
                finishIntentStatus = RESULT_CANCELED
            }

            setResult(finishIntentStatus, finishIntent)
            finish()
    }

    companion object {
        const val SECOND_ACTIVITY_ITEM_INTENT_RETURN_OBJECT =
            "lt.arturas.androidtopics.secondactivity_item_intent_return_object"
        const val SECOND_ACTIVITY_ITEM_SAVE_INSTANCE_STATE =
        "lt.arturas.androidtopics.secondactivity_item"
        const val SECOND_ACTIVITY_ITEM_ID = "lt.arturas.androidtopics.secondactivity_item_id"
        const val SECOND_ACTIVITY_ITEM_TEXT01 = "lt.arturas.androidtopics.secondactivity_item_text01"
        const val SECOND_ACTIVITY_ITEM_TEXT02 = "lt.arturas.androidtopics.secondactivity_item_text02"
        const val SECOND_ACTIVITY_FINISH_INTENT_STATUS =
            "lt.arturas.androidtopics.secondactivity_finish_intent_status"

        const val SECOND_ACTIVITY_ITEM_INTENT_RETURN_NEW = 101
        const val SECOND_ACTIVITY_ITEM_INTENT_RETURN_UPDATE = 102
    }
}