package ru.maksonic.beresta.ui.theme.color.palette

import ru.maksonic.beresta.ui.theme.color.AppColor
import ru.maksonic.beresta.ui.theme.color.Palette

/**
 * @Author maksonic on 25.02.2023
 */
val baseLightPalette = AppColor(
    primary = Palette.Blue.azureRadiance,
    onPrimary = Palette.Blue.coolBlack,
    primaryContainer = Palette.white,
    onPrimaryContainer = Palette.nero,
    secondary = Palette.alto,
    onSecondary = Palette.nero,
    secondaryContainer = Palette.snow,
    onSecondaryContainer = Palette.alto,
    tertiary = Palette.chineseSilver,
    onTertiary = Palette.Blue.coolBlack,
    tertiaryContainer = Palette.Blue.azureRadiance,
    onTertiaryContainer = Palette.white,
    error = Palette.error,
    onError = Palette.onError,
    errorContainer = Palette.errorContainer,
    onErrorContainer = Palette.onErrorContainer,
    background = Palette.snow,
    onBackground = Palette.nero,
    surface = Palette.snow,
    onSurface = Palette.netural10,
    surfaceVariant = Palette.lightSilver,
    onSurfaceVariant = Palette.chineseSilver,
    outline = Palette.doveGray,
    inverseOnSurface = Palette.white,
    inverseSurface = Palette.gray,
    inversePrimary = Palette.Red.roseBud,
    surfaceTint = Palette.gray,
    outlineVariant = Palette.lightSilver,
    scrim = Palette.black.copy(alpha = 0.24f),
    transparent = Palette.transparent,
    idle = Palette.Blue.azureRadiance,
    black = Palette.black,
    blue = Palette.Blue.azureRadiance,
    green = Palette.Green.electricGreen,
    purple = Palette.Purple.electricViolet,
    red = Palette.Red.coralRed,
    orange = Palette.Orange.vividGamboge,
    yellow = Palette.Yellow.bananaYellow,
)

val baseDarkPalette = AppColor(
    primary = Palette.snow,
    onPrimary = Palette.snow,
    primaryContainer = Palette.mineShaft,
    onPrimaryContainer = Palette.white,
    secondary = Palette.tundora,
    onSecondary = Palette.white,
    secondaryContainer = Palette.mineShaft,
    onSecondaryContainer = Palette.gray,
    tertiary = Palette.gray,
    onTertiary = Palette.white,
    tertiaryContainer = Palette.Blue.anakiwa,
    onTertiaryContainer = Palette.nero,
    error = Palette.carmineRed,
    onError = Palette.carmineRed,
    errorContainer = Palette.carmineRed,
    onErrorContainer = Palette.carmineRed,
    background = Palette.nero,
    onBackground = Palette.white,
    surface = Palette.nero,
    onSurface = Palette.white,
    surfaceVariant = Palette.tundora,
    onSurfaceVariant = Palette.doveGray,
    outline = Palette.lightSilver,
    inverseOnSurface = Palette.mineShaft,
    inverseSurface = Palette.chineseSilver,
    inversePrimary = Palette.tundora,
    surfaceTint = Palette.white,
    outlineVariant = Palette.doveGray,
    scrim = Palette.black.copy(alpha = 0.32f),
    transparent = Palette.transparent,
    idle = Palette.Blue.azureRadiance,
    black = Palette.white,
    blue = Palette.Blue.anakiwa,
    green = Palette.Green.mintGreen,
    purple = Palette.Purple.paleViolet,
    red = Palette.Red.roseBud,
    orange = Palette.Orange.peachOrange,
    yellow = Palette.Yellow.pastelYellow,
)

val highContrastPalette = AppColor(
    primary = Palette.snow,
    onPrimary = Palette.snow,
    primaryContainer = Palette.nero,
    onPrimaryContainer = Palette.white,
    secondary = Palette.doveGray,
    onSecondary = Palette.white,
    secondaryContainer = Palette.nero,
    onSecondaryContainer = Palette.doveGray,
    tertiary = Palette.doveGray,
    onTertiary = Palette.white,
    tertiaryContainer = Palette.Blue.anakiwa,
    onTertiaryContainer = Palette.nero,
    error = Palette.carmineRed,
    onError = Palette.carmineRed,
    errorContainer = Palette.carmineRed,
    onErrorContainer = Palette.carmineRed,
    background = Palette.black,
    onBackground = Palette.white,
    surface = Palette.black,
    onSurface = Palette.white,
    surfaceVariant = Palette.tundora,
    onSurfaceVariant = Palette.tundora,
    outline = Palette.chineseSilver,
    inverseOnSurface = Palette.mineShaft,
    inverseSurface = Palette.chineseSilver,
    inversePrimary = Palette.tundora,
    surfaceTint = Palette.white,
    outlineVariant = Palette.black,
    scrim = Palette.black.copy(alpha = 0.8f),
    transparent = Palette.transparent,
    idle = Palette.Blue.azureRadiance,
    black = Palette.mineShaft,
    blue = Palette.Blue.azureRadiance,
    green = Palette.Green.electricGreen,
    purple = Palette.Purple.electricViolet,
    red = Palette.Red.coralRed,
    orange = Palette.Orange.vividGamboge,
    yellow = Palette.Yellow.bananaYellow,
)