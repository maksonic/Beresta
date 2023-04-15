package ru.maksonic.beresta.feature.edit_note.core.screen.core

import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.edit_note.api.domain.RefactorNoteInteractor
import ru.maksonic.beresta.feature.notes_list.api.domain.usecase.FetchNoteByIdUseCase
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUiMapper
import ru.maksonic.beresta.navigation.router.Destination
import ru.maksonic.beresta.navigation.router.navigator.AppNavigator

/**
 * @Author maksonic on 25.03.2023
 */
class EditNoteProgram(
    private val interactor: RefactorNoteInteractor,
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
        val id = navigator.getLongArgument(Destination.EditNote.passedKey)

        runCatching {
            fetchNoteByIdUseCase(id).collect { noteDomain ->
                val note = mapper.mapTo(noteDomain)
                consumer(Msg.Inner.FetchedPassedNoteResult(note))
            }
        }.onFailure {
            consumer(Msg.Inner.FetchedPassedNoteResult(NoteUi.Default))
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