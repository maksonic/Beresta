package ru.maksonic.beresta.ui.theme.color.palette

import ru.maksonic.beresta.ui.theme.color.Palette

/**
 * @Author maksonic on 26.02.2023
 */
val outlinedBlackPalette = baseLightPalette.copy(
    primary = Palette.nero,
    onSurface = Palette.alto,
    onBackground = Palette.black
)
val darkBlackPalette = baseDarkPalette.copy(primary = Palette.mineShaft)
val highContrastBlackPalette = highContrastPalette.copy(primary = Palette.nero)