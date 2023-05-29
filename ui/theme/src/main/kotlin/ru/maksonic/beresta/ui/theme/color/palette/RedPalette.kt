package ru.maksonic.beresta.ui.theme.color.palette

import ru.maksonic.beresta.ui.theme.color.Palette
import ru.maksonic.beresta.ui.theme.color.PlaceholderColors
import ru.maksonic.beresta.ui.theme.color.PlaceholderPalette

/**
 * @Author maksonic on 25.02.2023
 */
val filledLightRedPalette = baseLightPalette.copy(
    primary = Palette.Red.coralRed,
    onPrimary = Palette.Red.blood,
    onPrimaryContainer = Palette.Red.sepiaBlack,
    secondary = Palette.Orange.blanchedAlmond,
    onSecondary = Palette.Red.sepiaBlack,
    secondaryContainer = Palette.Red.mistyRose,
    onSecondaryContainer = Palette.Red.melon,
    tertiary = Palette.Orange.frangipane,
    onTertiary = Palette.Red.blood,
    tertiaryContainer = Palette.Red.blood,
    background = Palette.Red.lavenderBlush,
    surface = Palette.Red.lavenderBlush,
    onSurface = Palette.Red.blood,
    surfaceVariant = Palette.Red.cinderella,
    onSurfaceVariant = Palette.Red.melon,
    inverseSurface = Palette.Red.blood,
    inversePrimary = Palette.Orange.peachOrange,
    surfaceTint = Palette.Red.coralRed,
    outlineVariant = Palette.Red.mistyRose,
    onSnackContainer = Palette.Red.melon,
    placeholderColors = PlaceholderColors(
        c0 = PlaceholderPalette.Red.Light.c0,
        c1 = PlaceholderPalette.Red.Light.c1,
        c2 = PlaceholderPalette.Red.Light.c2,
        c3 = PlaceholderPalette.Red.Light.c3,
        c4 = PlaceholderPalette.Red.Light.c4,
        f0 = PlaceholderPalette.Folder.Light.f0,
        f1 = PlaceholderPalette.Folder.Light.f1,
        f2 = PlaceholderPalette.Folder.Light.f2,
        f3 = PlaceholderPalette.Folder.Light.f3,
    )
)
val filledDarkRedPalette = baseDarkPalette.copy(
    primary = Palette.Red.x0,
    onPrimary = Palette.Red.x0,
    primaryContainer = Palette.Red.x2,
    onPrimaryContainer = Palette.white,
    secondary = Palette.Red.x4,
    onSecondary = Palette.white,
    secondaryContainer = Palette.Red.x2,
    onSecondaryContainer = Palette.Red.x5,
    tertiary = Palette.Red.x5,
    onTertiary = Palette.white,
    tertiaryContainer = Palette.Red.x0,
    onTertiaryContainer = Palette.Red.x1,
    background = Palette.Red.x1,
    surface = Palette.Red.x1,
    surfaceVariant = Palette.Red.x3,
    onSurfaceVariant = Palette.Red.x4,
    surfaceTint = Palette.black,
    inverseOnSurface = Palette.Red.x4,
    inversePrimary = Palette.Red.x5,
    outlineVariant = Palette.Red.x3,
    onSnackContainer = Palette.Red.melon,
    placeholderColors = PlaceholderColors(
        c0 = PlaceholderPalette.Red.Dark.Fill.c0,
        c1 = PlaceholderPalette.Red.Dark.Fill.c1,
        c2 = PlaceholderPalette.Red.Dark.Fill.c2,
        c3 = PlaceholderPalette.Red.Dark.Fill.c3,
        c4 = PlaceholderPalette.Red.Dark.Fill.c4,
        f0 = PlaceholderPalette.Folder.Dark.f0,
        f1 = PlaceholderPalette.Folder.Dark.f1,
        f2 = PlaceholderPalette.Folder.Dark.f2,
        f3 = PlaceholderPalette.Folder.Dark.f3,
    )
)
val outlinedLightRedPalette = baseLightPalette.copy(
    onPrimary = Palette.Red.blood,
    onTertiary = Palette.Red.blood,
    tertiaryContainer = Palette.Red.coralRed,
    onTertiaryContainer = Palette.white,
    inversePrimary = Palette.Orange.peachOrange,
    primary = Palette.Red.coralRed,
    onSnackContainer = Palette.Red.melon,
    placeholderColors = PlaceholderColors(
        c0 = PlaceholderPalette.Red.Light.c0,
        c1 = PlaceholderPalette.Red.Light.c1,
        c2 = PlaceholderPalette.Red.Light.c2,
        c3 = PlaceholderPalette.Red.Light.c3,
        c4 = PlaceholderPalette.Red.Light.c4,
        f0 = PlaceholderPalette.Folder.Light.f0,
        f1 = PlaceholderPalette.Folder.Light.f1,
        f2 = PlaceholderPalette.Folder.Light.f2,
        f3 = PlaceholderPalette.Folder.Light.f3,
    )
)
val outlinedDarkRedPalette = baseDarkPalette.copy(
    primary = Palette.Red.roseBud,
    tertiaryContainer = Palette.Red.roseBud,
    onPrimary = Palette.Red.roseBud,
    onSnackContainer = Palette.Red.melon,
    placeholderColors = PlaceholderColors(
        c0 = PlaceholderPalette.Red.Dark.c0,
        c1 = PlaceholderPalette.Red.Dark.c1,
        c2 = PlaceholderPalette.Red.Dark.c2,
        c3 = PlaceholderPalette.Red.Dark.c3,
        c4 = PlaceholderPalette.Red.Dark.c4,
        f0 = PlaceholderPalette.Folder.Dark.f0,
        f1 = PlaceholderPalette.Folder.Dark.f1,
        f2 = PlaceholderPalette.Folder.Dark.f2,
        f3 = PlaceholderPalette.Folder.Dark.f3,
    )
)
val highContrastRedPalette = highContrastPalette.copy(
    primary = Palette.Red.coralRed,
    tertiaryContainer = Palette.Red.coralRed,
    onTertiaryContainer = Palette.white,
    onPrimary = Palette.Red.coralRed,
    onSnackContainer = Palette.Red.coralRed,
    placeholderColors = PlaceholderColors(
        c0 = PlaceholderPalette.Red.Dark.c0,
        c1 = PlaceholderPalette.Red.Dark.c1,
        c2 = PlaceholderPalette.Red.Dark.c2,
        c3 = PlaceholderPalette.Red.Dark.c3,
        c4 = PlaceholderPalette.Red.Dark.c4,
        f0 = PlaceholderPalette.Folder.Dark.f0,
        f1 = PlaceholderPalette.Folder.Dark.f1,
        f2 = PlaceholderPalette.Folder.Dark.f2,
        f3 = PlaceholderPalette.Folder.Dark.f3,
    )
)