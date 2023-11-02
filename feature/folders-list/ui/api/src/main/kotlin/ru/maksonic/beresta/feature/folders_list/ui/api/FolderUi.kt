package ru.maksonic.beresta.feature.folders_list.ui.api

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import java.time.LocalDateTime

/**
 * @Author maksonic on 02.03.2023
 */
data class FolderUi(
    val id: Long,
    val title: String,
    val isSelected: Boolean = false,
    val isMovedToTrash: Boolean,
    val isPinned: Boolean,
    val pinTime: LocalDateTime?,
    val dateCreationRaw: LocalDateTime,
    val dateLastUpdateRaw: LocalDateTime?,
    val dateCreation: String = "",
    val dateMovedToTrashRaw: LocalDateTime?,
    val dateMovedToTrash: String? = "",
    val isSelectable: Boolean = true,
    val isStickyToStart: Boolean = false,
    val isStickyToEnd: Boolean = false,
    val notesCount: Int = 0,
) {
    companion object {
        val Empty = FolderUi(
            id = 0,
            title = "",
            isSelected = false,
            isMovedToTrash = false,
            isPinned = false,
            isStickyToStart = false,
            isStickyToEnd = false,
            pinTime = null,
            dateLastUpdateRaw = null,
            dateCreationRaw = LocalDateTime.now(),
            dateMovedToTrashRaw = null,
            notesCount = 0
        )
    }

    fun trash() = this.copy(
        isMovedToTrash = true,
        dateMovedToTrashRaw = LocalDateTime.now(),
        isSelected = false
    )

    @Stable
    @Immutable
    data class Collection(val data: List<FolderUi>) {
        companion object {
            val Empty = Collection(emptyList())
        }
    }
}

fun FolderUi.isDefaultId() = this.id == 0L

fun FolderUi.Collection.unselectAll() =
    this.copy(data = this.data.map { it.copy(isSelected = false) })