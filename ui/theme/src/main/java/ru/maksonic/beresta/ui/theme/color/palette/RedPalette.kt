package ru.maksonic.beresta.ui.theme.color.palette

import ru.maksonic.beresta.ui.theme.color.Palette

/**
 * @Author maksonic on 25.02.2023
 */
val filledLightRedPalette = baseLightPalette.copy(
    primary = Palette.Red.blood,
    tertiary = Palette.Red.roseBud,
    onTertiary = Palette.Red.blood,
    tertiaryContainer = Palette.Red.mistyRose,
    onTertiaryContainer = Palette.Red.melon,
    surface = Palette.Red.mistyRose,
    onSurface = Palette.Red.melon,
    surfaceVariant = Palette.Red.cinderella,
    onSurfaceVariant = Palette.Red.cinderella,
)
val filledDarkRedPalette = baseDarkPalette.copy(
    primary = Palette.Red.roseBud,
    onPrimary = Palette.Red.licorice,
    primaryContainer = Palette.Red.temptress,
    tertiary = Palette.Red.roseBud,
    onTertiary = Palette.Red.roseBud,
    tertiaryContainer = Palette.Red.darkSienna,
    onTertiaryContainer = Palette.Red.caputMortuum,
    surface = Palette.Red.darkSienna,
    onSurface = Palette.Red.caputMortuum,
    surfaceVariant = Palette.Red.caputMortuum,
    onSurfaceVariant = Palette.Red.garnet,
    background = Palette.Red.licorice
)
val outlinedLightRedPalette = baseLightPalette.copy(
    primary = Palette.Red.coralRed,
    onSurface = Palette.Red.cinderella,
    onSurfaceVariant = Palette.Red.cinderella
)
val outlinedDarkRedPalette = baseDarkPalette.copy(primary = Palette.Red.roseBud)
val highContrastRedPalette = highContrastPalette.copy(primary = Palette.Red.coralRed)