package ru.maksonic.beresta.screen.settings.security.core

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.feature.hidden_notes_dialog.domain.PinPrivacy
import ru.maksonic.beresta.platform.elm.core.ElmCommand
import ru.maksonic.beresta.platform.elm.core.ElmEffect
import ru.maksonic.beresta.platform.elm.core.ElmMessage
import ru.maksonic.beresta.platform.elm.core.ElmModel

/**
 * @Author maksonic on 03.08.2023
 */

@Stable
@Immutable
data class Model(
    val pinPrivacy: PinPrivacy,
    val isVisibleHiddenNotesNotCreatedPinDialog: Boolean,
    val isVisibleHiddenNotesDialog: Boolean,
) : ElmModel {
    companion object {
        val Initial = Model(
            pinPrivacy = PinPrivacy.INITIAL,
            isVisibleHiddenNotesNotCreatedPinDialog = false,
            isVisibleHiddenNotesDialog = false,
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        data object OnTopBarBackPressed : Ui()
        data object OnPinVisibilityClicked : Ui()
        data object OnKeyTapVisibilityClicked : Ui()
        data object OnHiddenNotesBiometricClicked : Ui()
        data object OnCreateHiddenNotesPinClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedPinPrivacyState(val privacy: PinPrivacy) : Inner()
        data class UpdatedHiddenNotesNotCreatedPinDialogVisibility(val isVisible: Boolean) : Inner()
        data class UpdatedHiddenNotesDialogVisibility(val isVisible: Boolean) : Inner()
        data class UpdatedBiometricState(val isEnabled: Boolean) : Inner()
        data object ShowedBiometricDialog : Inner()
        data class HiddenNotesBiometricStateResult(val isEnabled: Boolean): Inner()
    }
}

sealed class Cmd : ElmCommand {
    data object FetchPinPrivacyState : Cmd()
    data class UpdatePinVisibility(val isVisible: Boolean) : Cmd()
    data class UpdateKeyTapVisibility(val isVisible: Boolean) : Cmd()
    data class UpdateHiddenNotesBiometricDialogVisibility(val isEnabled: Boolean) : Cmd()
    data class UpdateHiddenNotesBiometric(val isEnabled: Boolean) : Cmd()
}

sealed class Eff : ElmEffect {
    data object NavigateBack : Eff()
    data object ShowBiometricDialog : Eff()
}