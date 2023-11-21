package ru.maksonic.beresta.feature.wallpaper_picker.data.store.gradient

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperGradient

/**
 * @Author maksonic on 20.11.2023
 */
@Serializable
data class WallpaperGradientDto(
    @SerialName("id") val id: Long,
    @SerialName("type") val type: WallpaperGradient.Type,
    @SerialName("value") val value: List<String>,
    @SerialName("is_dark") val isDark: Boolean
)