package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.core

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.api.ui.DialogContent
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.api.ui.PinFailStatus
import ru.maksonic.beresta.feature.hidden_notes_dialog.domain.PinFailInfo
import ru.maksonic.beresta.feature.hidden_notes_dialog.domain.PinPrivacy
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.core.biometric.BiometricState
import ru.maksonic.beresta.platform.elm.core.ElmBaseModel
import ru.maksonic.beresta.platform.elm.core.ElmCommand
import ru.maksonic.beresta.platform.elm.core.ElmEffect
import ru.maksonic.beresta.platform.elm.core.ElmMessage
import ru.maksonic.beresta.platform.elm.core.ElmModel

/**
 * @Author maksonic on 15.07.2023
 */
@Stable
@Immutable
data class Model(
    val base: ElmBaseModel,
    val input: String,
    val isCachedFirstInput: Boolean,
    val dialogContent: DialogContent,
    val pinPrivacy: PinPrivacy,
    val pinFailInfo: PinFailInfo,
    val isFetchedPinInfo: Boolean,
    val isVisibleBiometricKeyboardButton: Boolean,
    val isVisibleBiometricDialog: Boolean
) : ElmModel {
    companion object {
        val Initial = Model(
            base = ElmBaseModel.Initial,
            input = "",
            isCachedFirstInput = false,
            dialogContent = DialogContent.INITIAL,
            pinPrivacy = PinPrivacy.INITIAL,
            pinFailInfo = PinFailInfo.INITIAL,
            isFetchedPinInfo = false,
            isVisibleBiometricKeyboardButton = false,
            isVisibleBiometricDialog = false
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        data object ClosedDialog : Ui()
        data object OnBackspaceClicked : Ui()
        data class UpdateDialogContent(val content: DialogContent) : Ui()
        data object OnResetPinClicked : Ui()
        data object OnPinVisibilityClicked : Ui()
        data object OnKeyTapVisibilityClicked : Ui()
    }

    sealed class Inner : Msg() {
        data object FetchedPinStatusRequest : Inner()
        data class FetchedPinStatus(
            val info: PinFailInfo,
            val privacy: PinPrivacy,
            val isVisibleBiometricKeyboardButton: Boolean
        ) : Inner()

        data class UpdatedInput(val value: Int) : Inner()
        data class UpdatedScreenCapturePermission(val isEnabled: Boolean) : Inner()
        data object UpdatedCachePin : Inner()
        data object SuccessPinResult : Inner()
        data class FailurePinResult(val fail: PinFailStatus) : Inner()
        data object BiometricAuthSucceeded : Inner()
        data object BiometricAuthError : Inner()
        data class ShowedBiometricDialog(val state: BiometricState) : Inner()
        data object OnEnableBiometricAuthClicked : Inner()
        data object FinishedCoolDown : Inner()
    }
}

sealed class Cmd : ElmCommand {
    data object FetchPinStatus : Cmd()
    data class UpdateScreenCapturePermission(val isEnabled: Boolean) : Cmd()
    data class CreatePin(val code: String, val isCachedFirstInput: Boolean) : Cmd()
    data class VerifyPin(val code: String) : Cmd()
    data object ResetPin : Cmd()
    data object ResetCachePin : Cmd()
    data object ResetPinFailCounter : Cmd()
    data class UpdatePinVisibility(val isVisible: Boolean) : Cmd()
    data class UpdateKeyTapVisibility(val isVisible: Boolean) : Cmd()
    data class ShowPinCoolDownBlock(val failCount: Int, val endDate: Long) : Cmd()
    data object HidePinCoolDownBlock : Cmd()
    data class UpdateBiometricAuthState(val isEnabled: Boolean) : Cmd()
}

sealed class Eff : ElmEffect {
    data object NavigateToHiddenNotes : Eff()
    data class ShowWrongPinCodeMessage(val fail: PinFailStatus?) : Eff()
    data object CloseDialog : Eff()
    data object ShowEnableBiometricDialog : Eff()
    data class ShowBiometricDialog(val key: BiometricState) : Eff()
}