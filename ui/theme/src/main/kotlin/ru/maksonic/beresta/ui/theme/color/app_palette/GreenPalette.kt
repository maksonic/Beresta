package ru.maksonic.beresta.ui.theme.color.app_palette

import ru.maksonic.beresta.ui.theme.color.color_palette.Palette
import ru.maksonic.beresta.ui.theme.color.PlaceholderColors
import ru.maksonic.beresta.ui.theme.color.color_palette.PaletteImageComponent
import ru.maksonic.beresta.ui.theme.color.color_palette.ImagePalette

/**
 * @Author maksonic on 25.02.2023
 */
val filledLightGreenPalette = baseLightPalette.copy(
    primary = Palette.Green.electricGreen,
    onPrimary = Palette.Green.vividMalachite,
    onPrimaryContainer = Palette.Green.seaweed,
    secondary = Palette.Orange.blanchedAlmond,
    onSecondary = Palette.Green.seaweed,
    secondaryContainer = Palette.Green.nyanza,
    onSecondaryContainer = Palette.Green.menthol,
    tertiary = Palette.Orange.frangipane,
    onTertiary = Palette.Green.vividMalachite,
    tertiaryContainer = Palette.Green.vividMalachite,
    onTertiaryContainer = Palette.white,
    background = Palette.Green.mintCream,
    surface = Palette.Green.mintCream,
    onSurface = Palette.Green.vividMalachite,
    surfaceVariant = Palette.Green.snowyMint,
    onSurfaceVariant = Palette.Green.menthol,
    inverseSurface = Palette.Green.vividMalachite,
    inversePrimary = Palette.Orange.peachOrange,
    surfaceTint = Palette.Green.electricGreen,
    outlineVariant = Palette.Green.nyanza,
    onSnackContainer = Palette.Green.menthol,
    placeholderColors = PlaceholderColors(
        c0 = ImagePalette.Green.Light.c0,
        c1 = ImagePalette.Green.Light.c1,
        c2 = ImagePalette.Green.Light.c2,
        c3 = ImagePalette.Green.Light.c3,
        c4 = ImagePalette.Green.Light.c4,
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
val filledDarkGreenPalette = baseDarkPalette.copy(
    primary = Palette.Green.x0,
    onPrimary = Palette.Green.x0,
    primaryContainer = Palette.Green.x2,
    onPrimaryContainer = Palette.white,
    secondary = Palette.Green.x4,
    onSecondary = Palette.white,
    secondaryContainer = Palette.Green.x2,
    onSecondaryContainer = Palette.Green.x5,
    tertiary = Palette.Green.x5,
    onTertiary = Palette.white,
    tertiaryContainer = Palette.Green.x0,
    onTertiaryContainer = Palette.Green.x1,
    background = Palette.Green.x1,
    surface = Palette.Green.x1,
    surfaceVariant = Palette.Green.x3,
    onSurfaceVariant = Palette.Green.x4,
    surfaceTint = Palette.black,
    inverseOnSurface = Palette.Green.x4,
    inversePrimary = Palette.Green.x5,
    outlineVariant = Palette.Green.x3,
    onSnackContainer = Palette.Green.menthol,
    placeholderColors = PlaceholderColors(
        c0 = ImagePalette.Green.Dark.Fill.c0,
        c1 = ImagePalette.Green.Dark.Fill.c1,
        c2 = ImagePalette.Green.Dark.Fill.c2,
        c3 = ImagePalette.Green.Dark.Fill.c3,
        c4 = ImagePalette.Green.Dark.Fill.c4,
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
val outlinedLightGreenPalette = baseLightPalette.copy(
    onPrimary = Palette.Green.vividMalachite,
    onTertiary = Palette.Green.vividMalachite,
    tertiaryContainer = Palette.Green.electricGreen,
    onTertiaryContainer = Palette.nero,
    inversePrimary = Palette.Orange.peachOrange,
    primary = Palette.Green.electricGreen,
    onSnackContainer = Palette.Green.menthol,
    placeholderColors = PlaceholderColors(
        c0 = ImagePalette.Green.Light.c0,
        c1 = ImagePalette.Green.Light.c1,
        c2 = ImagePalette.Green.Light.c2,
        c3 = ImagePalette.Green.Light.c3,
        c4 = ImagePalette.Green.Light.c4,
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
val outlinedDarkGreenPalette = baseDarkPalette.copy(
    primary = Palette.Green.mintGreen,
    tertiaryContainer = Palette.Green.mintGreen,
    onPrimary = Palette.Green.mintGreen,
    onSnackContainer = Palette.Green.menthol,
    placeholderColors = PlaceholderColors(
        c0 = ImagePalette.Green.Dark.c0,
        c1 = ImagePalette.Green.Dark.c1,
        c2 = ImagePalette.Green.Dark.c2,
        c3 = ImagePalette.Green.Dark.c3,
        c4 = ImagePalette.Green.Dark.c4,
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
val highContrastGreenPalette = highContrastPalette.copy(
    primary = Palette.Green.electricGreen,
    tertiaryContainer = Palette.Green.electricGreen,
    onPrimary = Palette.Green.electricGreen,
    onSnackContainer = Palette.Green.electricGreen,
    placeholderColors = PlaceholderColors(
        c0 = ImagePalette.Green.Dark.c0,
        c1 = ImagePalette.Green.Dark.c1,
        c2 = ImagePalette.Green.Dark.c2,
        c3 = ImagePalette.Green.Dark.c3,
        c4 = ImagePalette.Green.Dark.c4,
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