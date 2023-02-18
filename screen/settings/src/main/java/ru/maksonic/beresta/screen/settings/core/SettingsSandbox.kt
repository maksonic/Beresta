package ru.maksonic.beresta.screen.settings.core

import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel

/**
 * @Author maksonic on 23.01.2023
 */
private typealias UpdateResult = UpdatedModel<Model, Set<Cmd>, Set<Eff>>

class SettingsSandbox(
    program: SettingsProgram
) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model(),
    subscriptions = listOf(program)
) {

    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Ui.OnTopBarBackPressed -> topBarBackPressed(model)
        is Msg.Ui.SwitchAppTheme -> afterThemeSwitch(model, msg)
    }

    private fun afterThemeSwitch(model: Model, msg: Msg.Ui.SwitchAppTheme): UpdateResult =
        UpdatedModel(model, commands = setOf(Cmd.SetTheme(msg.theme)))

    private fun topBarBackPressed(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.NavigateBack))
}