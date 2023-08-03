package ru.maksonic.beresta.screen.settings.security.core

import ru.maksonic.beresta.elm.core.ElmProgram
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.HiddenNotesApi

/**
 * @Author maksonic on 03.08.2023
 */
class SettingsSecurityProgram(
    private val pinSecurePrefs: HiddenNotesApi.Feature.SecurePrefs
) : ElmProgram<Msg, Cmd> {

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchPinSecurePrefs -> fetchPinSecurePrefs(consumer)
            is Cmd.UpdatePinVisibility -> updatePinVisibility(cmd.isVisible)
            is Cmd.UpdateKeyTapVisibility -> updateKeyTapVisibility(cmd.isVisible)
        }
    }

    private suspend fun fetchPinSecurePrefs(consumer: (Msg) -> Unit) {
        pinSecurePrefs.state.collect { state ->
            consumer(Msg.Inner.FetchedPinSecurePrefs(state))
        }
    }

    private suspend fun updatePinVisibility(isVisible: Boolean) =
        pinSecurePrefs.updatePinVisibility(isVisible)

    private suspend fun updateKeyTapVisibility(isVisible: Boolean) =
        pinSecurePrefs.updateKeyTapVisibility(isVisible)
}