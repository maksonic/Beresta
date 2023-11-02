package ru.maksonic.beresta.screen.main.ui.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.sorting_sheet.domain.SortDataKey
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.SortingSheetUiApi
import ru.maksonic.beresta.screen.main.core.ModalSheetContent
import ru.maksonic.beresta.screen.main.core.Model

/**
 * @Author maksonic on 27.06.2023
 */
@Composable
internal fun MultipleModalBottomSheetContent(
    model: Model,
    sortingSheetApi: SortingSheetUiApi,
    modifier: Modifier = Modifier
) {

    when (model.modalSheet.content) {
        ModalSheetContent.SORT_NOTES -> sortingSheetApi.SheetContent(SortDataKey.NOTES)
        ModalSheetContent.NOTHING -> Box(modifier.size(1.dp))
    }
}