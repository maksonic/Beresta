package ru.maksonic.beresta.common.ui_kit.bar.snackbar

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ru.maksonic.beresta.common.ui_kit.helpers.modifier.noRippleClick
import ru.maksonic.beresta.common.ui_kit.helpers.modifier.rippledClick
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.Close
import ru.maksonic.beresta.common.ui_kit.surface.SurfacePro
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.onSnack
import ru.maksonic.beresta.common.ui_theme.colors.onSnackContainer
import ru.maksonic.beresta.common.ui_theme.colors.primary
import ru.maksonic.beresta.common.ui_theme.colors.snack
import ru.maksonic.beresta.common.ui_theme.colors.transparent
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.provide.dp24
import ru.maksonic.beresta.common.ui_theme.provide.dp6
import ru.maksonic.beresta.common.ui_theme.provide.dp8
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 05.09.2023
 */
const val SNACK_MESSAGE_MAX_LINES = 3

@Composable
fun SnackBar(snackBarData: SnackBarData?, modifier: Modifier = Modifier) {
    SurfacePro(
        color = snack,
        shape = Theme.shape.cornerNormal,
        modifier = modifier
            .fillMaxWidth()
            .padding(dp8)
            .defaultMinSize(minHeight = Theme.size.minimumTouchTargetSize)
    ) {

        var ticks by rememberSaveable { mutableIntStateOf(5) }

        LaunchedEffect(Unit) {
            delay(1000)
            if (ticks > 0) {
                repeat(ticks) {
                    ticks -= 1
                    delay(1000)
                }
            }
        }

        val animatedProgress = remember {
            Animatable(
                initialValue = 1f,
                typeConverter = Float.VectorConverter,
                visibilityThreshold = 0.01f,
                label = "PinCoolDownProgressAnimationLabel"
            )
        }

        LaunchedEffect(Unit) {
            snapshotFlow { 0f }
                .collect { value ->
                    val duration = if (ticks > 0) ticks * 1000 else 0
                    animatedProgress.animateTo(value, tween(duration, easing = LinearEasing))
                }
        }

        Row(
            modifier = modifier.padding(dp8),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    progress = { animatedProgress.value },
                    modifier = modifier.size(dp24).scale(scaleX = -1f, scaleY = 1f),
                    color = onSnackContainer,
                    strokeWidth = 2.dp,
                    trackColor = transparent,
                    strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
                )
                Text(
                    text = ticks.toString(),
                    style = TextDesign.titleSmall.copy(onSnackContainer),
                )
            }

            Text(
                text = snackBarData?.visuals?.message ?: "",
                style = TextDesign.bodyMedium.copy(onSnackContainer),
                maxLines = SNACK_MESSAGE_MAX_LINES,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier
                    .weight(1f)
                    .padding(start = dp8, end = dp8)
            )

            Box(
                modifier
                    .height(Theme.size.chipHeight)
                    .clip(Theme.shape.cornerNormal)
                    .rippledClick(
                        rippleColor = primary,
                        onClick = { snackBarData?.performAction() }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text.shared.btnTitleCancel,
                    style = TextDesign.titleSmall.copy(onSnack),
                    modifier = modifier.padding(start = dp16, end = dp16)
                )
            }

            Icon(
                imageVector = AppIcon.Close,
                tint = onSnackContainer,
                contentDescription = "",
                modifier = modifier
                    .size(Theme.size.chipHeight)
                    .noRippleClick { snackBarData?.dismiss() }
                    .padding(dp6)
            )
        }
    }
}