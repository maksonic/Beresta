package ru.maksonic.beresta.feature.notes.api.ui

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import java.time.LocalDateTime

/**
 * @Author maksonic on 21.02.2023
 */
data class NoteUi(
    val id: Long,
    val key: Long = 0,
    val title: String,
    val message: String,
    val dateCreationRaw: LocalDateTime,
    val dateCreation: String = "",
    val dateMovedToTrashRaw: LocalDateTime?,
    val dateMovedToTrash: String = "",
    val dateLastUpdateRaw: LocalDateTime?,
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
            key = 0L,
            title = "",
            message = "",
            dateCreationRaw = LocalDateTime.now(),
            dateCreation = "",
            dateMovedToTrashRaw = null,
            dateMovedToTrash = "",
            dateLastUpdateRaw = null,
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

fun List<NoteUi>.sortByAlphabetAsc(withPinned: Boolean): List<NoteUi> {
    val pinned = this.filter { it.isPinned }
        .sortedWith(if (withPinned) compareBy { it.title } else compareByDescending { it.pinTime })
    val other = this.filter { !it.isPinned }.sortedBy { it.title }
    return pinned + other
}

fun List<NoteUi>.sortByAlphabetDesk(withPinned: Boolean): List<NoteUi> {
    val pinned = this.filter { it.isPinned }
        .sortedWith(
            if (withPinned) compareByDescending { it.title } else compareByDescending { it.pinTime }
        )
    val other = this.filter { !it.isPinned }.sortedByDescending { it.title }
    return pinned + other
}

fun List<NoteUi>.sortByCreationDateAsc(withPinned: Boolean): List<NoteUi> {
    val pinned = this.filter { it.isPinned }
        .sortedWith(
            if (withPinned) compareBy { it.dateCreationRaw } else compareByDescending { it.pinTime }
        )
    val other = this.filter { !it.isPinned }.sortedBy { it.dateCreationRaw }
    return pinned + other
}

fun List<NoteUi>.sortByCreationDateDesk(withPinned: Boolean): List<NoteUi> {
    val pinned = this.filter { it.isPinned }
        .sortedWith(if (withPinned) compareByDescending { it.dateCreationRaw }
        else compareByDescending { it.pinTime }
        )
    val other = this.filter { !it.isPinned }.sortedByDescending { it.dateCreationRaw }
    return pinned + other
}

fun List<NoteUi>.sortUpdateDateAsc(withPinned: Boolean): List<NoteUi> {
    val pinned = this.filter { it.isPinned }
        .sortedWith(if (withPinned) compareBy { it.dateLastUpdateRaw }
        else compareByDescending { it.pinTime }
        )
    val other = this.filter { !it.isPinned }.sortedBy { it.dateLastUpdateRaw }
    return pinned + other
}

fun List<NoteUi>.sortUpdateDateDesk(withPinned: Boolean): List<NoteUi> {
    val pinned = this.filter { it.isPinned }
        .sortedWith(if (withPinned) compareByDescending { it.dateLastUpdateRaw }
        else compareByDescending { it.pinTime }
        )
    val other = this.filter { !it.isPinned }.sortedByDescending { it.dateLastUpdateRaw }
    return pinned + other
}

