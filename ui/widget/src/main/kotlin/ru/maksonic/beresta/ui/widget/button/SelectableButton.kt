package ru.maksonic.beresta.ui.widget.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onSecondaryContainer
import ru.maksonic.beresta.ui.theme.color.outline
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.transparent
import ru.maksonic.beresta.ui.theme.component.TextDesign

/**
 * @Author maksonic on 07.07.2023
 */
@Composable
fun SelectableButton(
    title: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onSelect: () -> Unit
) {
    BoxWithScaleInOutOnClick(
        onClick = onSelect,
        modifier = modifier.height(Theme.widgetSize.minimumTouchTargetSize)
    ) {
        val borderColor = animateColorAsState(if (isSelected) primary else outline)
        val backgroundColor =
            animateColorAsState(if (isSelected) onSecondaryContainer else transparent)
        Box(
            Modifier
                .fillMaxSize()
                .clip(Theme.shape.cornerBig)
                .drawBehind { drawRect(backgroundColor.value) }
                .border(1.dp, borderColor.value, Theme.shape.cornerBig),
            contentAlignment = Alignment.Center
        ) {
            Text(text = title, style = TextDesign.bodyPrimary)
        }
    }
}