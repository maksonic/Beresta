package ru.maksonic.beresta.screen.settings.core

import ru.maksonic.beresta.elm.core.ElmProgram
import ru.maksonic.beresta.feature.theme_picker.api.ThemePickerApi

/**
 * @Author maksonic on 23.01.2023
 */
class SettingsProgram(
    private val themeSelector: ThemePickerApi.Feature.Theme
) : ElmProgram<Msg, Cmd> {

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchCurrentTheme -> fetchTheme(consumer)
        }
    }

    private suspend fun fetchTheme(consumer: (Msg) -> Unit) {
        themeSelector.current.collect { theme ->
            consumer(Msg.Inner.FetchedTheme(theme.first, theme.second))
        }
    }
}