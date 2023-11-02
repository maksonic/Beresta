package ru.maksonic.beresta.common.ui_theme

import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import ru.maksonic.beresta.common.ui_theme.colors.AppColor
import ru.maksonic.beresta.common.ui_theme.colors.LocalAppColors
import ru.maksonic.beresta.common.ui_theme.provide.AppImage
import ru.maksonic.beresta.common.ui_theme.provide.AppRipple
import ru.maksonic.beresta.common.ui_theme.provide.LocalAppDimen
import ru.maksonic.beresta.common.ui_theme.provide.LocalAppElevation
import ru.maksonic.beresta.common.ui_theme.provide.LocalAppImage
import ru.maksonic.beresta.common.ui_theme.provide.LocalAppPadding
import ru.maksonic.beresta.common.ui_theme.provide.LocalAppShape
import ru.maksonic.beresta.common.ui_theme.provide.LocalAppSize
import ru.maksonic.beresta.common.ui_theme.provide.LocalAppTonal
import ru.maksonic.beresta.common.ui_theme.provide.dimens
import ru.maksonic.beresta.common.ui_theme.provide.elevations
import ru.maksonic.beresta.common.ui_theme.provide.paddings
import ru.maksonic.beresta.common.ui_theme.provide.shapes
import ru.maksonic.beresta.common.ui_theme.provide.sizes
import ru.maksonic.beresta.common.ui_theme.provide.tonalElevations
import ru.maksonic.beresta.language_engine.shell.provider.LanguageModel
import ru.maksonic.beresta.language_engine.shell.provider.LocalAppLanguage

/**
 * @Author maksonic on 14.10.2023
 */
@Composable
fun LocalAppThemeProvider(
    darkMode: AppDarkMode,
    colors: AppColor,
    languages: LanguageModel,
    images: AppImage,
    animations: AppAnimationVelocity,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalAppColors provides colors,
        LocalAppLanguage provides languages,
        LocalAppSize provides sizes,
        LocalAppElevation provides elevations,
        LocalAppImage provides images,
        LocalAppPadding provides paddings,
        LocalAppDimen provides dimens,
        LocalAppShape provides shapes,
        LocalRippleTheme provides AppRipple,
        LocalAppTonal provides tonalElevations,
        LocalAppAnimationVelocity provides animations,
        LocalAppDarkMode provides darkMode,
        content = content
    )
}