package ru.maksonic.beresta.screen.settings.security.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import ru.maksonic.beresta.feature.hidden_notes_dialog.domain.PinPrivacySettings
import ru.maksonic.beresta.feature.hidden_notes_dialog.domain.usecase.FetchHiddenNotesPinStatusUseCase
import ru.maksonic.beresta.platform.elm.core.ElmProgram

/**
 * @Author maksonic on 03.08.2023
 */
class SettingsSecurityProgram(
    private val pinPrivacySettings: PinPrivacySettings,
    private val fetchHiddenNotesPinStatusUseCase: FetchHiddenNotesPinStatusUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ElmProgram<Msg, Cmd> {

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchPinPrivacyState -> fetchPinPrivacyState(consumer)
            is Cmd.UpdatePinVisibility -> updatePinVisibility(cmd.isVisible)
            is Cmd.UpdateKeyTapVisibility -> updateKeyTapVisibility(cmd.isVisible)
            is Cmd.UpdateHiddenNotesBiometricDialogVisibility -> {
                updateHiddenNotesBiometricVisibility(cmd.isEnabled, consumer)
            }

            is Cmd.UpdateHiddenNotesBiometric -> updateHiddenNotesBiometric(cmd.isEnabled)
        }
    }

    private suspend fun fetchPinPrivacyState(consumer: (Msg) -> Unit) =
        pinPrivacySettings.state.collect { privacyState ->
            consumer(Msg.Inner.FetchedPinPrivacyState(privacyState))
        }

    private suspend fun updatePinVisibility(isVisible: Boolean) =
        pinPrivacySettings.updateVisibility(isVisible)


    private suspend fun updateKeyTapVisibility(isVisible: Boolean) =
        pinPrivacySettings.updateKeyTapVisibility(isVisible)

    private suspend fun updateHiddenNotesBiometricVisibility(
        isEnabled: Boolean, consumer: (Msg) -> Unit
    ) = CoroutineScope(ioDispatcher).launch {
        fetchHiddenNotesPinStatusUseCase().collect { isValid ->
            if (isValid) {
                if (isEnabled) {
                    pinPrivacySettings.updateBiometricEnablement(false)
                } else {
                    consumer(Msg.Inner.ShowedBiometricDialog)
                }
            } else {
                consumer(Msg.Inner.UpdatedHiddenNotesNotCreatedPinDialogVisibility(true))
            }

            this.coroutineContext.cancel()
        }
    }

    private suspend fun updateHiddenNotesBiometric(isEnabled: Boolean) =
        pinPrivacySettings.updateBiometricEnablement(isEnabled)

}
