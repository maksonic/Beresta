package ru.maksonic.beresta.ui.theme.color.palette

import ru.maksonic.beresta.ui.theme.color.Palette

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
    onTertiary = Palette.Green.vividMalachite,
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
    outlineVariant = Palette.Green.nyanza
    )
val filledDarkGreenPalette = baseDarkPalette.copy(
    primary = Palette.Green.x0,
    onPrimary = Palette.Green.x0,
    primaryContainer = Palette.Green.x2,
    onPrimaryContainer = Palette.white,
    secondary = Palette.Green.x4,
    onSecondary = Palette.white,
    secondaryContainer = Palette.Green.x2,
    onSecondaryContainer = Palette.Green.x5,
    tertiary = Palette.Green.x5,
    onTertiary = Palette.white,
    tertiaryContainer = Palette.Green.x0,
    onTertiaryContainer = Palette.Green.x1,
    background = Palette.Green.x1,
    surface = Palette.Green.x1,
    surfaceVariant = Palette.Green.x3,
    onSurfaceVariant = Palette.Green.x4,
    surfaceTint = Palette.black,
    inverseOnSurface = Palette.Green.x4,
    inversePrimary = Palette.Green.x5,
    outlineVariant = Palette.Green.x3
)
val outlinedLightGreenPalette = baseLightPalette.copy(
    onPrimary = Palette.Green.vividMalachite,
    onTertiary = Palette.Green.vividMalachite,
    tertiaryContainer = Palette.Green.electricGreen,
    onTertiaryContainer = Palette.nero,
    inversePrimary = Palette.Orange.peachOrange,
    primary = Palette.Green.electricGreen,
    )
val outlinedDarkGreenPalette = baseDarkPalette.copy(
    primary = Palette.Green.mintGreen,
    tertiaryContainer = Palette.Green.mintGreen,
    onPrimary = Palette.Green.mintGreen
)
val highContrastGreenPalette = highContrastPalette.copy(
    primary = Palette.Green.electricGreen,
    tertiaryContainer = Palette.Green.electricGreen,
    onPrimary = Palette.Green.electricGreen
)