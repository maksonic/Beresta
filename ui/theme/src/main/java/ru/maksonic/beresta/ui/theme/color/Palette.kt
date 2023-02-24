package ru.maksonic.beresta.ui.theme.color

import androidx.compose.ui.graphics.Color

/**
 * @Author maksonic on 08.11.2022
 */
private object Palette {
    //Black
    val black = Color(0xFF000000)
    val nero = Color(0xFF212121)
    val mineShaft = Color(0xFF313131)
    val tundora = Color(0xFF414141)
    val doveGray = Color(0xFF616161)
    val gray = Color(0xFF868686)
    val alto = Color(0xFFDFDFDF)
    //Blue
    val azureRadiance = Color(0xFF0374F4)
    val anakiwa = Color(0xFF93C4F5)
    val cornflowerBlue = Color(0xFFC0DCF9)
    val tropicalBlue = Color(0xFFCCE3FA)
    val solitude = Color(0xFFE2F0FF)
    //Other
    val carmineRed = Color(0xFFB00020)
    val white = Color(0xFFFFFFFF)
    val snow = Color(0xFFF6F6F6)
    val shadow = Color(0x2A000000)
    val transparent = Color(0x00000000)
}

val baseLightPalette = AppColor(
    primary = Palette.azureRadiance,
    onPrimary = Palette.white,
    primaryContainer = Palette.white,
    onPrimaryContainer = Palette.black,
    secondary = Palette.gray,
    onSecondary = Palette.alto,
    secondaryContainer = Palette.alto,
    onSecondaryContainer = Palette.alto,
    tertiary = Palette.tropicalBlue,
    onTertiary = Palette.tundora,
    tertiaryContainer = Palette.solitude,
    onTertiaryContainer = Palette.cornflowerBlue,
    error = Palette.carmineRed,
    onError = Palette.carmineRed,
    errorContainer = Palette.carmineRed,
    onErrorContainer = Palette.carmineRed,
    background = Palette.snow,
    onBackground = Palette.black,
    surface = Palette.white,
    onSurface = Palette.black,
    surfaceVariant = Palette.tropicalBlue,
    onSurfaceVariant = Palette.solitude,
    outline = Palette.carmineRed,
    shadow = Palette.black,
    scrim = Palette.black.copy(alpha = 0.24f),
    transparent = Palette.transparent
)
val baseDarkPalette = AppColor(
    primary = Palette.anakiwa,
    onPrimary = Palette.black,
    primaryContainer = Palette.mineShaft,
    onPrimaryContainer = Palette.snow,
    secondary = Palette.gray,
    onSecondary = Palette.white,
    secondaryContainer = Palette.doveGray,
    onSecondaryContainer = Palette.gray,
    tertiary = Palette.doveGray,
    onTertiary = Palette.white,
    tertiaryContainer = Palette.tundora,
    onTertiaryContainer = Palette.doveGray,
    error = Palette.carmineRed,
    onError = Palette.carmineRed,
    errorContainer = Palette.carmineRed,
    onErrorContainer = Palette.carmineRed,
    background = Palette.nero,
    onBackground = Palette.snow,
    surface = Palette.mineShaft,
    onSurface = Palette.white,
    surfaceVariant = Palette.doveGray,
    onSurfaceVariant = Palette.tundora,
    outline = Palette.carmineRed,
    shadow = Palette.black,
    scrim = Palette.black.copy(alpha = 0.32f),
    transparent = Palette.transparent
)


val highContrastPalette = AppColor(
    primary = Palette.azureRadiance,
    onPrimary = Palette.white,
    primaryContainer = Palette.nero,
    onPrimaryContainer = Palette.white,
    secondary = Palette.gray,
    onSecondary = Palette.white,
    secondaryContainer = Palette.doveGray,
    onSecondaryContainer = Palette.gray,
    tertiary = Palette.doveGray,
    onTertiary = Palette.white,
    tertiaryContainer = Palette.black,
    onTertiaryContainer = Palette.mineShaft,
    error = Palette.carmineRed,
    onError = Palette.carmineRed,
    errorContainer = Palette.carmineRed,
    onErrorContainer = Palette.carmineRed,
    background = Palette.black,
    onBackground = Palette.white,
    surface = Palette.black,
    onSurface = Palette.white,
    surfaceVariant = Palette.mineShaft,
    onSurfaceVariant = Palette.tundora,
    outline = Palette.carmineRed,
    shadow = Palette.black,
    scrim = Palette.black.copy(alpha = 0.8f),
    transparent = Palette.transparent
)

