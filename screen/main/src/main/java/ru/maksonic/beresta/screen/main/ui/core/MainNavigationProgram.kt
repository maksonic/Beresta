package ru.maksonic.beresta.screen.main.ui.core

import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.navigation.router.AppNavigator

/**
 * @Author maksonic on 16.01.2023
 */
class MainNavigationProgram(
    private val navigator: AppNavigator
) : ElmProgram<Screen.Msg, Screen.Cmd> {
    override suspend fun executeProgram(cmd: Screen.Cmd, consumer: (Screen.Msg) -> Unit) {
        when (cmd) {
            is Screen.Cmd.NavigateToSettingsScreen -> navigator.mainToSettings()
        }
    }
}