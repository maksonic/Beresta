package ru.maksonic.beresta.feature.app_theme.domain

import kotlinx.coroutines.flow.Flow

/**
 * @Author maksonic on 12.10.2023
 */
interface AppThemeRepository {
    fun fetchAppCurrentTheme(): Flow<AppThemeDomain>
    fun fetchAppPalette(): Flow<ThemePaletteContainer>
    fun fetchAppThemeDarkMode(): Flow<Boolean>
    suspend fun setTheme(theme: AppThemeDomain)
    suspend fun setPalette(currentPalette: AppThemePaletteDomain)
    suspend fun updateDarkMode(isDark: Boolean)
}