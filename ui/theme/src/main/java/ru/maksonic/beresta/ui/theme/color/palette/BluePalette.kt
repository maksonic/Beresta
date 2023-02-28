package ru.maksonic.beresta.ui.theme.color.palette

import ru.maksonic.beresta.ui.theme.color.Palette

/**
 * @Author maksonic on 26.02.2023
 */
val filledLightBluePalette = baseLightPalette.copy(
    primary = Palette.Blue.coolBlack,
    tertiary = Palette.Blue.anakiwa,
    onTertiary = Palette.Blue.coolBlack,
    tertiaryContainer = Palette.Blue.solitude,
    onTertiaryContainer = Palette.Blue.paleCornflowerBlue,
    surface = Palette.Blue.solitude,
    onSurface = Palette.Blue.paleCornflowerBlue,
    surfaceVariant = Palette.Blue.frenchPassLight,
    onSurfaceVariant = Palette.Blue.frenchPassLight,
)
val filledDarkBluePalette = baseDarkPalette.copy(
    primary = Palette.Blue.anakiwa,
    onPrimary = Palette.Blue.eerieBlue,
    primaryContainer = Palette.Blue.cloudBurst,
    tertiary = Palette.Blue.anakiwa,
    onTertiary = Palette.Blue.anakiwa,
    tertiaryContainer = Palette.Blue.yankeesBlue,
    onTertiaryContainer = Palette.Blue.japaneseIndigo,
    surface = Palette.Blue.yankeesBlue,
    onSurface = Palette.Blue.japaneseIndigo,
    surfaceVariant = Palette.Blue.japaneseIndigo,
    onSurfaceVariant = Palette.Blue.policeBlue,
    background = Palette.Blue.eerieBlue
)
val outlinedDarkBluePalette = baseDarkPalette.copy(primary = Palette.Blue.anakiwa)
val highContrastBluePalette = highContrastPalette.copy(primary = Palette.Blue.azureRadiance)