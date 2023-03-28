package ru.maksonic.beresta.screen.main.presentation.core

import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.edit_note.api.domain.RefactorNoteInteractor
import ru.maksonic.beresta.feature.notes_list.api.domain.usecase.FetchNotesUseCase
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUiMapper

/**
 * @Author maksonic on 21.02.2023
 */
class MainProgram(
    private val fetchingUseCase: FetchNotesUseCase,
    private val notesInteractor: RefactorNoteInteractor,
    private val mapper: NoteUiMapper,
) : ElmProgram<Msg, Cmd> {

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.RunFetchingNotesCollection -> fetchNotes(consumer)
            is Cmd.RemoveSelected -> moveSelectedToTrash(cmd.notes)
            is Cmd.UpdatePinnedNotesInCache -> updatePinned(cmd.pinned)
        }
    }

    private suspend fun fetchNotes(consumer: (Msg) -> Unit) {
        runCatching {
            fetchingUseCase().collect { notesDomain ->
                val notes = mapper.mapListTo(notesDomain)
                val sorted = notes.sortedWith(comparator = compareByDescending<NoteUi> { note ->
                    note.isPinned
                }.thenBy { it.id })
                consumer(Msg.Inner.FetchedNotesCollection(NoteUi.Collection(sorted)))
            }
        }.onFailure { fail ->
            //  val message =
            //       fail.localizedMessage ?: resourceProvider.getString(R.string.error_fetching_notes)
            consumer(Msg.Inner.FetchedError("Error"))
        }
    }


    private suspend fun updatePinned(notes: List<NoteUi>) {
        val notesDomain = mapper.mapListFrom(notes)
        notesInteractor.updateAll(notesDomain)
    }

    private suspend fun moveSelectedToTrash(notes: List<NoteUi>) {
        val remove = notes.filter { it.isMovedToTrash }
        val notesDomain = mapper.mapListFrom(remove)
        notesInteractor.updateAll(notesDomain)
    }
}