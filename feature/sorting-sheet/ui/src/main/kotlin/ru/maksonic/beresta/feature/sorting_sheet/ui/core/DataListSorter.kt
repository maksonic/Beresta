package ru.maksonic.beresta.feature.sorting_sheet.ui.core

import androidx.compose.runtime.MutableState
import ru.maksonic.beresta.core.ui.ext.update
import ru.maksonic.beresta.feature.sorting_sheet.api.ListSortUiState
import ru.maksonic.beresta.feature.sorting_sheet.api.Order
import ru.maksonic.beresta.feature.sorting_sheet.api.Sort
import ru.maksonic.beresta.feature.sorting_sheet.api.SortDataKey

/**
 * @Author maksonic on 10.09.2023
 */
class DataListSorter(private val sharedState: MutableState<ListSortUiState>) {

    fun update(state: ListSortUiState) = sharedState.update(state)

    fun updateOrder(value: Pair<SortDataKey, Order>) {
        val orderState = with(sharedState.value) {
            when (value.first) {
                SortDataKey.NOTES -> {
                    this.copy(notes = this.notes.copy(order = value.second))
                }

                SortDataKey.HIDDEN_NOTES -> {
                    this.copy(hiddenNotes = this.hiddenNotes.copy(order = value.second))
                }

                SortDataKey.FOLDERS -> {
                    this.copy(folders = this.folders.copy(order = value.second))
                }
            }
        }
        sharedState.update(orderState)
    }

    fun updateSort(value: Pair<SortDataKey, Sort>) {
        val sortState = with(sharedState.value) {
            when (value.first) {
                SortDataKey.NOTES -> {
                    this.copy(notes = this.notes.copy(sort = value.second))
                }

                SortDataKey.HIDDEN_NOTES -> {
                    this.copy(hiddenNotes = this.hiddenNotes.copy(sort = value.second))
                }

                SortDataKey.FOLDERS -> {
                    this.copy(folders = this.folders.copy(sort = value.second))
                }
            }
        }
        sharedState.update(sortState)
    }

    fun updateCheckbox(value: Pair<SortDataKey, Boolean>) {
        val checkboxState = with(sharedState.value) {
            when (value.first) {
                SortDataKey.NOTES -> {
                    this.copy(notes = this.notes.copy(isSortPinned = value.second))
                }

                SortDataKey.HIDDEN_NOTES -> {
                    this.copy(hiddenNotes = this.hiddenNotes.copy(isSortPinned = value.second))
                }

                SortDataKey.FOLDERS -> {
                    this.copy(folders = this.folders.copy(isSortPinned = value.second))
                }
            }
        }
        sharedState.update(checkboxState)
    }

    fun updateGridNotesCount(count: Int) =
        sharedState.update(sharedState.value.copy(gridNotesCount = count))

    fun updateGridHiddenNotesCount(count: Int) =
        sharedState.update(sharedState.value.copy(gridHiddenNotesCount = count))

    fun setInitialSortState(key: SortDataKey) {
        val initialState = with(sharedState.value) {
            when (key) {
                SortDataKey.NOTES -> {
                    this.copy(notes = ListSortUiState.Initial.notes)
                }

                SortDataKey.HIDDEN_NOTES -> {
                    this.copy(hiddenNotes = ListSortUiState.Initial.hiddenNotes)
                }

                SortDataKey.FOLDERS -> {
                    this.copy(folders = ListSortUiState.Initial.folders)
                }
            }
        }
        sharedState.update(initialState)
    }

}