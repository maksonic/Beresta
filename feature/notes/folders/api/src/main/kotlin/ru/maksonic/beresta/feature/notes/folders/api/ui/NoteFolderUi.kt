package ru.maksonic.beresta.feature.notes.folders.api.ui

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import java.time.LocalDateTime

/**
 * @Author maksonic on 02.03.2023
 */
data class NoteFolderUi(
    val id: Long = 0,
    val title: String = "",
    val isSelected: Boolean = false,
    val isCurrent: Boolean = false,
    val isMovedToTrash: Boolean = false,
    val isPinned: Boolean = false,
    val isSticky: Boolean = false,
    val pinTime: LocalDateTime?,
    val dateCreationRaw: LocalDateTime,
) {
    companion object {
        val InitialSelected = NoteFolderUi(
            id = 0,
            title = "All",
            isSelected = true,
            isCurrent = true,
            isPinned = true,
            isMovedToTrash = true,
            isSticky = true,
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
            isSticky = false,
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
    this.sortedWith(comparator = compareByDescending<NoteFolderUi> { it.isSticky }
        .thenByDescending { it.pinTime }
        .thenByDescending { it.dateCreationRaw }
    )
