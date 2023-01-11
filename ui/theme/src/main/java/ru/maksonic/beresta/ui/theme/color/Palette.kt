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
    val tundora2 = Color(0xFF616161)
    val grayDark = Color(0xFF868686)
    val grayDusty = Color(0xFFDFDFDF)
    //Blue
    val azureRadiance = Color(0xFF0374F4)
    val cornflowerBlue = Color(0xFF90CAF9)
    val tropicalBlue = Color(0xFFCCE3FA)
    val solitude = Color(0xFFE2F0FF)
    //Other
    val carmineRed = Color(0xFFB00020)
    val white = Color(0xFFFFFFFF)
    val snow = Color(0xFFF6F6F6)
    val transparent = Color(0x00000000)
}

val baseLightPalette = AppColor(
    primary = Palette.azureRadiance,
    onPrimary = Palette.white,
    primaryContainer = Palette.white,
    onPrimaryContainer = Palette.black,
    secondary = Palette.grayDark,
    onSecondary = Palette.grayDusty,
    secondaryContainer = Palette.grayDusty,
    onSecondaryContainer = Palette.grayDark,
    tertiary = Palette.tropicalBlue,
    onTertiary = Palette.tundora,
    tertiaryContainer = Palette.solitude,
    onTertiaryContainer = Palette.grayDark,
    error = Palette.carmineRed,
    onError = Palette.carmineRed,
    errorContainer = Palette.carmineRed,
    onErrorContainer = Palette.carmineRed,
    background = Palette.snow,
    onBackground = Palette.black,
    surface = Palette.white,
    onSurface = Palette.black,
    surfaceVariant = Palette.mineShaft,
    onSurfaceVariant = Palette.black,
    outline = Palette.carmineRed,
    shadow = Palette.black,
    transparent = Palette.transparent
)
val baseDarkPalette = AppColor(
    primary = Palette.cornflowerBlue,
    onPrimary = Palette.black,
    primaryContainer = Palette.mineShaft,
    onPrimaryContainer = Palette.snow,
    secondary = Palette.grayDark,
    onSecondary = Palette.white,
    secondaryContainer = Palette.tundora2,
    onSecondaryContainer = Palette.grayDusty,
    tertiary = Palette.tundora,
    onTertiary = Palette.white,
    tertiaryContainer = Palette.tundora,
    onTertiaryContainer = Palette.white,
    error = Palette.carmineRed,
    onError = Palette.carmineRed,
    errorContainer = Palette.carmineRed,
    onErrorContainer = Palette.carmineRed,
    background = Palette.nero,
    onBackground = Palette.snow,
    surface = Palette.mineShaft,
    onSurface = Palette.white,
    surfaceVariant = Palette.grayDusty,
    onSurfaceVariant = Palette.grayDark,
    outline = Palette.carmineRed,
    shadow = Palette.black,
    transparent = Palette.transparent
)

