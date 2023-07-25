package ru.maksonic.beresta.feature.folders_chips.ui.widget

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.widget.functional.animation.PlaceholderListWidget

/**
 * @Author maksonic on 29.05.2023
 */
private const val PLACEHOLDER_LIST_COUNT = 5

@Composable
internal fun Placeholder(modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxWidth()
            .padding(top = Theme.widgetSize.topBarNormalHeight)
            .height(Theme.widgetSize.noteChipsContainerHeight),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier
                .horizontalScroll(rememberScrollState())
                .padding(start = dp4)
        ) {
            PlaceholderListWidget(
                placeholdersCount = PLACEHOLDER_LIST_COUNT,
                isColumn = false
            ) { animateColor ->
                ChipPlaceholderItem(animateColor)
            }
        }
    }
}

@Composable
private fun ChipPlaceholderItem(
    animateColor: Animatable<Color, AnimationVector4D>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .padding(start = dp12)
            .height(Theme.widgetSize.filterChipHeight)
            .aspectRatio(3f / 1f)
            .clip(CircleShape)
            .drawBehind { drawRect(animateColor.value) }
    )
}

