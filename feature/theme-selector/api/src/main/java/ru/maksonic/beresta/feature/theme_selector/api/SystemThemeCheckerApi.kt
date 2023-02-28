package ru.maksonic.beresta.feature.theme_selector.api

import kotlinx.coroutines.flow.StateFlow

/**
 * @Author maksonic on 28.02.2023
 */
interface SystemThemeCheckerApi {
    fun check(isDarkTheme: Boolean)
    val isEnabledSystemDarkMode: StateFlow<Boolean>
}