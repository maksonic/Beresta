package ru.maksonic.beresta.feature.notes.folders.api.ui

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import java.time.LocalDateTime

/**
 * @Author maksonic on 02.03.2023
 */
data class NoteFolderUi(
    val id: Long,
    val title: String,
    val isSelected: Boolean = false,
    val isCurrent: Boolean = false,
    val isMovedToTrash: Boolean,
    val isPinned: Boolean,
    val isSelectable: Boolean = true,
    val isStickyToStart: Boolean = false,
    val isStickyToEnd: Boolean = false,
    val pinTime: LocalDateTime?,
    val dateCreationRaw: LocalDateTime,
) {
    companion object {
        val StartListFolder = NoteFolderUi(
            id = 0,
            title = "All",
            isSelected = true,
            isCurrent = true,
            isPinned = false,
            isMovedToTrash = false,
            isSelectable = false,
            isStickyToStart = true,
            isStickyToEnd = false,
            pinTime = null,
            dateCreationRaw = LocalDateTime.now()
        )
        val EndListFolder = NoteFolderUi(
            id = 1,
            title = "Without category",
            isSelected = false,
            isCurrent = false,
            isPinned = false,
            isMovedToTrash = false,
            isSelectable = false,
            isStickyToStart = false,
            isStickyToEnd = true,
            pinTime = null,
            dateCreationRaw = LocalDateTime.now()
        )
        val Empty = NoteFolderUi(
            id = 0,
            title = "",
            isSelected = false,
            isCurrent = false,
            isMovedToTrash = false,
            isPinned = false,
            isStickyToStart = false,
            isStickyToEnd = false,
            pinTime = null,
            dateCreationRaw = LocalDateTime.now()
        )
    }

    @Stable
    @Immutable
    data class Collection(val data: List<NoteFolderUi>) {
        companion object {
            val Empty = Collection(emptyList())
        }
    }
}

fun NoteFolderUi.isDefaultId() = this.id == 0L

fun List<NoteFolderUi>.sortStickyThenDescendingByPinTimeThenByDate() =
    this.sortedWith(comparator = compareByDescending<NoteFolderUi> { it.pinTime }
        .thenByDescending { it.dateCreationRaw }
    )
