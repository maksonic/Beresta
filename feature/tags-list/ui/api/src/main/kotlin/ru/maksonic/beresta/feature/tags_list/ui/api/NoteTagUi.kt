package ru.maksonic.beresta.feature.tags_list.ui.api

/**
 * @Author maksonic on 05.11.2023
 */
data class NoteTagUi(
    val id: Long,
    val title: String,
    val isSelected: Boolean = false
) {
    companion object {
        val Default = NoteTagUi(0, "Default", false)
    }

    data class Collection(val data: List<NoteTagUi>) {
        companion object {
            val Empty = Collection(emptyList())
        }
    }}