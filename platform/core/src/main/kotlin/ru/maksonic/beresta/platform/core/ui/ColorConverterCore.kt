package ru.maksonic.beresta.platform.core.ui


import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.common.core.ui.ColorConverter

/**
 * @Author maksonic on 21.11.2023
 */
class ColorConverterCore : ColorConverter<Color> {
    override fun hexToColor(colorString: String): Color = Color(
        red = colorString.substring(2, 4).toInt(16),
        green = colorString.substring(4, 6).toInt(16),
        blue = colorString.substring(6, 8).toInt(16),
        alpha = colorString.substring(0, 2).toInt(16)
    )
}