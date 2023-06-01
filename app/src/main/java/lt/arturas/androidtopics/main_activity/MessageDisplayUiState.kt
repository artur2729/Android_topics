package lt.arturas.androidtopics.main_activity

import lt.arturas.androidtopics.repository.Item

data class MessageDisplayUiState(
    val item: Item = Item(-1,"",""),
    val isDeleted: Boolean = false
    )
