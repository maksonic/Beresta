package ru.maksonic.beresta.common.ui_theme

import androidx.compose.runtime.Composable
import ru.maksonic.beresta.common.ui_theme.colors.AppColor
import ru.maksonic.beresta.common.ui_theme.colors.LocalAppColors
import ru.maksonic.beresta.common.ui_theme.provide.AppDimen
import ru.maksonic.beresta.common.ui_theme.provide.AppElevation
import ru.maksonic.beresta.common.ui_theme.provide.AppImage
import ru.maksonic.beresta.common.ui_theme.provide.AppPadding
import ru.maksonic.beresta.common.ui_theme.provide.AppShape
import ru.maksonic.beresta.common.ui_theme.provide.AppSize
import ru.maksonic.beresta.common.ui_theme.provide.AppTonal
import ru.maksonic.beresta.common.ui_theme.provide.LocalAppDimen
import ru.maksonic.beresta.common.ui_theme.provide.LocalAppElevation
import ru.maksonic.beresta.common.ui_theme.provide.LocalAppImage
import ru.maksonic.beresta.common.ui_theme.provide.LocalAppPadding
import ru.maksonic.beresta.common.ui_theme.provide.LocalAppShape
import ru.maksonic.beresta.common.ui_theme.provide.LocalAppSize
import ru.maksonic.beresta.common.ui_theme.provide.LocalAppTonal

/**
 * @Author maksonic on 08.11.2022
 */
object  Theme {
    val darkMode: AppDarkMode @Composable get() = LocalAppDarkMode.current
    val color: AppColor @Composable get() = LocalAppColors.current
    val size: AppSize @Composable get() = LocalAppSize.current
    val elevation: AppElevation @Composable get() = LocalAppElevation.current
    val image: AppImage @Composable get() = LocalAppImage.current
    val padding: AppPadding @Composable get() = LocalAppPadding.current
    val dimen: AppDimen @Composable get() = LocalAppDimen.current
    val shape: AppShape @Composable get() = LocalAppShape.current
    val tonal: AppTonal @Composable get() = LocalAppTonal.current
    val animVelocity: AppAnimationVelocity @Composable get() = LocalAppAnimationVelocity.current
}