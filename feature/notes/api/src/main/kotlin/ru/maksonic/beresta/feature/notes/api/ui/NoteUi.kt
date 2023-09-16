package ru.maksonic.beresta.feature.notes.api.ui

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import java.time.LocalDateTime

/**
 * @Author maksonic on 21.02.2023
 */
data class Style(
    val isPinned: Boolean,
    val markerColorId: Long,
    val wallpaperId: Long,
) {
    companion object {
        val Initial = Style(
            isPinned = false,
            markerColorId = 0L,
            wallpaperId = 0L
        )
    }
}

data class NoteUi(
    val id: Long,
    val folderId: Long = 2L,
    val key: Long = 0,
    val title: String,
    val message: String,
    val dateCreationRaw: LocalDateTime,
    val dateCreation: String = "",
    val dateLastUpdateRaw: LocalDateTime?,
    val dateMovedToTrashRaw: LocalDateTime?,
    val dateMovedToTrash: String = "",
    val isHidden: Boolean = false,
    val pinTime: LocalDateTime?,
    val isMovedToTrash: Boolean,
    val style: Style = Style.Initial
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
            pinTime = null,
            isMovedToTrash = false,
            style = Style.Initial
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