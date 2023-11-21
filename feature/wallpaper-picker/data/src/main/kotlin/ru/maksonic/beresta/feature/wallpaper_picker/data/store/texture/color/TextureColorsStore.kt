package ru.maksonic.beresta.feature.wallpaper_picker.data.store.texture.color

import androidx.compose.ui.graphics.Color
import kotlinx.serialization.json.Json
import ru.maksonic.beresta.common.core.json.JsonConverter
import ru.maksonic.beresta.common.core.ui.ColorConverter
import ru.maksonic.beresta.feature.wallpaper_picker.data.store.texture.BaseWallpaperColors
import ru.maksonic.beresta.feature.wallpaper_picker.domain.BackgroundColor
import ru.maksonic.beresta.feature.wallpaper_picker.domain.TintColor

/**
 * @Author maksonic on 31.10.2023
 */
class TextureColorsStore(
    private val json: Json,
    private val jsonConverter: JsonConverter,
    private val colorConverter: ColorConverter<Color>
) : BaseWallpaperColors() {
    private companion object {
        private const val FILE_NAME = "texture_bg_colors.json"
    }

    private fun colors(): List<BackgroundColor<Color>> {
        val data = jsonConverter.convertFileToString(FILE_NAME).getOrNull()

        return if (data.isNullOrBlank()) {
            emptyList()
        } else {
            json.decodeFromString<List<TextureColorDto>>(data).toUi(colorConverter)
        }
    }

    val backgrounds: List<BackgroundColor<Color>> = colors()

    val tints: List<TintColor<Color>> = colors().toTints()
}