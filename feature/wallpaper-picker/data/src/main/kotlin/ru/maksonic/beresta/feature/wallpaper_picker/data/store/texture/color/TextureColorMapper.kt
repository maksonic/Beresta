package ru.maksonic.beresta.feature.wallpaper_picker.data.store.texture.color

import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.common.core.ui.ColorConverter
import ru.maksonic.beresta.feature.wallpaper_picker.domain.BackgroundColor
import ru.maksonic.beresta.feature.wallpaper_picker.domain.TintColor

/**
 * @Author maksonic on 21.11.2023
 */
private fun TextureColorDto.toUiBackgroundColor(colorConverter: ColorConverter<Color>) =
    BackgroundColor(
        id = this.id,
        value = colorConverter.hexToColor(this.value),
        isDark = this.isDark
    )

internal fun List<TextureColorDto>.toUi(colorConverter: ColorConverter<Color>) =
    this.map { it.toUiBackgroundColor(colorConverter) }

internal fun List<BackgroundColor<Color>>.toTints() = this.map { TintColor(it.id, it.value) }