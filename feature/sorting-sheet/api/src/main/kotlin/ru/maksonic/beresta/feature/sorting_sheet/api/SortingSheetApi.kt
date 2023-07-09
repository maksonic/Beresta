package ru.maksonic.beresta.feature.sorting_sheet.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.core.SharedUiState

/**
 * @Author maksonic on 06.07.2023
 */
interface SortingSheetApi {
    interface Feature {
        interface State {
            val current: Flow<ListSortUiState>
            suspend fun setOrderState(order: Order)
            suspend fun setSortState(sort: Sort)
            suspend fun setCheckboxState(isSortPinned: Boolean)
            suspend fun setGridCount(cellCount: Int)
            suspend fun resetSortState()
        }
    }

    interface Ui {
        val state: SharedUiState<ListSortUiState>

        @Composable
        fun SheetContent(hideSheet: () -> Unit)
    }
}

