package ru.maksonic.beresta.feature.wallpaper_picker.data.store.gradient

import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.common.core.ui.ColorConverter
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperGradient

/**
 * @Author maksonic on 21.11.2023
 */
private fun WallpaperGradientDto.toUi(colorConverter: ColorConverter<Color>) =
    WallpaperGradient(
        id = this.id,
        gradientType = this.type,
        value = this.value.map { colorConverter.hexToColor(it) },
        isDark = this.isDark
    )

internal fun List<WallpaperGradientDto>.toUi(colorConverter: ColorConverter<Color>) =
    this.map { it.toUi(colorConverter) }