package ru.maksonic.beresta.feature.theme_picker.core

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.maksonic.beresta.feature.theme_picker.api.ThemePickerApi

/**
 * @Author maksonic on 24.04.2023
 */
class SystemDarkModeChecker : ThemePickerApi.DarkModeChecker {
    private val mutableThemeValue = MutableStateFlow(false)
    override val isEnabledSystemDarkModeState: StateFlow<Boolean> = mutableThemeValue.asStateFlow()

    override fun checkSystemDarkTheme(isDarkTheme: Boolean) {
        mutableThemeValue.update { isDarkTheme }
    }
}