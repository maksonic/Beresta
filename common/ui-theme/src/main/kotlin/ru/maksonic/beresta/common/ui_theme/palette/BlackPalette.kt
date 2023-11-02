package ru.maksonic.beresta.common.ui_theme.palette

import ru.maksonic.beresta.common.ui_theme.colors.Palette

/**
 * @Author maksonic on 26.02.2023
 */
val outlinedLightBlackPalette = baseLightPalette.copy(
    onPrimary = Palette.nero,
    onSecondary = Palette.nero,
    tertiary = Palette.nero,
    tertiaryContainer = Palette.nero,
    onTertiaryContainer = Palette.white,
    inversePrimary = Palette.white,
    primary = Palette.nero,
    onSnack = Palette.white
)
val outlinedDarkBlackPalette = baseNightPalette.copy(
    primary = Palette.white,
    tertiaryContainer = Palette.white,
    onPrimary = Palette.white,
    onSnack = Palette.black,
)