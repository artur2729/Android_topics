package lt.arturas.androidtopics.repository

class ItemRepository {

    val items = mutableListOf<Item>()

    fun getItem(id: Int) = items.find { it.id == id }

    fun addItem(item: Item) {
        var newId = 0

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

    fun addDummyListOfItems() {
        items.addAll(generateListOfItems())
    }

    private fun generateListOfItems(): List<Item> {
        val list = mutableListOf<Item>()

        for (number in 1..20) {
            val item = Item(
                number,
                "dummy text01: $number",
                "dummy text02: ${(1..100).random()}"
            )
            list.add(item)
        }

        return list
    }

    companion object {
        val instance: ItemRepository = ItemRepository()
    }
}
