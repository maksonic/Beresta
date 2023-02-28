package ru.maksonic.beresta.feature.theme_selector.api

import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.ui.theme.AppTheme

/**
 * @Author maksonic on 28.02.2023
 */
interface ThemeSelectorApi {
    val currentTheme: Flow<AppTheme>
    suspend fun setTheme(theme: AppTheme)
}