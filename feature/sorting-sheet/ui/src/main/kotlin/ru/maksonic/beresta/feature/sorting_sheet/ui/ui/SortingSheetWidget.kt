package ru.maksonic.beresta.feature.sorting_sheet.ui.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.feature.sorting_sheet.api.ListSortUiState
import ru.maksonic.beresta.feature.sorting_sheet.api.SortDataKey
import ru.maksonic.beresta.feature.sorting_sheet.api.SortingSheetApi

/**
 * @Author maksonic on 06.07.2023
 */
class SortingSheetWidget(override val state: SharedUiState<ListSortUiState>) : SortingSheetApi.Ui {

    @Composable
    override fun SheetContent(sortDataKey: SortDataKey, hideSheet: () -> Unit) {
        val listSortUiState = state.state.collectAsStateWithLifecycle()

        Container(sortDataKey, hideSheet, listSortUiState)
    }
}