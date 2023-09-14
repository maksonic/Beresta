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
    val isHidden: Boolean = false,
    val pinTime: LocalDateTime?,
    val isMovedToTrash: Boolean,
    val folderId: Long = 2L,
    val markerColorId: Long = 0L
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
            isMovedToTrash = false,
            markerColorId = 0L
        )

        val Preview = Default.copy(title = "Note title preview", message = "Note message preview")
    }

    fun trash() = this.copy(isMovedToTrash = true, dateMovedToTrashRaw = LocalDateTime.now())
    fun restored() = this.copy(isMovedToTrash = false, dateMovedToTrashRaw = null)

    @Stable
    @Immutable
    data class Collection(val data: List<NoteUi>) {
        companion object {
            val Empty = Collection(emptyList())
        }
    }
}

fun List<NoteUi>.findNotesByFoldersId(ids: List<Long>) =
    this.filter { note -> ids.any { folderId -> note.folderId == folderId } }

fun NoteUi.isBlank() = this.title.isBlank() && this.message.isBlank()

fun NoteUi.isDefaultId() = this.id == NoteUi.Default.id