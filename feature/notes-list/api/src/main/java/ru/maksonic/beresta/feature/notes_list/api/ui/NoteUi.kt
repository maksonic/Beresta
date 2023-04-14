package ru.maksonic.beresta.feature.notes_list.api.ui

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import kotlinx.parcelize.Parcelize
import java.time.Instant
import java.time.LocalDateTime

/**
 * @Author maksonic on 21.02.2023
 */
@Parcelize
data class NoteUi(
    val id: Long = 0L,
    val title: String = "",
    val message: String = "",
    val dateCreation: String = "",
    val dateCreationRaw: LocalDateTime? = LocalDateTime.now(),
    val backgroundId: Int = 0,
    val currentFolder: String = "",
    val isSelected: Boolean = false,
    val isPinned: Boolean = false,
    val pinTime: LocalDateTime? = null,
    val isMovedToTrash: Boolean = false
) : Parcelable {
    companion object {
        val Default = NoteUi()

        val Preview = NoteUi(
            id = 0,
            title = "Note title preview",
            message = "Note message preview",
            dateCreation = "12:00 AM - 1 January 1970",
        )
    }

    @Stable
    @Immutable
    data class Collection(val data: List<NoteUi>) {
        companion object {
            val Empty = Collection(emptyList())
        }
    }
}

fun NoteUi.isEmpty() = this.title.isBlank() && this.message.isBlank()

fun List<NoteUi>.sortDescendingPinnedByTimeThenByDate() =
    this.sortedWith(comparator = compareByDescending<NoteUi> { note -> note.pinTime }
        .thenByDescending { chip -> chip.dateCreationRaw })

/*
fun List<NoteUi>.sortDescendingPinnedByTimeThenByDate() =
    this.sortedWith(comparator = compareByDescending<NoteUi> { note -> note.pinTime }
        .thenByDescending { chip -> chip.dateCreationRaw })

*/

