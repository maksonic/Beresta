package ru.maksonic.beresta.screen.settings.security.core

import ru.maksonic.beresta.platform.elm.core.ElmUpdate
import ru.maksonic.beresta.platform.elm.core.Sandbox

/**
 * @Author maksonic on 03.08.2023
 */
private typealias Update = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

class SettingsSecuritySandbox(
    program: SettingsSecurityProgram
) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(Cmd.FetchPinPrivacyState),
    subscriptions = listOf(program)
) {
    override fun update(msg: Msg, model: Model): Update = when (msg) {
        is Msg.Inner.FetchedPinPrivacyState -> fetchedPinSecurePrefs(model, msg)
        is Msg.Ui.OnTopBarBackPressed -> onTopBarBackPressed(model)
        is Msg.Ui.OnKeyTapVisibilityClicked -> onKeyTapVisibilityClicked(model)
        is Msg.Ui.OnPinVisibilityClicked -> onPinVisibilityClicked(model)
        is Msg.Ui.OnHiddenNotesBiometricClicked -> onHiddenNotesBiometricClicked(model)
        is Msg.Ui.OnCreateHiddenNotesPinClicked -> onCreateHiddenNotesPinClicked(model)
        is Msg.Inner.UpdatedHiddenNotesNotCreatedPinDialogVisibility -> {
            updatedHiddenNotesNotCreatedPinDialogVisibility(model, msg)
        }

        is Msg.Inner.UpdatedHiddenNotesDialogVisibility -> {
            updatedHiddenNotesDialogVisibility(model, msg)
        }

        is Msg.Inner.UpdatedBiometricState -> updatedBiometricState(model, msg)
        is Msg.Inner.ShowedBiometricDialog -> showedBiometricDialog(model)
        is Msg.Inner.HiddenNotesBiometricStateResult -> hiddenNotesBiometricResult(model, msg)
    }

    private fun fetchedPinSecurePrefs(
        model: Model,
        msg: Msg.Inner.FetchedPinPrivacyState
    ): Update = ElmUpdate(model.copy(pinPrivacy = msg.privacy))


    private fun onTopBarBackPressed(model: Model): Update =
        ElmUpdate(model, effects = setOf(Eff.NavigateBack))

    private fun onKeyTapVisibilityClicked(model: Model): Update = ElmUpdate(
        model = model,
        commands = setOf(Cmd.UpdateKeyTapVisibility(!model.pinPrivacy.isVisibleOnKeyboardTap))
    )

    private fun onPinVisibilityClicked(model: Model): Update = ElmUpdate(
        model = model,
        commands = setOf(Cmd.UpdatePinVisibility(!model.pinPrivacy.isVisibleWhenInputProcess))
    )

    private fun onHiddenNotesBiometricClicked(model: Model): Update =
        ElmUpdate(
            model = model,
            commands = setOf(
                Cmd.UpdateHiddenNotesBiometricDialogVisibility(
                    model.pinPrivacy.isEnabledBiometric
                )
            )
        )

    private fun onCreateHiddenNotesPinClicked(model: Model): Update = ElmUpdate(
        model = model.copy(
            isVisibleHiddenNotesNotCreatedPinDialog = false,
            isVisibleHiddenNotesDialog = true
        )
    )

    private fun updatedHiddenNotesNotCreatedPinDialogVisibility(
        model: Model,
        msg: Msg.Inner.UpdatedHiddenNotesNotCreatedPinDialogVisibility
    ): Update =
        ElmUpdate(model.copy(isVisibleHiddenNotesNotCreatedPinDialog = msg.isVisible))

    private fun updatedHiddenNotesDialogVisibility(
        model: Model,
        msg: Msg.Inner.UpdatedHiddenNotesDialogVisibility
    ): Update =
        ElmUpdate(model.copy(isVisibleHiddenNotesDialog = msg.isVisible))

    private fun updatedBiometricState(
        model: Model,
        msg: Msg.Inner.UpdatedBiometricState
    ): Update = ElmUpdate(
        model,
        commands = setOf(Cmd.UpdateHiddenNotesBiometric(msg.isEnabled))
    )

    private fun showedBiometricDialog(model: Model): Update = ElmUpdate(
        model, effects = setOf(Eff.ShowBiometricDialog)
    )

    private fun hiddenNotesBiometricResult(
        model: Model,
        msg: Msg.Inner.HiddenNotesBiometricStateResult
    ): Update =
        ElmUpdate(model, commands = setOf(Cmd.UpdateHiddenNotesBiometric(msg.isEnabled)))
}
