package ru.maksonic.beresta.ui.theme.color.palette

import ru.maksonic.beresta.ui.theme.color.Palette

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
    inversePrimary = Palette.black,
    primary = Palette.nero,
)
val outlinedDarkBlackPalette = baseDarkPalette.copy(
    primary = Palette.white,
    tertiaryContainer = Palette.white,
    onPrimary = Palette.white
)