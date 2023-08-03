package ru.maksonic.beresta.screen.settings.security.core

import ru.maksonic.beresta.elm.core.ElmUpdate
import ru.maksonic.beresta.elm.core.Sandbox

/**
 * @Author maksonic on 03.08.2023
 */
private typealias UpdateResult = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

class SettingsSecuritySandbox(
    program: SettingsSecurityProgram
) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(Cmd.FetchPinSecurePrefs),
    subscriptions = listOf(program)
) {
    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Ui.OnTopBarBackPressed -> onTopBarBackPressed(model)
        is Msg.Ui.OnKeyTapVisibilityClicked -> onKeyTapVisibilityClicked(model)
        is Msg.Ui.OnPinVisibilityClicked -> onPinVisibilityClicked(model)
        is Msg.Inner.FetchedPinSecurePrefs -> fetchedPinSecurePrefs(model, msg)
    }

    private fun onTopBarBackPressed(model: Model): UpdateResult =
        ElmUpdate(model, effects = setOf(Eff.NavigateBack))

    private fun onKeyTapVisibilityClicked(model: Model): UpdateResult =
        ElmUpdate(
            model.updatedKeyTapVisibility(),
            commands = setOf(Cmd.UpdateKeyTapVisibility(!model.pinSecure.isVisibleKeyboardTap))
        )

    private fun onPinVisibilityClicked(model: Model): UpdateResult =
        ElmUpdate(
            model.updatedPinVisibility(),
            commands = setOf(Cmd.UpdatePinVisibility(!model.pinSecure.isVisible))
        )

    private fun fetchedPinSecurePrefs(
        model: Model,
        msg: Msg.Inner.FetchedPinSecurePrefs
    ): UpdateResult =
        ElmUpdate(model.copy(pinSecure = msg.pinSecureUiState))
}
