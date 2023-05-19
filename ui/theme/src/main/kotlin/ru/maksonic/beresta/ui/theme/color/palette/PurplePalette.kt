package ru.maksonic.beresta.ui.theme.color.palette

import ru.maksonic.beresta.ui.theme.color.Palette

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
    onTertiary = Palette.Purple.deepViolet,
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
    onSnackContainer = Palette.Purple.mauve
    )
val filledDarkPurplePalette = baseDarkPalette.copy(
    primary = Palette.Purple.x0,
    onPrimary = Palette.Purple.x0,
    primaryContainer = Palette.Purple.x2,
    onPrimaryContainer = Palette.white,
    secondary = Palette.Purple.x4,
    onSecondary = Palette.white,
    secondaryContainer = Palette.Purple.x2,
    onSecondaryContainer = Palette.Purple.x5,
    tertiary = Palette.Purple.x5,
    onTertiary = Palette.white,
    tertiaryContainer = Palette.Purple.x0,
    onTertiaryContainer = Palette.white,
    background = Palette.Purple.x1,
    surface = Palette.Purple.x1,
    surfaceVariant = Palette.Purple.x3,
    surfaceTint = Palette.black,
    onSurfaceVariant = Palette.Purple.x4,
    inverseOnSurface = Palette.Purple.x4,
    inversePrimary = Palette.Purple.x5,
    outlineVariant = Palette.Purple.x3,
    onSnackContainer = Palette.Purple.mauve
)
val outlinedLightPurplePalette = baseLightPalette.copy(
    onPrimary = Palette.Purple.deepViolet,
    onTertiary = Palette.Purple.deepViolet,
    tertiaryContainer = Palette.Purple.electricViolet,
    onTertiaryContainer = Palette.white,
    inversePrimary = Palette.Green.mintGreen,
    primary = Palette.Purple.electricViolet,
    onSnackContainer = Palette.Purple.mauve
)
val outlinedDarkPurplePalette = baseDarkPalette.copy(
    primary = Palette.Purple.paleViolet,
    tertiaryContainer = Palette.Purple.paleViolet,
    onPrimary = Palette.Purple.paleViolet,
    onSnackContainer = Palette.Purple.mauve
)
val highContrastPurplePalette = highContrastPalette.copy(
    primary = Palette.Purple.electricViolet,
    tertiaryContainer = Palette.Purple.electricViolet,
    onTertiaryContainer = Palette.white,
    onPrimary = Palette.Purple.electricViolet,
    onSnackContainer = Palette.Purple.electricViolet
)