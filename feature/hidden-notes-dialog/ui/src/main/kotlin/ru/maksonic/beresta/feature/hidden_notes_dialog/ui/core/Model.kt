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
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui.PinInfo
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui.PinInputVisibility

/**
 * @Author maksonic on 15.07.2023
 */
@Stable
@Immutable
data class Model(
    val base: ElmBaseModel,
    val input: String,
    val cachedInput: String,
    val dialogContent: DialogContent,
    val pinSecure: PinInputVisibility,
    val pinInfo: PinInfo,
    val isFetchedPinInfo: Boolean
) : ElmModel {
    companion object {

        val Initial = Model(
            base = ElmBaseModel.Initial,
            input = "",
            cachedInput = "",
            dialogContent = DialogContent.INITIAL,
            pinSecure = PinInputVisibility.INITIAL,
            pinInfo = PinInfo.INITIAL,
            isFetchedPinInfo = false
        )
    }

    fun updatedPinVisibility() = this.copy(
        pinSecure = this.pinSecure.copy(isVisiblePin = !this.pinSecure.isVisiblePin)
    )

    fun updatedKeyTapVisibility() = this.copy(
        pinSecure = this.pinSecure.copy(isVisibleOnKeyboardTap = !this.pinSecure.isVisibleOnKeyboardTap)
    )
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
        data class FetchedPinStatus(val info: PinInfo) : Inner()
        data class FetchedPinPrivacyState(val pinInputVisibility: PinInputVisibility) : Inner()
        data class UpdatedInput(val value: Int) : Inner()
        data class UpdatedScreenCapturePermission(val isEnabled: Boolean) : Inner()
        data class UpdatedCachePin(val value: String) : Inner()
        data object SuccessCodeResult : Inner()
        data class FailureCodeResult(val fail: PinFailStatus) : Inner()
        data object FinishedCoolDown : Inner()
    }
}

sealed class Cmd : ElmCommand {
    data object FetchPinStatus : Cmd()
    data object FetchPinPrivacyState : Cmd()
    data class UpdateScreenCapturePermission(val isEnabled: Boolean) : Cmd()
    data class CreatePin(
        val code: String, val cachedCode: String, val scope: CoroutineScope
    ) : Cmd()

    data class VerifyPin(val code: String, val scope: CoroutineScope) : Cmd()
    data object ResetPin : Cmd()
    data object ResetPinFailCounter : Cmd()
    data class UpdatePinVisibility(val isVisible: Boolean) : Cmd()
    data class UpdateKeyTapVisibility(val isVisible: Boolean) : Cmd()
    data class ShowPinCoolDownBlock(val failCount: Int, val endDate: Long) : Cmd()
    data object HidePinCoolDownBlock : Cmd()
}

sealed class Eff : ElmEffect {
    data object NavigateToHiddenNotes : Eff()
    data class ShowWrongPinCodeMessage(val fail: PinFailStatus?) : Eff()
    data object CloseDialog : Eff()
}