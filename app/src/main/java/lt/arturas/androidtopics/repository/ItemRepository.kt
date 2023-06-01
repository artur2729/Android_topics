package lt.arturas.androidtopics.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ItemRepository {

    private val items = mutableListOf<Item>()

    fun getItem(id: Int) = items.find { it.id == id }

    suspend fun getItems() = withContext(Dispatchers.IO) {
        items
    }

    fun addItem(item: Item) {
        var newId = 1

        if (!items.isEmpty()) {
            newId = items.maxBy { it.id }.id.inc()
        }

        val newItem = Item(_id = newId, item.text01, item.text02)
        items.add(newItem)
    }

    fun updateItem(item: Item?) {
        if (item != null) {
            val index = items.indexOfFirst { it.id == item.id }
            if (index >= 0) {
                items[index] = item
            }
        }
    }

    suspend fun addDummyListOfItems() {
        withContext(Dispatchers.IO) {
            items.addAll(generateListOfItems())
        }
    }

    private suspend fun generateListOfItems() = withContext(Dispatchers.IO) {

        val list = mutableListOf<Item>()

        for (number in 1..20) {
            val item = Item(
                number,
                "dummy text01: $number",
                "dummy text02: ${(1..100).random()}"
            )
            list.add(item)
        }

        return@withContext list


    }

    suspend fun deleteItem(item: Item) =
        withContext(Dispatchers.IO){
            items.remove(item)
        }


    companion object {
        val instance: ItemRepository = ItemRepository()
    }
}
