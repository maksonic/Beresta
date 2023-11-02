package ru.maksonic.beresta.common.ui_theme.palette

import ru.maksonic.beresta.common.ui_theme.colors.Palette

/**
 * @Author maksonic on 26.02.2023
 */
val filledLightBluePalette = baseLightPalette.copy(
    primary = Palette.Blue.azureRadiance,
    onPrimary = Palette.Blue.coolBlack,
    onPrimaryContainer = Palette.Blue.eerieBlue,
    secondary = Palette.Red.cinderella,
    onSecondary = Palette.Blue.eerieBlue,
    secondaryContainer = Palette.Blue.solitude,
    onSecondaryContainer = Palette.Blue.paleCornflowerBlue,
    tertiary = Palette.Red.melon,
    tertiaryContainer = Palette.Blue.coolBlack,
    onTertiaryContainer = Palette.white,
    background = Palette.Blue.zircon,
    surface = Palette.Blue.zircon,
    onSurface = Palette.Blue.coolBlack,
    surfaceVariant = Palette.Blue.frenchPassLight,
    onSurfaceVariant = Palette.Blue.paleCornflowerBlue,
    inverseSurface = Palette.Blue.coolBlack,
    inversePrimary = Palette.Red.roseBud,
    surfaceTint = Palette.Blue.azureRadiance,
    outlineVariant = Palette.Blue.solitude,
    onSnack = Palette.Blue.anakiwa,
)
val filledDarkBluePalette = baseNightPalette.copy(
    primary = Palette.Blue.x0,
    onPrimary = Palette.Blue.x0,
    primaryContainer = Palette.Blue.x2,
    onPrimaryContainer = Palette.white,
    secondary = Palette.Blue.x4,
    onSecondary = Palette.white,
    secondaryContainer = Palette.Blue.x2,
    onSecondaryContainer = Palette.Blue.x5,
    tertiary = Palette.Blue.x5,
    tertiaryContainer = Palette.Blue.x0,
    onTertiaryContainer = Palette.Blue.x1,
    background = Palette.Blue.x1,
    surface = Palette.Blue.x1,
    surfaceVariant = Palette.Blue.x3,
    onSurfaceVariant = Palette.Blue.x4,
    surfaceTint = Palette.black,
    inversePrimary = Palette.Blue.x5,
    outlineVariant = Palette.Blue.x3,
    snack = Palette.Blue.eerieBlue,
    onSnack = Palette.Blue.anakiwa,
    onSnackContainer = Palette.white
)
val outlinedDarkBluePalette = baseNightPalette.copy(
    primary = Palette.Blue.anakiwa,
    tertiaryContainer = Palette.Blue.anakiwa,
    onPrimary = Palette.Blue.anakiwa,
    onSnack = Palette.Blue.coolBlack,
)
val highContrastBluePalette = darkPalette.copy(
    primary = Palette.Blue.azureRadiance,
    tertiaryContainer = Palette.Blue.azureRadiance,
    onTertiaryContainer = Palette.white,
    onPrimary = Palette.Blue.azureRadiance,
)