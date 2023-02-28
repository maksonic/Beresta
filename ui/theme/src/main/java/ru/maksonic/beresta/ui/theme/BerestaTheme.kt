package ru.maksonic.beresta.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import ru.maksonic.beresta.feature.language_selector.api.provider.BerestaLanguage
import ru.maksonic.beresta.ui.theme.color.AppColor
import ru.maksonic.beresta.ui.theme.color.AppThemePalette
import ru.maksonic.beresta.ui.theme.color.PaletteStore
import ru.maksonic.beresta.ui.theme.color.palette.*
import ru.maksonic.beresta.ui.theme.component.AppImage

/**
 * @Author maksonic on 08.11.2022
 */
@Composable
fun BerestaTheme(
    lightPalette: AppColor = baseLightPalette,
    darkPalette: AppColor = baseDarkPalette,
    darkTheme: Boolean = isSystemInDarkTheme(),
    provideLanguages: BerestaLanguage = BerestaLanguage(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) darkPalette else lightPalette

    AppLocalProvider(colors, provideImages(darkTheme), languages = provideLanguages, content)
}

@Composable
fun AppTheme(
    darkTheme: Boolean = false,
    provideLanguages: BerestaLanguage,
    palette: PaletteStore,
    content: @Composable () -> Unit
) {
    val lightPalette = when (palette.light) {
        AppThemePalette.BLUE -> filledLightBluePalette
        AppThemePalette.GREEN -> filledLightGreenPalette
        AppThemePalette.PURPLE -> filledLightPurplePalette
        AppThemePalette.RED -> filledLightRedPalette
        AppThemePalette.ORANGE -> filledLightOrangePalette
        AppThemePalette.YELLOW -> filledLightYellowPalette
        AppThemePalette.BLACK_OUT -> outlinedLightBlackPalette
        AppThemePalette.BLUE_OUT -> baseLightPalette
        AppThemePalette.GREEN_OUT -> outlinedLightGreenPalette
        AppThemePalette.PURPLE_OUT -> outlinedLightPurplePalette
        AppThemePalette.RED_OUT -> outlinedLightRedPalette
        AppThemePalette.ORANGE_OUT -> outlinedLightOrangePalette
        AppThemePalette.YELLOW_OUT -> outlinedLightYellowPalette
    }

    val darkPalette = when (palette.dark) {
        AppThemePalette.BLUE -> filledDarkBluePalette
        AppThemePalette.GREEN -> filledDarkGreenPalette
        AppThemePalette.PURPLE -> filledDarkPurplePalette
        AppThemePalette.RED -> filledDarkRedPalette
        AppThemePalette.ORANGE -> filledDarkOrangePalette
        AppThemePalette.YELLOW -> filledDarkYellowPalette
        AppThemePalette.BLACK_OUT -> outlinedDarkBlackPalette
        AppThemePalette.BLUE_OUT -> outlinedDarkBluePalette
        AppThemePalette.GREEN_OUT -> outlinedDarkGreenPalette
        AppThemePalette.PURPLE_OUT -> outlinedDarkPurplePalette
        AppThemePalette.RED_OUT -> outlinedDarkRedPalette
        AppThemePalette.ORANGE_OUT -> outlinedDarkOrangePalette
        AppThemePalette.YELLOW_OUT -> outlinedDarkYellowPalette
    }
    BerestaTheme(
        lightPalette = lightPalette,
        darkPalette = darkPalette,
        darkTheme = darkTheme,
        provideLanguages = provideLanguages,
        content = content
    )
}

@Composable
fun HighContrastTheme(
    darkTheme: Boolean = true,
    provideLanguages: BerestaLanguage,
    palette: AppThemePalette = AppThemePalette.BLUE,
    content: @Composable () -> Unit
) {
    val contrastPalette = when (palette) {
        AppThemePalette.BLUE -> highContrastBluePalette
        AppThemePalette.GREEN -> highContrastGreenPalette
        AppThemePalette.PURPLE -> highContrastPurplePalette
        AppThemePalette.RED -> highContrastRedPalette
        AppThemePalette.ORANGE -> highContrastOrangePalette
        AppThemePalette.YELLOW -> highContrastYellowPalette
        AppThemePalette.BLACK_OUT -> highContrastBlackPalette
        AppThemePalette.BLUE_OUT -> highContrastPalette
        AppThemePalette.GREEN_OUT -> highContrastPalette
        AppThemePalette.PURPLE_OUT -> highContrastPalette
        AppThemePalette.RED_OUT -> highContrastPalette
        AppThemePalette.ORANGE_OUT -> highContrastPalette
        AppThemePalette.YELLOW_OUT -> highContrastPalette
    }

    BerestaTheme(
        darkPalette = contrastPalette,
        darkTheme = darkTheme,
        provideLanguages = provideLanguages,
        content = content
    )
}

private fun provideImages(isDark: Boolean): AppImage {
    val splashBottomLogo =
        if (isDark) R.drawable.logo_bottom_maksonic_night else R.drawable.logo_bottom_maksonic_day
    return AppImage(splashBottomLogo = splashBottomLogo)
}