package ru.maksonic.beresta.feature.app_theme.domain.usecase

import ru.maksonic.beresta.feature.app_theme.domain.AppThemeDomain
import ru.maksonic.beresta.feature.app_theme.domain.AppThemePaletteDomain
import ru.maksonic.beresta.feature.app_theme.domain.AppThemeRepository

/**
 * @Author maksonic on 13.10.2023
 */
interface AppThemeInteractor {
    suspend fun setTheme(theme: AppThemeDomain)
    suspend fun updateDarkMode(isDark: Boolean)
    suspend fun setPalette(palette: AppThemePaletteDomain)

    class Core(private val repository: AppThemeRepository) : AppThemeInteractor {
        override suspend fun setTheme(theme: AppThemeDomain) = repository.setTheme(theme)
        override suspend fun setPalette(palette: AppThemePaletteDomain) = repository.setPalette(palette)
        override suspend fun updateDarkMode(isDark: Boolean) = repository.updateDarkMode(isDark)
    }
}