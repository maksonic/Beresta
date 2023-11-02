package ru.maksonic.beresta.feature.app_theme.domain.mapper

import ru.maksonic.beresta.feature.app_theme.domain.AppThemePaletteDomain

/**
 * @Author maksonic on 15.10.2023
 */
interface AppThemePaletteMapper<Ui> {
    fun uiToDomain(ui: Ui): AppThemePaletteDomain
    fun domainToUi(domain: AppThemePaletteDomain): Ui
}