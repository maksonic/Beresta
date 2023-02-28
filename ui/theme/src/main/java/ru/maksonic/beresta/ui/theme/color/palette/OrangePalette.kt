package ru.maksonic.beresta.ui.theme.color.palette

import ru.maksonic.beresta.ui.theme.color.Palette

/**
 * @Author maksonic on 25.02.2023
 */
val filledLightOrangePalette = baseLightPalette.copy(
    primary = Palette.Orange.mustardBrown,
    tertiary = Palette.Orange.peachOrange,
    onTertiary = Palette.Orange.mustardBrown,
    tertiaryContainer = Palette.Orange.serenade,
    onTertiaryContainer = Palette.Orange.frangipane,
    surface = Palette.Orange.serenade,
    onSurface = Palette.Orange.frangipane,
    surfaceVariant = Palette.Orange.blanchedAlmond,
    onSurfaceVariant = Palette.Orange.blanchedAlmond,
)
val filledDarkOrangePalette = baseDarkPalette.copy(
    primary = Palette.Orange.peachOrange,
    onPrimary = Palette.Orange.blackChocolate,
    primaryContainer = Palette.Orange.cafeNoir,
    tertiary = Palette.Orange.peachOrange,
    onTertiary = Palette.Orange.peachOrange,
    tertiaryContainer = Palette.Orange.bistre,
    onTertiaryContainer = Palette.Orange.darkBrown,
    surface = Palette.Orange.bistre,
    onSurface = Palette.Orange.darkBrown,
    surfaceVariant = Palette.Orange.darkBrown,
    onSurfaceVariant = Palette.Orange.coffee,
    background = Palette.Orange.blackChocolate
)
val outlinedLightOrangePalette = baseLightPalette.copy(
    primary = Palette.Orange.vividGamboge,
    onSurface = Palette.Orange.blanchedAlmond,
    onSurfaceVariant = Palette.Orange.blanchedAlmond
)
val outlinedDarkOrangePalette = baseDarkPalette.copy(primary = Palette.Orange.peachOrange)
val highContrastOrangePalette = highContrastPalette.copy(primary = Palette.Orange.vividGamboge)