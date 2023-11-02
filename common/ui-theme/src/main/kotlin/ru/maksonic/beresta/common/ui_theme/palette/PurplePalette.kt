package ru.maksonic.beresta.common.ui_theme.palette

import ru.maksonic.beresta.common.ui_theme.colors.Palette

/**
 * @Author maksonic on 25.02.2023
 */
val filledLightPurplePalette = baseLightPalette.copy(
    primary = Palette.Purple.electricViolet,
    onPrimary = Palette.Purple.deepViolet,
    onPrimaryContainer = Palette.Purple.darkPurple,
    secondary = Palette.Green.snowyMint,
    onSecondary = Palette.Purple.darkPurple,
    secondaryContainer = Palette.Purple.magnolia,
    onSecondaryContainer = Palette.Purple.mauve,
    tertiary = Palette.Green.menthol,
    tertiaryContainer = Palette.Purple.deepViolet,
    background = Palette.Purple.whitePointer,
    surface = Palette.Purple.whitePointer,
    onSurface = Palette.Purple.deepViolet,
    surfaceVariant = Palette.Purple.brilliantLavender,
    onSurfaceVariant = Palette.Purple.mauve,
    inverseSurface = Palette.Purple.deepViolet,
    inversePrimary = Palette.Green.mintGreen,
    surfaceTint = Palette.Purple.electricViolet,
    outlineVariant = Palette.Purple.magnolia,
    onSnack = Palette.Purple.paleViolet
)
val filledDarkPurplePalette = baseNightPalette.copy(
    primary = Palette.Purple.x0,
    onPrimary = Palette.Purple.x0,
    primaryContainer = Palette.Purple.x2,
    onPrimaryContainer = Palette.white,
    secondary = Palette.Purple.x4,
    onSecondary = Palette.white,
    secondaryContainer = Palette.Purple.x2,
    onSecondaryContainer = Palette.Purple.x5,
    tertiary = Palette.Purple.x5,
    tertiaryContainer = Palette.Purple.x0,
    onTertiaryContainer = Palette.white,
    background = Palette.Purple.x1,
    surface = Palette.Purple.x1,
    surfaceVariant = Palette.Purple.x3,
    surfaceTint = Palette.black,
    onSurfaceVariant = Palette.Purple.x4,
    inversePrimary = Palette.Purple.x5,
    outlineVariant = Palette.Purple.x3,
    snack = Palette.Purple.darkPurple,
    onSnack = Palette.Purple.paleViolet,
    onSnackContainer = Palette.white
)
val outlinedLightPurplePalette = baseLightPalette.copy(
    onPrimary = Palette.Purple.deepViolet,
    onTertiary = Palette.Purple.deepViolet,
    tertiaryContainer = Palette.Purple.electricViolet,
    onTertiaryContainer = Palette.white,
    inversePrimary = Palette.Green.mintGreen,
    primary = Palette.Purple.electricViolet,
    onSnack = Palette.Purple.paleViolet
)
val outlinedDarkPurplePalette = baseNightPalette.copy(
    primary = Palette.Purple.paleViolet,
    tertiaryContainer = Palette.Purple.paleViolet,
    onPrimary = Palette.Purple.paleViolet,
    onSnack = Palette.Purple.deepViolet
)
val highContrastPurplePalette = darkPalette.copy(
    primary = Palette.Purple.electricViolet,
    tertiaryContainer = Palette.Purple.electricViolet,
    onTertiaryContainer = Palette.white,
    onPrimary = Palette.Purple.electricViolet,
    onSnack = Palette.Purple.deepViolet
)