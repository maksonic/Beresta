package ru.maksonic.beresta.ui.theme.color.palette

import ru.maksonic.beresta.ui.theme.color.Palette

/**
 * @Author maksonic on 25.02.2023
 */
val lightPurplePalette = baseLightPalette.copy(
    primary = Palette.deepViolet,
    tertiary = Palette.paleViolet,
    onTertiary = Palette.deepViolet,
    tertiaryContainer = Palette.magnolia,
    onTertiaryContainer = Palette.mauve,
    surface = Palette.magnolia,
    onSurface = Palette.mauve,
    surfaceVariant = Palette.brilliantLavender,
    onSurfaceVariant = Palette.magnolia,
)
val outlinedPurplePalette = baseLightPalette.copy(
    primary = Palette.electricViolet,
    onSurface = Palette.brilliantLavender
)

val darkPurplePalette = baseDarkPalette.copy(primary = Palette.paleViolet)
val highContrastPurplePalette = highContrastPalette.copy(primary = Palette.electricViolet)