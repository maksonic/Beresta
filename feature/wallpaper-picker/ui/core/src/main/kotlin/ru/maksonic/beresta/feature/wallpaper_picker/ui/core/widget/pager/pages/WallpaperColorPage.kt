package ru.maksonic.beresta.feature.wallpaper_picker.ui.core.widget.pager.pages

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.BaseWallpaper
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperColor
import ru.maksonic.beresta.feature.wallpaper_picker.ui.api.WallpaperDefault
import ru.maksonic.beresta.feature.wallpaper_picker.ui.core.widget.pager.base.BaseWallpaperItem
import ru.maksonic.beresta.feature.wallpaper_picker.ui.core.widget.pager.base.BaseWallpaperPage

/**
 * @Author maksonic on 30.10.2023
 */
@Composable
fun WallpaperColorPage(
    colorsData: List<WallpaperColor<Color>>,
    currentSelectedId: Long,
    onClick: (BaseWallpaper<Color>) -> Unit
) {
    BaseWallpaperPage {
        itemsIndexed(colorsData) { index, color ->
            BaseWallpaperItem(
                isSelected = color.id == currentSelectedId,
                onClick = { color.onClick(currentSelectedId, onClick, WallpaperDefault) }
            ) {
                Box(contentAlignment = Alignment.Center) {
                    WallpaperColorContent(color)

                    if (index == 0) {
                        val lineColor = Theme.color.error

                        Canvas(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(Theme.shape.cornerSmall)
                                .border(1.dp, lineColor, Theme.shape.cornerSmall)
                        ) {
                            val canvasWidth = size.width
                            val canvasHeight = size.height
                            drawLine(
                                start = Offset(x = 0f, y = 0f),
                                end = Offset(x = canvasWidth, y = canvasHeight),
                                strokeWidth = 10f,
                                color = lineColor
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
internal fun WallpaperColorContent(color: WallpaperColor<Color>, modifier: Modifier = Modifier) {
    val animDelay = Theme.animVelocity.common
    var isAnimated by remember { mutableStateOf(false) }
    val animVelocity = if (isAnimated) animDelay else 0
    val animatedColor = animateColorAsState(color.value, tween(animVelocity), label = "")

    LaunchedEffect(Unit) {
        if (!isAnimated) {
            delay(animDelay.toLong())
            isAnimated = true
        }
    }

    Canvas(modifier.fillMaxSize(), onDraw = { drawRect(animatedColor.value) })
}