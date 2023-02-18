package ru.maksonic.beresta.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
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
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) darkPalette else lightPalette

    AppLocalProvider(colors, provideImages(darkTheme), content)
}


@Composable
fun AppTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    BerestaTheme(
        lightPalette = baseLightPalette,
        darkPalette = baseDarkPalette,
        darkTheme = darkTheme,
        content = content
    )
}

private fun provideImages(isDark: Boolean): AppImage {
    val splashBottomLogo =
        if (isDark) R.drawable.logo_bottom_maksonic_night else R.drawable.logo_bottom_maksonic_day
    return AppImage(splashBottomLogo = splashBottomLogo)
}