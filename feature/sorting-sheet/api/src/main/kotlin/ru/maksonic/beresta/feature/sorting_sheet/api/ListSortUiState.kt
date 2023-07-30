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

fun SharedUiState<ListSortUiState>.updateOrder(value: Pair<SortDataKey, Order>) = this.update {
    when (value.first) {
        SortDataKey.NOTES -> it.copy(notes = it.notes.copy(order = value.second))
        SortDataKey.HIDDEN_NOTES -> it.copy(hiddenNotes = it.hiddenNotes.copy(order = value.second))
        SortDataKey.FOLDERS -> it.copy(folders = it.folders.copy(order = value.second))
    }
}

fun SharedUiState<ListSortUiState>.updateSort(value: Pair<SortDataKey, Sort>) = this.update {
    when (value.first) {
        SortDataKey.NOTES -> it.copy(notes = it.notes.copy(sort = value.second))
        SortDataKey.HIDDEN_NOTES -> it.copy(hiddenNotes = it.hiddenNotes.copy(sort = value.second))
        SortDataKey.FOLDERS -> it.copy(folders = it.folders.copy(sort = value.second))
    }
}

fun SharedUiState<ListSortUiState>.updateCheckbox(value: Pair<SortDataKey, Boolean>) = this.update {
    when (value.first) {
        SortDataKey.NOTES -> {
            it.copy(notes = it.notes.copy(isSortPinned = value.second))
        }

        SortDataKey.HIDDEN_NOTES -> {
            it.copy(hiddenNotes = it.hiddenNotes.copy(isSortPinned = value.second))
        }

        SortDataKey.FOLDERS -> {
            it.copy(folders = it.folders.copy(isSortPinned = value.second))
        }
    }
}

fun SharedUiState<ListSortUiState>.setInitialSortState(key: SortDataKey) = this.update {
    when (key) {
        SortDataKey.NOTES -> it.copy(notes = ListSortUiState.Initial.notes)
        SortDataKey.HIDDEN_NOTES -> it.copy(hiddenNotes = ListSortUiState.Initial.hiddenNotes)
        SortDataKey.FOLDERS -> it.copy(folders = ListSortUiState.Initial.folders)
    }
}

val listUiSortState: ListSortUiState @Composable get() = LocalListSortState.current

