package ru.maksonic.beresta.common.ui_kit.widget.marker_color_picker

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.common.ui_kit.animation.AnimateFadeInOut
import ru.maksonic.beresta.common.ui_kit.helpers.modifier.noRippleClick
import ru.maksonic.beresta.common.ui_kit.icon.IconBase
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.Done
import ru.maksonic.beresta.common.ui_theme.colors.secondaryContainer
import ru.maksonic.beresta.common.ui_theme.colors.tertiaryContainer

/**
 * @Author maksonic on 29.10.2023
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SelectableCircleItem(
    modifier: Modifier = Modifier,
    primaryColor: Color,
    secondaryColor: Color = Color.Transparent,
    isSelected: Boolean,
    isEnabledLongClick: Boolean = false,
    onClick: () -> Unit,
    onLongClick: () -> Unit = onClick,
    isVisibleSecondColor: Boolean = false,
) {
    val border = animateDpAsState(if (isSelected) 2.dp else 0.dp, label = "")
    val borderColor = animateColorAsState(
        if (isSelected) tertiaryContainer else secondaryContainer, label = ""
    )
    val click = if (isEnabledLongClick) Modifier.combinedClickable(
        onClick = onClick,
        onLongClick = onLongClick
    ) else Modifier.noRippleClick { onClick() }

    Box(
        modifier = modifier
            .fillMaxHeight(1f)
            .aspectRatio(1f)
            .clip(CircleShape)
            .drawBehind { drawRect(primaryColor) }
            .border(border.value, borderColor.value, CircleShape)
            .then(click),
        contentAlignment = Alignment.Center
    ) {
        if (isVisibleSecondColor) {
            Canvas(
                modifier
                    .fillMaxSize()
                    .rotate(-45f), onDraw = {
                    drawArc(
                        color = secondaryColor,
                        startAngle = -180f,
                        sweepAngle = 180f,
                        useCenter = true,
                        topLeft = Offset.Zero,
                        size = Size(size.width, size.height),
                    )
                })
        }

        AnimateFadeInOut(isSelected) {
            Box(
                Modifier
                    .fillMaxWidth(0.4f)
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .background(tertiaryContainer.copy(0.8f)),
                contentAlignment = Alignment.Center
            ) {
                IconBase(
                    icon = AppIcon.Done,
                    tint = secondaryContainer,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}