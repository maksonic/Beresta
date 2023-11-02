package ru.maksonic.beresta.feature.wallpaper_picker.ui.core

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.common.ui_kit.animation.AnimateFadeInOut
import ru.maksonic.beresta.common.ui_kit.helpers.modifier.noRippleClick
import ru.maksonic.beresta.common.ui_kit.surface.SurfacePro
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.scrim
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.BaseWallpaper

/**
 * @Author maksonic on 30.10.2023
 */
private const val SHEET_OFFSET = 400

@Composable
fun Container(
    isVisible: Boolean,
    currentWallpaper: BaseWallpaper<Color>,
    updateWallpaper: (BaseWallpaper<Color>) -> Unit,
    modifier: Modifier = Modifier,
    onCloseClicked: () -> Unit,
    viewModel: WallpaperPickerViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    if (isVisible) {
        BackHandler {
            onCloseClicked()
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
                    .noRippleClick { onCloseClicked() }, contentAlignment = Alignment.BottomCenter
            ) {
                AnimatedVisibility(
                    visible = contentVisibility.value,
                    enter = slideInVertically(initialOffsetY = { SHEET_OFFSET }),
                    exit = slideOutVertically(targetOffsetY = { SHEET_OFFSET })
                ) {
                    Content(
                        wallpapersUiState = state,
                        currentSelected = currentWallpaper,
                        onCloseClicked = onCloseClicked,
                        updateWallpaper = updateWallpaper
                    )
                }
            }
        }
    }
}