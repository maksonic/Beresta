package ru.maksonic.beresta.feature.folders_list.api.ui

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import java.time.Instant

/**
 * @Author maksonic on 02.03.2023
 */
data class FilterChipUi(
    val id: Long = 0,
    val title: String = "",
    val isSelected: Boolean = false,
    val isCurrent: Boolean = false,
    val isMovedToTrash: Boolean = false,
    val isPinned: Boolean = false,
    val pinTime: Instant = Instant.now(),
    val isSticky: Boolean = false
) {
    companion object {
        val InitialSelected = FilterChipUi(
            id = 0,
            title = "All",
            isSelected = true,
            isCurrent = true,
            isPinned = true,
            isMovedToTrash = true,
            isSticky = true
        )
        val Empty = FilterChipUi(
            id = 0,
            title = "",
            isSelected = false,
            isCurrent = false,
            isMovedToTrash = false,
            isPinned = false,
            isSticky = false
        )
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