package ru.maksonic.beresta.feature.notes_list.ui.widget

import androidx.compose.animation.animateColorAsState
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.notes_list.api.FilterChip
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.primaryContainer
import ru.maksonic.beresta.ui.theme.color.tertiary
import ru.maksonic.beresta.ui.theme.component.Shape
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.widget.button.BoxWithScaleInOutOnClick

/**
 * @Author maksonic on 25.12.2022
 */
@Composable
internal fun ChipItem(
    filterChip: FilterChip,
    index: Int,
    selected: Boolean,
    onChipClick: (Int) -> Unit,
    isVisibleFirstNote: () -> Boolean,
    modifier: Modifier = Modifier
) {
    val selectedBorder = if (selected) 2.dp else 0.dp
    val borderColor = if (selected) primary else {
        if (isVisibleFirstNote()) primaryContainer else tertiary
    }
    val backgroundColor = animateColorAsState(
        targetValue = if (isVisibleFirstNote()) primaryContainer else tertiary
    )
    BoxWithScaleInOutOnClick(
        onClick = {
            onChipClick(index)
        }
    ) {
        Row(
            modifier
                .height(Theme.widgetSize.filterChipHeight)
                .clip(Shape.cornerNormal)
                .border(selectedBorder, borderColor, Shape.cornerNormal)
                .drawBehind { drawRect(backgroundColor.value) },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = filterChip.title,
                style = TextDesign.captionSmall,
                modifier = modifier.padding(start = dp16, end = dp16)
            )
        }
    }
}

@Preview
@Composable
private fun ChipItemPreview() {
    BerestaTheme {
        val selectedState = remember { mutableStateOf(true) }
        Row {
            ChipItem(
                filterChip = FilterChip.Preview,
                index = 0,
                selected = selectedState.value,
                onChipClick = { selectedState.value = !selectedState.value },
                isVisibleFirstNote = { false }
            )
            Spacer(Modifier.size(dp8))
            ChipItem(
                filterChip = FilterChip.Preview,
                index = 0,
                selected = !selectedState.value,
                onChipClick = { selectedState.value = !selectedState.value },
                isVisibleFirstNote = { true },
            )
        }
    }
}