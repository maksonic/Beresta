package ru.maksonic.beresta.feature.wallpaper_picker.ui.core.widget.pager

import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.BaseWallpaper
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperColor
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperGradient
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperImage
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperTexture

/**
 * @Author maksonic on 18.09.2023
 */
object WallpaperPickerPagerPage {
    const val COLORS = 0
    const val GRADIENTS = 1
    const val TEXTURES = 2
    const val IMAGES = 3
    const val COUNT = 4

    fun getPageByWallpaper(wallpaper: BaseWallpaper<Color>) = when (wallpaper) {
        is WallpaperColor -> COLORS
        is WallpaperGradient -> GRADIENTS
        is WallpaperTexture -> TEXTURES
        is WallpaperImage -> IMAGES
        else -> COLORS
    }
}