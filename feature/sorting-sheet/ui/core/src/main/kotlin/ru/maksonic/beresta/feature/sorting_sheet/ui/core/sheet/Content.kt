package ru.maksonic.beresta.feature.sorting_sheet.ui.core.sheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.common.core.ui.sorting.Sort
import ru.maksonic.beresta.common.ui_kit.button.radio.RadioButtonEndHint
import ru.maksonic.beresta.common.ui_kit.checkbox.CheckboxPrimaryNamed
import ru.maksonic.beresta.common.ui_kit.divider.DividerSecondary
import ru.maksonic.beresta.common.ui_kit.text.TextHeadlineSmall
import ru.maksonic.beresta.common.ui_theme.provide.dp32
import ru.maksonic.beresta.common.ui_theme.provide.dp8
import ru.maksonic.beresta.feature.sorting_sheet.domain.SortDataKey
import ru.maksonic.beresta.feature.sorting_sheet.domain.isFolders
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.listFoldersSortState
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.listHiddenNotesSortState
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.listNotesSortState
import ru.maksonic.beresta.feature.sorting_sheet.ui.core.Msg
import ru.maksonic.beresta.feature.sorting_sheet.ui.core.widget.OrderSelector
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 16.10.2023
 */
@Composable
internal fun Content(
    sortDataKey: SortDataKey,
    send: SendMessage,
    modifier: Modifier
) {
    val sheetTitle = with(text.sortingSheet) {
        if (sortDataKey.isFolders) titleSheetFolders else titleSheetNotes
    }
    val checkBoxLabel = with(text.sortingSheet) {
        if (sortDataKey.isFolders) hintCheckboxPinnedFolders else hintCheckboxPinnedNotes
    }

    val currentSortState = when (sortDataKey) {
        SortDataKey.NOTES -> listNotesSortState.sort
        SortDataKey.HIDDEN_NOTES -> listHiddenNotesSortState.sort
        SortDataKey.FOLDERS -> listFoldersSortState.sort
    }

    val isSortPinned = when (sortDataKey) {
        SortDataKey.NOTES -> listNotesSortState.isSortPinned
        SortDataKey.HIDDEN_NOTES -> listHiddenNotesSortState.isSortPinned
        SortDataKey.FOLDERS -> listFoldersSortState.isSortPinned
    }

    Column(
        modifier = modifier.padding(bottom = dp32),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextHeadlineSmall(
            text = sheetTitle,
            modifier = modifier.padding(bottom = dp8)
        )

        OrderSelector(
            sortDataKey = sortDataKey,
            onOrderClicked = { send(Msg.Ui.OnOrderClicked(Pair(sortDataKey, it))) }
        )

        sortVariants.forEach { item ->
            RadioButtonEndHint(
                selected = item.second == currentSortState,
                title = item.first,
                onClick = { send(Msg.Ui.OnSortSelected(Pair(sortDataKey, item.second))) }
            )
        }

        DividerSecondary()

        CheckboxPrimaryNamed(
            checked = isSortPinned,
            title = checkBoxLabel,
            onCheckedChange = { send(Msg.Ui.OnCheckboxClicked(Pair(sortDataKey, it))) }
        )
    }
}

private val sortVariants
    @Composable get() = with(text.sortingSheet) {
        listOf(
            hintSortCategoryAlphabet to Sort.ALPHABET,
            hintSortCategoryDateCreation to Sort.DATE_CREATION,
            hintSortCategoryDateUpdate to Sort.DATE_UPDATE,
        )
    }