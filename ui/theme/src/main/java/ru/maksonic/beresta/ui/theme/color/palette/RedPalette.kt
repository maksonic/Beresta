package ru.maksonic.beresta.ui.theme.color.palette

import ru.maksonic.beresta.ui.theme.color.Palette

/**
 * @Author maksonic on 25.02.2023
 */
val lightRedPalette = baseLightPalette.copy(
    primary = Palette.blood,
    tertiary = Palette.roseBud,
    tertiaryContainer = Palette.mistyRose,
    onTertiaryContainer = Palette.melon,
    surfaceVariant = Palette.cinderella,
    onSurfaceVariant = Palette.mistyRose,
)

val darkRedPalette = baseDarkPalette.copy(primary = Palette.roseBud)
val highContrastRedPalette = highContrastPalette.copy(primary = Palette.coralRed)