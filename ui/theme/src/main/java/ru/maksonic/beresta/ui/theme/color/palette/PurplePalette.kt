package ru.maksonic.beresta.ui.theme.color.palette

import ru.maksonic.beresta.ui.theme.color.Palette

/**
 * @Author maksonic on 25.02.2023
 */
val filledLightPurplePalette = baseLightPalette.copy(
    primary = Palette.Purple.deepViolet,
    tertiary = Palette.Purple.paleViolet,
    onTertiary = Palette.Purple.deepViolet,
    tertiaryContainer = Palette.Purple.magnolia,
    onTertiaryContainer = Palette.Purple.mauve,
    surface = Palette.Purple.magnolia,
    onSurface = Palette.Purple.mauve,
    surfaceVariant = Palette.Purple.brilliantLavender,
    onSurfaceVariant = Palette.Purple.brilliantLavender,
)
val filledDarkPurplePalette = baseDarkPalette.copy(
    primary = Palette.Purple.paleViolet,
    onPrimary = Palette.Purple.eeriePurple,
    primaryContainer = Palette.Purple.russianViolet,
    tertiary = Palette.Purple.paleViolet,
    onTertiary = Palette.Purple.paleViolet,
    tertiaryContainer = Palette.Purple.deepPurple,
    onTertiaryContainer = Palette.Purple.americanPurple,
    surface = Palette.Purple.deepPurple,
    onSurface = Palette.Purple.americanPurple,
    surfaceVariant = Palette.Purple.americanPurple,
    onSurfaceVariant = Palette.Purple.imperial,
    background = Palette.Purple.eeriePurple
)
val outlinedLightPurplePalette = baseLightPalette.copy(
    primary = Palette.Purple.electricViolet,
    onSurface = Palette.Purple.brilliantLavender,
    onSurfaceVariant = Palette.Purple.brilliantLavender
)
val outlinedDarkPurplePalette = baseDarkPalette.copy(primary = Palette.Purple.paleViolet)
val highContrastPurplePalette = highContrastPalette.copy(primary = Palette.Purple.electricViolet)