package ru.maksonic.beresta.screen.settings.core

import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel

/**
 * @Author maksonic on 23.01.2023
 */
private typealias UpdateResult = UpdatedModel<Screen.Model, Set<Screen.Cmd>, Set<Screen.Eff>>

class SettingsSandbox(
    program: SettingsProgram
) : Sandbox<Screen.Model, Screen.Msg, Screen.Cmd, Screen.Eff>(
    initialModel = Screen.Model(),
    subscriptions = listOf(program)
) {

    override fun update(msg: Screen.Msg, model: Screen.Model): UpdateResult = when (msg) {
        is Screen.Msg.Ui.TopBarBackPressed -> topBarBackPressed(model)
    }

    private fun topBarBackPressed(model: Screen.Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Screen.Eff.NavigateBack))
}