package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.program

import ru.maksonic.beresta.elm.core.ElmProgram
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.HiddenNotesApi
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.Cmd
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.Msg

/**
 * @Author maksonic on 29.08.2023
 */
class HiddenNotesPinPrivacyProgram(
    private val pinPrivacyState: HiddenNotesApi.Feature.PinPrivacyState
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchPinPrivacyState -> fetchPinPrivacyState(consumer)
            is Cmd.UpdatePinVisibility -> updatePinVisibility(cmd.isVisible)
            is Cmd.UpdateKeyTapVisibility -> updateKeyTapVisibility(cmd.isVisible)
            else -> {}
        }
    }

    private suspend fun fetchPinPrivacyState(consumer: (Msg) -> Unit) =
        pinPrivacyState.state.collect { state ->
            consumer(Msg.Inner.FetchedPinPrivacyState(state))
        }

    private suspend fun updatePinVisibility(isVisible: Boolean) =
        pinPrivacyState.updatePinVisibility(isVisible)

    private suspend fun updateKeyTapVisibility(isVisible: Boolean) =
        pinPrivacyState.updateKeyTapVisibility(isVisible)
}