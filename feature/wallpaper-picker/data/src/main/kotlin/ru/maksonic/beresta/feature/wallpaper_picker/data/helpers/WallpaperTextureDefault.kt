package ru.maksonic.beresta.feature.wallpaper_picker.data.helpers

import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperTexture

/**
 * @Author maksonic on 31.10.2023
 */
internal val textureDefault = WallpaperTexture(
    id = 0,
    tintColor = wallpaperTintColorDefault,
    backgroundColor = wallpaperBackgroundColorDefault,
    resId = 0,
    isDark = false
)

fun List<WallpaperTexture<Color>>.findById(id: Long) = this.find { it.id == id } ?: textureDefault