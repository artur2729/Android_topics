package lt.arturas.androidtopics.second_activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import lt.arturas.androidtopics.repository.Item
import lt.arturas.androidtopics.repository.ItemRepository

class SecondActivityViewModel: ViewModel() {

    private val _itemLiveData = MutableLiveData<Item>()
    val itemLiveData: LiveData<Item>
        get() = _itemLiveData

    fun fetchItem(itemId: Int){
        if (_itemLiveData.value == null){
            if (itemId>0){
                _itemLiveData.value = ItemRepository.instance.getItem(itemId)
            } else{
                _itemLiveData.value = Item(-1, "", "")
            }
        }
    }
}