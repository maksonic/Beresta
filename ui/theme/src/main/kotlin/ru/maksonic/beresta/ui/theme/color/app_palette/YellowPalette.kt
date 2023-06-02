package ru.maksonic.beresta.ui.theme.color.app_palette

import ru.maksonic.beresta.ui.theme.color.color_palette.Palette
import ru.maksonic.beresta.ui.theme.color.PlaceholderColors
import ru.maksonic.beresta.ui.theme.color.color_palette.PaletteImageComponent
import ru.maksonic.beresta.ui.theme.color.color_palette.ImagePalette

/**
 * @Author maksonic on 25.02.2023
 */
val filledLightYellowPalette = baseLightPalette.copy(
    primary = Palette.Yellow.bananaYellow,
    onPrimary = Palette.Yellow.buddhaGold,
    onPrimaryContainer = Palette.Yellow.americanBronze,
    secondary = Palette.Purple.brilliantLavender,
    onSecondary = Palette.Yellow.americanBronze,
    secondaryContainer = Palette.Yellow.cosmicLatte,
    onSecondaryContainer = Palette.Yellow.blond,
    tertiary = Palette.Purple.mauve,
    onTertiary = Palette.Yellow.bananaYellow,
    tertiaryContainer = Palette.Yellow.buddhaGold,
    background = Palette.Yellow.milk,
    surface = Palette.Yellow.milk,
    onSurface = Palette.Yellow.buddhaGold,
    surfaceVariant = Palette.Yellow.lemonChiffon,
    onSurfaceVariant = Palette.Yellow.blond,
    inverseSurface = Palette.Yellow.buddhaGold,
    inversePrimary = Palette.Purple.paleViolet,
    surfaceTint = Palette.Yellow.bananaYellow,
    outlineVariant = Palette.Yellow.cosmicLatte,
    onSnackContainer = Palette.Yellow.blond,
    placeholderColors = PlaceholderColors(
        c0 = ImagePalette.Yellow.Light.c0,
        c1 = ImagePalette.Yellow.Light.c1,
        c2 = ImagePalette.Yellow.Light.c2,
        c3 = ImagePalette.Yellow.Light.c3,
        c4 = ImagePalette.Yellow.Light.c4,
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
val filledDarkYellowPalette = baseDarkPalette.copy(
    primary = Palette.Yellow.x0,
    onPrimary = Palette.Yellow.x0,
    primaryContainer = Palette.Yellow.x2,
    onPrimaryContainer = Palette.white,
    secondary = Palette.Yellow.x4,
    onSecondary = Palette.white,
    secondaryContainer = Palette.Yellow.x2,
    onSecondaryContainer = Palette.Yellow.x5,
    tertiary = Palette.Yellow.x5,
    onTertiary = Palette.white,
    tertiaryContainer = Palette.Yellow.x0,
    onTertiaryContainer = Palette.white,
    background = Palette.Yellow.x1,
    surface = Palette.Yellow.x1,
    surfaceVariant = Palette.Yellow.x3,
    onSurfaceVariant = Palette.Yellow.x4,
    surfaceTint = Palette.black,
    inverseOnSurface = Palette.Yellow.x5,
    inversePrimary = Palette.Yellow.x5,
    outlineVariant = Palette.Yellow.x3,
    onSnackContainer = Palette.Yellow.blond,
    placeholderColors = PlaceholderColors(
        c0 = ImagePalette.Yellow.Dark.Fill.c0,
        c1 = ImagePalette.Yellow.Dark.Fill.c1,
        c2 = ImagePalette.Yellow.Dark.Fill.c2,
        c3 = ImagePalette.Yellow.Dark.Fill.c3,
        c4 = ImagePalette.Yellow.Dark.Fill.c4,
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
val outlinedLightYellowPalette = baseLightPalette.copy(
    onPrimary = Palette.Yellow.buddhaGold,
    onTertiary = Palette.Yellow.bananaYellow,
    tertiaryContainer = Palette.Yellow.bananaYellow,
    onTertiaryContainer = Palette.nero,
    inversePrimary = Palette.Purple.paleViolet,
    primary = Palette.Yellow.bananaYellow,
    onSnackContainer = Palette.Yellow.blond,
    placeholderColors = PlaceholderColors(
        c0 = ImagePalette.Yellow.Light.c0,
        c1 = ImagePalette.Yellow.Light.c1,
        c2 = ImagePalette.Yellow.Light.c2,
        c3 = ImagePalette.Yellow.Light.c3,
        c4 = ImagePalette.Yellow.Light.c4,
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
val outlinedDarkYellowPalette = baseDarkPalette.copy(
    primary = Palette.Yellow.pastelYellow,
    tertiaryContainer = Palette.Yellow.pastelYellow,
    onPrimary = Palette.Yellow.pastelYellow,
    onSnackContainer = Palette.Yellow.blond,
    placeholderColors = PlaceholderColors(
        c0 = ImagePalette.Yellow.Dark.c0,
        c1 = ImagePalette.Yellow.Dark.c1,
        c2 = ImagePalette.Yellow.Dark.c2,
        c3 = ImagePalette.Yellow.Dark.c3,
        c4 = ImagePalette.Yellow.Dark.c4,
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
val highContrastYellowPalette = highContrastPalette.copy(
    primary = Palette.Yellow.bananaYellow,
    tertiaryContainer = Palette.Yellow.bananaYellow,
    onPrimary = Palette.Yellow.bananaYellow,
    onSnackContainer = Palette.Yellow.bananaYellow,
    placeholderColors = PlaceholderColors(
        c0 = ImagePalette.Yellow.Dark.c0,
        c1 = ImagePalette.Yellow.Dark.c1,
        c2 = ImagePalette.Yellow.Dark.c2,
        c3 = ImagePalette.Yellow.Dark.c3,
        c4 = ImagePalette.Yellow.Dark.c4,
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