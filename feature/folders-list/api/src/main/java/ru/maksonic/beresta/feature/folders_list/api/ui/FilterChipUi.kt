package ru.maksonic.beresta.feature.folders_list.api.ui

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

/**
 * @Author maksonic on 02.03.2023
 */
data class FilterChipUi(
    val id: Long = 0,
    val title: String = "",
    val isSelected: Boolean = false,
    val isCurrent: Boolean = false,
    val isPinned: Boolean = false,
    val isMovedToTrash: Boolean = false
) {
    companion object {
        val InitialSelected = FilterChipUi(0, "All", isSelected = true, isCurrent = true, true)
        val Empty = FilterChipUi(0, "", isSelected = false, isCurrent = false, false)
    }

    @Stable
    @Immutable
    data class Collection(val data: List<FilterChipUi>) {
        companion object {
            private val chipsPreview = listOf(
                FilterChipUi(0, "All", true),
                FilterChipUi(1, "Maksonic"),
                FilterChipUi(2, "Preview"),
            )
            val Empty = Collection(emptyList())
            val Preview = Collection(chipsPreview)
        }
    }
}

fun List<FilterChipUi>.sortByPinnedThenByDescendingId() =
    this.sortedWith(comparator = compareByDescending<FilterChipUi> { chip -> chip.isPinned }
        .thenByDescending { chip -> chip.id })

