package ru.maksonic.beresta.ui.theme

import androidx.compose.runtime.Composable
import ru.maksonic.beresta.ui.theme.color.AppColor
import ru.maksonic.beresta.ui.theme.color.LocalAppColors
import ru.maksonic.beresta.ui.theme.component.*

/**
 * @Author maksonic on 08.11.2022
 */
object Theme {
    val darkMode: AppDarkMode @Composable get() = LocalAppDarkMode.current
    val color: AppColor @Composable get() = LocalAppColors.current
    val widgetSize: AppWidgetSize @Composable get() = LocalAppWidgetSize.current
    val elevation: AppElevation @Composable get() = LocalAppElevation.current
    val image: AppImage @Composable get() = LocalAppImage.current
    val padding: AppPadding @Composable get() = LocalAppPadding.current
    val dimen: AppDimen @Composable get() = LocalAppDimen.current
    val shape: AppShape @Composable get() = LocalAppShape.current
    val tonal: AppTonal @Composable get() = LocalAppTonal.current
    val animVelocity: AppAnimationVelocity @Composable get() = LocalAppAnimationVelocity.current
}