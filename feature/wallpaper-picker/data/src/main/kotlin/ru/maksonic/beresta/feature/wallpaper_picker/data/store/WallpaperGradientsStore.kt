package ru.maksonic.beresta.feature.wallpaper_picker.data.store

import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.feature.wallpaper_picker.domain.WallpaperParams
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperGradient

/**
 * @Author maksonic on 23.09.2023
 */
class WallpaperGradientsStore : BaseWallpaperStore<WallpaperGradient<Color>> {
    private val _gradients = listOf(
        WallpaperGradient(
            id = 200001L,
            gradientType = WallpaperGradient.Type.VERTICAL,
            value = listOf(Color.Red, Color.Magenta),
            isDark = false
        ),
        WallpaperGradient(
            id = 200002L,
            gradientType = WallpaperGradient.Type.VERTICAL,
            value = listOf(Color.Red, Color.Blue),
            isDark = false
        ),
        WallpaperGradient(
            id = 200003L,
            gradientType = WallpaperGradient.Type.VERTICAL,
            value = listOf(Color.Green, Color.Cyan),
            isDark = false
        ),
    )

    private val wallpaperDefault = WallpaperGradient<Color>(
        id = 0,
        gradientType = WallpaperGradient.Type.VERTICAL,
        value = emptyList(),
        isDark = false
    )

    override val data: List<WallpaperGradient<Color>> = _gradients

    override fun findByParams(params: WallpaperParams): WallpaperGradient<Color> =
        _gradients.find { it.id == params.id } ?: wallpaperDefault
}