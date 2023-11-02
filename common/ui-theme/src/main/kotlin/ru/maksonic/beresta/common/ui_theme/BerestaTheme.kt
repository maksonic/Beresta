package ru.maksonic.beresta.common.ui_theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import kotlinx.coroutines.delay
import ru.maksonic.beresta.common.ui_theme.colors.AppColor
import ru.maksonic.beresta.common.ui_theme.colors.animated
import ru.maksonic.beresta.common.ui_theme.palette.baseLightPalette
import ru.maksonic.beresta.common.ui_theme.palette.baseNightPalette
import ru.maksonic.beresta.common.ui_theme.palette.filledDarkBluePalette
import ru.maksonic.beresta.common.ui_theme.palette.filledDarkGreenPalette
import ru.maksonic.beresta.common.ui_theme.palette.filledDarkOrangePalette
import ru.maksonic.beresta.common.ui_theme.palette.filledDarkPurplePalette
import ru.maksonic.beresta.common.ui_theme.palette.filledDarkRedPalette
import ru.maksonic.beresta.common.ui_theme.palette.filledDarkYellowPalette
import ru.maksonic.beresta.common.ui_theme.palette.filledLightBluePalette
import ru.maksonic.beresta.common.ui_theme.palette.filledLightGreenPalette
import ru.maksonic.beresta.common.ui_theme.palette.filledLightOrangePalette
import ru.maksonic.beresta.common.ui_theme.palette.filledLightPurplePalette
import ru.maksonic.beresta.common.ui_theme.palette.filledLightRedPalette
import ru.maksonic.beresta.common.ui_theme.palette.filledLightYellowPalette
import ru.maksonic.beresta.common.ui_theme.palette.highContrastBluePalette
import ru.maksonic.beresta.common.ui_theme.palette.highContrastGreenPalette
import ru.maksonic.beresta.common.ui_theme.palette.highContrastOrangePalette
import ru.maksonic.beresta.common.ui_theme.palette.highContrastPurplePalette
import ru.maksonic.beresta.common.ui_theme.palette.highContrastRedPalette
import ru.maksonic.beresta.common.ui_theme.palette.highContrastYellowPalette
import ru.maksonic.beresta.common.ui_theme.palette.outlinedDarkBlackPalette
import ru.maksonic.beresta.common.ui_theme.palette.outlinedDarkBluePalette
import ru.maksonic.beresta.common.ui_theme.palette.outlinedDarkGreenPalette
import ru.maksonic.beresta.common.ui_theme.palette.outlinedDarkOrangePalette
import ru.maksonic.beresta.common.ui_theme.palette.outlinedDarkPurplePalette
import ru.maksonic.beresta.common.ui_theme.palette.outlinedDarkRedPalette
import ru.maksonic.beresta.common.ui_theme.palette.outlinedDarkYellowPalette
import ru.maksonic.beresta.common.ui_theme.palette.outlinedLightBlackPalette
import ru.maksonic.beresta.common.ui_theme.palette.outlinedLightGreenPalette
import ru.maksonic.beresta.common.ui_theme.palette.outlinedLightOrangePalette
import ru.maksonic.beresta.common.ui_theme.palette.outlinedLightPurplePalette
import ru.maksonic.beresta.common.ui_theme.palette.outlinedLightRedPalette
import ru.maksonic.beresta.common.ui_theme.palette.outlinedLightYellowPalette
import ru.maksonic.beresta.common.ui_theme.provide.provideImages
import ru.maksonic.beresta.language_engine.shell.provider.LanguageModel

/**
 * @Author maksonic on 08.11.2022
 */
private const val DELAY_FOR_APPLY_ANIMATED_COLORS = 1000L

