package ru.maksonic.beresta.feature.edit_note.core

import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.notes.list.api.domain.NotesInteractor
import ru.maksonic.beresta.feature.notes.list.api.domain.usecase.FetchNoteByIdUseCase
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteUiMapper
import ru.maksonic.beresta.feature.notes.list.api.ui.isDefaultId
import ru.maksonic.beresta.navigation.router.Destination
import ru.maksonic.beresta.navigation.router.navigator.AppNavigator
import java.time.LocalDateTime

/**
 * @Author maksonic on 26.04.2023
 */
class EditNoteProgram(
    private val interactor: NotesInteractor,
    private val fetchNoteByIdUseCase: FetchNoteByIdUseCase,
    private val mapper: NoteUiMapper,
    private val navigator: AppNavigator
) : ElmProgram<Msg, Cmd> {

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchNote -> fetchNote(consumer)
            is Cmd.SaveNote -> saveOrUpdateNote(cmd.note)
        }
    }

    private suspend fun fetchNote(consumer: (Msg) -> Unit) {
        val id = navigator.getLong(Destination.EditNote.passedKey)

        runCatching {
            fetchNoteByIdUseCase(id).collect { noteDomain ->
                val note = mapper.mapTo(noteDomain)
                consumer(Msg.Inner.FetchedPassedNoteResult(note))
            }
        }.onFailure {
            consumer(Msg.Inner.FetchedPassedNoteResult(NoteUi.Default))
        }
    }

    private suspend fun saveOrUpdateNote(note: NoteUi) {
        val noteDomain = mapper.mapFrom(note)
        interactor.let {
            if (note.isDefaultId())
                it.addNote(noteDomain.copy(dateCreation = LocalDateTime.now()))
            else
                it.updateNote(noteDomain.copy(dateCreation = note.dateCreationRaw))
        }
    }
}