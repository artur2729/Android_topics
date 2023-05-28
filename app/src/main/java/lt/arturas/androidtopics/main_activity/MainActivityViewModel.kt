package lt.arturas.androidtopics.main_activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import lt.arturas.androidtopics.repository.Item
import lt.arturas.androidtopics.repository.ItemRepository

class MainActivityViewModel: ViewModel(){

    private val _itemsLiveData = MutableLiveData<List<Item>>(mutableListOf())
    val itemsLiveData: LiveData<List<Item>>
        get() = _itemsLiveData

    fun fetchItems(){
        if (_itemsLiveData.value == null || _itemsLiveData.value?.isEmpty() == true){
            ItemRepository.instance.addDummyListOfItems()
        }
        _itemsLiveData.value = ItemRepository.instance.items
    }
}