package ru.maksonic.beresta.feature.app_theme.domain.mapper

import ru.maksonic.beresta.common.core.Mapper
import ru.maksonic.beresta.feature.app_theme.domain.AppThemeDomain
import ru.maksonic.beresta.feature.app_theme.domain.ThemeContainer

/**
 * @Author maksonic on 14.10.2023
 */
interface AppThemeContainerMapper<UiContainer, AppUiTheme> : Mapper<ThemeContainer, UiContainer> {
    fun onlyThemeToUi(domain: AppThemeDomain): AppUiTheme
    fun onlyThemeToDomain(ui: AppUiTheme): AppThemeDomain
}