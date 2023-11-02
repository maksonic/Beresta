package ru.maksonic.beresta.feature.app_theme.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.common.domain.UseCase
import ru.maksonic.beresta.feature.app_theme.domain.AppThemeRepository

/**
 * @Author maksonic on 13.10.2023
 */
class FetchAppThemeDarkModeUseCase(
    private val repository: AppThemeRepository
) : UseCase.Default<Flow<Boolean>> {
    override fun invoke(): Flow<Boolean> = repository.fetchAppThemeDarkMode()
}