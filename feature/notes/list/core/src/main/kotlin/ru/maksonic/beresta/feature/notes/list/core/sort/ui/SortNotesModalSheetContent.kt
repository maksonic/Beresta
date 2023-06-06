package ru.maksonic.beresta.feature.notes.list.core.sort.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import ru.maksonic.beresta.feature.notes.list.api.ui.SortedNotes
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onSecondaryContainer
import ru.maksonic.beresta.ui.theme.color.onSurface
import ru.maksonic.beresta.ui.theme.color.onTertiaryContainer
import ru.maksonic.beresta.ui.theme.color.outline
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.surfaceVariant
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.Shape
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp32
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.widget.button.PrimaryButton
import ru.maksonic.beresta.ui.widget.functional.clickAction

/**
 * @Author maksonic on 04.06.2023
 */
@Composable
internal fun SortNotesModalSheetContent(
    currentSortItemSelected: MutableState<SortedNotes>,
    checkboxSortPinned: MutableState<Boolean>,
    onBtnSaveClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(bottom = dp16), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text.sortNotesSheet.titleSheet,
            style = TextDesign.topBar,
            modifier = modifier.padding(bottom = dp16)
        )

        sortVariants.forEachIndexed { index, item ->
            if (index % 2 == 0) {
                CategoryDivider(title = dividers[index / 2])
            }
            Item(
                item,
                selected = item.second == currentSortItemSelected.value,
                onSelect = { new -> currentSortItemSelected.value = new })
        }

        Divider(
            color = onSecondaryContainer,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = dp32, end = dp32, top = dp8, bottom = dp16)
        )

        Row(
            modifier
                .fillMaxWidth()
                .padding(start = dp16, end = dp16)
                .clip(Shape.cornerSmall)
                .clickAction(primary) { checkboxSortPinned.value = !checkboxSortPinned.value },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(
                checked = checkboxSortPinned.value,
                onCheckedChange = { checkboxSortPinned.value = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = tertiaryContainer,
                    checkmarkColor = onTertiaryContainer,
                    uncheckedColor = outline,
                    disabledCheckedColor = outline,
                    disabledUncheckedColor = outline,
                    disabledIndeterminateColor = outline
                )
            )
            Text(
                text = text.sortNotesSheet.hintCheckboxSortPinned,
                style = TextDesign.captionNormal.copy(tertiaryContainer)
            )
        }

        Row(modifier.fillMaxWidth()) {
            PrimaryButton(
                action = {
                    checkboxSortPinned.value = false
                    currentSortItemSelected.value = SortedNotes.DATE_CREATION_DESC
                },
                title = text.shared.btnTitleByDefault,
                backgroundColor = surfaceVariant,
                titleColor = onSurface,
                modifier = modifier
                    .weight(1f)
                    .padding(start = dp16, end = dp8, top = dp16),

                )

            PrimaryButton(
                action = onBtnSaveClicked,
                title = text.shared.btnTitleSave,
                modifier = modifier
                    .weight(1f)
                    .padding(start = dp8, end = dp16, top = dp16)
            )
        }
    }
}

@Composable
private fun Item(
    item: Pair<String, SortedNotes>,
    selected: Boolean,
    onSelect: (SortedNotes) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .fillMaxWidth()
            .height(Theme.widgetSize.minimumTouchTargetSize)
            .padding(start = dp16, end = dp16)
            .clip(Shape.cornerSmall)
            .clickAction(primary) { onSelect(item.second) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = { onSelect(item.second) },
            colors = RadioButtonDefaults.colors(
                selectedColor = tertiaryContainer,
                unselectedColor = outline
            ),
        )
        Text(text = item.first, style = TextDesign.bodyPrimary)
    }
}

@Composable
private fun CategoryDivider(title: String, modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(start = dp16, end = dp16, top = dp4, bottom = dp4),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, style = TextDesign.captionSmall.copy(color = tertiaryContainer))

        Divider(
            color = onSecondaryContainer,
            modifier = modifier
                .weight(1f)
                .padding(start = dp16)
        )
    }
}

private val dividers
    @Composable get() = with(text.sortNotesSheet) {
        listOf(hintSortCategoryAlphabet, hintSortCategoryDateCreation, hintSortCategoryDateUpdate)
    }

private val sortVariants
    @Composable get() = with(text.sortNotesSheet) {
        listOf(
            itemInAlphabet to SortedNotes.ALPHABET_ASC,
            itemInReverseAlphabet to SortedNotes.ALPHABET_DESC,
            itemByDateCreation to SortedNotes.DATE_CREATION_ASC,
            itemByReverseDateCreation to SortedNotes.DATE_CREATION_DESC,
            itemByDateUpdate to SortedNotes.DATE_UPDATE_ASC,
            itemByReverseDateUpdate to SortedNotes.DATE_UPDATE_DESC,
        )
    }