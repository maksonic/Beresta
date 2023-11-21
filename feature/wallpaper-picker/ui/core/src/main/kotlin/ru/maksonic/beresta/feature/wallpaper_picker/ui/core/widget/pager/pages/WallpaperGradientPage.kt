package ru.maksonic.beresta.feature.wallpaper_picker.ui.core.widget.pager.pages

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.secondaryContainer
import ru.maksonic.beresta.common.ui_theme.colors.surface
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.BaseWallpaper
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperGradient
import ru.maksonic.beresta.feature.wallpaper_picker.ui.api.WallpaperDefault
import ru.maksonic.beresta.feature.wallpaper_picker.ui.core.widget.pager.base.BaseWallpaperItem
import ru.maksonic.beresta.feature.wallpaper_picker.ui.core.widget.pager.base.BaseWallpaperPage
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 30.10.2023
 */
const val DEF_GRADIENT_ID = 200000L

@Composable
fun WallpaperGradientPage(
    gradientsData: List<WallpaperGradient<Color>>,
    currentSelectedId: Long,
    onClick: (BaseWallpaper<Color>) -> Unit
) {
    if (gradientsData.isNotEmpty()) {
        BaseWallpaperPage {
            items(gradientsData) { gradient ->
                BaseWallpaperItem(
                    isSelected = gradient.id == currentSelectedId,
                    onClick = { gradient.onClick(currentSelectedId, onClick, WallpaperDefault) }
                ) {
                    WallpaperGradientContent(gradient)
                }
            }
        }
    } else {
        Box(
            Modifier
                .fillMaxSize()
                .background(secondaryContainer), contentAlignment = Alignment.Center
        ) {
            Text(text.editor.errorWallpaperGradientsNotFound)
        }
    }
}

@Composable
internal fun WallpaperGradientContent(
    gradient: WallpaperGradient<Color>,
    modifier: Modifier = Modifier
) {
    if (gradient.id != DEF_GRADIENT_ID) {
        ValidGradient(gradient, modifier)
    } else {
        WallpaperColorContent(color = WallpaperDefault.copy(value = surface))
    }
}

@Composable
private fun ValidGradient(
    gradient: WallpaperGradient<Color>,
    modifier: Modifier = Modifier
) {
    val animDelay = Theme.animVelocity.common
    var isAnimated by remember { mutableStateOf(false) }
    val animVelocity = if (isAnimated) animDelay else 0
    val animatedColors = gradient.value.map { color ->
        animateColorAsState(color, tween(animVelocity), label = "")
    }
    val brush = when (gradient.gradientType) {
        WallpaperGradient.Type.VERTICAL -> {
            Brush.verticalGradient(animatedColors.map { it.value })
        }

        WallpaperGradient.Type.HORIZONTAL -> {
            Brush.horizontalGradient(animatedColors.map { it.value })
        }
    }

    LaunchedEffect(Unit) {
        if (!isAnimated) {
            delay(animDelay.toLong())
            isAnimated = true
        }
    }

    Canvas(modifier.fillMaxSize(), onDraw = { drawRect(brush) })
}