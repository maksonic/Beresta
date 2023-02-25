package ru.maksonic.beresta.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import ru.maksonic.beresta.feature.language_selector.api.provider.BerestaLanguage
import ru.maksonic.beresta.ui.theme.color.AppColor
import ru.maksonic.beresta.ui.theme.color.ThemeColorPalette
import ru.maksonic.beresta.ui.theme.color.palette.baseDarkPalette
import ru.maksonic.beresta.ui.theme.color.palette.baseLightPalette
import ru.maksonic.beresta.ui.theme.color.palette.darkGreenPalette
import ru.maksonic.beresta.ui.theme.color.palette.darkOrangePalette
import ru.maksonic.beresta.ui.theme.color.palette.darkPurplePalette
import ru.maksonic.beresta.ui.theme.color.palette.darkRedPalette
import ru.maksonic.beresta.ui.theme.color.palette.darkYellowPalette
import ru.maksonic.beresta.ui.theme.color.palette.highContrastGreenPalette
import ru.maksonic.beresta.ui.theme.color.palette.highContrastOrangePalette
import ru.maksonic.beresta.ui.theme.color.palette.highContrastPalette
import ru.maksonic.beresta.ui.theme.color.palette.highContrastPurplePalette
import ru.maksonic.beresta.ui.theme.color.palette.highContrastRedPalette
import ru.maksonic.beresta.ui.theme.color.palette.highContrastYellowPalette
import ru.maksonic.beresta.ui.theme.color.palette.lightGreenPalette
import ru.maksonic.beresta.ui.theme.color.palette.lightOrangePalette
import ru.maksonic.beresta.ui.theme.color.palette.lightPurplePalette
import ru.maksonic.beresta.ui.theme.color.palette.lightRedPalette
import ru.maksonic.beresta.ui.theme.color.palette.lightYellowPalette
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
        ThemeColorPalette.BLUE -> baseLightPalette
        ThemeColorPalette.GREEN -> lightGreenPalette
        ThemeColorPalette.PURPLE -> lightPurplePalette
        ThemeColorPalette.RED -> lightRedPalette
        ThemeColorPalette.ORANGE -> lightOrangePalette
        ThemeColorPalette.YELLOW -> lightYellowPalette
    }

    val darkPalette = when (palette) {
        ThemeColorPalette.BLUE -> baseDarkPalette
        ThemeColorPalette.GREEN -> darkGreenPalette
        ThemeColorPalette.PURPLE -> darkPurplePalette
        ThemeColorPalette.RED -> darkRedPalette
        ThemeColorPalette.ORANGE -> darkOrangePalette
        ThemeColorPalette.YELLOW -> darkYellowPalette
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
        ThemeColorPalette.BLUE -> highContrastPalette
        ThemeColorPalette.GREEN -> highContrastGreenPalette
        ThemeColorPalette.PURPLE -> highContrastPurplePalette
        ThemeColorPalette.RED -> highContrastRedPalette
        ThemeColorPalette.ORANGE -> highContrastOrangePalette
        ThemeColorPalette.YELLOW -> highContrastYellowPalette
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