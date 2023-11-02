package ru.maksonic.beresta.feature.app_theme.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import ru.maksonic.beresta.common.domain.UseCase
import ru.maksonic.beresta.feature.app_theme.domain.AppThemeDomain
import ru.maksonic.beresta.feature.app_theme.domain.AppThemeRepository
import ru.maksonic.beresta.feature.app_theme.domain.ThemeContainer

/**
 * @Author maksonic on 13.10.2023
 */
class FetchAppThemeContainerUseCase(
    private val repository: AppThemeRepository
) : UseCase.Default<Flow<ThemeContainer>> {
    override fun invoke(): Flow<ThemeContainer> = combine(
        repository.fetchAppCurrentTheme(),
        repository.fetchAppPalette(),
        repository.fetchAppThemeDarkMode()
    ) { currentTheme, paletteContainer, isDark ->

        val currentPalette = when (currentTheme) {
            AppThemeDomain.SYSTEM -> if (isDark) paletteContainer.night else paletteContainer.light
            AppThemeDomain.DAY -> paletteContainer.light
            AppThemeDomain.NIGHT -> paletteContainer.night
            AppThemeDomain.DARK -> paletteContainer.dark
        }
        ThemeContainer(currentTheme, currentPalette, paletteContainer, isDark)
    }
}