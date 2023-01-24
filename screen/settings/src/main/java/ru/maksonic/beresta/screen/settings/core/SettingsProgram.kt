package ru.maksonic.beresta.screen.settings.core

import ru.maksonic.beresta.elm.ElmProgram

/**
 * @Author maksonic on 23.01.2023
 */
class SettingsProgram: ElmProgram<Screen.Msg, Screen.Cmd> {

    override suspend fun executeProgram(cmd: Screen.Cmd, consumer: (Screen.Msg) -> Unit) {

    }
}