package ru.maksonic.beresta.feature.folders_chips.api.ui

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
    val isCurrent: Boolean = false,
    val isMovedToTrash: Boolean,
    val isPinned: Boolean,
    val pinTime: LocalDateTime?,
    val dateCreationRaw: LocalDateTime,
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
            isCurrent = false,
            isMovedToTrash = false,
            isPinned = false,
            isStickyToStart = false,
            isStickyToEnd = false,
            pinTime = null,
            dateCreationRaw = LocalDateTime.now(),
            dateMovedToTrashRaw = null,
            notesCount = 0
        )
    }

    @Stable
    @Immutable
    data class Collection(val data: List<FolderUi>) {
        companion object {
            val Empty = Collection(emptyList())
        }
    }
}

fun FolderUi.isDefaultId() = this.id == 0L

fun List<FolderUi>.sortStickyThenDescendingByPinTimeThenByDate() =
    this.sortedWith(comparator = compareByDescending<FolderUi> { it.isStickyToStart }
        .thenByDescending { it.pinTime }
        .thenByDescending { it.dateCreationRaw }
        .thenByDescending { it.isStickyToEnd }
    )