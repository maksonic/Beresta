package ru.maksonic.beresta.common.ui_kit.sheet

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import ru.maksonic.beresta.common.ui_kit.animation.AnimateFadeInOut
import ru.maksonic.beresta.common.ui_kit.helpers.modifier.noRippleClick
import ru.maksonic.beresta.common.ui_kit.surface.SurfacePro
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.scrim

/**
 * @Author maksonic on 05.11.2023
 */
private const val SHEET_OFFSET = 400

@Composable
fun ModalSheetContainer(
    isVisible: Boolean,
    modifier: Modifier = Modifier,
    hideSheet: () -> Unit,
    content: @Composable () -> Unit
) {

    if (isVisible) {
        BackHandler {
            hideSheet()
        }
    }

    AnimateFadeInOut(
        visible = isVisible,
        fadeInDuration = Theme.animVelocity.dialogVisibility,
        fadeOutDuration = Theme.animVelocity.dialogVisibility
    ) {
        val contentVisibility = remember { mutableStateOf(false) }

        LaunchedEffect(isVisible) {
            delay(100)
            contentVisibility.value = isVisible
        }

        SurfacePro(color = scrim.copy(0.2f)) {
            Box(
                modifier
                    .fillMaxSize()
                    .noRippleClick(onClick = hideSheet), contentAlignment = Alignment.BottomCenter
            ) {
                AnimatedVisibility(
                    visible = contentVisibility.value,
                    enter = slideInVertically(initialOffsetY = { SHEET_OFFSET }),
                    exit = slideOutVertically(targetOffsetY = { SHEET_OFFSET })
                ) {
                    content()
                }
            }
        }
    }
}