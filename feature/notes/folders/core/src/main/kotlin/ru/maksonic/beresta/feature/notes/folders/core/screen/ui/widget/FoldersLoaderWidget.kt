package ru.maksonic.beresta.feature.notes.folders.core.screen.ui.widget

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.component.Shape
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.functional.animation.PlaceholderListWidget

/**
 * @Author maksonic on 25.04.2023
 */
private const val PLACEHOLDER_LIST_COUNT = 15

@Composable
internal fun FoldersLoaderWidget(modifier: Modifier = Modifier) {
    Box(modifier.background(background)) {
        PlaceholderListWidget(
            placeholdersCount = PLACEHOLDER_LIST_COUNT,
            modifier = Modifier.padding(top = dp12)
        ) { animateColor ->
            FolderPlaceholderItem(animateColor)
        }
    }
}

@Composable
private fun FolderPlaceholderItem(
    animateColor: Animatable<Color, AnimationVector4D>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .padding(start = dp16, end = dp16, bottom = dp12)
            .fillMaxWidth()
            .height(Theme.widgetSize.minimumTouchTargetSize.plus(dp12))
            .clip(Shape.cornerNormal)
            .drawBehind { drawRect(animateColor.value) }
    )
}

