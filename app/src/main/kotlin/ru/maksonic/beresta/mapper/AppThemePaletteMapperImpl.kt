package ru.maksonic.beresta.mapper

import ru.maksonic.beresta.common.ui_theme.AppThemePaletteUi
import ru.maksonic.beresta.feature.app_theme.domain.AppThemePaletteDomain
import ru.maksonic.beresta.feature.app_theme.domain.mapper.AppThemePaletteMapper

/**
 * @Author maksonic on 15.10.2023
 */
class AppThemePaletteMapperImpl : AppThemePaletteMapper<AppThemePaletteUi> {
    override fun uiToDomain(ui: AppThemePaletteUi) = AppThemePaletteDomain.valueOf(ui.name)
    override fun domainToUi(domain: AppThemePaletteDomain) = AppThemePaletteUi.valueOf(domain.name)
}