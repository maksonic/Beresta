package ru.maksonic.beresta.feature.notes_list.ui.core.card.inidcator

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.common.ui_kit.animation.AnimateFadeInOut
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.LockOpen
import ru.maksonic.beresta.common.ui_kit.icons.Pin
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.ColorContainer
import ru.maksonic.beresta.common.ui_theme.colors.primary
import ru.maksonic.beresta.common.ui_theme.colors.surface
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.provide.dp24
import ru.maksonic.beresta.common.ui_theme.provide.dp32
import ru.maksonic.beresta.common.ui_theme.provide.dp4
import ru.maksonic.beresta.common.ui_theme.provide.dp8
import ru.maksonic.beresta.feature.notes_list.ui.api.card.isSquare
import ru.maksonic.beresta.feature.notes_list.ui.api.card.noteUiCardState

/**
 * @Author maksonic on 31.10.2023
 */
@Composable
internal fun IndicationTopPanel(
    markerColor: ColorContainer,
    modifier: Modifier = Modifier,
    iconTint: Color = primary,
    isPinned: Boolean,
    isHiddenAndTrashed: Boolean
) {
    Row(
        modifier
            .height(dp32)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (noteUiCardState.isVisibleColorMarker && markerColor.colorId != 0L) {
            Canvas(
                modifier
                    .weight(1f)
                    .padding(start = dp16, end = if (isPinned) dp8 else dp16)
                    .size(dp4)
                    .clip(CircleShape),
                onDraw = { drawRect(markerColor.value) })
        }

        AnimateFadeInOut(isHiddenAndTrashed) {
            Icon(
                imageVector = AppIcon.LockOpen,
                modifier = modifier
                    .padding(end = dp4)
                    .size(dp16),
                tint = iconTint,
                contentDescription = null
            )
        }

        AnimateFadeInOut(isPinned) {
            val shape = if (noteUiCardState.shape.isSquare) Theme.shape.cornerSmall else CircleShape

            Box(
                modifier
                    .padding(end = dp4)
                    .clip(shape)
                    .background(surface.copy(0.5f))
            ) {
                Icon(
                    imageVector = AppIcon.Pin,
                    modifier = modifier
                        .size(dp24)
                        .padding(dp4),
                    tint = iconTint,
                    contentDescription = null
                )
            }
        }
    }
}