package ru.maksonic.beresta.ui.theme.color.palette

import ru.maksonic.beresta.ui.theme.color.Palette

/**
 * @Author maksonic on 26.02.2023
 */
val outlinedLightBlackPalette = baseLightPalette.copy(
    primary = Palette.nero,
    onSurface = Palette.alto,
    onSurfaceVariant = Palette.alto,
    onBackground = Palette.black
)
val outlinedDarkBlackPalette = baseDarkPalette.copy(primary = Palette.snow)
val highContrastBlackPalette = highContrastPalette.copy(primary = Palette.nero)