@Composable
fun BerestaTheme(
    theme: AppThemeUi = AppThemeUi.SYSTEM,
    darkMode: AppDarkMode = AppDarkMode(isSystemInDarkTheme()),
    lightPalette: AppColor = baseLightPalette,
    darkPalette: AppColor = baseNightPalette,
    contrastPalette: AppColor = darkPalette,
    languages: LanguageModel = LanguageModel.Default,
    animationVelocity: AppAnimationVelocity.Key = AppAnimationVelocity.Key.NORMAL,
    content: @Composable () -> Unit
) {
    val isFirstLaunch = rememberSaveable { mutableStateOf(true) }
    val colors = when (theme) {
        AppThemeUi.SYSTEM -> if (darkMode.value) darkPalette else lightPalette
        AppThemeUi.DAY -> lightPalette
        AppThemeUi.NIGHT -> darkPalette
        AppThemeUi.DARK -> contrastPalette
    }
    val themeSwitchVelocity = when (animationVelocity) {
        AppAnimationVelocity.Key.DISABLE -> 0
        AppAnimationVelocity.Key.SLOW -> 900
        AppAnimationVelocity.Key.NORMAL -> 400
        AppAnimationVelocity.Key.FAST -> 250
        AppAnimationVelocity.Key.VERY_FAST -> 200
    }
    val colorsProvider = if (isFirstLaunch.value) colors else colors.animated(themeSwitchVelocity)

    val animations = when (animationVelocity) {
        AppAnimationVelocity.Key.DISABLE -> AppAnimationVelocity.Disabled
        AppAnimationVelocity.Key.SLOW -> AppAnimationVelocity.Slow
        AppAnimationVelocity.Key.NORMAL -> AppAnimationVelocity.Normal
        AppAnimationVelocity.Key.FAST -> AppAnimationVelocity.Fast
        AppAnimationVelocity.Key.VERY_FAST -> AppAnimationVelocity.VeryFast
    }

    LaunchedEffect(isFirstLaunch.value) {
        if (isFirstLaunch.value) {
            delay(DELAY_FOR_APPLY_ANIMATED_COLORS)
            isFirstLaunch.value = false
        }
    }

    LocalAppThemeProvider(
        darkMode = darkMode,
        colors = colorsProvider,
        languages = languages,
        images = provideImages(darkMode.value),
        animations = animations,
        content = content
    )
}

@Composable
fun BaseTheme(
    theme: AppThemeUi,
    darkMode: AppDarkMode,
    palette: ThemePalettesUiContainer,
    languages: LanguageModel,
    animationVelocity: AppAnimationVelocity.Key,
    content: @Composable () -> Unit
) {
    val lightPalette = when (palette.light) {
        AppThemePaletteUi.BLUE -> filledLightBluePalette
        AppThemePaletteUi.GREEN -> filledLightGreenPalette
        AppThemePaletteUi.PURPLE -> filledLightPurplePalette
        AppThemePaletteUi.RED -> filledLightRedPalette
        AppThemePaletteUi.ORANGE -> filledLightOrangePalette
        AppThemePaletteUi.YELLOW -> filledLightYellowPalette
        AppThemePaletteUi.BLACK_OUT -> outlinedLightBlackPalette
        AppThemePaletteUi.BLUE_OUT -> baseLightPalette
        AppThemePaletteUi.GREEN_OUT -> outlinedLightGreenPalette
        AppThemePaletteUi.PURPLE_OUT -> outlinedLightPurplePalette
        AppThemePaletteUi.RED_OUT -> outlinedLightRedPalette
        AppThemePaletteUi.ORANGE_OUT -> outlinedLightOrangePalette
        AppThemePaletteUi.YELLOW_OUT -> outlinedLightYellowPalette
    }

    val darkPalette = when (palette.night) {
        AppThemePaletteUi.BLUE -> filledDarkBluePalette
        AppThemePaletteUi.GREEN -> filledDarkGreenPalette
        AppThemePaletteUi.PURPLE -> filledDarkPurplePalette
        AppThemePaletteUi.RED -> filledDarkRedPalette
        AppThemePaletteUi.ORANGE -> filledDarkOrangePalette
        AppThemePaletteUi.YELLOW -> filledDarkYellowPalette
        AppThemePaletteUi.BLACK_OUT -> outlinedDarkBlackPalette
        AppThemePaletteUi.BLUE_OUT -> outlinedDarkBluePalette
        AppThemePaletteUi.GREEN_OUT -> outlinedDarkGreenPalette
        AppThemePaletteUi.PURPLE_OUT -> outlinedDarkPurplePalette
        AppThemePaletteUi.RED_OUT -> outlinedDarkRedPalette
        AppThemePaletteUi.ORANGE_OUT -> outlinedDarkOrangePalette
        AppThemePaletteUi.YELLOW_OUT -> outlinedDarkYellowPalette
    }

    val contrastPalette = when (palette.dark) {
        AppThemePaletteUi.BLUE -> highContrastBluePalette
        AppThemePaletteUi.GREEN -> highContrastGreenPalette
        AppThemePaletteUi.PURPLE -> highContrastPurplePalette
        AppThemePaletteUi.RED -> highContrastRedPalette
        AppThemePaletteUi.ORANGE -> highContrastOrangePalette
        AppThemePaletteUi.YELLOW -> highContrastYellowPalette
        else -> darkPalette
    }

    BerestaTheme(
        theme = theme,
        darkMode = darkMode,
        lightPalette = lightPalette,
        darkPalette = darkPalette,
        contrastPalette = contrastPalette,
        languages = languages,
        animationVelocity = animationVelocity,
        content = content
    )
}