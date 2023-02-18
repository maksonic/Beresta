package ru.maksonic.beresta.feature.theme_selector.api

import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.ui.theme.AppTheme

/**
 * @Author maksonic on 15.02.2023
 */
interface ThemeSelectorApi {
    suspend fun setTheme(theme: AppTheme)
    val currentTheme: Flow<AppTheme>
}