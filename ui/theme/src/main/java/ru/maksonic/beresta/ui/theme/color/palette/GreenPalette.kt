package ru.maksonic.beresta.ui.theme.color.palette

import ru.maksonic.beresta.ui.theme.color.Palette

/**
 * @Author maksonic on 25.02.2023
 */
val lightGreenPalette = baseLightPalette.copy(
    primary = Palette.vividMalachite,
    tertiary = Palette.mintGreen,
    onTertiary = Palette.vividMalachite,
    tertiaryContainer = Palette.nyanza,
    onTertiaryContainer = Palette.menthol,
    surface = Palette.nyanza,
    onSurface = Palette.menthol,
    surfaceVariant = Palette.snowyMint,
    onSurfaceVariant = Palette.nyanza,
)
val outlinedGreenPalette = baseLightPalette.copy(
    primary = Palette.electricGreen,
    onPrimary = Palette.nero,
    onSurface = Palette.snowyMint
)
val darkGreenPalette = baseDarkPalette.copy(primary = Palette.mintGreen)
val highContrastGreenPalette = highContrastPalette.copy(primary = Palette.electricGreen)