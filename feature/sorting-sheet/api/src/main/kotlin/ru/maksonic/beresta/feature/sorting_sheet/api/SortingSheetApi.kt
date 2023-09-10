package ru.maksonic.beresta.feature.sorting_sheet.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import kotlinx.coroutines.flow.Flow

/**
 * @Author maksonic on 06.07.2023
 */
interface SortingSheetApi {
    interface Ui {
        val sharedState: State<ListSortUiState>
        fun update(state: ListSortUiState)
        fun updateOrder(value: Pair<SortDataKey, Order>)
        fun updateSort(value: Pair<SortDataKey, Sort>)
        fun updateCheckbox(value: Pair<SortDataKey, Boolean>)
        fun updateGridNotesCount(count: Int)
        fun updateGridHiddenNotesCount(count: Int)
        fun setInitialSortState(key: SortDataKey)

        @Composable
        fun SheetContent(sortDataKey: SortDataKey, hideSheet: () -> Unit)
    }

    interface Storage {
        fun current(key: SortDataKey): Flow<ListSortUiState>
        suspend fun setOrderState(value: Pair<SortDataKey, Order>)
        suspend fun setSortState(value: Pair<SortDataKey, Sort>)
        suspend fun setCheckboxState(value: Pair<SortDataKey, Boolean>)
        suspend fun setGridCount(value: Pair<GridCountKey, Int>)
        suspend fun resetSortState(key: SortDataKey)
    }
}

