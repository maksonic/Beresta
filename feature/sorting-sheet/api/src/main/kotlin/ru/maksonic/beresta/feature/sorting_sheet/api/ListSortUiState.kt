package ru.maksonic.beresta.feature.sorting_sheet.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * @Author maksonic on 06.07.2023
 */
val LocalListSortState = staticCompositionLocalOf<ListSortUiState> {
    error("No sort list ui state provided")
}

data class CommonSort(
    val order: Order,
    val sort: Sort,
    val isSortPinned: Boolean,
) {
    companion object {
        val Initial = CommonSort(
            order = Order.DESCENDING,
            sort = Sort.DATE_CREATION,
            isSortPinned = false,
        )
    }
}

data class ListSortUiState(
    val notes: CommonSort,
    val hiddenNotes: CommonSort,
    val folders: CommonSort,
    val gridNotesCount: Int,
    val gridHiddenNotesCount: Int

) {
    companion object {
        val Initial = ListSortUiState(
            notes = CommonSort.Initial,
            hiddenNotes = CommonSort.Initial,
            folders = CommonSort.Initial,
            gridNotesCount = 1,
            gridHiddenNotesCount = 1
        )
    }
}

val listUiSortState: ListSortUiState @Composable get() = LocalListSortState.current

