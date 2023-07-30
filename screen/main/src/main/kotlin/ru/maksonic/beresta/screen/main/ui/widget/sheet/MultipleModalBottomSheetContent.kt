package ru.maksonic.beresta.screen.main.ui.widget.sheet

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
import ru.maksonic.beresta.screen.main.core.CurrentSheetContent
import ru.maksonic.beresta.screen.main.core.Model
import ru.maksonic.beresta.screen.main.core.Msg
import ru.maksonic.beresta.screen.main.ui.SendMessage
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
            CurrentSheetContent.SORT_NOTES -> {
                sortedSheetApi.SheetContent(
                    sortDataKey = SortDataKey.NOTES,
                    hideSheet = { send(Msg.Ui.OnHideModalBottomSheet) }
                )
            }

            CurrentSheetContent.NOTHING -> Box(modifier.size(1.dp))
        }
    }
}