package ru.maksonic.beresta.ui.theme.color.palette

import ru.maksonic.beresta.ui.theme.color.Palette

/**
 * @Author maksonic on 25.02.2023
 */
val filledLightGreenPalette = baseLightPalette.copy(
    primary = Palette.Green.vividMalachite,
    tertiary = Palette.Green.mintGreen,
    onTertiary = Palette.Green.vividMalachite,
    tertiaryContainer = Palette.Green.nyanza,
    onTertiaryContainer = Palette.Green.menthol,
    surface = Palette.Green.nyanza,
    onSurface = Palette.Green.menthol,
    surfaceVariant = Palette.Green.snowyMint,
    onSurfaceVariant = Palette.Green.snowyMint,
)
val filledDarkGreenPalette = baseDarkPalette.copy(
    primary = Palette.Green.mintGreen,
    onPrimary = Palette.Green.seaweed,
    primaryContainer = Palette.Green.calPolyPomonaGreen,
    tertiary = Palette.Green.mintGreen,
    onTertiary = Palette.Green.mintGreen,
    tertiaryContainer = Palette.Green.phthaloGreen,
    onTertiaryContainer = Palette.Green.mughalGreen,
    surface = Palette.Green.phthaloGreen,
    onSurface = Palette.Green.mughalGreen,
    surfaceVariant = Palette.Green.mughalGreen,
    onSurfaceVariant = Palette.Green.japaneseLaurel,
    background = Palette.Green.seaweed
)
val outlinedLightGreenPalette = baseLightPalette.copy(
    primary = Palette.Green.electricGreen,
    onPrimary = Palette.nero,
    onSurface = Palette.Green.snowyMint,
    onSurfaceVariant = Palette.Green.snowyMint
)
val outlinedDarkGreenPalette = baseDarkPalette.copy(primary = Palette.Green.mintGreen)
val highContrastGreenPalette = highContrastPalette.copy(primary = Palette.Green.electricGreen)