package ru.maksonic.beresta.screen.folders.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject
import ru.maksonic.beresta.feature.sorting_sheet.api.SortDataKey
import ru.maksonic.beresta.feature.sorting_sheet.api.SortingSheetApi
import ru.maksonic.beresta.screen.folders.core.ModalSheetContent
import ru.maksonic.beresta.screen.folders.core.Model
import ru.maksonic.beresta.screen.folders.core.Msg
import ru.maksonic.beresta.screen.folders.ui.SendMessage
import ru.maksonic.beresta.ui.theme.color.secondaryContainer

/**
 * @Author maksonic on 27.06.2023
 */
@Composable
internal fun MultipleModalBottomSheetContent(
    model: State<Model>,
    send: SendMessage,
    modifier: Modifier = Modifier,
    sortedSheetApi: SortingSheetApi.Ui = koinInject()
) {

    Box(modifier.background(secondaryContainer)) {
        when (model.value.modalSheet.content) {
            ModalSheetContent.SORT_FOLDERS -> {
                sortedSheetApi.SheetContent(
                    sortDataKey = SortDataKey.FOLDERS,
                    hideSheet = { send(Msg.Ui.OnHideModalBottomSheet) }
                )
            }

            ModalSheetContent.NOTHING -> Box(modifier.size(1.dp))
        }
    }
}