package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import kotlinx.coroutines.CoroutineScope
import ru.maksonic.beresta.elm.core.ElmBaseModel
import ru.maksonic.beresta.elm.core.ElmCommand
import ru.maksonic.beresta.elm.core.ElmEffect
import ru.maksonic.beresta.elm.core.ElmMessage
import ru.maksonic.beresta.elm.core.ElmModel
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui.DialogContent
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui.PinFailStatus
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui.PinVisibilityUiState

/**
 * @Author maksonic on 15.07.2023
 */

@Stable
@Immutable
data class PinCodeInfo(
    val isCreated: Boolean,
    val isCoolDown: Boolean,
    val coolDownDelay: Int,
    val failCount: Int
) {
    companion object {
        private const val INITIAL_FAIL_DELAY = 15

        val INITIAL = PinCodeInfo(
            isCreated = false,
            isCoolDown = false,
            failCount = 0,
            coolDownDelay = INITIAL_FAIL_DELAY
        )
    }
}

@Stable
@Immutable
data class Model(
    val base: ElmBaseModel,
    val input: String,
    val cachedInput: String,
    val dialogContent: DialogContent,
    val pinSecure: PinVisibilityUiState,
    val pinInfo: PinCodeInfo
) : ElmModel {
    companion object {

        val Initial = Model(
            base = ElmBaseModel.Initial,
            input = "",
            cachedInput = "",
            dialogContent = DialogContent.INITIAL,
            pinSecure = PinVisibilityUiState.INITIAL,
            pinInfo = PinCodeInfo.INITIAL
        )
    }

    fun updatedPinVisibility() = this.copy(
        pinSecure = this.pinSecure.copy(isVisible = !this.pinSecure.isVisible)
    )

    fun updatedKeyTapVisibility() = this.copy(
        pinSecure = this.pinSecure.copy(isVisibleKeyboardTap = !this.pinSecure.isVisibleKeyboardTap)
    )
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        data object CloseDialog : Ui()
        data object OnBackspaceClicked : Ui()
        data class UpdateDialogContent(val content: DialogContent) : Ui()
        data object ResetPinCodeClicked : Ui()
        data object OnPinVisibilityClicked : Ui()
        data object OnKeyTapVisibilityClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedPinCodeStatus(val info: PinCodeInfo) : Inner()
        data class FetchedPinSecurePrefs(val pinVisibilityUiState: PinVisibilityUiState) : Inner()
        data class UpdateInput(val value: Int) : Inner()
        data class UpdatedScreenCapturePermission(val isEnabled: Boolean) : Inner()
        data class UpdatedCacheCode(val value: String) : Inner()
        data object SuccessCodeResult : Inner()
        data class FailureCodeResult(val fail: PinFailStatus) : Inner()
        data object CancelCoolDown : Inner()
        //data class FetchedPinCoolDownWorkResult(val isBlocked: Boolean): Inner()
    }
}

sealed class Cmd : ElmCommand {
    data object FetchSavedPinCodeStatus : Cmd()
    data object FetchPinSecurePrefs : Cmd()
    data class CreatePinCode(val code: String, val cachedCode: String) : Cmd()
    data class VerifyPinCode(val scope: CoroutineScope, val code: String) : Cmd()
    data class UpdateScreenCapturePermission(val isEnabled: Boolean) : Cmd()
    data object ResetHiddenNotesPinCode : Cmd()
    data class UpdatePinVisibility(val isVisible: Boolean) : Cmd()
    data class UpdateKeyTapVisibility(val isVisible: Boolean) : Cmd()
    data class RunPinCoolDownWork(val count: Int) : Cmd()
    data object ResetPinFailCounter : Cmd()
    data object ResetPinFailCoolDown : Cmd()
}

sealed class Eff : ElmEffect {
    data object NavigateToHiddenNotes : Eff()
    data class ShowWrongPinCodeMessage(val fail: PinFailStatus?) : Eff()
    data object CloseDialog : Eff()
}