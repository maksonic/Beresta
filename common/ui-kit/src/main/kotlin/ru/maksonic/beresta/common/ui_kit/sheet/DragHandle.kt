package ru.maksonic.beresta.common.ui_kit.sheet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.onSecondaryContainer
import ru.maksonic.beresta.common.ui_theme.colors.transparent
import ru.maksonic.beresta.common.ui_theme.provide.dp32
import ru.maksonic.beresta.common.ui_theme.provide.dp4

/**
 * @Author maksonic on 05.11.2023
 */
@Composable
fun DragHandle(
    modifier: Modifier = Modifier,
    width: Dp = dp32,
    height: Dp = dp4,
    shape: Shape = MaterialTheme.shapes.extraLarge,
    color: Color = onSecondaryContainer,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(Theme.size.minimumTouchTargetSize),
        color = transparent
    ) {
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Box(
                Modifier
                    .size(width = width, height = height)
                    .clip(shape)
                    .drawBehind { drawRect(color) }
            )
        }
    }
}