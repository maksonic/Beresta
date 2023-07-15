package ru.maksonic.beresta.feature.edit_note.ui.ui.widget

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.surface
import ru.maksonic.beresta.ui.theme.color.surfaceVariant
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.ArrowBack
import ru.maksonic.beresta.ui.theme.icons.MoreVertical
import ru.maksonic.beresta.ui.widget.bar.system.SystemStatusBarHeight
import ru.maksonic.beresta.ui.widget.button.ClickableCircleIcon
import ru.maksonic.beresta.ui.widget.functional.animation.animateDp

/**
 * @Author maksonic on 27.04.2023
 */
@Composable
internal fun EditNoteTopBar(
    isScrollUp: State<Boolean>,
    canScrollBackward: State<Boolean>,
    backAction: () -> Unit,
    menuAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    val panelHeight = Theme.widgetSize.topBarNormalHeight
    val offsetValue = -panelHeight.plus(SystemStatusBarHeight)
    val panelOffset = animateDp(if (isScrollUp.value) 0.dp else offsetValue)
    val color = animateColorAsState(
        if (canScrollBackward.value) surfaceVariant else surface, tween(Theme.animVelocity.common)
    )

    Row(
        modifier
            .fillMaxWidth()
            .height(panelHeight)
            .padding(start = dp4, end = dp4)
            .graphicsLayer {
                translationY = panelOffset.value.toPx()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ClickableCircleIcon(
            icon = AppIcon.ArrowBack,
            action = backAction,
            colorState = color
        )
        ClickableCircleIcon(
            icon = AppIcon.MoreVertical,
            action = menuAction,
            colorState = color
        )
    }
}