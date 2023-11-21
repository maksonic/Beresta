package ru.maksonic.beresta.feature.wallpaper_picker.data.store.gradient

import androidx.compose.ui.graphics.Color
import kotlinx.serialization.json.Json
import ru.maksonic.beresta.common.core.json.JsonConverter
import ru.maksonic.beresta.common.core.ui.ColorConverter
import ru.maksonic.beresta.feature.wallpaper_picker.data.store.BaseWallpaperStore
import ru.maksonic.beresta.feature.wallpaper_picker.domain.WallpaperParams
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperGradient

/**
 * @Author maksonic on 23.09.2023
 */
class WallpaperGradientsStore(
    private val json: Json,
    private val jsonConverter: JsonConverter,
    private val colorConverter: ColorConverter<Color>
) : BaseWallpaperStore<WallpaperGradient<Color>> {
    private companion object {
        private const val FILE_NAME = "gradients.json"
    }

    private val wallpaperDefault = WallpaperGradient(
        id = 200000L,
        gradientType = WallpaperGradient.Type.VERTICAL,
        value = listOf(Color.Transparent, Color.Transparent),
        isDark = false
    )

    private fun gradients(): List<WallpaperGradient<Color>> {
        val data = jsonConverter.convertFileToString(FILE_NAME).getOrNull()

        return if (data.isNullOrBlank()) {
            emptyList()
        } else {
            json.decodeFromString<List<WallpaperGradientDto>>(data).toUi(colorConverter)
        }
    }

    override val data: List<WallpaperGradient<Color>> = gradients()

    override fun findByParams(params: WallpaperParams): WallpaperGradient<Color> {
        return data.find { it.id == params.id } ?: wallpaperDefault
    }
}