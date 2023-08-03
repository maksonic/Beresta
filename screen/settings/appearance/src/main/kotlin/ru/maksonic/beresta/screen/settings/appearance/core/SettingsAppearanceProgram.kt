package ru.maksonic.beresta.screen.settings.appearance.core

import ru.maksonic.beresta.elm.core.ElmProgram
import ru.maksonic.beresta.feature.notes.api.ui.NoteCardElevation
import ru.maksonic.beresta.feature.notes.api.ui.NoteCardShape
import ru.maksonic.beresta.feature.notes.api.NotesApi
import ru.maksonic.beresta.feature.notes.api.ui.updateElevation
import ru.maksonic.beresta.feature.notes.api.ui.updateMaxMessageLines
import ru.maksonic.beresta.feature.notes.api.ui.updateMaxTitleLines
import ru.maksonic.beresta.feature.notes.api.ui.updateShape
import ru.maksonic.beresta.language_engine.shell.LanguageEngineApi
import ru.maksonic.beresta.ui.theme.component.AppAnimationVelocity

/**
 * @Author maksonic on 07.07.2023
 */
class SettingsAppearanceProgram(
    private val noteCardUiApi: NotesApi.Ui.Card,
    private val noteCardFeatureState: NotesApi.Feature.NoteCardState,
    private val languageEngineApi: LanguageEngineApi,
    private val animationVelocity: AnimationVelocity,
) : ElmProgram<Msg, Cmd> {

    companion object {
        private const val INITIAL_TITLE_LINES_COUNT = 1
        private const val INITIAL_MESSAGE_LINES_COUNT = 2
    }

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.UpdateNoteCardShape -> updateNoteCardShape(cmd.shape)
            is Cmd.UpdateNoteCardElevation -> updateNoteCardElevation(cmd.isEnabled)
            is Cmd.UpdatedNoteCardTitleMaxLines -> updateNoteCardTitleMaxLines(cmd.value)
            is Cmd.UpdatedNoteCardMessageMaxLines -> updateNoteCardMessageMaxLines(cmd.value)
            is Cmd.ResetNoteCardLinesByDefault -> resetNoteCardLinesByDefault()
            is Cmd.FetchCurrentAppLang -> fetchAppLang(consumer)
            is Cmd.UpdateAnimationsVelocity -> updateAnimationVelocity(cmd.key)
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

    private suspend fun resetNoteCardLinesByDefault() = noteCardUiApi.state.update {
        it.copy(
            maxTitleLines = INITIAL_TITLE_LINES_COUNT,
            maxMessageLines = INITIAL_MESSAGE_LINES_COUNT
        )
    }
        .let { noteCardFeatureState.setByDefaultCardMaxLines() }

    private suspend fun fetchAppLang(consumer: (Msg) -> Unit) = languageEngineApi.current.collect {
        consumer(Msg.Inner.FetchedAppLang(it))
    }

    private suspend fun updateAnimationVelocity(key: AppAnimationVelocity.Key) =
        animationVelocity.current.update(key).let {
            animationVelocity.updateInDatastore(key)
        }
}