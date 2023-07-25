package ru.maksonic.beresta.feature.hidden_notes.ui.core

import ru.maksonic.beresta.elm.core.ElmUpdate
import ru.maksonic.beresta.elm.core.Sandbox

/**
 * @Author maksonic on 15.07.2023
 */
private typealias UpdateResult = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

class HiddenNotesEnterPasswordSandbox(
    program: HiddenNotesEnterPasswordProgram
) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(Cmd.FetchSavedPinCodeStatus),
    subscriptions = listOf(program)
) {

    private companion object {
        private const val PIN_CODE_LENGTH = 6
        private const val INITIAL_PLACEHOLDER = "••••••"
    }

    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Inner.UpdatedScreenCapturePermissionState -> {
            updatedScreenCapturePermissionState(model, msg)
        }

        is Msg.Ui.CloseDialog -> closedDialog(model)
        is Msg.Inner.UpdateInput -> updatedInput(model, msg)
        is Msg.Ui.OnBackspaceClicked -> onBackspaceClicked(model)
        is Msg.Inner.UpdatedCacheCode -> updatedCacheCode(model, msg)
        is Msg.Inner.FetchedPinCodeStatus -> fetchedPiCodeStatus(model, msg)
        is Msg.Inner.SuccessCreatedCodeResult -> successCreatedCodeResult(model)
        is Msg.Inner.SuccessVerifiedCodeResult -> successVerifyCodeResult(model)
        is Msg.Inner.FailureCodeResult -> failureCodeResult(model, msg)
    }

    private fun updatedScreenCapturePermissionState(
        model: Model,
        msg: Msg.Inner.UpdatedScreenCapturePermissionState
    ): UpdateResult =
        ElmUpdate(model, commands = setOf(Cmd.UpdateScreenCapturePermission(msg.isEnabled)))

    private fun closedDialog(model: Model): UpdateResult = ElmUpdate(
        model = model.copy(
            inputValue = "",
            cachedCode = "",
            placeholder = INITIAL_PLACEHOLDER,
        ),
        commands = setOf(Cmd.UpdateScreenCapturePermission(false)),
        effects = setOf(Eff.CloseDialog)
    )

    private fun updatedInput(model: Model, msg: Msg.Inner.UpdateInput): UpdateResult {
        val code = model.inputValue.plus("${msg.value}").take(PIN_CODE_LENGTH)
        val isCreate = code.length == PIN_CODE_LENGTH && !model.isHasPinCode
        val isVerify = code.length == PIN_CODE_LENGTH && model.isHasPinCode
        val command = when {
            isCreate -> setOf(Cmd.CreatePinCode(code, model.cachedCode))
            isVerify -> setOf(Cmd.VerifyPinCode(code))
            else -> emptySet()
        }

        return ElmUpdate(
            model.copy(
                inputValue = code, placeholder = model.placeholder.replaceFirst("•", "*")
            ),
            commands = command
        )
    }

    private fun onBackspaceClicked(model: Model): UpdateResult {
        val updatedInput = model.inputValue.dropLast(1)
        val placeholder = model.placeholder.reversed().replaceFirst("*", "•").reversed()
        return ElmUpdate(model.copy(inputValue = updatedInput, placeholder = placeholder))
    }

    private fun updatedCacheCode(model: Model, msg: Msg.Inner.UpdatedCacheCode): UpdateResult =
        ElmUpdate(
            model.copy(inputValue = "", placeholder = INITIAL_PLACEHOLDER, cachedCode = msg.value)
        )

    private fun successCreatedCodeResult(model: Model): UpdateResult =
        ElmUpdate(
            model = model.copy(
                isHasPinCode = true,
                inputValue = "",
                cachedCode = "",
                placeholder = INITIAL_PLACEHOLDER
            ),
            effects = setOf(Eff.NavigateToHiddenNotes)
        )

    private fun successVerifyCodeResult(model: Model): UpdateResult =
        ElmUpdate(
            model = model.copy(
                inputValue = "",
                cachedCode = "",
                placeholder = INITIAL_PLACEHOLDER
            ),
            effects = setOf(Eff.NavigateToHiddenNotes)
        )

    private fun failureCodeResult(model: Model, msg: Msg.Inner.FailureCodeResult): UpdateResult =
        ElmUpdate(
            model = model.copy(
                inputValue = "",
                cachedCode = "",
                placeholder = INITIAL_PLACEHOLDER
            ),
            effects = setOf(Eff.ShowWrongPinCodeMessage(msg.fail))
        )

    private fun fetchedPiCodeStatus(
        model: Model,
        msg: Msg.Inner.FetchedPinCodeStatus
    ): UpdateResult =
        ElmUpdate(model.copy(isHasPinCode = msg.isCreated))
}