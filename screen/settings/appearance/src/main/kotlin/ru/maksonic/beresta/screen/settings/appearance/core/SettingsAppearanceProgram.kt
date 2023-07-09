package ru.maksonic.beresta.screen.settings.appearance.core

import ru.maksonic.beresta.elm.core.ElmProgram
import ru.maksonic.beresta.feature.notes.api.NoteCardElevation
import ru.maksonic.beresta.feature.notes.api.NoteCardShape
import ru.maksonic.beresta.feature.notes.api.NotesApi
import ru.maksonic.beresta.feature.notes.api.updateElevation
import ru.maksonic.beresta.feature.notes.api.updateMaxMessageLines
import ru.maksonic.beresta.feature.notes.api.updateMaxTitleLines
import ru.maksonic.beresta.feature.notes.api.updateShape

/**
 * @Author maksonic on 07.07.2023
 */
class SettingsAppearanceProgram(
    private val noteCardUiApi: NotesApi.Ui.Card,
    private val noteCardFeatureState: NotesApi.Feature.NoteCardState
) : ElmProgram<Msg, Cmd> {

    companion object {
        private const val INITIAL_TITLE_LINES_COUNT = 1
        private const val INITIAL_MESSAGE_LINES_COUNT = 3
    }

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.UpdateNoteCardShape -> updateNoteCardShape(cmd.shape)
            is Cmd.UpdateNoteCardElevation -> updateNoteCardElevation(cmd.isEnabled)
            is Cmd.UpdatedNoteCardTitleMaxLines -> updateNoteCardTitleMaxLines(cmd.value)
            is Cmd.UpdatedNoteCardMessageMaxLines -> updateNoteCardMessageMaxLines(cmd.value)
            is Cmd.ResetNoteCardLinesByDefault -> resetNoteCardLinesByDefault()
        }
    }

    private suspend fun updateNoteCardShape(cardShape: NoteCardShape) = noteCardUiApi.state
        .updateShape(cardShape)
        .let { noteCardFeatureState.setCardShape(cardShape) }

    private suspend fun updateNoteCardElevation(isEnabled: Boolean) {
        val elevation = if (isEnabled) NoteCardElevation.DISABLED else NoteCardElevation.ENABLED

        noteCardUiApi.state.updateElevation(elevation).let {
            noteCardFeatureState.setCardElevation(elevation)
        }
    }

    private suspend fun updateNoteCardTitleMaxLines(count: Int) = noteCardUiApi.state
        .updateMaxTitleLines(count)
        .let { noteCardFeatureState.setCardTitleMaxLines(count) }

    private suspend fun updateNoteCardMessageMaxLines(count: Int) = noteCardUiApi.state
        .updateMaxMessageLines(count)
        .let { noteCardFeatureState.setCardMessageMaxLines(count) }

    private suspend fun resetNoteCardLinesByDefault() = noteCardUiApi.state
        .update {
            it.copy(
                maxTitleLines = INITIAL_TITLE_LINES_COUNT,
                maxMessageLines = INITIAL_MESSAGE_LINES_COUNT
            )
        }
        .let { noteCardFeatureState.setByDefaultCardMaxLines() }
}