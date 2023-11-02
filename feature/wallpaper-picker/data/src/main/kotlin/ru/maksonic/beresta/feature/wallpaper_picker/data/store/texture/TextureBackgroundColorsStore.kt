package ru.maksonic.beresta.feature.wallpaper_picker.data.store.texture

import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.feature.wallpaper_picker.domain.BackgroundColor

/**
 * @Author maksonic on 31.10.2023
 */
class TextureBackgroundColorsStore : BaseWallpaperColors() {
    private val prefixColors: List<BackgroundColor<Color>> = listOf(
        BackgroundColor(0L, Color.DarkGray, false),
        BackgroundColor(1L, Color.White, false),
        BackgroundColor(2L, Color.Black, true)
    )

    val colors: List<BackgroundColor<Color>> = prefixColors + baseColors.toList()
}