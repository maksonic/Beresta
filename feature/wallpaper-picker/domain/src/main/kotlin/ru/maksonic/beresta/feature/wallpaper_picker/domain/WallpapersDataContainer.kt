package ru.maksonic.beresta.feature.wallpaper_picker.domain

import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperColor
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperGradient
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperImage
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperTexture

/**
 * @Author maksonic on 30.10.2023
 */
data class WallpapersDataContainer<T>(
    val colors: List<WallpaperColor<T>>,
    val gradients: List<WallpaperGradient<T>>,
    val textures: List<WallpaperTexture<T>>,
    val images: List<WallpaperImage<T>>,
) {
    companion object {
        fun <T> empty() = WallpapersDataContainer<T>(
            colors = emptyList(),
            gradients = emptyList(),
            textures = emptyList(),
            images = emptyList()
        )
    }
}