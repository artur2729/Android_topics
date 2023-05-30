package lt.arturas.androidtopics.second_activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import lt.arturas.androidtopics.ActivityLifecycles
import lt.arturas.androidtopics.main_activity.MainActivity
import lt.arturas.androidtopics.R
import lt.arturas.androidtopics.databinding.ActivitySecondBinding
import lt.arturas.androidtopics.repository.Item

class SecondActivity2 : ActivityLifecycles() {

    private lateinit var binding: ActivitySecondBinding
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

        activityViewModel.fetchItem(
            //intent.getIntExtra(MainActivity.MAIN_ACTIVITY_ITEM_INTENT_ID, -1)
            getIntentExtra()
        )

    }

    private fun getIntentExtra() = intent.getIntExtra(MainActivity.MAIN_ACTIVITY_ITEM_INTENT_ID, -1)

    fun onClickCloseButton(view: View) {
        finish()
    }

    fun onClickSaveButton() {
        activityViewModel.saveItem(binding.item as Item)

        finish()
    }
}