package ru.maksonic.beresta.screen.settings.core

import kotlinx.coroutines.flow.collectLatest
import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.theme_selector.api.ThemeSelectorApi
import ru.maksonic.beresta.ui.theme.AppTheme

/**
 * @Author maksonic on 23.01.2023
 */
class SettingsProgram(private val themeSelector: ThemeSelectorApi.Theme) : ElmProgram<Msg, Cmd> {

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchCurrentTheme -> fetchTheme(consumer)
        }
    }

    private suspend fun fetchTheme(consumer: (Msg) -> Unit) {
        themeSelector.currentTheme.collectLatest { currentTheme ->
            consumer(Msg.Inner.FetchedTheme(currentTheme))
        }
    }
}