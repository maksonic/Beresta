package ru.maksonic.beresta.feature.edit_note.ui

import ru.maksonic.beresta.elm.core.ElmProgram
import ru.maksonic.beresta.feature.notes.api.domain.NotesInteractor
import ru.maksonic.beresta.feature.notes.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes.api.ui.NoteUiMapper
import ru.maksonic.beresta.feature.notes.api.ui.isDefaultId
import ru.maksonic.beresta.navigation.router.AbstractNavigator
import ru.maksonic.beresta.navigation.router.Destination
import java.time.LocalDateTime

/**
 * @Author maksonic on 26.04.2023
 */
class EditNoteProgram(
    private val interactor: NotesInteractor,
    private val mapper: NoteUiMapper,
    private val navigator: AbstractNavigator,
) : ElmProgram<Msg, Cmd> {

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchNote -> fetchNote(consumer)
            is Cmd.SaveNote -> saveOrUpdateNote(cmd.note)
        }
    }

    private suspend fun fetchNote(consumer: (Msg) -> Unit) {
        val args = navigator.getNoteEditorArgs(Destination.EditNote.passedKeysList)

        runCatching {
            interactor.fetchById(args.second).collect { noteDomain ->
                val note = mapper.mapTo(noteDomain)
                consumer(Msg.Inner.FetchedPassedNoteResult(args.first, note))
            }

        }.onFailure {
            consumer(Msg.Inner.FetchedPassedNoteResult(args.first, NoteUi.Default))
        }
    }

    private suspend fun saveOrUpdateNote(note: NoteUi) {
        val currentRawTime = LocalDateTime.now()
        val noteDomain = mapper.mapFrom(note)

        interactor.let {
            if (note.isDefaultId())
                it.add(noteDomain.copy(dateCreationRaw = currentRawTime))
            else
                it.update(
                    noteDomain.copy(
                        dateCreationRaw = note.dateCreationRaw,
                        dateLastUpdateRaw = currentRawTime
                    )
                )
        }
    }
}