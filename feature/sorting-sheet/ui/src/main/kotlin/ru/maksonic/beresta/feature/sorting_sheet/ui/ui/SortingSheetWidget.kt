package ru.maksonic.beresta.feature.sorting_sheet.ui.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import ru.maksonic.beresta.feature.sorting_sheet.api.ListSortUiState
import ru.maksonic.beresta.feature.sorting_sheet.api.Order
import ru.maksonic.beresta.feature.sorting_sheet.api.Sort
import ru.maksonic.beresta.feature.sorting_sheet.api.SortDataKey
import ru.maksonic.beresta.feature.sorting_sheet.api.SortingSheetApi
import ru.maksonic.beresta.feature.sorting_sheet.ui.core.DataListSorter

/**
 * @Author maksonic on 06.07.2023
 */
class SortingSheetWidget : SortingSheetApi.Ui {
    override val sharedState = mutableStateOf(ListSortUiState.Initial)
    private val sorter = DataListSorter(sharedState)

    override fun update(state: ListSortUiState) = sorter.update(state)
    override fun updateOrder(value: Pair<SortDataKey, Order>) = sorter.updateOrder(value)
    override fun updateSort(value: Pair<SortDataKey, Sort>) = sorter.updateSort(value)
    override fun updateCheckbox(value: Pair<SortDataKey, Boolean>) = sorter.updateCheckbox(value)
    override fun updateGridNotesCount(count: Int) = sorter.updateGridNotesCount(count)
    override fun updateGridHiddenNotesCount(count: Int) = sorter.updateGridHiddenNotesCount(count)
    override fun setInitialSortState(key: SortDataKey) = sorter.setInitialSortState(key)

    @Composable
    override fun SheetContent(sortDataKey: SortDataKey, hideSheet: () -> Unit) {
        val listSortUiState = remember { sharedState }

        Container(sortDataKey, hideSheet, listSortUiState)
    }
}