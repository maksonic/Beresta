package ru.maksonic.beresta.feature.notes.list.api.ui

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.Placeholder
import java.time.LocalDateTime

/**
 * @Author maksonic on 21.02.2023
 */
data class NoteUi(
    val id: Long,
    val title: String,
    val message: String,
    val dateCreationRaw: LocalDateTime,
    val backgroundId: Int = 0,
    val isSelected: Boolean = false,
    val isPinned: Boolean,
    val pinTime: LocalDateTime?,
    val isMovedToTrash: Boolean,
    val folderId: Long = 2L
) {

    companion object {
        val Default = NoteUi(
            id = 0L,
            title = "",
            message = "",
            dateCreationRaw = LocalDateTime.now(),
            backgroundId = 0,
            isSelected = false,
            isPinned = false,
            pinTime = null,
            isMovedToTrash = false
        )

        val Preview = Default.copy(title = "Note title preview", message = "Note message preview")
    }

    @Stable
    @Immutable
    data class Collection(val data: List<NoteUi>) {
        companion object {
            val Empty = Collection(emptyList())
        }
    }
}

fun NoteUi.isBlank() = this.title.isBlank() && this.message.isBlank()

fun NoteUi.isDefaultId() = this.id == NoteUi.Default.id

fun List<NoteUi>.sortDescendingByPinTimeThenByDate() =
    this.sortedWith(comparator = compareByDescending<NoteUi> { note -> note.pinTime }
        .thenByDescending { note -> note.dateCreationRaw })
