package ru.maksonic.beresta.ui.theme.color.app_palette

import ru.maksonic.beresta.ui.theme.color.color_palette.Palette
import ru.maksonic.beresta.ui.theme.color.PlaceholderColors
import ru.maksonic.beresta.ui.theme.color.color_palette.PaletteImageComponent
import ru.maksonic.beresta.ui.theme.color.color_palette.ImagePalette

/**
 * @Author maksonic on 25.02.2023
 */
val filledLightPurplePalette = baseLightPalette.copy(
    primary = Palette.Purple.electricViolet,
    onPrimary = Palette.Purple.deepViolet,
    onPrimaryContainer = Palette.Purple.darkPurple,
    secondary = Palette.Green.snowyMint,
    onSecondary = Palette.Purple.darkPurple,
    secondaryContainer = Palette.Purple.magnolia,
    onSecondaryContainer = Palette.Purple.mauve,
    tertiary = Palette.Green.menthol,
    onTertiary = Palette.Purple.deepViolet,
    tertiaryContainer = Palette.Purple.deepViolet,
    background = Palette.Purple.whitePointer,
    surface = Palette.Purple.whitePointer,
    onSurface = Palette.Purple.deepViolet,
    surfaceVariant = Palette.Purple.brilliantLavender,
    onSurfaceVariant = Palette.Purple.mauve,
    inverseSurface = Palette.Purple.deepViolet,
    inversePrimary = Palette.Green.mintGreen,
    surfaceTint = Palette.Purple.electricViolet,
    outlineVariant = Palette.Purple.magnolia,
    onSnackContainer = Palette.Purple.mauve,
    placeholderColors = PlaceholderColors(
        c0 = ImagePalette.Purple.Light.c0,
        c1 = ImagePalette.Purple.Light.c1,
        c2 = ImagePalette.Purple.Light.c2,
        c3 = ImagePalette.Purple.Light.c3,
        c4 = ImagePalette.Purple.Light.c4,
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
val filledDarkPurplePalette = baseDarkPalette.copy(
    primary = Palette.Purple.x0,
    onPrimary = Palette.Purple.x0,
    primaryContainer = Palette.Purple.x2,
    onPrimaryContainer = Palette.white,
    secondary = Palette.Purple.x4,
    onSecondary = Palette.white,
    secondaryContainer = Palette.Purple.x2,
    onSecondaryContainer = Palette.Purple.x5,
    tertiary = Palette.Purple.x5,
    onTertiary = Palette.white,
    tertiaryContainer = Palette.Purple.x0,
    onTertiaryContainer = Palette.white,
    background = Palette.Purple.x1,
    surface = Palette.Purple.x1,
    surfaceVariant = Palette.Purple.x3,
    surfaceTint = Palette.black,
    onSurfaceVariant = Palette.Purple.x4,
    inverseOnSurface = Palette.Purple.x4,
    inversePrimary = Palette.Purple.x5,
    outlineVariant = Palette.Purple.x3,
    onSnackContainer = Palette.Purple.mauve,
    placeholderColors = PlaceholderColors(
        c0 = ImagePalette.Purple.Dark.Fill.c0,
        c1 = ImagePalette.Purple.Dark.Fill.c1,
        c2 = ImagePalette.Purple.Dark.Fill.c2,
        c3 = ImagePalette.Purple.Dark.Fill.c3,
        c4 = ImagePalette.Purple.Dark.Fill.c4,
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
val outlinedLightPurplePalette = baseLightPalette.copy(
    onPrimary = Palette.Purple.deepViolet,
    onTertiary = Palette.Purple.deepViolet,
    tertiaryContainer = Palette.Purple.electricViolet,
    onTertiaryContainer = Palette.white,
    inversePrimary = Palette.Green.mintGreen,
    primary = Palette.Purple.electricViolet,
    onSnackContainer = Palette.Purple.mauve,
    placeholderColors = PlaceholderColors(
        c0 = ImagePalette.Purple.Light.c0,
        c1 = ImagePalette.Purple.Light.c1,
        c2 = ImagePalette.Purple.Light.c2,
        c3 = ImagePalette.Purple.Light.c3,
        c4 = ImagePalette.Purple.Light.c4,
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
val outlinedDarkPurplePalette = baseDarkPalette.copy(
    primary = Palette.Purple.paleViolet,
    tertiaryContainer = Palette.Purple.paleViolet,
    onPrimary = Palette.Purple.paleViolet,
    onSnackContainer = Palette.Purple.mauve,
    placeholderColors = PlaceholderColors(
        c0 = ImagePalette.Purple.Dark.c0,
        c1 = ImagePalette.Purple.Dark.c1,
        c2 = ImagePalette.Purple.Dark.c2,
        c3 = ImagePalette.Purple.Dark.c3,
        c4 = ImagePalette.Purple.Dark.c4,
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
val highContrastPurplePalette = highContrastPalette.copy(
    primary = Palette.Purple.electricViolet,
    tertiaryContainer = Palette.Purple.electricViolet,
    onTertiaryContainer = Palette.white,
    onPrimary = Palette.Purple.electricViolet,
    onSnackContainer = Palette.Purple.electricViolet,
    placeholderColors = PlaceholderColors(
        c0 = ImagePalette.Purple.Dark.c0,
        c1 = ImagePalette.Purple.Dark.c1,
        c2 = ImagePalette.Purple.Dark.c2,
        c3 = ImagePalette.Purple.Dark.c3,
        c4 = ImagePalette.Purple.Dark.c4,
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