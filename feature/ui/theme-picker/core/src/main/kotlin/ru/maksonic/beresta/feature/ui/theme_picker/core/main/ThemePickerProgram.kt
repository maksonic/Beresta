package ru.maksonic.beresta.feature.ui.theme_picker.core.main

import ru.maksonic.beresta.common.ui_theme.AppThemePaletteUi
import ru.maksonic.beresta.common.ui_theme.AppThemeUi
import ru.maksonic.beresta.common.ui_theme.ThemeUiContainer
import ru.maksonic.beresta.feature.app_theme.domain.mapper.AppThemeContainerMapper
import ru.maksonic.beresta.feature.app_theme.domain.mapper.AppThemePaletteMapper
import ru.maksonic.beresta.feature.app_theme.domain.usecase.AppThemeInteractor
import ru.maksonic.beresta.feature.app_theme.domain.usecase.FetchAppThemeContainerUseCase
import ru.maksonic.beresta.platform.elm.core.ElmProgram

/**
 * @Author maksonic on 20.06.2023
 */
class ThemePickerProgram(
    private val fetchAppThemeContainerUseCase: FetchAppThemeContainerUseCase,
    private val appThemeInteractor: AppThemeInteractor,
    private val themeMapper: AppThemeContainerMapper<ThemeUiContainer, AppThemeUi>,
    private val paletteMapper: AppThemePaletteMapper<AppThemePaletteUi>,
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchThemeWithPalette -> fetchThemeWithPalette(consumer)
            is Cmd.SaveSelectedTheme -> saveCurrentTheme(cmd.theme)
            is Cmd.SaveSelectedColorPalette -> setPalette(cmd.palette)
        }
    }

    private suspend fun fetchThemeWithPalette(consumer: (Msg) -> Unit) =
        fetchAppThemeContainerUseCase().collect { container ->
            val currentTheme = themeMapper.mapTo(container).currentTheme
            val currentPalette = paletteMapper.domainToUi(container.currentPalette)
            consumer(Msg.Inner.FetchedAppTheme(currentTheme, currentPalette))
        }

    private suspend fun saveCurrentTheme(theme: AppThemeUi) =
        appThemeInteractor.setTheme(themeMapper.onlyThemeToDomain(theme))

    private suspend fun setPalette(palette: AppThemePaletteUi) =
        appThemeInteractor.setPalette(paletteMapper.uiToDomain(palette))
}