package ru.maksonic.beresta.feature.folders_list.ui.core.list

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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.common.ui_kit.placeholder.base.PlaceholderBaseLoadingContainer
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.background
import ru.maksonic.beresta.common.ui_theme.provide.dp12
import ru.maksonic.beresta.common.ui_theme.provide.dp16

/**
 * @Author maksonic on 20.10.2023
 */
private const val PLACEHOLDER_LIST_COUNT = 15

@Composable
internal fun LoadingPlaceholderContent(modifier: Modifier) {
    Box(modifier.background(background)) {
        PlaceholderBaseLoadingContainer(
            placeholdersCount = PLACEHOLDER_LIST_COUNT,
            modifier = Modifier.padding(top = dp12)
        ) { animateColor ->
            FolderPlaceholderItem(animateColor, Modifier.padding(start = dp16, end = dp16))
        }
    }
}

@Composable
internal fun LoadingTrashPlaceholderContent(modifier: Modifier) {
    Box(modifier.background(background)) {
        PlaceholderBaseLoadingContainer(
            placeholdersCount = PLACEHOLDER_LIST_COUNT,
            modifier = Modifier.padding(top = dp12, start = dp16, end = dp16)
        ) { animateColor ->
            FolderPlaceholderItem(animateColor = animateColor, height = 100.dp)
        }
    }
}


@Composable
private fun FolderPlaceholderItem(
    animateColor: Animatable<Color, AnimationVector4D>,
    modifier: Modifier = Modifier,
    height: Dp = Theme.size.minimumTouchTargetSize.plus(dp12)
) {
    Box(
        modifier
            .padding(bottom = dp12)
            .fillMaxWidth()
            .height(height)
            .clip(Theme.shape.cornerNormal)
            .drawBehind { drawRect(animateColor.value) }
    )
}