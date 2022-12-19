package ru.maksonic.beresta.ui.theme.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import ru.maksonic.beresta.ui.theme.Theme

/**
 * @Author maksonic on 08.11.2022
 */
val LocalAppDimen = staticCompositionLocalOf<AppDimen> {
    error("No dimens provided")
}

data class AppDimen(
    val durationAnimDefault: Int,
    val durationAnimFast: Int,
    val durationAnimOnboarding: Int
)

val dimens = AppDimen(
    durationAnimFast = 150,
    durationAnimDefault = 300,
    durationAnimOnboarding = 600
)

val dimenAnimDef @Composable get() = Theme.dimen.durationAnimDefault
val dimenAnimFast @Composable get() = Theme.dimen.durationAnimFast