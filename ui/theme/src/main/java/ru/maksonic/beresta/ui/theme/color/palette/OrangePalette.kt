package ru.maksonic.beresta.ui.theme.color.palette

import ru.maksonic.beresta.ui.theme.color.Palette

/**
 * @Author maksonic on 25.02.2023
 */
val lightOrangePalette = baseLightPalette.copy(
    primary = Palette.mustardBrown,
    tertiary = Palette.peachOrange,
    tertiaryContainer = Palette.serenade,
    onTertiaryContainer = Palette.frangipane,
    surfaceVariant = Palette.blanchedAlmond,
    onSurfaceVariant = Palette.serenade,
)

val darkOrangePalette = baseDarkPalette.copy(primary = Palette.peachOrange)
val highContrastOrangePalette = highContrastPalette.copy(primary = Palette.vividGamboge)