package ru.maksonic.beresta.ui.theme.color.app_palette

import ru.maksonic.beresta.ui.theme.color.color_palette.Palette
import ru.maksonic.beresta.ui.theme.color.PlaceholderColors
import ru.maksonic.beresta.ui.theme.color.color_palette.PaletteImageComponent
import ru.maksonic.beresta.ui.theme.color.color_palette.ImagePalette

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
        c0 = ImagePalette.Red.Light.c0,
        c1 = ImagePalette.Red.Light.c1,
        c2 = ImagePalette.Red.Light.c2,
        c3 = ImagePalette.Red.Light.c3,
        c4 = ImagePalette.Red.Light.c4,
        f0 = PaletteImageComponent.Folder.Light.f0,
        f1 = PaletteImageComponent.Folder.Light.f1,
        f2 = PaletteImageComponent.Folder.Light.f2,
        f3 = PaletteImageComponent.Folder.Light.f3,
        t0 = PaletteImageComponent.Trash.Light.t0,
        t1 = PaletteImageComponent.Trash.Light.t1,
        t2 = PaletteImageComponent.Trash.Light.t2,
        t3 = PaletteImageComponent.Trash.Light.t3,
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
        c0 = ImagePalette.Red.Dark.Fill.c0,
        c1 = ImagePalette.Red.Dark.Fill.c1,
        c2 = ImagePalette.Red.Dark.Fill.c2,
        c3 = ImagePalette.Red.Dark.Fill.c3,
        c4 = ImagePalette.Red.Dark.Fill.c4,
        f0 = PaletteImageComponent.Folder.Dark.f0,
        f1 = PaletteImageComponent.Folder.Dark.f1,
        f2 = PaletteImageComponent.Folder.Dark.f2,
        f3 = PaletteImageComponent.Folder.Dark.f3,
        t0 = PaletteImageComponent.Trash.Dark.t0,
        t1 = PaletteImageComponent.Trash.Dark.t1,
        t2 = PaletteImageComponent.Trash.Dark.t2,
        t3 = PaletteImageComponent.Trash.Dark.t3,
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
        c0 = ImagePalette.Red.Light.c0,
        c1 = ImagePalette.Red.Light.c1,
        c2 = ImagePalette.Red.Light.c2,
        c3 = ImagePalette.Red.Light.c3,
        c4 = ImagePalette.Red.Light.c4,
        f0 = PaletteImageComponent.Folder.Light.f0,
        f1 = PaletteImageComponent.Folder.Light.f1,
        f2 = PaletteImageComponent.Folder.Light.f2,
        f3 = PaletteImageComponent.Folder.Light.f3,
        t0 = PaletteImageComponent.Trash.Light.t0,
        t1 = PaletteImageComponent.Trash.Light.t1,
        t2 = PaletteImageComponent.Trash.Light.t2,
        t3 = PaletteImageComponent.Trash.Light.t3,
    )
)
val outlinedDarkRedPalette = baseDarkPalette.copy(
    primary = Palette.Red.roseBud,
    tertiaryContainer = Palette.Red.roseBud,
    onPrimary = Palette.Red.roseBud,
    onSnackContainer = Palette.Red.melon,
    placeholderColors = PlaceholderColors(
        c0 = ImagePalette.Red.Dark.c0,
        c1 = ImagePalette.Red.Dark.c1,
        c2 = ImagePalette.Red.Dark.c2,
        c3 = ImagePalette.Red.Dark.c3,
        c4 = ImagePalette.Red.Dark.c4,
        f0 = PaletteImageComponent.Folder.Dark.f0,
        f1 = PaletteImageComponent.Folder.Dark.f1,
        f2 = PaletteImageComponent.Folder.Dark.f2,
        f3 = PaletteImageComponent.Folder.Dark.f3,
        t0 = PaletteImageComponent.Trash.Dark.t0,
        t1 = PaletteImageComponent.Trash.Dark.t1,
        t2 = PaletteImageComponent.Trash.Dark.t2,
        t3 = PaletteImageComponent.Trash.Dark.t3,
    )
)
val highContrastRedPalette = highContrastPalette.copy(
    primary = Palette.Red.coralRed,
    tertiaryContainer = Palette.Red.coralRed,
    onTertiaryContainer = Palette.white,
    onPrimary = Palette.Red.coralRed,
    onSnackContainer = Palette.Red.coralRed,
    placeholderColors = PlaceholderColors(
        c0 = ImagePalette.Red.Dark.c0,
        c1 = ImagePalette.Red.Dark.c1,
        c2 = ImagePalette.Red.Dark.c2,
        c3 = ImagePalette.Red.Dark.c3,
        c4 = ImagePalette.Red.Dark.c4,
        f0 = PaletteImageComponent.Folder.Dark.f0,
        f1 = PaletteImageComponent.Folder.Dark.f1,
        f2 = PaletteImageComponent.Folder.Dark.f2,
        f3 = PaletteImageComponent.Folder.Dark.f3,
        t0 = PaletteImageComponent.Trash.Dark.t0,
        t1 = PaletteImageComponent.Trash.Dark.t1,
        t2 = PaletteImageComponent.Trash.Dark.t2,
        t3 = PaletteImageComponent.Trash.Dark.t3,
    )
)