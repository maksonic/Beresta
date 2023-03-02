package ru.maksonic.beresta.feature.notes_list.api.ui

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

/**
 * @Author maksonic on 02.03.2023
 */
data class FilterChipUi(
    val id: Int = 0,
    val title: String = "",
    val isSelected: Boolean = false
) {

    @Stable
    @Immutable
    data class Collection(val data: List<FilterChipUi>) {
        companion object {
            private val chipsPreview = listOf(
                FilterChipUi(0, "All", true),
                FilterChipUi(1, "Maksonic"),
                FilterChipUi(2, "Sample"),
                FilterChipUi(3, "Some bullshit"),
                FilterChipUi(4, "Other"),
                FilterChipUi(5, "Fresh"),
                FilterChipUi(6, "My secrets"),
                FilterChipUi(7, "Test"),
            )

            val Preview = Collection(chipsPreview)
        }
    }
}

