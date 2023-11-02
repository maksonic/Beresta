package ru.maksonic.beresta.feature.app_theme.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import ru.maksonic.beresta.feature.app_theme.data.local.ThemeDataStore
import ru.maksonic.beresta.feature.app_theme.data.local.ThemePaletteDataStore
import ru.maksonic.beresta.feature.app_theme.domain.AppThemeDomain
import ru.maksonic.beresta.feature.app_theme.domain.AppThemePaletteDomain
import ru.maksonic.beresta.feature.app_theme.domain.AppThemeRepository
import ru.maksonic.beresta.feature.app_theme.domain.ThemePaletteContainer

/**
 * @Author maksonic on 12.10.2023
 */
class AppThemeRepositoryImpl(
    private val themeDataStore: ThemeDataStore,
    private val paletteDataStore: ThemePaletteDataStore,
    private val ioDispatcher: CoroutineDispatcher
) : AppThemeRepository {
    override fun fetchAppCurrentTheme(): Flow<AppThemeDomain> = themeDataStore.current

    override fun fetchAppPalette(): Flow<ThemePaletteContainer> = paletteDataStore.current

    override fun fetchAppThemeDarkMode(): Flow<Boolean> = themeDataStore.isDarkMode

    override suspend fun setTheme(theme: AppThemeDomain) = themeDataStore.setTheme(theme)

    override suspend fun setPalette(currentPalette: AppThemePaletteDomain) {
        CoroutineScope(ioDispatcher).launch {
            combine(fetchAppCurrentTheme(), fetchAppThemeDarkMode()) { theme, isDark ->
                paletteDataStore.setPalette(theme, currentPalette, isDark)
                this.coroutineContext.cancel()
            }.collect()
        }
    }

    override suspend fun updateDarkMode(isDark: Boolean) = themeDataStore.updateDarkMode(isDark)

}