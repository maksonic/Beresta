package ru.maksonic.beresta.feature.wallpaper_picker.data.helpers

import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.feature.wallpaper_picker.domain.TintColor

/**
 * @Author maksonic on 31.10.2023
 */
internal val wallpaperTintColorDefault = TintColor(0, Color.Transparent)

internal fun List<TintColor<Color>>.findById(id: Long) = this.find { it.id == id }
    ?: wallpaperTintColorDefault