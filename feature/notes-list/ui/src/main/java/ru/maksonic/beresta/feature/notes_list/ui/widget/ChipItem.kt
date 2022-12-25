package ru.maksonic.beresta.feature.notes_list.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.primaryContainer
import ru.maksonic.beresta.ui.theme.component.Shape
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.button.BoxWithScaleInOutOnClick

/**
 * @Author maksonic on 25.12.2022
 */

@Preview
@Composable
private fun ChipItemPreview() {
    BerestaTheme {
        val selectedState = remember { mutableStateOf(false) }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            ChipItem(
                chipTitle = "Test chip filter",
                index = 0,
                selected = selectedState.value,
                onClick = { selectedState.value = !selectedState.value })
        }
    }
}

@Composable
internal fun ChipItem(
    chipTitle: String,
    index: Int,
    selected: Boolean,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val color = if (selected) primary else primaryContainer
    val selectedBorder = if (selected) 2.dp else 0.dp

    BoxWithScaleInOutOnClick(
        onClick = { onClick.invoke(index) }
    ) {
        Row(
            modifier
                .clip(Shape.cornerNormal)
                .border(selectedBorder, color, Shape.cornerNormal)
                .background(primaryContainer),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = chipTitle,
                style = TextDesign.caption,
                modifier = modifier.padding(dp16)
            )
        }
    }
}