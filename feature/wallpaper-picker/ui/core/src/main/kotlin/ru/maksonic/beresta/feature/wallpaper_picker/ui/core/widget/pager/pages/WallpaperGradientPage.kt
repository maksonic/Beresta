package ru.maksonic.beresta.feature.wallpaper_picker.ui.core.widget.pager.pages

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.BaseWallpaper
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperGradient
import ru.maksonic.beresta.feature.wallpaper_picker.ui.api.WallpaperDefault
import ru.maksonic.beresta.feature.wallpaper_picker.ui.core.widget.pager.base.BaseWallpaperItem
import ru.maksonic.beresta.feature.wallpaper_picker.ui.core.widget.pager.base.BaseWallpaperPage

/**
 * @Author maksonic on 30.10.2023
 */
@Composable
fun WallpaperGradientPage(
    gradientsData: List<WallpaperGradient<Color>>,
    currentSelectedId: Long,
    onClick: (BaseWallpaper<Color>) -> Unit
) {
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
}

@Composable
internal fun WallpaperGradientContent(
    gradient: WallpaperGradient<Color>,
    modifier: Modifier = Modifier
) {
    val animatedColors = gradient.value.map { color -> animateColorAsState(color, label = "") }
    val brush = when (gradient.gradientType) {
        WallpaperGradient.Type.VERTICAL -> {
            Brush.verticalGradient(animatedColors.map { it.value })
        }

        WallpaperGradient.Type.HORIZONTAL -> {
            Brush.horizontalGradient(animatedColors.map { it.value })
        }
    }

    Canvas(modifier.fillMaxSize(), onDraw = { drawRect(brush) })
}