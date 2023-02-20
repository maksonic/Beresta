package ru.maksonic.beresta.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import ru.maksonic.beresta.feature.language_selector.api.provider.BerestaLanguage
import ru.maksonic.beresta.ui.theme.color.AppColor
import ru.maksonic.beresta.ui.theme.color.baseDarkPalette
import ru.maksonic.beresta.ui.theme.color.baseLightPalette
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

    AppLocalProvider(colors, provideImages(darkTheme), languages = provideLanguages,content)
}


@Composable
fun AppTheme(
    darkTheme: Boolean = false,
    provideLanguages: BerestaLanguage,
    content: @Composable () -> Unit
) {
    BerestaTheme(
        lightPalette = baseLightPalette,
        darkPalette = baseDarkPalette,
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