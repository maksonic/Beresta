package ru.maksonic.beresta.feature.sorting_sheet.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import ru.maksonic.beresta.core.SharedUiState

/**
 * @Author maksonic on 06.07.2023
 */
val LocalListSortState = staticCompositionLocalOf<ListSortUiState> {
    error("No sort list ui state provided")
}

data class ListSortUiState(
    val order: Order,
    val sort: Sort,
    val isSortPinned: Boolean,
    val gridCount: Int

) {
    companion object {
        val Initial = ListSortUiState(
            order = Order.DESCENDING,
            sort = Sort.DATE_CREATION,
            isSortPinned = false,
            gridCount = 1
        )
    }
}

fun SharedUiState<ListSortUiState>.setInitialSortState() = this.update {
    it.copy(
        order = ListSortUiState.Initial.order,
        sort = ListSortUiState.Initial.sort,
        isSortPinned = ListSortUiState.Initial.isSortPinned
    )
}

val listUiSortState: ListSortUiState @Composable get() = LocalListSortState.current

