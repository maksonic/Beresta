package ru.maksonic.beresta.feature.theme_selector.core

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.maksonic.beresta.feature.theme_selector.api.SystemThemeCheckerApi

/**
 * @Author maksonic on 28.02.2023
 */
class SystemThemeCheckerCore : SystemThemeCheckerApi {
    private val mutableThemeValue = MutableStateFlow(false)
    override val isEnabledSystemDarkMode: StateFlow<Boolean> = mutableThemeValue.asStateFlow()

    override fun check(isDarkTheme: Boolean) {
        mutableThemeValue.update { isDarkTheme }
    }
}