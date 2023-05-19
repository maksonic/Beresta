package ru.maksonic.beresta.ui.theme

import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import ru.maksonic.beresta.language_engine.shell.provider.BerestaLanguage
import ru.maksonic.beresta.language_engine.shell.provider.LocalBerestaLanguage
import ru.maksonic.beresta.ui.theme.color.AppColor
import ru.maksonic.beresta.ui.theme.color.LocalAppColors
import ru.maksonic.beresta.ui.theme.component.AppAnimationVelocity
import ru.maksonic.beresta.ui.theme.component.AppImage
import ru.maksonic.beresta.ui.theme.component.AppRipple
import ru.maksonic.beresta.ui.theme.component.LocalAppAnimationVelocity
import ru.maksonic.beresta.ui.theme.component.LocalAppDimen
import ru.maksonic.beresta.ui.theme.component.LocalAppElevation
import ru.maksonic.beresta.ui.theme.component.LocalAppImage
import ru.maksonic.beresta.ui.theme.component.LocalAppPadding
import ru.maksonic.beresta.ui.theme.component.LocalAppShape
import ru.maksonic.beresta.ui.theme.component.LocalAppTonal
import ru.maksonic.beresta.ui.theme.component.LocalAppWidgetSize
import ru.maksonic.beresta.ui.theme.component.dimens
import ru.maksonic.beresta.ui.theme.component.elevations
import ru.maksonic.beresta.ui.theme.component.paddings
import ru.maksonic.beresta.ui.theme.component.shapes
import ru.maksonic.beresta.ui.theme.component.tonalElevations
import ru.maksonic.beresta.ui.theme.component.widgetsSize

/**
 * @Author maksonic on 08.11.2022
 */
@Composable
fun AppLocalProvider(
    colors: AppColor,
    images: AppImage,
    languages: BerestaLanguage,
    animations: AppAnimationVelocity,
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
        LocalAppAnimationVelocity provides animations,
        content = content
    )
}