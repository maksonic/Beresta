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
    val isPinned: Boolean = false,
) {

    @Stable
    @Immutable
    data class Collection(val data: List<FilterChipUi>) {
        companion object {
            private val chipsPreview = listOf(
                FilterChipUi(0, "All", true),
                FilterChipUi(1, "Maksonic"),
                FilterChipUi(2, "Some shit"),
                FilterChipUi(3, "Folder"),
                FilterChipUi(4, "Other"),
                FilterChipUi(5, "Fresh"),
                FilterChipUi(6, "My secrets"),
                FilterChipUi(7, "Test"),
            )
            val Empty = Collection(emptyList())
            val Preview = Collection(chipsPreview)
        }
    }
}

