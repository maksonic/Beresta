package ru.maksonic.beresta.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import ru.maksonic.beresta.language_engine.shell.provider.BerestaLanguage
import ru.maksonic.beresta.ui.theme.color.AppColor
import ru.maksonic.beresta.ui.theme.color.AppThemePalette
import ru.maksonic.beresta.ui.theme.color.PaletteStore
import ru.maksonic.beresta.ui.theme.color.app_palette.baseDarkPalette
import ru.maksonic.beresta.ui.theme.color.app_palette.baseLightPalette
import ru.maksonic.beresta.ui.theme.color.app_palette.filledDarkBluePalette
import ru.maksonic.beresta.ui.theme.color.app_palette.filledDarkGreenPalette
import ru.maksonic.beresta.ui.theme.color.app_palette.filledDarkOrangePalette
import ru.maksonic.beresta.ui.theme.color.app_palette.filledDarkPurplePalette
import ru.maksonic.beresta.ui.theme.color.app_palette.filledDarkRedPalette
import ru.maksonic.beresta.ui.theme.color.app_palette.filledDarkYellowPalette
import ru.maksonic.beresta.ui.theme.color.app_palette.filledLightBluePalette
import ru.maksonic.beresta.ui.theme.color.app_palette.filledLightGreenPalette
import ru.maksonic.beresta.ui.theme.color.app_palette.filledLightOrangePalette
import ru.maksonic.beresta.ui.theme.color.app_palette.filledLightPurplePalette
import ru.maksonic.beresta.ui.theme.color.app_palette.filledLightRedPalette
import ru.maksonic.beresta.ui.theme.color.app_palette.filledLightYellowPalette
import ru.maksonic.beresta.ui.theme.color.app_palette.highContrastBluePalette
import ru.maksonic.beresta.ui.theme.color.app_palette.highContrastGreenPalette
import ru.maksonic.beresta.ui.theme.color.app_palette.highContrastOrangePalette
import ru.maksonic.beresta.ui.theme.color.app_palette.highContrastPalette
import ru.maksonic.beresta.ui.theme.color.app_palette.highContrastPurplePalette
import ru.maksonic.beresta.ui.theme.color.app_palette.highContrastRedPalette
import ru.maksonic.beresta.ui.theme.color.app_palette.highContrastYellowPalette
import ru.maksonic.beresta.ui.theme.color.app_palette.outlinedDarkBlackPalette
import ru.maksonic.beresta.ui.theme.color.app_palette.outlinedDarkBluePalette
import ru.maksonic.beresta.ui.theme.color.app_palette.outlinedDarkGreenPalette
import ru.maksonic.beresta.ui.theme.color.app_palette.outlinedDarkOrangePalette
import ru.maksonic.beresta.ui.theme.color.app_palette.outlinedDarkPurplePalette
import ru.maksonic.beresta.ui.theme.color.app_palette.outlinedDarkRedPalette
import ru.maksonic.beresta.ui.theme.color.app_palette.outlinedDarkYellowPalette
import ru.maksonic.beresta.ui.theme.color.app_palette.outlinedLightBlackPalette
import ru.maksonic.beresta.ui.theme.color.app_palette.outlinedLightGreenPalette
import ru.maksonic.beresta.ui.theme.color.app_palette.outlinedLightOrangePalette
import ru.maksonic.beresta.ui.theme.color.app_palette.outlinedLightPurplePalette
import ru.maksonic.beresta.ui.theme.color.app_palette.outlinedLightRedPalette
import ru.maksonic.beresta.ui.theme.color.app_palette.outlinedLightYellowPalette
import ru.maksonic.beresta.ui.theme.component.AppAnimationVelocity
import ru.maksonic.beresta.ui.theme.component.AppDarkMode
import ru.maksonic.beresta.ui.theme.component.AppImage

/**
 * @Author maksonic on 08.11.2022
 */
@Composable
fun BerestaTheme(
    darkMode: AppDarkMode = AppDarkMode(isSystemInDarkTheme()),
    lightPalette: AppColor = baseLightPalette,
    darkPalette: AppColor = baseDarkPalette,
    provideLanguages: BerestaLanguage = BerestaLanguage(),
    animationVelocity: AppAnimationVelocity.Value = AppAnimationVelocity.Value.NORMAL,
    content: @Composable () -> Unit
) {
    val colors = if (darkMode.value) darkPalette else lightPalette

    val animations = when (animationVelocity) {
        AppAnimationVelocity.Value.DISABLE -> AppAnimationVelocity.Disabled
        AppAnimationVelocity.Value.SLOW -> AppAnimationVelocity.Slow
        AppAnimationVelocity.Value.NORMAL -> AppAnimationVelocity.Normal
        AppAnimationVelocity.Value.FAST -> AppAnimationVelocity.Fast
    }

    AppLocalProvider(
        darkMode = darkMode,
        colors = colors,
        images = provideImages(darkMode.value),
        languages = provideLanguages,
        animations = animations,
        content = content
    )
}

@Composable
fun AppTheme(
    darkMode: AppDarkMode,
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
        darkMode = darkMode,
        lightPalette = lightPalette,
        darkPalette = darkPalette,
        provideLanguages = provideLanguages,
        content = content
    )
}

@Composable
fun HighContrastTheme(
    darkMode: AppDarkMode,
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
        else -> highContrastPalette
    }

    BerestaTheme(
        darkMode = darkMode,
        darkPalette = contrastPalette,
        provideLanguages = provideLanguages,
        content = content
    )
}

private fun provideImages(isDark: Boolean): AppImage {
    val splashCenterLogo =
        if (isDark) R.drawable.splash_logo_night else R.drawable.splash_logo

    val splashBottomLogo =
        if (isDark) R.drawable.bottom_brand_logo_night_raw else R.drawable.bottom_brand_logo_day_raw

    return AppImage(
        splashCenterLogo = splashCenterLogo,
        splashBottomLogo = splashBottomLogo
    )
}