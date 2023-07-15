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
    val folders: CommonSort,
    val gridCount: Int

) {
    companion object {
        val Initial = ListSortUiState(
            notes = CommonSort.Initial,
            folders = CommonSort.Initial,
            gridCount = 1
        )
    }
}

fun SharedUiState<ListSortUiState>.updateOrder(value: Pair<SortDataKey, Order>) = this.update {
    if (value.first.isNotes) {
        it.copy(notes = it.notes.copy(order = value.second))
    } else {
        it.copy(folders = it.folders.copy(order = value.second))
    }
}

fun SharedUiState<ListSortUiState>.updateSort(value: Pair<SortDataKey, Sort>) = this.update {
    if (value.first.isNotes) {
        it.copy(notes = it.notes.copy(sort = value.second))
    } else {
        it.copy(folders = it.folders.copy(sort = value.second))
    }
}

fun SharedUiState<ListSortUiState>.updateCheckbox(value: Pair<SortDataKey, Boolean>) = this.update {
    if (value.first.isNotes) {
        it.copy(notes = it.notes.copy(isSortPinned = value.second))
    } else {
        it.copy(folders = it.folders.copy(isSortPinned = value.second))
    }
}

fun SharedUiState<ListSortUiState>.setInitialSortState(key: SortDataKey) = this.update {
    if (key.isNotes) {
        it.copy(
            notes = it.notes.copy(
                order = CommonSort.Initial.order,
                sort = CommonSort.Initial.sort,
                isSortPinned = CommonSort.Initial.isSortPinned
            )
        )
    } else {
        it.copy(
            notes = it.folders.copy(
                order = CommonSort.Initial.order,
                sort = CommonSort.Initial.sort,
                isSortPinned = CommonSort.Initial.isSortPinned
            )
        )
    }
}

val listUiSortState: ListSortUiState @Composable get() = LocalListSortState.current

