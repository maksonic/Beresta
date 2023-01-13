package ru.maksonic.beresta.feature.notes_list.ui.widget

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.notes_list.api.NoteUi
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.primaryContainer
import ru.maksonic.beresta.ui.theme.component.Shape
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.functional.noRippleClickable

/**
 * @Author maksonic on 25.12.2022
 */

@Preview
@Composable
private fun ChipItemPreview() {
    BerestaTheme {
        val selectedState = remember { mutableStateOf(false) }
        val chipBackground = primaryContainer
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            ChipItem(
                chipFilter = NoteUi.Companion.Preview.filters.first(),
                index = 0,
                selected = selectedState.value,
                onChipClick = { selectedState.value = !selectedState.value },
                chipBackgroundColor = { chipBackground })
        }
    }
}

@Composable
internal fun ChipItem(
    chipFilter: NoteUi.Filter,
    index: Int,
    selected: Boolean,
    onChipClick: (Int) -> Unit,
    chipBackgroundColor: () -> Color,
    modifier: Modifier = Modifier
) {
    val color = if (selected) primary else primaryContainer
    val selectedBorder = if (selected) 2.dp else 0.dp
    Row(
        modifier
            .noRippleClickable { onChipClick(index) }
            .height(Theme.widgetSize.filterChipHeight)
            .clip(Shape.cornerNormal)
            .border(selectedBorder, color, Shape.cornerNormal)
            .drawBehind { drawRect(chipBackgroundColor()) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = chipFilter.title,
            style = TextDesign.caption,
            modifier = modifier.padding(start = dp16, end = dp16)
        )
    }
}