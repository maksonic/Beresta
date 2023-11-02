package ru.maksonic.beresta.screen.settings.core

import ru.maksonic.beresta.common.ui_theme.AppThemeUi
import ru.maksonic.beresta.common.ui_theme.ThemeUiContainer
import ru.maksonic.beresta.feature.app_theme.domain.mapper.AppThemeContainerMapper
import ru.maksonic.beresta.feature.app_theme.domain.usecase.FetchAppThemeContainerUseCase
import ru.maksonic.beresta.platform.elm.core.ElmProgram

/**
 * @Author maksonic on 23.01.2023
 */
class SettingsProgram(
    private val fetchAppThemeContainerUseCase: FetchAppThemeContainerUseCase,
    private val themeMapper: AppThemeContainerMapper<ThemeUiContainer, AppThemeUi>
) : ElmProgram<Msg, Cmd> {

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchCurrentTheme -> fetchTheme(consumer)
        }
    }

    private suspend fun fetchTheme(consumer: (Msg) -> Unit) =
        fetchAppThemeContainerUseCase().collect { container ->
            val currentTheme = themeMapper.onlyThemeToUi(container.currentTheme)
            consumer(Msg.Inner.FetchedTheme(currentTheme, container.isDarkMode))
        }
}