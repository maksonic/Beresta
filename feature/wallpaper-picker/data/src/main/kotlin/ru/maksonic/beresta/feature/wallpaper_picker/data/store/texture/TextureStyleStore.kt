package ru.maksonic.beresta.feature.wallpaper_picker.data.store.texture

import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.feature.wallpaper_picker.domain.BackgroundColor
import ru.maksonic.beresta.feature.wallpaper_picker.domain.TintColor
import ru.maksonic.beresta.feature.wallpaper_picker.domain.helpers.TextureStyle

/**
 * @Author maksonic on 31.10.2023
 */
class TextureStyleStore {
    /*
    * ID tint - 700000 - light | 710000 - dark
    * ID background - 800000 - light | 810000 - dark
    * */
    private val t700001 = Color(0xFF8C1717)
    private val b800001 = Color(0xFF641010)

    private val t700002 = Color(0xFF8D5E16)
    private val b800002 = Color(0xFF654310)

    private val t700003 = Color(0xFF8D8D16)
    private val b800003 = Color(0xFF656510)

    private val t700004 = Color(0xFF168D1E)
    private val b800004 = Color(0xFF106516)

    private val _styles: TextureStyle<Color> = listOf(
        Pair(TintColor(700001L, t700001), BackgroundColor(800001, b800001, true)),
        Pair(TintColor(700002L, t700002), BackgroundColor(800002, b800002, true)),
        Pair(TintColor(700003L, t700003), BackgroundColor(800003, b800003, true)),
        Pair(TintColor(700004L, t700004), BackgroundColor(800004, b800004, true)),
    )

    val dayStyleList = _styles
}