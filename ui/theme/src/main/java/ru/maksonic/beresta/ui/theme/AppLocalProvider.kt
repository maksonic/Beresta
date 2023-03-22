package ru.maksonic.beresta.ui.theme

import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import ru.maksonic.beresta.feature.language_selector.api.provider.BerestaLanguage
import ru.maksonic.beresta.feature.language_selector.api.provider.LocalBerestaLanguage
import ru.maksonic.beresta.ui.theme.color.AppColor
import ru.maksonic.beresta.ui.theme.color.LocalAppColors
import ru.maksonic.beresta.ui.theme.component.*

/**
 * @Author maksonic on 08.11.2022
 */
@Composable
fun AppLocalProvider(
    colors: AppColor,
    images: AppImage,
    languages: BerestaLanguage,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalAppColors provides colors,
        LocalAppWidgetSize provides widgetsSize,
        LocalAppElevation provides elevations,
        LocalAppImage provides images,
        LocalAppPadding provides paddings,
        LocalAppDimen provides dimens,
        LocalAppShape provides shapes,
        LocalRippleTheme provides AppRipple,
        LocalBerestaLanguage provides languages,
        LocalAppTonal provides tonalElevations,
        content = content
    )
}