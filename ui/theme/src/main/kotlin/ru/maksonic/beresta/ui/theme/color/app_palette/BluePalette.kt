package ru.maksonic.beresta.ui.theme.color.app_palette

import ru.maksonic.beresta.ui.theme.color.color_palette.Palette
import ru.maksonic.beresta.ui.theme.color.PlaceholderColors
import ru.maksonic.beresta.ui.theme.color.color_palette.PaletteImageComponent
import ru.maksonic.beresta.ui.theme.color.color_palette.ImagePalette

/**
 * @Author maksonic on 26.02.2023
 */
val filledLightBluePalette = baseLightPalette.copy(
    primary = Palette.Blue.azureRadiance,
    onPrimary = Palette.Blue.coolBlack,
    onPrimaryContainer = Palette.Blue.eerieBlue,
    secondary = Palette.Red.cinderella,
    onSecondary = Palette.Blue.eerieBlue,
    secondaryContainer = Palette.Blue.solitude,
    onSecondaryContainer = Palette.Blue.paleCornflowerBlue,
    tertiary = Palette.Red.melon,
    onTertiary = Palette.Blue.coolBlack,
    tertiaryContainer = Palette.Blue.coolBlack,
    onTertiaryContainer = Palette.white,
    background = Palette.Blue.zircon,
    surface = Palette.Blue.zircon,
    onSurface = Palette.Blue.coolBlack,
    surfaceVariant = Palette.Blue.frenchPassLight,
    onSurfaceVariant = Palette.Blue.paleCornflowerBlue,
    inverseSurface = Palette.Blue.coolBlack,
    inversePrimary = Palette.Red.roseBud,
    surfaceTint = Palette.Blue.azureRadiance,
    outlineVariant = Palette.Blue.solitude,
    onSnackContainer = Palette.Blue.anakiwa,
)
val filledDarkBluePalette = baseDarkPalette.copy(
    primary = Palette.Blue.x0,
    onPrimary = Palette.Blue.x0,
    primaryContainer = Palette.Blue.x2,
    onPrimaryContainer = Palette.white,
    secondary = Palette.Blue.x4,
    onSecondary = Palette.white,
    secondaryContainer = Palette.Blue.x2,
    onSecondaryContainer = Palette.Blue.x5,
    tertiary = Palette.Blue.x5,
    onTertiary = Palette.white,
    tertiaryContainer = Palette.Blue.x0,
    onTertiaryContainer = Palette.Blue.x1,
    background = Palette.Blue.x1,
    surface = Palette.Blue.x1,
    surfaceVariant = Palette.Blue.x3,
    onSurfaceVariant = Palette.Blue.x4,
    surfaceTint = Palette.black,
    inverseOnSurface = Palette.Blue.x4,
    inversePrimary = Palette.Blue.x5,
    outlineVariant = Palette.Blue.x3,
    onSnackContainer = Palette.Blue.anakiwa,
    placeholderColors = PlaceholderColors(
        c0 = ImagePalette.Blue.Dark.Fill.c0,
        c1 = ImagePalette.Blue.Dark.Fill.c1,
        c2 = ImagePalette.Blue.Dark.Fill.c2,
        c3 = ImagePalette.Blue.Dark.Fill.c3,
        c4 = ImagePalette.Blue.Dark.Fill.c4,
        f0 = PaletteImageComponent.Folder.Dark.f0,
        f1 = PaletteImageComponent.Folder.Dark.f1,
        f2 = PaletteImageComponent.Folder.Dark.f2,
        f3 = PaletteImageComponent.Folder.Dark.f3,
        t0 = PaletteImageComponent.Trash.Dark.t0,
        t1 = PaletteImageComponent.Trash.Dark.t1,
        t2 = PaletteImageComponent.Trash.Dark.t2,
        t3 = PaletteImageComponent.Trash.Dark.t3,
        s0 = PaletteImageComponent.Search.Dark.s0,
        s1 = PaletteImageComponent.Search.Dark.s1,
        s2 = PaletteImageComponent.Search.Dark.s2,
        s3 = PaletteImageComponent.Search.Dark.s3,
    )
)
val outlinedDarkBluePalette = baseDarkPalette.copy(
    primary = Palette.Blue.anakiwa,
    tertiaryContainer = Palette.Blue.anakiwa,
    onPrimary = Palette.Blue.anakiwa,
    onSnackContainer = Palette.Blue.anakiwa,
)
val highContrastBluePalette = highContrastPalette.copy(
    primary = Palette.Blue.azureRadiance,
    tertiaryContainer = Palette.Blue.azureRadiance,
    onTertiaryContainer = Palette.white,
    onPrimary = Palette.Blue.azureRadiance,
    onSnackContainer = Palette.Blue.azureRadiance,
)