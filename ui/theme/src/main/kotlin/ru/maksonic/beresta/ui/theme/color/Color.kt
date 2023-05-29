package ru.maksonic.beresta.ui.theme.color

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.ui.theme.Theme

/**
 * @Author maksonic on 08.11.2022
 */
val LocalAppColors = staticCompositionLocalOf<AppColor> {
    error("No colors provided")
}

@Stable
data class AppColor(
    val primary : Color,
    val onPrimary : Color,
    val primaryContainer : Color,
    val onPrimaryContainer : Color,
    val secondary : Color,
    val onSecondary : Color,
    val secondaryContainer : Color,
    val onSecondaryContainer : Color,
    val tertiary : Color,
    val onTertiary : Color,
    val tertiaryContainer : Color,
    val onTertiaryContainer : Color,
    val error : Color,
    val errorContainer : Color,
    val onError : Color,
    val onErrorContainer : Color,
    val background : Color,
    val onBackground : Color,
    val surface : Color,
    val onSurface : Color,
    val surfaceVariant : Color,
    val onSurfaceVariant : Color,
    val outline : Color,
    val inverseOnSurface : Color,
    val inverseSurface : Color,
    val inversePrimary : Color,
    val surfaceTint : Color,
    val outlineVariant : Color,
    val scrim: Color,
    val snack: Color,
    val onSnack: Color,
    val onSnackContainer: Color,
    val transparent: Color,
    val idle: Color,
    val black: Color,
    val blue: Color,
    val green: Color,
    val purple: Color,
    val red: Color,
    val orange: Color,
    val yellow: Color,
    val placeholderColors: PlaceholderColors
)



data class PlaceholderColors(
    val c0: Color,
    val c1: Color,
    val c2: Color,
    val c3: Color,
    val c4: Color,
    val f0 : Color,
    val f1 : Color,
    val f2 : Color,
    val f3 : Color,
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
val inverseOnSurface : Color @Composable get() = Theme.color.inverseOnSurface
val inverseSurface : Color @Composable get() = Theme.color.inverseSurface
val inversePrimary : Color @Composable get() = Theme.color.inversePrimary
val surfaceTint : Color @Composable get() = Theme.color.surfaceTint
val outlineVariant : Color @Composable get() = Theme.color.outlineVariant
val scrim: Color @Composable get() = Theme.color.scrim
val transparent: Color @Composable get() = Theme.color.transparent
val snack: Color @Composable get() = Theme.color.snack
val onSnack: Color @Composable get() = Theme.color.onSnack
val onSnackContainer: Color @Composable get() = Theme.color.onSnackContainer