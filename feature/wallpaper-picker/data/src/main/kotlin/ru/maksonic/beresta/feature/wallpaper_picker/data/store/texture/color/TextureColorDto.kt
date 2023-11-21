package ru.maksonic.beresta.feature.wallpaper_picker.data.store.texture.color

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 21.11.2023
 */
@Serializable
data class TextureColorDto(
    @SerialName("id") val id: Long,
    @SerialName("value") val value: String,
    @SerialName("is_dark") val isDark: Boolean
)