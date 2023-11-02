package ru.maksonic.beresta.common.ui_theme.provide

import androidx.compose.runtime.staticCompositionLocalOf

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


