package ru.maksonic.beresta.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import ru.maksonic.beresta.feature.language_selector.api.provider.BerestaLanguage
import ru.maksonic.beresta.ui.theme.color.AppColor
import ru.maksonic.beresta.ui.theme.color.ThemeColorPalette
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
    palette: ThemeColorPalette = ThemeColorPalette.RED,
    content: @Composable () -> Unit
) {
    val lightPalette = when (palette) {
        ThemeColorPalette.BLUE -> lightBluePalette
        ThemeColorPalette.GREEN -> lightGreenPalette
        ThemeColorPalette.PURPLE -> lightPurplePalette
        ThemeColorPalette.RED -> lightRedPalette
        ThemeColorPalette.ORANGE -> lightOrangePalette
        ThemeColorPalette.YELLOW -> lightYellowPalette
        ThemeColorPalette.BLACK_OUT -> outlinedBlackPalette
        ThemeColorPalette.BLUE_OUT -> baseLightPalette
        ThemeColorPalette.GREEN_OUT -> outlinedGreenPalette
        ThemeColorPalette.PURPLE_OUT -> outlinedPurplePalette
        ThemeColorPalette.RED_OUT -> outlinedRedPalette
        ThemeColorPalette.ORANGE_OUT -> outlinedOrangePalette
        ThemeColorPalette.YELLOW_OUT -> outlinedYellowPalette
    }

    val darkPalette = when (palette) {
        ThemeColorPalette.BLUE -> darkBluePalette
        ThemeColorPalette.GREEN -> darkGreenPalette
        ThemeColorPalette.PURPLE -> darkPurplePalette
        ThemeColorPalette.RED -> darkRedPalette
        ThemeColorPalette.ORANGE -> darkOrangePalette
        ThemeColorPalette.YELLOW -> darkYellowPalette
        ThemeColorPalette.BLACK_OUT -> darkBlackPalette
        ThemeColorPalette.BLUE_OUT -> baseDarkPalette
        ThemeColorPalette.GREEN_OUT -> baseDarkPalette
        ThemeColorPalette.PURPLE_OUT -> baseDarkPalette
        ThemeColorPalette.RED_OUT -> baseDarkPalette
        ThemeColorPalette.ORANGE_OUT -> baseDarkPalette
        ThemeColorPalette.YELLOW_OUT -> baseDarkPalette
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
    palette: ThemeColorPalette = ThemeColorPalette.BLUE,
    content: @Composable () -> Unit
) {
    val contrastPalette = when (palette) {
        ThemeColorPalette.BLUE -> highContrastBluePalette
        ThemeColorPalette.GREEN -> highContrastGreenPalette
        ThemeColorPalette.PURPLE -> highContrastPurplePalette
        ThemeColorPalette.RED -> highContrastRedPalette
        ThemeColorPalette.ORANGE -> highContrastOrangePalette
        ThemeColorPalette.YELLOW -> highContrastYellowPalette
        ThemeColorPalette.BLACK_OUT -> highContrastBlackPalette
        ThemeColorPalette.BLUE_OUT -> highContrastPalette
        ThemeColorPalette.GREEN_OUT -> highContrastPalette
        ThemeColorPalette.PURPLE_OUT -> highContrastPalette
        ThemeColorPalette.RED_OUT -> highContrastPalette
        ThemeColorPalette.ORANGE_OUT -> highContrastPalette
        ThemeColorPalette.YELLOW_OUT -> highContrastPalette
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