package ru.maksonic.beresta.ui.theme.color.palette

import ru.maksonic.beresta.ui.theme.color.Palette

/**
 * @Author maksonic on 26.02.2023
 */
val lightBluePalette = baseLightPalette.copy(
    primary = Palette.coolBlack,
    tertiary = Palette.anakiwa,
    onTertiary = Palette.coolBlack,
    tertiaryContainer = Palette.solitude,
    onTertiaryContainer = Palette.paleCornflowerBlue,
    surface = Palette.solitude,
    onSurface = Palette.paleCornflowerBlue,
    surfaceVariant = Palette.frenchPassLight,
    onSurfaceVariant = Palette.solitude,
)
val darkBluePalette = baseDarkPalette.copy(primary = Palette.anakiwa)
val highContrastBluePalette = highContrastPalette.copy(primary = Palette.azureRadiance)