package ru.maksonic.beresta.ui.theme.color

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.ui.theme.Theme

/**
 * @Author maksonic on 08.11.2022
 */
val LocalAppColors = staticCompositionLocalOf<AppColor> {
    error("No colors provided")
}

data class AppColor(
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,
    val secondary: Color,
    val onSecondary: Color,
    val secondaryContainer: Color,
    val onSecondaryContainer: Color,
    val tertiary: Color,
    val onTertiary: Color,
    val tertiaryContainer: Color,
    val onTertiaryContainer: Color,
    val error: Color,
    val onError: Color,
    val errorContainer: Color,
    val onErrorContainer: Color,
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val onSurface: Color,
    val surfaceVariant: Color,
    val onSurfaceVariant: Color,
    val outline: Color,
    val shadow: Color,
    val transparent: Color
)

val primary: Color @Composable get() = Theme.color.primary
val onPrimary: Color @Composable get() = Theme.color.onPrimary
val primaryContainer: Color @Composable get() = Theme.color.primaryContainer
val onPrimaryContainer: Color @Composable get() = Theme.color.onPrimaryContainer
val secondary: Color @Composable get() = Theme.color.secondary
val onSecondary: Color @Composable get() = Theme.color.onSecondary
val secondaryContainer: Color @Composable get() = Theme.color.secondaryContainer
val onSecondaryContainer: Color @Composable get() = Theme.color.onSecondaryContainer
val tertiary: Color @Composable get() = Theme.color.tertiary
val onTertiary: Color @Composable get() = Theme.color.onTertiary
val tertiaryContainer: Color @Composable get() = Theme.color.tertiaryContainer
val onTertiaryContainer: Color @Composable get() = Theme.color.onTertiaryContainer
val error: Color @Composable get() = Theme.color.error
val onError: Color @Composable get() = Theme.color.onError
val errorContainer: Color @Composable get() = Theme.color.errorContainer
val onErrorContainer: Color @Composable get() = Theme.color.onErrorContainer
val background: Color @Composable get() = Theme.color.background
val onBackground: Color @Composable get() = Theme.color.onBackground
val surface: Color @Composable get() = Theme.color.surface
val onSurface: Color @Composable get() = Theme.color.onSurface
val surfaceVariant: Color @Composable get() = Theme.color.surfaceVariant
val onSurfaceVariant: Color @Composable get() = Theme.color.onSurfaceVariant
val outline: Color @Composable get() = Theme.color.outline
val shadow: Color @Composable get() = Theme.color.shadow
val transparent: Color @Composable get() = Theme.color.transparent