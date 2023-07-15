package ru.maksonic.beresta.feature.folders_chips.ui

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
import ru.maksonic.beresta.feature.folders_chips.api.FoldersApi
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.component.Shape
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.functional.animation.PlaceholderListWidget

/**
 * @Author maksonic on 12.07.2023
 */
class FoldersPlaceholder : FoldersApi.Ui.Placeholder {
    companion object {
        private const val PLACEHOLDER_LIST_COUNT = 15
    }

    @Composable
    override fun List(modifier: Modifier) {
        Box(modifier.background(background)) {
            PlaceholderListWidget(
                placeholdersCount = PLACEHOLDER_LIST_COUNT,
                modifier = Modifier.padding(top = dp12)
            ) { animateColor ->
                FolderPlaceholderItem(animateColor, Modifier.padding(start = dp16, end = dp16))
            }
        }
    }

    @Composable
    override fun TrashList(modifier: Modifier) {
        Box(modifier.background(background)) {
            PlaceholderListWidget(
                placeholdersCount = PLACEHOLDER_LIST_COUNT,
                modifier = Modifier.padding(top = dp12)
            ) { animateColor ->
                FolderPlaceholderItem(
                    animateColor = animateColor,
                    height = 100.dp
                )
            }
        }
    }
}


@Composable
private fun FolderPlaceholderItem(
    animateColor: Animatable<Color, AnimationVector4D>,
    modifier: Modifier = Modifier,
    height: Dp = Theme.widgetSize.minimumTouchTargetSize.plus(dp12)
) {
    Box(
        modifier
            .padding(bottom = dp12)
            .fillMaxWidth()
            .height(height)
            .clip(Shape.cornerNormal)
            .drawBehind { drawRect(animateColor.value) }
    )
}