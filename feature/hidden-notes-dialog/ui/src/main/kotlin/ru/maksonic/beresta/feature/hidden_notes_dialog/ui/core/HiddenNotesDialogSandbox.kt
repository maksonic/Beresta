package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core

import android.util.Log
import androidx.lifecycle.viewModelScope
import ru.maksonic.beresta.elm.core.ElmUpdate
import ru.maksonic.beresta.elm.core.Sandbox
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui.DialogContent
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.program.HiddenNotesPasswordProgram
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.program.HiddenNotesScreenCaptureProgram

/**
 * @Author maksonic on 15.07.2023
 */
private typealias UpdateResult = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

class HiddenNotesDialogSandbox(
    passwordProgram: HiddenNotesPasswordProgram,
    screenCaptureProgram: HiddenNotesScreenCaptureProgram,
) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(
        Cmd.FetchSavedPinCodeStatus,
        Cmd.FetchPinSecurePrefs
    ),
    subscriptions = listOf(passwordProgram, screenCaptureProgram)
) {
    private companion object {
        private const val PIN_CODE_LENGTH = 6
        private const val FAIL_COUNT = 1
    }

    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Inner.UpdatedScreenCapturePermission -> updatedScreenCapturePermission(model, msg)
        is Msg.Ui.CloseDialog -> closedDialog(model)
        is Msg.Inner.UpdateInput -> updatedInput(model, msg)
        is Msg.Ui.OnBackspaceClicked -> onBackspaceClicked(model)
        is Msg.Inner.UpdatedCacheCode -> updatedCacheCode(model, msg)
        is Msg.Inner.FetchedPinCodeStatus -> fetchedPiCodeStatus(model, msg)
        is Msg.Inner.SuccessCodeResult -> successCodeResult(model)
        is Msg.Inner.FailureCodeResult -> failureCodeResult(model, msg)
        is Msg.Ui.UpdateDialogContent -> updatedDialogCurrentContent(model, msg)
        is Msg.Ui.ResetPinCodeClicked -> resetPinCodeClicked(model)
        is Msg.Ui.OnKeyTapVisibilityClicked -> onKeyTapVisibilityClicked(model)
        is Msg.Ui.OnPinVisibilityClicked -> onPinVisibilityClicked(model)
        is Msg.Inner.FetchedPinSecurePrefs -> fetchedPinSecurePrefs(model, msg)
        //is Msg.Inner.FetchedPinCoolDownWorkResult -> fetchedPinCoolDownWorkResult(model, msg)
        is Msg.Inner.CancelCoolDown -> ElmUpdate(model, commands = setOf(Cmd.ResetPinFailCoolDown))
    }

    private fun updatedScreenCapturePermission(
        model: Model,
        msg: Msg.Inner.UpdatedScreenCapturePermission
    ): UpdateResult =
        ElmUpdate(model, commands = setOf(Cmd.UpdateScreenCapturePermission(msg.isEnabled)))

    private fun closedDialog(model: Model): UpdateResult {
        val content = if (model.pinInfo.failCount >= FAIL_COUNT) {
            DialogContent.BLOCK_KEYBOARD
        } else {
            if (model.pinInfo.isCreated) DialogContent.KEYBOARD else DialogContent.INITIAL
        }

        return ElmUpdate(
            model = model.copy(input = "", cachedInput = "", dialogContent = content),
            commands = setOf(Cmd.UpdateScreenCapturePermission(false)),
            effects = setOf(Eff.CloseDialog)
        )
    }

    private fun updatedInput(model: Model, msg: Msg.Inner.UpdateInput): UpdateResult {
        val code = model.input.plus("${msg.value}").take(PIN_CODE_LENGTH)
        val isCreate = code.length == PIN_CODE_LENGTH && !model.pinInfo.isCreated
        val isVerify = code.length == PIN_CODE_LENGTH && model.pinInfo.isCreated
        val command = when {
            isCreate -> setOf(Cmd.CreatePinCode(code, model.cachedInput))
            isVerify -> setOf(Cmd.VerifyPinCode(viewModelScope, code))
            else -> emptySet()
        }

        return ElmUpdate(model.copy(input = code), commands = command)
    }

    private fun onBackspaceClicked(model: Model): UpdateResult =
        ElmUpdate(model.copy(input = model.input.dropLast(1)))

    private fun updatedCacheCode(model: Model, msg: Msg.Inner.UpdatedCacheCode): UpdateResult =
        ElmUpdate(model.copy(input = "", cachedInput = msg.value))

    private fun successCodeResult(model: Model): UpdateResult = ElmUpdate(
        model = model.copy(
            input = "",
            cachedInput = "",
            pinInfo = model.pinInfo.copy(failCount = 0)
        ),
        commands = setOf(Cmd.ResetPinFailCounter),
        effects = setOf(Eff.NavigateToHiddenNotes)
    )

    private fun failureCodeResult(model: Model, msg: Msg.Inner.FailureCodeResult): UpdateResult {
        val updatedCounter = model.pinInfo.failCount + 1
        val content = if (updatedCounter >= 1) DialogContent.BLOCK_KEYBOARD else model.dialogContent

        return ElmUpdate(
            model = model.copy(
                input = "",
                cachedInput = "",
                pinInfo = model.pinInfo.copy(failCount = updatedCounter),
                dialogContent = content
            ),
            commands = if (updatedCounter >= 1) setOf(Cmd.RunPinCoolDownWork(updatedCounter)) else emptySet(),
            effects = setOf(Eff.ShowWrongPinCodeMessage(msg.fail))
        )
    }

    private fun fetchedPiCodeStatus(
        model: Model,
        msg: Msg.Inner.FetchedPinCodeStatus
    ): UpdateResult {
        val content = if (msg.info.isCreated) {
            if (msg.info.isCoolDown) DialogContent.BLOCK_KEYBOARD else DialogContent.KEYBOARD
        } else {
            DialogContent.INITIAL
        }

        return ElmUpdate(model.copy(pinInfo = msg.info, dialogContent = content))
    }

    private fun updatedDialogCurrentContent(
        model: Model,
        msg: Msg.Ui.UpdateDialogContent
    ): UpdateResult = ElmUpdate(model.copy(dialogContent = msg.content))

    private fun resetPinCodeClicked(model: Model): UpdateResult = ElmUpdate(
        model.copy(
            pinInfo = model.pinInfo.copy(isCreated = false),
            input = "",
            cachedInput = "",
            dialogContent = DialogContent.KEYBOARD
        ),
        commands = setOf(Cmd.ResetHiddenNotesPinCode)
    )

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
        ElmUpdate(model.copy(pinSecure = msg.pinVisibilityUiState))

    /* private fun fetchedPinCoolDownWorkResult(
         model: Model,
         msg: Msg.Inner.FetchedPinCoolDownWorkResult
     ): UpdateResult {
         val content = if (msg.isBlocked) DialogContent.BLOCK_KEYBOARD else model.dialogContent)
         return ElmUpdate(model.copy(dialogContent = content))
     }*/
}