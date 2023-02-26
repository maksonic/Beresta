package ru.maksonic.beresta.ui.theme.color.palette

import ru.maksonic.beresta.ui.theme.color.Palette

/**
 * @Author maksonic on 25.02.2023
 */
val lightRedPalette = baseLightPalette.copy(
    primary = Palette.blood,
    tertiary = Palette.roseBud,
    onTertiary = Palette.blood,
    tertiaryContainer = Palette.mistyRose,
    onTertiaryContainer = Palette.melon,
    surface = Palette.mistyRose,
    onSurface = Palette.melon,
    surfaceVariant = Palette.cinderella,
    onSurfaceVariant = Palette.mistyRose,
)
val outlinedRedPalette = baseLightPalette.copy(
    primary = Palette.coralRed,
    onSurface = Palette.cinderella
)
val darkRedPalette = baseDarkPalette.copy(primary = Palette.roseBud)
val highContrastRedPalette = highContrastPalette.copy(primary = Palette.coralRed)