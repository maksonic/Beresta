package ru.maksonic.beresta.feature.wallpaper_picker.ui.core.widget.pager.base

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.common.ui_kit.animation.AnimateFadeInOut
import ru.maksonic.beresta.common.ui_kit.helpers.modifier.noRippleClick
import ru.maksonic.beresta.common.ui_kit.icon.IconBase
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.Done
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.outline
import ru.maksonic.beresta.common.ui_theme.colors.secondaryContainer
import ru.maksonic.beresta.common.ui_theme.colors.tertiaryContainer
import ru.maksonic.beresta.common.ui_theme.provide.dp2
import ru.maksonic.beresta.common.ui_theme.provide.dp6
import ru.maksonic.beresta.common.ui_theme.provide.dp8

/**
 * @Author maksonic on 17.09.2023
 */
@Composable
internal fun BaseWallpaperItem(
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val border = animateDpAsState(
        if (isSelected) dp2 else 1.dp, animationSpec = tween(Theme.animVelocity.common), label = ""
    )
    val borderColor = animateColorAsState(
        if (isSelected) tertiaryContainer else outline,
        tween(Theme.animVelocity.common), label = ""
    )

    Box(
        modifier
            .padding(start = dp6, end = dp6, bottom = dp8)
            .fillMaxWidth()
            .aspectRatio(1f / 1.5f)
            .border(border.value, borderColor.value, Theme.shape.cornerMinimal)
            .clip(Theme.shape.cornerMinimal)
            .noRippleClick { onClick() },
        contentAlignment = Alignment.Center
    ) {
        content()

        AnimateFadeInOut(isSelected) {
            Box(
                modifier
                    .fillMaxWidth(0.4f)
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .background(tertiaryContainer.copy(0.8f)),
                contentAlignment = Alignment.Center
            ) {
                IconBase(
                    icon = AppIcon.Done,
                    tint = secondaryContainer,
                    modifier = modifier
                        .fillMaxSize()
                )
            }
        }
    }
}