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
import ru.maksonic.beresta.feature.sorting_sheet.api.SortDataKey
import ru.maksonic.beresta.feature.sorting_sheet.api.isNotes
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
    sortDataKey: SortDataKey,
    send: SendMessage,
    sortState: State<ListSortUiState>,
    hideSheet: () -> Unit,
    modifier: Modifier
) {

    CompositionLocalProvider(LocalListSortState provides sortState.value) {
        Column {
            OrderSelector(
                key = sortDataKey,
                onOrderClicked = { send(Msg.Ui.OnOrderClicked(Pair(sortDataKey, it))) }
            )

            val sort = if (sortDataKey.isNotes) listUiSortState.notes else listUiSortState.folders

            sortVariants.forEach { item ->
                SortItem(
                    item = item,
                    selected = item.second == sort.sort,
                    onSelect = { send(Msg.Ui.OnSortSelected(Pair(sortDataKey, it))) }
                )
            }

            Divider(
                color = onSecondaryContainer,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = dp32, end = dp32, top = dp16, bottom = dp16)
            )

            CheckboxButton(sortDataKey) { send(Msg.Ui.OnCheckboxClicked(Pair(sortDataKey, it))) }

            ModalSheetBottomButtonsRow(
                onLeftClicked = { send(Msg.Ui.OnDefaultBtnClicked(sortDataKey)) },
                onRightClicked = hideSheet
            )
        }
    }
}

private val sortVariants
    @Composable get() = with(text.sortSheet) {
        listOf(
            hintSortCategoryAlphabet to Sort.ALPHABET,
            hintSortCategoryDateCreation to Sort.DATE_CREATION,
            hintSortCategoryDateUpdate to Sort.DATE_UPDATE,
        )
    }
