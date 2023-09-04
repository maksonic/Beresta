package ru.maksonic.beresta.screen.settings.security.core

import ru.maksonic.beresta.elm.core.ElmProgram
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.HiddenNotesApi

/**
 * @Author maksonic on 03.08.2023
 */
class SettingsSecurityProgram(
    private val pinPinPrivacyState: HiddenNotesApi.Feature.PinPrivacyState
) : ElmProgram<Msg, Cmd> {

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchPinSecurePrefs -> fetchPinSecurePrefs(consumer)
            is Cmd.UpdatePinVisibility -> updatePinVisibility(cmd.isVisible)
            is Cmd.UpdateKeyTapVisibility -> updateKeyTapVisibility(cmd.isVisible)
        }
    }

    private suspend fun fetchPinSecurePrefs(consumer: (Msg) -> Unit) {
        pinPinPrivacyState.state.collect { state ->
            consumer(Msg.Inner.FetchedPinSecurePrefs(state))
        }
    }

    private suspend fun updatePinVisibility(isVisible: Boolean) =
        pinPinPrivacyState.updatePinVisibility(isVisible)

    private suspend fun updateKeyTapVisibility(isVisible: Boolean) =
        pinPinPrivacyState.updateKeyTapVisibility(isVisible)
}