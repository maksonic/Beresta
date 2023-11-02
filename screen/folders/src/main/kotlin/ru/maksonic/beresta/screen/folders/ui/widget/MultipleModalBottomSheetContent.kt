package ru.maksonic.beresta.screen.folders.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.common.ui_theme.colors.secondaryContainer
import ru.maksonic.beresta.feature.sorting_sheet.domain.SortDataKey
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.SortingSheetUiApi
import ru.maksonic.beresta.screen.folders.core.ModalSheetContent
import ru.maksonic.beresta.screen.folders.core.Model

/**
 * @Author maksonic on 27.06.2023
 */
@Composable
internal fun MultipleModalBottomSheetContent(
    model: Model,
    sortingSheetApi: SortingSheetUiApi,
    modifier: Modifier = Modifier
) {

    Box(modifier.background(secondaryContainer)) {
        when (model.modalSheet.content) {
            ModalSheetContent.SORT_FOLDERS -> sortingSheetApi.SheetContent(SortDataKey.FOLDERS)
            ModalSheetContent.NOTHING -> Box(modifier.size(1.dp))
        }
    }
}