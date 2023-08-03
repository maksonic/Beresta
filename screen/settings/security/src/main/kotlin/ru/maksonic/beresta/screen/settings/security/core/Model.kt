package ru.maksonic.beresta.screen.settings.security.core

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.core.ElmCommand
import ru.maksonic.beresta.elm.core.ElmEffect
import ru.maksonic.beresta.elm.core.ElmMessage
import ru.maksonic.beresta.elm.core.ElmModel
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui.PinSecureUiState

/**
 * @Author maksonic on 03.08.2023
 */
@Stable
@Immutable
data class Model(
    val pinSecure: PinSecureUiState,
    ) : ElmModel {
    companion object {
        val Initial = Model(
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
        data object OnTopBarBackPressed : Ui()
        data object OnPinVisibilityClicked : Ui()
        data object OnKeyTapVisibilityClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedPinSecurePrefs(val pinSecureUiState: PinSecureUiState) : Inner()
    }
}

sealed class Cmd : ElmCommand {
    data object FetchPinSecurePrefs : Cmd()
    data class UpdatePinVisibility(val isVisible: Boolean) : Cmd()
    data class UpdateKeyTapVisibility(val isVisible: Boolean) : Cmd()
}

sealed class Eff : ElmEffect {
    data object NavigateBack : Eff()
}