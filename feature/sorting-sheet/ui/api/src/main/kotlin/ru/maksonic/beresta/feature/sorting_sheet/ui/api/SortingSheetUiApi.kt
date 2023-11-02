package ru.maksonic.beresta.feature.sorting_sheet.ui.api

import androidx.compose.runtime.Composable
import ru.maksonic.beresta.feature.sorting_sheet.domain.SortDataKey

/**
 * @Author maksonic on 16.10.2023
 */
interface SortingSheetUiApi {
    @Composable
    fun SheetContent(sortDataKey: SortDataKey)
}