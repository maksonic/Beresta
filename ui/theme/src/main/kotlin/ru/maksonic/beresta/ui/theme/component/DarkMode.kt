package ru.maksonic.beresta.ui.theme.component

import androidx.compose.runtime.staticCompositionLocalOf

/**
 * @Author maksonic on 08.07.2023
 */
val LocalAppDarkMode = staticCompositionLocalOf<AppDarkMode> {
    error("No darkMode provided")
}

data class AppDarkMode(val value: Boolean) {
    companion object {
        val Enabled = AppDarkMode(true)
        val Disabled = AppDarkMode(false)
    }
}