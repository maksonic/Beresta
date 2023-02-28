package ru.maksonic.beresta.ui.theme.color.palette

import ru.maksonic.beresta.ui.theme.color.Palette

/**
 * @Author maksonic on 25.02.2023
 */
val filledLightYellowPalette = baseLightPalette.copy(
    primary = Palette.Yellow.buddhaGold,
    tertiary = Palette.Yellow.pastelYellow,
    onTertiary = Palette.Yellow.buddhaGold,
    tertiaryContainer = Palette.Yellow.cosmicLatte,
    onTertiaryContainer = Palette.Yellow.blond,
    surface = Palette.Yellow.cosmicLatte,
    onSurface = Palette.Yellow.blond,
    surfaceVariant = Palette.Yellow.lemonChiffon,
    onSurfaceVariant = Palette.Yellow.lemonChiffon,
)
val filledDarkYellowPalette = baseDarkPalette.copy(
    primary = Palette.Yellow.pastelYellow,
    onPrimary = Palette.Yellow.eternity,
    primaryContainer = Palette.Yellow.thatchYellow,
    tertiary = Palette.Yellow.pastelYellow,
    onTertiary = Palette.Yellow.pastelYellow,
    tertiaryContainer = Palette.Yellow.pullmanYellow,
    onTertiaryContainer = Palette.Yellow.woodland,
    surface = Palette.Yellow.pullmanYellow,
    onSurface = Palette.Yellow.woodland,
    surfaceVariant = Palette.Yellow.woodland,
    onSurfaceVariant = Palette.Yellow.costaDelSol,
    background = Palette.Yellow.eternity
)
val outlinedLightYellowPalette = baseLightPalette.copy(
    primary = Palette.Yellow.bananaYellow,
    onPrimary = Palette.nero,
    onSurface = Palette.Yellow.lemonChiffon,
    onSurfaceVariant = Palette.Yellow.lemonChiffon
)
val outlinedDarkYellowPalette = baseDarkPalette.copy(primary = Palette.Yellow.pastelYellow)
val highContrastYellowPalette = highContrastPalette.copy(primary = Palette.Yellow.bananaYellow)