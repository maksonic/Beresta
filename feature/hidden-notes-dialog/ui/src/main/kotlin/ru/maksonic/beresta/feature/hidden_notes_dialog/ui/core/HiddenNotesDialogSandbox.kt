package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core

import androidx.lifecycle.viewModelScope
import ru.maksonic.beresta.core.system.pinCoolDownTime
import ru.maksonic.beresta.elm.core.ElmUpdate
import ru.maksonic.beresta.elm.core.Sandbox
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui.DialogContent
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.program.HiddenNotesPinPrivacyProgram
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.program.HiddenNotesPinProgram
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.program.HiddenNotesScreenCaptureProgram
import java.time.LocalDateTime

/**
 * @Author maksonic on 15.07.2023
 */
private typealias UpdateResult = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

class HiddenNotesDialogSandbox(
    pinProgram: HiddenNotesPinProgram,
    pinPrivacyProgram: HiddenNotesPinPrivacyProgram,
    screenCaptureProgram: HiddenNotesScreenCaptureProgram,
) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(Cmd.FetchPinPrivacyState),
    subscriptions = listOf(pinProgram, pinPrivacyProgram, screenCaptureProgram)
) {
    private companion object {
        private const val PIN_CODE_LENGTH = 6
        private const val FAIL_COUNT = 4
    }

    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Inner.FetchedPinStatusRequest -> fetchedPinStatusRequest(model)
        is Msg.Inner.FetchedPinStatus -> fetchedPinStatus(model, msg)
        is Msg.Inner.FetchedPinPrivacyState -> fetchedPinPrivacyState(model, msg)
        is Msg.Inner.UpdatedScreenCapturePermission -> updatedScreenCapturePermission(model, msg)
        is Msg.Ui.ClosedDialog -> closedDialog(model)
        is Msg.Inner.UpdatedInput -> updatedInput(model, msg)
        is Msg.Ui.UpdateDialogContent -> updatedDialogCurrentContent(model, msg)
        is Msg.Ui.OnBackspaceClicked -> onBackspaceClicked(model)
        is Msg.Inner.UpdatedCachePin -> updatedCacheCode(model, msg)
        is Msg.Inner.SuccessCodeResult -> successPinResult(model)
        is Msg.Inner.FailureCodeResult -> failurePinResult(model, msg)
        is Msg.Ui.OnResetPinClicked -> resetPinCodeClicked(model)
        is Msg.Ui.OnKeyTapVisibilityClicked -> onKeyTapVisibilityClicked(model)
        is Msg.Ui.OnPinVisibilityClicked -> onPinVisibilityClicked(model)
        is Msg.Inner.FinishedCoolDown -> finishedCoolDownBlock(model)
    }

    private fun fetchedPinStatusRequest(model: Model): UpdateResult =
        ElmUpdate(model, commands = setOf(Cmd.FetchPinStatus))

    private fun fetchedPinStatus(
        model: Model,
        msg: Msg.Inner.FetchedPinStatus
    ): UpdateResult {
        val content = if (msg.info.isCreated) {
            if (msg.info.isCoolDown) {
                DialogContent.BLOCK_KEYBOARD
            } else {
                DialogContent.KEYBOARD
            }
        } else {
            DialogContent.INITIAL
        }

        return ElmUpdate(
            model.copy(
                pinInfo = msg.info.copy(delay = coolDownDelay(msg.info.failCount)),
                isFetchedPinInfo = true,
                dialogContent = content
            )
        )
    }

    private fun fetchedPinPrivacyState(
        model: Model,
        msg: Msg.Inner.FetchedPinPrivacyState
    ): UpdateResult =
        ElmUpdate(model.copy(pinSecure = msg.pinInputVisibility))

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

    private fun updatedInput(model: Model, msg: Msg.Inner.UpdatedInput): UpdateResult {
        val code = model.input.plus("${msg.value}").take(PIN_CODE_LENGTH)
        val isCreate = code.length == PIN_CODE_LENGTH && !model.pinInfo.isCreated
        val isVerify = code.length == PIN_CODE_LENGTH && model.pinInfo.isCreated
        val command = when {
            isCreate -> setOf(Cmd.CreatePin(code, model.cachedInput, viewModelScope))
            isVerify -> setOf(Cmd.VerifyPin(code, viewModelScope))
            else -> emptySet()
        }

        return ElmUpdate(model.copy(input = code), commands = command)
    }

    private fun updatedDialogCurrentContent(
        model: Model,
        msg: Msg.Ui.UpdateDialogContent
    ): UpdateResult = ElmUpdate(model.copy(dialogContent = msg.content))

    private fun onBackspaceClicked(model: Model): UpdateResult =
        ElmUpdate(model.copy(input = model.input.dropLast(1)))

    private fun updatedCacheCode(model: Model, msg: Msg.Inner.UpdatedCachePin): UpdateResult =
        ElmUpdate(model.copy(input = "", cachedInput = msg.value))

    private fun successPinResult(model: Model): UpdateResult = ElmUpdate(
        model = model.copy(
            input = "",
            cachedInput = "",
            pinInfo = model.pinInfo.copy(failCount = 0)
        ),
        commands = setOf(Cmd.ResetPinFailCounter),
        effects = setOf(Eff.NavigateToHiddenNotes)
    )

    private fun failurePinResult(model: Model, msg: Msg.Inner.FailureCodeResult): UpdateResult {
        val failCount = model.pinInfo.failCount + 1
        val isBlocked = failCount >= FAIL_COUNT && model.pinInfo.isCreated
        val content = if (isBlocked) DialogContent.BLOCK_KEYBOARD else model.dialogContent

        val endDate = LocalDateTime.now().pinCoolDownTime(coolDownDelay(failCount))
        val command = if (isBlocked) setOf(Cmd.ShowPinCoolDownBlock(failCount, endDate))
        else emptySet()
        val effect = if (isBlocked) emptySet() else setOf(Eff.ShowWrongPinCodeMessage(msg.fail))

        return ElmUpdate(
            model = model.copy(
                input = "",
                cachedInput = "",
                pinInfo = model.pinInfo.copy(
                    failCount = failCount,
                    endDate = endDate,
                    delay = coolDownDelay(failCount)
                ),
                dialogContent = content
            ),
            commands = command,
            effects = effect
        )
    }

    private fun resetPinCodeClicked(model: Model): UpdateResult = ElmUpdate(
        model.copy(
            pinInfo = model.pinInfo.copy(isCreated = false, delay = 5L, failCount = 0),
            input = "",
            cachedInput = "",
            dialogContent = DialogContent.KEYBOARD
        ),
        commands = setOf(Cmd.ResetPin)
    )

    private fun onKeyTapVisibilityClicked(model: Model): UpdateResult =
        ElmUpdate(
            model.updatedKeyTapVisibility(),
            commands = setOf(Cmd.UpdateKeyTapVisibility(!model.pinSecure.isVisibleOnKeyboardTap))
        )

    private fun onPinVisibilityClicked(model: Model): UpdateResult =
        ElmUpdate(
            model.updatedPinVisibility(),
            commands = setOf(Cmd.UpdatePinVisibility(!model.pinSecure.isVisiblePin))
        )

    private fun finishedCoolDownBlock(model: Model): UpdateResult =
        ElmUpdate(
            model.copy(dialogContent = DialogContent.KEYBOARD),
            commands = setOf(Cmd.HidePinCoolDownBlock)
        )

    private fun coolDownDelay(failCount: Int) = when (failCount) {
        4 -> 5L
        5 -> 10L
        6 -> 15L
        7 -> 20L
        8 -> 25L
        else -> 30L
    }
}