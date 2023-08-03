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
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui.PinSecureUiState

/**
 * @Author maksonic on 15.07.2023
 */
@Stable
@Immutable
data class Model(
    val base: ElmBaseModel,
    val input: String,
    val cachedInput: String,
    val isHasPinCode: Boolean,
    val dialogContent: DialogContent,
    val pinSecure: PinSecureUiState,
) : ElmModel {
    companion object {
        val Initial = Model(
            base = ElmBaseModel.Initial,
            input = "",
            cachedInput = "",
            isHasPinCode = false,
            dialogContent = DialogContent.INITIAL,
            pinSecure = PinSecureUiState.INITIAL
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
        data class FetchedPinCodeStatus(val isCreated: Boolean) : Inner()
        data class FetchedPinSecurePrefs(val pinSecureUiState: PinSecureUiState) : Inner()
        data class UpdateInput(val value: Int) : Inner()
        data class UpdatedScreenCapturePermission(val isEnabled: Boolean) : Inner()
        data class UpdatedCacheCode(val value: String) : Inner()
        data object SuccessCodeResult : Inner()
        data class FailureCodeResult(val fail: PinFailStatus) : Inner()
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
}

sealed class Eff : ElmEffect {
    data object NavigateToHiddenNotes : Eff()
    data class ShowWrongPinCodeMessage(val fail: PinFailStatus?) : Eff()
    data object CloseDialog : Eff()
}