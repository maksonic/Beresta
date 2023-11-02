package ru.maksonic.beresta.common.ui_theme.palette

import ru.maksonic.beresta.common.ui_theme.colors.Palette

/**
 * @Author maksonic on 25.02.2023
 */
val filledLightGreenPalette = baseLightPalette.copy(
    primary = Palette.Green.electricGreen,
    onPrimary = Palette.Green.vividMalachite,
    onPrimaryContainer = Palette.Green.seaweed,
    secondary = Palette.Orange.blanchedAlmond,
    onSecondary = Palette.Green.seaweed,
    secondaryContainer = Palette.Green.nyanza,
    onSecondaryContainer = Palette.Green.menthol,
    tertiary = Palette.Orange.frangipane,
    tertiaryContainer = Palette.Green.vividMalachite,
    onTertiaryContainer = Palette.white,
    background = Palette.Green.mintCream,
    surface = Palette.Green.mintCream,
    onSurface = Palette.Green.vividMalachite,
    surfaceVariant = Palette.Green.snowyMint,
    onSurfaceVariant = Palette.Green.menthol,
    inverseSurface = Palette.Green.vividMalachite,
    inversePrimary = Palette.Orange.peachOrange,
    surfaceTint = Palette.Green.electricGreen,
    outlineVariant = Palette.Green.nyanza,
    onSnack = Palette.Green.mintGreen
)
val filledDarkGreenPalette = baseNightPalette.copy(
    primary = Palette.Green.x0,
    onPrimary = Palette.Green.x0,
    primaryContainer = Palette.Green.x2,
    onPrimaryContainer = Palette.white,
    secondary = Palette.Green.x4,
    onSecondary = Palette.white,
    secondaryContainer = Palette.Green.x2,
    onSecondaryContainer = Palette.Green.x5,
    tertiary = Palette.Green.x5,
    tertiaryContainer = Palette.Green.x0,
    onTertiaryContainer = Palette.Green.x1,
    background = Palette.Green.x1,
    surface = Palette.Green.x1,
    surfaceVariant = Palette.Green.x3,
    onSurfaceVariant = Palette.Green.x4,
    surfaceTint = Palette.black,
    inversePrimary = Palette.Green.x5,
    outlineVariant = Palette.Green.x3,
    snack = Palette.Green.seaweed,
    onSnack = Palette.Green.mintGreen,
    onSnackContainer = Palette.white
)
val outlinedLightGreenPalette = baseLightPalette.copy(
    onPrimary = Palette.Green.vividMalachite,
    tertiaryContainer = Palette.Green.electricGreen,
    onTertiaryContainer = Palette.nero,
    inversePrimary = Palette.Orange.peachOrange,
    primary = Palette.Green.electricGreen,
    onSnack = Palette.Green.mintGreen,
)
val outlinedDarkGreenPalette = baseNightPalette.copy(
    primary = Palette.Green.mintGreen,
    tertiaryContainer = Palette.Green.mintGreen,
    onPrimary = Palette.Green.mintGreen,
    onSnack = Palette.Green.vividMalachite,
)
val highContrastGreenPalette = darkPalette.copy(
    primary = Palette.Green.electricGreen,
    tertiaryContainer = Palette.Green.electricGreen,
    onPrimary = Palette.Green.electricGreen,
    onSnack = Palette.Green.vividMalachite
)