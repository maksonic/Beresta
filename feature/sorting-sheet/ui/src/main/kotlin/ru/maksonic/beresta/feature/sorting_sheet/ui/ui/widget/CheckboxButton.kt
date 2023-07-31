package ru.maksonic.beresta.feature.sorting_sheet.ui.ui.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import ru.maksonic.beresta.feature.sorting_sheet.api.SortDataKey
import ru.maksonic.beresta.feature.sorting_sheet.api.isFolders
import ru.maksonic.beresta.feature.sorting_sheet.api.listUiSortState
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.color.onTertiaryContainer
import ru.maksonic.beresta.ui.theme.color.outline
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.Shape
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.functional.clickAction

/**
 * @Author maksonic on 06.07.2023
 */
@Composable
internal fun CheckboxButton(
    key: SortDataKey,
    modifier: Modifier = Modifier,
    onCheckboxClicked: (Boolean) -> Unit
) {
    val sortState = with(listUiSortState) {
        when (key) {
            SortDataKey.NOTES -> notes
            SortDataKey.HIDDEN_NOTES -> hiddenNotes
            SortDataKey.FOLDERS -> folders
        }
    }
    val isChecked = rememberUpdatedState(!sortState.isSortPinned)

    Row(
        modifier
            .fillMaxWidth()
            .padding(start = dp16, end = dp16)
            .clip(Shape.cornerSmall)
            .clickAction(primary) { onCheckboxClicked(isChecked.value) },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val title = with(text.sortSheet) {
            if (key.isFolders) hintCheckboxPinnedFolders else hintCheckboxPinnedNotes
        }

        Checkbox(
            checked = sortState.isSortPinned,
            onCheckedChange = onCheckboxClicked,
            colors = CheckboxDefaults.colors(
                checkedColor = tertiaryContainer,
                checkmarkColor = onTertiaryContainer,
                uncheckedColor = outline,
                disabledCheckedColor = outline,
                disabledUncheckedColor = outline,
                disabledIndeterminateColor = outline
            )
        )
        Text(text = title, style = TextDesign.captionNormal)
    }
}