package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.core

import ru.maksonic.beresta.feature.hidden_notes_dialog.domain.PinFailInfo
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.api.ui.DialogContent
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.api.ui.isKeyboard
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.core.program.HiddenNotesPinProgram
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.core.program.HiddenNotesScreenCaptureProgram
import ru.maksonic.beresta.platform.core.system.pinCoolDownTime
import ru.maksonic.beresta.platform.elm.core.ElmUpdate
import ru.maksonic.beresta.platform.elm.core.Sandbox
import java.time.LocalDateTime

/**
 * @Author maksonic on 15.07.2023
 */
private typealias Update = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

class HiddenNotesDialogSandbox(
    pinProgram: HiddenNotesPinProgram,
    screenCaptureProgram: HiddenNotesScreenCaptureProgram,
) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    subscriptions = listOf(pinProgram, screenCaptureProgram)
) {
    private companion object {
        private const val PIN_CODE_LENGTH = 5
        private const val FAIL_COUNT = 4
    }

    override fun update(msg: Msg, model: Model): Update = when (msg) {
        is Msg.Inner.FetchedPinStatusRequest -> fetchedPinStatusRequest(model)
        is Msg.Inner.FetchedPinStatus -> fetchedPinStatus(model, msg)
        is Msg.Inner.UpdatedScreenCapturePermission -> updatedScreenCapturePermission(model, msg)
        is Msg.Ui.ClosedDialog -> closedDialog(model)
        is Msg.Inner.UpdatedInput -> updatedInput(model, msg)
        is Msg.Ui.UpdateDialogContent -> updatedDialogCurrentContent(model, msg)
        is Msg.Ui.OnBackspaceClicked -> onBackspaceClicked(model)
        is Msg.Inner.UpdatedCachePin -> updatedCacheCode(model)
        is Msg.Inner.SuccessPinResult -> successPinResult(model)
        is Msg.Inner.FailurePinResult -> failurePinResult(model, msg)
        is Msg.Ui.OnResetPinClicked -> onResetPinClicked(model)
        is Msg.Ui.OnKeyTapVisibilityClicked -> onKeyTapVisibilityClicked(model)
        is Msg.Ui.OnPinVisibilityClicked -> onPinVisibilityClicked(model)
        is Msg.Inner.FinishedCoolDown -> finishedCoolDownBlock(model)
        is Msg.Inner.ShowedBiometricDialog -> showedBiometricDialog(model, msg)
        is Msg.Inner.OnEnableBiometricAuthClicked -> onEnableBiometricAuthClicked(model)
        is Msg.Inner.BiometricAuthError -> ElmUpdate(model)
        is Msg.Inner.BiometricAuthSucceeded -> biometricAuthSucceeded(model)
    }

    private fun fetchedPinStatusRequest(model: Model): Update =
        ElmUpdate(model, commands = setOf(Cmd.FetchPinStatus))

    private fun fetchedPinStatus(
        model: Model,
        msg: Msg.Inner.FetchedPinStatus
    ): Update {
        val keyboardContent =
            if (msg.info.isCoolDown) DialogContent.BLOCK_KEYBOARD else DialogContent.KEYBOARD
        val dialogContent = if (msg.info.isValid) keyboardContent else model.dialogContent

        val isVisibleBiometricDialog = msg.privacy.isEnabledBiometric
                && msg.info.isValid
                && !msg.info.isCoolDown
                && dialogContent.isKeyboard

        return ElmUpdate(
            model.copy(
                pinFailInfo = msg.info.copy(delay = coolDownDelay(msg.info.failCount)),
                pinPrivacy = msg.privacy,
                isFetchedPinInfo = true,
                dialogContent = dialogContent,
                isVisibleBiometricKeyboardButton = msg.isVisibleBiometricKeyboardButton,
                isVisibleBiometricDialog = isVisibleBiometricDialog
            )
        )
    }

    private fun updatedScreenCapturePermission(
        model: Model,
        msg: Msg.Inner.UpdatedScreenCapturePermission
    ): Update =
        ElmUpdate(model, commands = setOf(Cmd.UpdateScreenCapturePermission(msg.isEnabled)))

    private fun closedDialog(model: Model): Update {
        val content = if (model.pinFailInfo.failCount >= FAIL_COUNT) {
            DialogContent.BLOCK_KEYBOARD
        } else {
            if (model.pinFailInfo.isValid) DialogContent.KEYBOARD else DialogContent.INITIAL
        }

        return ElmUpdate(
            model = model.copy(input = "", isCachedFirstInput = false, dialogContent = content),
            commands = setOf(Cmd.UpdateScreenCapturePermission(false), Cmd.ResetCachePin),
            effects = setOf(Eff.CloseDialog)
        )
    }

    private fun updatedInput(model: Model, msg: Msg.Inner.UpdatedInput): Update {
        val code = model.input.plus("${msg.value}").take(PIN_CODE_LENGTH)
        val isCreate = code.length == PIN_CODE_LENGTH && !model.pinFailInfo.isValid
        val isVerify = code.length == PIN_CODE_LENGTH && model.pinFailInfo.isValid
        val command = when {
            isCreate -> setOf(Cmd.CreatePin(code, model.isCachedFirstInput))
            isVerify -> setOf(Cmd.VerifyPin(code))
            else -> emptySet()
        }

        return ElmUpdate(model.copy(input = code), commands = command)
    }

    private fun updatedDialogCurrentContent(
        model: Model,
        msg: Msg.Ui.UpdateDialogContent
    ): Update = ElmUpdate(model.copy(dialogContent = msg.content))

    private fun onBackspaceClicked(model: Model): Update =
        ElmUpdate(model.copy(input = model.input.dropLast(1)))

    private fun updatedCacheCode(model: Model): Update =
        ElmUpdate(model.copy(input = "", isCachedFirstInput = true))

    private fun successPinResult(model: Model): Update = ElmUpdate(
        model = model.copy(
            input = "",
            isCachedFirstInput = false,
            pinFailInfo = model.pinFailInfo.copy(failCount = 0),
        ),
        commands = setOf(Cmd.ResetPinFailCounter, Cmd.ResetCachePin),
        effects = setOf(Eff.NavigateToHiddenNotes)
    )

    private fun failurePinResult(model: Model, msg: Msg.Inner.FailurePinResult): Update {
        val failCount = model.pinFailInfo.failCount + 1
        val isBlocked = failCount >= FAIL_COUNT && model.pinFailInfo.isValid
        val content = if (isBlocked) DialogContent.BLOCK_KEYBOARD else model.dialogContent
        val endDate = LocalDateTime.now().pinCoolDownTime(coolDownDelay(failCount))
        val command = if (isBlocked) setOf(
            Cmd.ShowPinCoolDownBlock(failCount, endDate), Cmd.ResetCachePin
        )
        else setOf(Cmd.ResetCachePin)

        val effect = if (isBlocked) emptySet() else setOf(Eff.ShowWrongPinCodeMessage(msg.fail))

        return ElmUpdate(
            model = model.copy(
                input = "",
                isCachedFirstInput = false,
                pinFailInfo = model.pinFailInfo.copy(
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

    private fun onResetPinClicked(model: Model): Update = ElmUpdate(
        model.copy(
            pinFailInfo = PinFailInfo.INITIAL,
            input = "",
            isCachedFirstInput = false,
            pinPrivacy = model.pinPrivacy.copy(
                isEnabledBiometric = false,
                isCanUseBiometric = false
            ),
            dialogContent = DialogContent.KEYBOARD
        ),
        commands = setOf(
            Cmd.ResetPin, Cmd.ResetCachePin, Cmd.UpdateBiometricAuthState(false)
        )
    )

    private fun showedBiometricDialog(
        model: Model,
        msg: Msg.Inner.ShowedBiometricDialog
    ): Update = ElmUpdate(model, effects = setOf(Eff.ShowBiometricDialog(msg.state)))

    private fun onEnableBiometricAuthClicked(model: Model): Update = ElmUpdate(
        model = model.copy(
            input = "",
            isCachedFirstInput = false,
            pinFailInfo = model.pinFailInfo.copy(failCount = 0),
        ),
        commands = setOf(Cmd.ResetPinFailCounter, Cmd.UpdateBiometricAuthState(true)),
        effects = setOf(Eff.NavigateToHiddenNotes)
    )

    private fun onKeyTapVisibilityClicked(model: Model): Update = ElmUpdate(
        model.copy(
            pinPrivacy = model.pinPrivacy.copy(
                isVisibleOnKeyboardTap = !model.pinPrivacy.isVisibleOnKeyboardTap
            )
        ),
        commands = setOf(Cmd.UpdateKeyTapVisibility(!model.pinPrivacy.isVisibleOnKeyboardTap))
    )

    private fun onPinVisibilityClicked(model: Model): Update = ElmUpdate(
        model.copy(
            pinPrivacy = model.pinPrivacy.copy(
                isVisibleWhenInputProcess = !model.pinPrivacy.isVisibleWhenInputProcess
            )
        ),
        commands = setOf(Cmd.UpdatePinVisibility(!model.pinPrivacy.isVisibleWhenInputProcess))
    )

    private fun finishedCoolDownBlock(model: Model): Update =
        ElmUpdate(
            model.copy(dialogContent = DialogContent.KEYBOARD, isVisibleBiometricDialog = false),
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

    private fun biometricAuthSucceeded(model: Model): Update =
        ElmUpdate(model, effects = setOf(Eff.NavigateToHiddenNotes))
}