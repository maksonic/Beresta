package ru.maksonic.beresta.mapper

import ru.maksonic.beresta.common.ui_theme.AppThemePaletteUi
import ru.maksonic.beresta.common.ui_theme.AppThemeUi
import ru.maksonic.beresta.common.ui_theme.ThemePalettesUiContainer
import ru.maksonic.beresta.common.ui_theme.ThemeUiContainer
import ru.maksonic.beresta.feature.app_theme.domain.mapper.AppThemeContainerMapper
import ru.maksonic.beresta.feature.app_theme.domain.AppThemeDomain
import ru.maksonic.beresta.feature.app_theme.domain.AppThemePaletteDomain
import ru.maksonic.beresta.feature.app_theme.domain.ThemeContainer
import ru.maksonic.beresta.feature.app_theme.domain.ThemePaletteContainer

/**
 * @Author maksonic on 14.10.2023
 */
class AppThemeContainerMapperImpl : AppThemeContainerMapper<ThemeUiContainer, AppThemeUi> {
    override fun onlyThemeToUi(domain: AppThemeDomain): AppThemeUi = AppThemeUi.valueOf(domain.name)
    override fun onlyThemeToDomain(ui: AppThemeUi) = AppThemeDomain.valueOf(ui.name)

    override fun mapTo(i: ThemeContainer) = ThemeUiContainer(
        currentTheme = AppThemeUi.valueOf(i.currentTheme.name),
        currentPalette = AppThemePaletteUi.valueOf(i.currentPalette.name),
        paletteContainer = ThemePalettesUiContainer(
            light = AppThemePaletteUi.valueOf(i.paletteContainer.light.name),
            night = AppThemePaletteUi.valueOf(i.paletteContainer.night.name),
            dark = AppThemePaletteUi.valueOf(i.paletteContainer.dark.name),
        ),
        isDarkMode = i.isDarkMode
    )

    override fun mapFrom(o: ThemeUiContainer): ThemeContainer = ThemeContainer(
        currentTheme = AppThemeDomain.valueOf(o.currentTheme.name),
        currentPalette = AppThemePaletteDomain.valueOf(o.currentPalette.name),
        paletteContainer = ThemePaletteContainer(
            light = AppThemePaletteDomain.valueOf(o.paletteContainer.light.name),
            night = AppThemePaletteDomain.valueOf(o.paletteContainer.night.name),
            dark = AppThemePaletteDomain.valueOf(o.paletteContainer.dark.name),
        ),
        isDarkMode = o.isDarkMode
    )
}