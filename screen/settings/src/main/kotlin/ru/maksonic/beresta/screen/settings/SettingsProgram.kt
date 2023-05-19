package ru.maksonic.beresta.screen.settings

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.theme_picker.api.ThemePickerApi

/**
 * @Author maksonic on 23.01.2023
 */
class SettingsProgram(
    private val themeSelector: ThemePickerApi.Theme,
    private val darkModeChecker: ThemePickerApi.DarkModeChecker,
) : ElmProgram<Msg, Cmd> {

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchCurrentTheme -> fetchTheme(consumer)
        }
    }

    private suspend fun fetchTheme(consumer: (Msg) -> Unit) {
        combine(
            themeSelector.current,
            darkModeChecker.isEnabledSystemDarkModeState
        ) { theme, isDark ->
            consumer(Msg.Inner.FetchedTheme(theme, isDark))
        }.collect()
    }
}