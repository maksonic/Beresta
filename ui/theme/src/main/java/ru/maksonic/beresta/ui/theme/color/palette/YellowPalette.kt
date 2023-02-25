package ru.maksonic.beresta.ui.theme.color.palette

import ru.maksonic.beresta.ui.theme.color.Palette

/**
 * @Author maksonic on 25.02.2023
 */
val lightYellowPalette = baseLightPalette.copy(
    primary = Palette.buddhaGold,
    tertiary = Palette.pastelYellow,
    tertiaryContainer = Palette.cosmicLatte,
    onTertiaryContainer = Palette.blond,
    surfaceVariant = Palette.lemonChiffon,
    onSurfaceVariant = Palette.cosmicLatte,
)

val darkYellowPalette = baseDarkPalette.copy(primary = Palette.frangipane)
val highContrastYellowPalette = highContrastPalette.copy(primary = Palette.bananaYellow)