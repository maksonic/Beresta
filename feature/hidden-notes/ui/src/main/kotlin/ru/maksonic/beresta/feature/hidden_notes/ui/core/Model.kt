package ru.maksonic.beresta.feature.hidden_notes.ui.core

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.core.ElmBaseModel
import ru.maksonic.beresta.elm.core.ElmCommand
import ru.maksonic.beresta.elm.core.ElmEffect
import ru.maksonic.beresta.elm.core.ElmMessage
import ru.maksonic.beresta.elm.core.ElmModel

/**
 * @Author maksonic on 15.07.2023
 */
enum class PinFailStatus {
    NOT_MATCHED, NOT_VERIFIED
}

@Stable
@Immutable
data class Model(
    val base: ElmBaseModel,
    val inputValue: String,
    val cachedCode: String,
    val placeholder: String,
    val isHasPinCode: Boolean,
) : ElmModel {
    companion object {
        val Initial = Model(
            base = ElmBaseModel.Initial,
            inputValue = "",
            cachedCode = "",
            placeholder = "••••••",
            isHasPinCode = false,
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        object CloseDialog : Ui()
        object OnBackspaceClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedPinCodeStatus(val isCreated: Boolean) : Inner()
        data class UpdateInput(val value: Int) : Inner()
        data class UpdatedScreenCapturePermissionState(val isEnabled: Boolean) : Inner()
        data class UpdatedCacheCode(val value: String) : Inner()
        object SuccessCreatedCodeResult : Inner()
        object SuccessVerifiedCodeResult : Inner()
        data class FailureCodeResult(val fail: PinFailStatus) : Inner()
    }
}

sealed class Cmd : ElmCommand {
    object FetchSavedPinCodeStatus : Cmd()
    data class CreatePinCode(val code: String, val cachedCode: String) : Cmd()
    data class VerifyPinCode(val code: String) : Cmd()
    data class UpdateScreenCapturePermission(val isEnabled: Boolean) : Cmd()
}

sealed class Eff : ElmEffect {
   object NavigateToHiddenNotes : Eff()
    data class ShowWrongPinCodeMessage(val fail: PinFailStatus?) : Eff()
    object CloseDialog : Eff()
}