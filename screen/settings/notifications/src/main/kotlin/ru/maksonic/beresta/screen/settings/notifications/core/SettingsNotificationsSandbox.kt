package ru.maksonic.beresta.screen.settings.notifications.core

import ru.maksonic.beresta.elm.core.ElmUpdate
import ru.maksonic.beresta.elm.core.Sandbox
import ru.maksonic.beresta.screen.settings.notifications.core.programs.SettingsVibrationProgram

/**
 * @Author maksonic on 07.07.2023
 */
private typealias UpdateResult = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

class SettingsNotificationsSandbox(
    vibrationProgram: SettingsVibrationProgram
) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(Cmd.FetchVibrationState),
    subscriptions = listOf(vibrationProgram)
) {
    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Ui.OnTopBarBackPressed -> onTopBarBackPressed(model)
        is Msg.Inner.FetchedVibrationStateResult -> fetchedVibrationStateResult(model, msg)
        is Msg.Ui.OnVibrationItemClicked -> onVibrationItemClicked(model)
    }

    private fun onTopBarBackPressed(model: Model): UpdateResult =
        ElmUpdate(model, effects = setOf(Eff.NavigateBack))

    private fun fetchedVibrationStateResult(
        model: Model,
        msg: Msg.Inner.FetchedVibrationStateResult
    ): UpdateResult =
        ElmUpdate(model.copy(isEnabledVibration = msg.isEnabled))

    private fun onVibrationItemClicked(model: Model): UpdateResult =
        ElmUpdate(model, commands = setOf(Cmd.UpdateVibrationState(model.isEnabledVibration)))
}
