package ru.maksonic.beresta.ui.theme.color.app_palette

import ru.maksonic.beresta.ui.theme.color.color_palette.Palette

/**
 * @Author maksonic on 26.02.2023
 */
val outlinedLightBlackPalette = baseLightPalette.copy(
    onPrimary = Palette.nero,
    onSecondary = Palette.nero,
    tertiary = Palette.nero,
    onTertiary = Palette.white,
    tertiaryContainer = Palette.nero,
    onTertiaryContainer = Palette.white,
    inversePrimary = Palette.white,
    primary = Palette.nero,
    onSnack = Palette.white
)
val outlinedDarkBlackPalette = baseDarkPalette.copy(
    primary = Palette.white,
    tertiaryContainer = Palette.white,
    onPrimary = Palette.white,
    onSnack = Palette.black,
)