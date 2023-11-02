package ru.maksonic.beresta.feature.wallpaper_picker.data.store.texture

import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.feature.wallpaper_picker.domain.TintColor

/**
 * @Author maksonic on 31.10.2023
 */
class TextureTintColorsStore : BaseWallpaperColors() {

    private val prefixColors: List<TintColor<Color>> = listOf(
        TintColor(0L, Color.DarkGray),
        TintColor(1L, Color.White),
        TintColor(2L, Color.Black)
    )

    val colors: List<TintColor<Color>> =
        prefixColors + baseColors.toList().map { TintColor(it.id, it.value) }
}