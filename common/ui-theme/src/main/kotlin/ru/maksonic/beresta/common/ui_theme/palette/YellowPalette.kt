package ru.maksonic.beresta.common.ui_theme.palette

import ru.maksonic.beresta.common.ui_theme.colors.Palette

/**
 * @Author maksonic on 25.02.2023
 */
val filledLightYellowPalette = baseLightPalette.copy(
    primary = Palette.Yellow.bananaYellow,
    onPrimary = Palette.Yellow.buddhaGold,
    onPrimaryContainer = Palette.Yellow.americanBronze,
    secondary = Palette.Purple.brilliantLavender,
    onSecondary = Palette.Yellow.americanBronze,
    secondaryContainer = Palette.Yellow.cosmicLatte,
    onSecondaryContainer = Palette.Yellow.blond,
    tertiary = Palette.Purple.mauve,
    tertiaryContainer = Palette.Yellow.buddhaGold,
    background = Palette.Yellow.milk,
    surface = Palette.Yellow.milk,
    onSurface = Palette.Yellow.buddhaGold,
    surfaceVariant = Palette.Yellow.lemonChiffon,
    onSurfaceVariant = Palette.Yellow.blond,
    inverseSurface = Palette.Yellow.buddhaGold,
    inversePrimary = Palette.Purple.paleViolet,
    surfaceTint = Palette.Yellow.bananaYellow,
    outlineVariant = Palette.Yellow.cosmicLatte,
    onSnack = Palette.Yellow.pastelYellow
)
val filledDarkYellowPalette = baseNightPalette.copy(
    primary = Palette.Yellow.x0,
    onPrimary = Palette.Yellow.x0,
    primaryContainer = Palette.Yellow.x2,
    onPrimaryContainer = Palette.white,
    secondary = Palette.Yellow.x4,
    onSecondary = Palette.white,
    secondaryContainer = Palette.Yellow.x2,
    onSecondaryContainer = Palette.Yellow.x5,
    tertiary = Palette.Yellow.x5,
    tertiaryContainer = Palette.Yellow.x0,
    onTertiaryContainer = Palette.white,
    background = Palette.Yellow.x1,
    surface = Palette.Yellow.x1,
    surfaceVariant = Palette.Yellow.x3,
    onSurfaceVariant = Palette.Yellow.x4,
    surfaceTint = Palette.black,
    inversePrimary = Palette.Yellow.x5,
    outlineVariant = Palette.Yellow.x3,
    snack = Palette.Yellow.americanBronze,
    onSnack = Palette.Yellow.pastelYellow,
    onSnackContainer = Palette.white
)
val outlinedLightYellowPalette = baseLightPalette.copy(
    onPrimary = Palette.Yellow.buddhaGold,
    tertiaryContainer = Palette.Yellow.bananaYellow,
    onTertiaryContainer = Palette.nero,
    inversePrimary = Palette.Purple.paleViolet,
    primary = Palette.Yellow.bananaYellow,
    onSnack = Palette.Yellow.pastelYellow
)
val outlinedDarkYellowPalette = baseNightPalette.copy(
    primary = Palette.Yellow.pastelYellow,
    tertiaryContainer = Palette.Yellow.pastelYellow,
    onPrimary = Palette.Yellow.pastelYellow,
    onSnack = Palette.Yellow.buddhaGold
)
val highContrastYellowPalette = darkPalette.copy(
    primary = Palette.Yellow.bananaYellow,
    tertiaryContainer = Palette.Yellow.bananaYellow,
    onPrimary = Palette.Yellow.bananaYellow,
    onSnack = Palette.Yellow.buddhaGold
)