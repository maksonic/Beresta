package ru.maksonic.beresta.feature.sorting_sheet.api

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.core.SharedUiState

/**
 * @Author maksonic on 06.07.2023
 */
interface SortingSheetApi {
    interface Feature {
        interface State {
            fun current(key: SortDataKey): Flow<ListSortUiState>
            suspend fun setOrderState(value: Pair<SortDataKey, Order>)
            suspend fun setSortState(value: Pair<SortDataKey, Sort>)
            suspend fun setCheckboxState(value: Pair<SortDataKey, Boolean>)
            suspend fun setGridCount(value: Pair<GridCountKey, Int>)
            suspend fun resetSortState(key: SortDataKey)
        }
    }

    interface Ui {
        val state: SharedUiState<ListSortUiState>

        @Composable
        fun SheetContent(sortDataKey: SortDataKey, hideSheet: () -> Unit)
    }
}

