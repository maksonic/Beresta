package ru.maksonic.beresta.feature.wallpaper_picker.data.helpers

import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.feature.wallpaper_picker.domain.BackgroundColor

/**
 * @Author maksonic on 31.10.2023
 */
val wallpaperBackgroundColorDefault = BackgroundColor(0, Color.Transparent, false)

fun List<BackgroundColor<Color>>.findById(id: Long) = this.find { it.id == id }
    ?: wallpaperBackgroundColorDefault