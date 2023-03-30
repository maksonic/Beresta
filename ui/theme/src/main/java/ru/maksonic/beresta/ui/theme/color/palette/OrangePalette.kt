package ru.maksonic.beresta.ui.theme.color.palette

import ru.maksonic.beresta.ui.theme.color.Palette

/**
 * @Author maksonic on 25.02.2023
 */
val filledLightOrangePalette = baseLightPalette.copy(
    primary = Palette.Orange.vividGamboge,
    onPrimary = Palette.Orange.mustardBrown,
    onPrimaryContainer = Palette.Orange.cola,
    secondary = Palette.Yellow.lemonChiffon,
    onSecondary = Palette.Orange.cola,
    secondaryContainer = Palette.Orange.serenade,
    onSecondaryContainer = Palette.Orange.frangipane,
    tertiary = Palette.Yellow.blond,
    onTertiary = Palette.Orange.mustardBrown,
    tertiaryContainer = Palette.Orange.mustardBrown,
    onTertiaryContainer = Palette.white,
    background = Palette.Orange.bridalHeath,
    surface = Palette.Orange.bridalHeath,
    onSurface = Palette.Orange.mustardBrown,
    surfaceVariant = Palette.Orange.blanchedAlmond,
    onSurfaceVariant = Palette.Orange.frangipane,
    inverseSurface = Palette.Orange.mustardBrown,
    inversePrimary = Palette.Yellow.pastelYellow,
    surfaceTint = Palette.Orange.vividGamboge,
    outlineVariant = Palette.Orange.serenade
)
val filledDarkOrangePalette = baseDarkPalette.copy(
    primary = Palette.Orange.x0,
    onPrimary = Palette.Orange.x0,
    primaryContainer = Palette.Orange.x2,
    onPrimaryContainer = Palette.white,
    secondary = Palette.Orange.x4,
    onSecondary = Palette.white,
    secondaryContainer = Palette.Orange.x2,
    onSecondaryContainer = Palette.Orange.x5,
    tertiary = Palette.Orange.x5,
    onTertiary = Palette.white,
    tertiaryContainer = Palette.Orange.x0,
    onTertiaryContainer = Palette.Orange.x1,
    background = Palette.Orange.x1,
    surface = Palette.Orange.x1,
    surfaceVariant = Palette.Orange.x3,
    onSurfaceVariant = Palette.Orange.x4,
    surfaceTint = Palette.black,
    inverseOnSurface = Palette.Orange.x4,
    inversePrimary = Palette.Orange.x5,
    outlineVariant = Palette.Orange.x3
)
val outlinedLightOrangePalette = baseLightPalette.copy(
    onPrimary = Palette.Orange.mustardBrown,
    onTertiary = Palette.Orange.mustardBrown,
    tertiaryContainer = Palette.Orange.vividGamboge,
    onTertiaryContainer = Palette.white,
    inversePrimary = Palette.Yellow.pastelYellow,
    primary = Palette.Orange.vividGamboge,
)
val outlinedDarkOrangePalette = baseDarkPalette.copy(
    primary = Palette.Orange.peachOrange,
    tertiaryContainer = Palette.Orange.peachOrange,
    onPrimary = Palette.Orange.peachOrange
)
val highContrastOrangePalette = highContrastPalette.copy(
    primary = Palette.Orange.vividGamboge,
    tertiaryContainer = Palette.Orange.vividGamboge,
    onPrimary = Palette.Orange.vividGamboge
)