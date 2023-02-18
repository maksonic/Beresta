package ru.maksonic.beresta.screen.settings.core

import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.theme_selector.api.ThemeSelectorApi
import ru.maksonic.beresta.ui.theme.AppTheme

/**
 * @Author maksonic on 23.01.2023
 */
class SettingsProgram(private val themeSelector: ThemeSelectorApi): ElmProgram<Msg, Cmd> {

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.SetTheme -> setAppTheme(cmd.theme)
        }
    }

    private suspend fun setAppTheme(theme: AppTheme) {
        themeSelector.setTheme(theme)
    }
}