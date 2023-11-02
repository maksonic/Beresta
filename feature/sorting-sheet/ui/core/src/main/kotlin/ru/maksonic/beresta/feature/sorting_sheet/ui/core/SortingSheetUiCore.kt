package ru.maksonic.beresta.feature.sorting_sheet.ui.core

import androidx.compose.runtime.Composable
import ru.maksonic.beresta.feature.sorting_sheet.domain.SortDataKey
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.SortingSheetUiApi
import ru.maksonic.beresta.feature.sorting_sheet.ui.core.sheet.Container

/**
 * @Author maksonic on 16.10.2023
 */
class SortingSheetUiCore : SortingSheetUiApi {
    @Composable
    override fun SheetContent(sortDataKey: SortDataKey) {
        Container(sortDataKey)
    }
}