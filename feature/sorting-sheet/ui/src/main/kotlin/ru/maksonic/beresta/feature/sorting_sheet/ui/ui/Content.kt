package ru.maksonic.beresta.feature.sorting_sheet.ui.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.sorting_sheet.api.ListSortUiState
import ru.maksonic.beresta.feature.sorting_sheet.api.LocalListSortState
import ru.maksonic.beresta.feature.sorting_sheet.api.Sort
import ru.maksonic.beresta.feature.sorting_sheet.api.listUiSortState
import ru.maksonic.beresta.feature.sorting_sheet.ui.core.Msg
import ru.maksonic.beresta.feature.sorting_sheet.ui.ui.widget.CheckboxButton
import ru.maksonic.beresta.feature.sorting_sheet.ui.ui.widget.OrderSelector
import ru.maksonic.beresta.feature.sorting_sheet.ui.ui.widget.SortItem
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.color.onSecondaryContainer
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp32
import ru.maksonic.beresta.ui.widget.button.ModalSheetBottomButtonsRow

/**
 * @Author maksonic on 06.07.2023
 */
@Composable
internal fun Content(
    send: SendMessage,
    sortState: State<ListSortUiState>,
    hideSheet: () -> Unit,
    modifier: Modifier
) {

    CompositionLocalProvider(LocalListSortState provides sortState.value) {
        Column {
            OrderSelector(onOrderClicked = { send(Msg.Ui.OnOrderClicked(it)) })

            sortVariants.forEach { item ->
                SortItem(
                    item = item,
                    selected = item.second == listUiSortState.sort,
                    onSelect = { send(Msg.Ui.OnSortSelected(it)) }
                )
            }

            Divider(
                color = onSecondaryContainer,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = dp32, end = dp32, top = dp16, bottom = dp16)
            )

            CheckboxButton { send(Msg.Ui.OnCheckboxClicked(it)) }

            ModalSheetBottomButtonsRow(
                onLeftClicked = { send(Msg.Ui.OnDefaultBtnClicked) },
                onRightClicked = hideSheet
            )
        }
    }
}

private val sortVariants
    @Composable get() = with(text.sortNotesSheet) {
        listOf(
            "Алфавит" to Sort.ALPHABET,
            "Дата создания" to Sort.DATE_CREATION,
            "Дата изменения" to Sort.DATE_UPDATE,
        )
    }
