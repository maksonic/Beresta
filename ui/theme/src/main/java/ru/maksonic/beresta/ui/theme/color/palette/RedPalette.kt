package ru.maksonic.beresta.ui.theme.color.palette

import ru.maksonic.beresta.ui.theme.color.Palette

/**
 * @Author maksonic on 25.02.2023
 */
val filledLightRedPalette = baseLightPalette.copy(
    primary = Palette.Red.coralRed,
    onPrimary = Palette.Red.blood,
    onPrimaryContainer = Palette.Red.sepiaBlack,
    secondary = Palette.Orange.blanchedAlmond,
    onSecondary = Palette.Red.sepiaBlack,
    secondaryContainer = Palette.Red.mistyRose,
    onSecondaryContainer = Palette.Red.melon,
    tertiary = Palette.Red.roseBud,
    onTertiary = Palette.Red.blood,
    tertiaryContainer = Palette.Red.blood,
    background = Palette.Red.lavenderBlush,
    surface = Palette.Red.lavenderBlush,
    onSurface = Palette.Red.blood,
    surfaceVariant = Palette.Red.cinderella,
    onSurfaceVariant = Palette.Red.melon,
    inverseSurface = Palette.Red.blood,
    inversePrimary = Palette.Orange.peachOrange,
    surfaceTint = Palette.Red.coralRed,
)
val filledDarkRedPalette = baseDarkPalette.copy(
    primary = Palette.Red.x0,
    onPrimary = Palette.Red.x0,
    primaryContainer = Palette.Red.x2,
    onPrimaryContainer = Palette.white,
    secondary = Palette.Red.x4,
    onSecondary = Palette.white,
    secondaryContainer = Palette.Red.x2,
    onSecondaryContainer = Palette.Red.x5,
    tertiary = Palette.doveGray,
    onTertiary = Palette.white,
    tertiaryContainer = Palette.Red.x0,
    onTertiaryContainer = Palette.Red.x1,
    background = Palette.Red.x1,
    surface = Palette.Red.x1,
    surfaceVariant = Palette.Red.x3,
    onSurfaceVariant = Palette.Red.x4,
    surfaceTint = Palette.black,
    inverseOnSurface = Palette.Red.x4,
    inversePrimary = Palette.Red.x5,
)
val outlinedLightRedPalette = baseLightPalette.copy(
    onPrimary = Palette.Red.blood,
    tertiary = Palette.Red.roseBud,
    onTertiary = Palette.Red.blood,
    tertiaryContainer = Palette.Red.coralRed,
    onTertiaryContainer = Palette.white,
    inversePrimary = Palette.Orange.peachOrange,
    primary = Palette.Red.coralRed,
)
val outlinedDarkRedPalette = baseDarkPalette.copy(
    primary = Palette.Red.roseBud,
    tertiaryContainer = Palette.Red.roseBud,
    onPrimary = Palette.Red.roseBud
)
val highContrastRedPalette = highContrastPalette.copy(
    primary = Palette.Red.coralRed,
    tertiaryContainer = Palette.Red.coralRed,
    onTertiaryContainer = Palette.white,
    onPrimary = Palette.Red.coralRed
)