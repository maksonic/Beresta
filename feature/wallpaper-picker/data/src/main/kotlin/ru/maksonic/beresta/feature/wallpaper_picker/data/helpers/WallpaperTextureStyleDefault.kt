package ru.maksonic.beresta.feature.wallpaper_picker.data.helpers

import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.feature.wallpaper_picker.domain.BackgroundColor
import ru.maksonic.beresta.feature.wallpaper_picker.domain.TintColor
import ru.maksonic.beresta.feature.wallpaper_picker.domain.WallpaperParams

/**
 * @Author maksonic on 31.10.2023
 */
internal val wallpaperTextureStyleDefault =
    Pair(wallpaperTintColorDefault, wallpaperBackgroundColorDefault)

fun List<Pair<TintColor<Color>, BackgroundColor<Color>>>.findByParams(params: WallpaperParams) =
    this.find { it.first.id == params.tintColorId && it.second.id == params.backgroundColorId }
        ?: wallpaperTextureStyleDefault
