package ru.maksonic.beresta.feature.edit_note.ui.core.core

import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.edit_note.domain.EditNoteInteractor
import ru.maksonic.beresta.feature.notes_list.api.NoteUi
import ru.maksonic.beresta.feature.notes_list.api.NoteUiMapper
import ru.maksonic.beresta.feature.notes_list.domain.FetchNoteByIdUseCase
import ru.maksonic.beresta.navigation.router.navigator.AppNavigator

/**
 * @Author maksonic on 26.01.2023
 */
class EditNoteProgram(
    private val interactor: EditNoteInteractor,
    private val fetchNoteByIdUseCase: FetchNoteByIdUseCase,
    private val mapper: NoteUiMapper,
    private val navigator: AppNavigator
) : ElmProgram<Msg, Cmd> {

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchNote -> fetchNote(consumer)
            is Cmd.SaveNote -> createNewNote(cmd.note)
            is Cmd.UpdateCurrentNote -> updateCurrentNote(cmd.note)
        }
    }

    private suspend fun fetchNote(consumer: (Msg) -> Unit) {
        val id = navigator.getLongArgument("noteId")

        runCatching {
            fetchNoteByIdUseCase(id).collect { noteDomain ->
                val note = mapper.mapTo(noteDomain)
                consumer(Msg.Inner.FetchingNoteResult(note))
            }
        }.onFailure {
            consumer(Msg.Inner.FetchingNoteResult(NoteUi()))
        }
    }

    private suspend fun createNewNote(note: NoteUi) {
        val noteDomain = mapper.mapFrom(note)
        interactor.addNote(noteDomain)
    }
    private suspend fun updateCurrentNote(note: NoteUi) {
        val noteDomain = mapper.mapFrom(note)
        interactor.updateNote(noteDomain)
    }
}