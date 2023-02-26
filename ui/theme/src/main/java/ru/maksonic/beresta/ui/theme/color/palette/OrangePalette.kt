package ru.maksonic.beresta.ui.theme.color.palette

import ru.maksonic.beresta.ui.theme.color.Palette

/**
 * @Author maksonic on 25.02.2023
 */
val lightOrangePalette = baseLightPalette.copy(
    primary = Palette.mustardBrown,
    tertiary = Palette.peachOrange,
    onTertiary = Palette.mustardBrown,
    tertiaryContainer = Palette.serenade,
    onTertiaryContainer = Palette.frangipane,
    surface = Palette.serenade,
    onSurface = Palette.frangipane,
    surfaceVariant = Palette.blanchedAlmond,
    onSurfaceVariant = Palette.serenade,
)

val outlinedOrangePalette = baseLightPalette.copy(
    primary = Palette.vividGamboge,
    onSurface = Palette.blanchedAlmond
)

val darkOrangePalette = baseDarkPalette.copy(primary = Palette.peachOrange)
val highContrastOrangePalette = highContrastPalette.copy(primary = Palette.vividGamboge)