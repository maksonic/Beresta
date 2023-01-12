package ru.maksonic.beresta.feature.notes_list.api

/**
 * @Author maksonic on 22.12.2022
 */
data class NoteUi(
    val id: Long = 0L,
    val title: String = "",
    val message: String = "",
    val dateCreation: String = "",
    val isSelected: Boolean = false
) {
    data class Filter(
        val id: Long,
        val title: String,
        val isSelected: Boolean,
    )

    companion object {

        object Preview {
            val note = NoteUi(
                id = 0,
                title = "Note title preview",
                message = "Note message preview",
                dateCreation = "12:00 AM - 1 January 1970"
            )
            val filters = listOf(
                Filter(0, "Все", true),
                Filter(1, "Просто папка", false),
                Filter(2, "Fake folder", false),
                Filter(3, "Some folder", false),
                Filter(4, "Favorites", false),
                Filter(5, "Test folder", false),
                Filter(6, "Best notes", false),
                Filter(7, "Maksonic", false),
            )
        }
    }
}