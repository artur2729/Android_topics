package lt.arturas.androidtopics.main_activity

import lt.arturas.androidtopics.repository.Item

data class MainActivityUIState(
    val items: List<Item> = mutableListOf(),
    val isLoading: Boolean = false,
    val isListVisible: Boolean = true
)
