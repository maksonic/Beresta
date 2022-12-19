package ru.maksonic.beresta.ui.theme.color

import androidx.compose.ui.graphics.Color

/**
 * @Author maksonic on 08.11.2022
 */
private object Palette {
    val black = Color(0xFF000000)
    val nero = Color(0xFF212121)
    val mineShaft = Color(0xFF313131)
    val tundora = Color(0xFF414141)
    val grayDark = Color(0xFF868686)
    val grayDusty = Color(0xFFDFDFDF)
    val celloBlue = Color(0xFF11426A)
    val azureRadiance = Color(0xFF0374F4)
    val cornflowerDarkBlue = Color(0xFF7BABD1)
    val cornflowerBlue = Color(0xFF90CAF9)
    val solitude = Color(0xFFE2F0FF)
    val snow = Color(0xFFF6F6F6)
    val white = Color(0xFFFFFFFF)
    val transparent = Color(0x00000000)
    val carmineRed = Color(0xFFB00020)
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
    tertiary = Palette.carmineRed,
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
    shadow = Palette.black
)
val baseDarkPalette = AppColor(
    primary = Palette.cornflowerBlue,
    onPrimary = Palette.black,
    primaryContainer = Palette.mineShaft,
    onPrimaryContainer = Palette.snow,
    secondary = Palette.grayDark,
    onSecondary = Palette.white,
    secondaryContainer = Palette.tundora,
    onSecondaryContainer = Palette.grayDusty,
    tertiary = Palette.carmineRed,
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
    shadow = Palette.black
)
