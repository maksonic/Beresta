package ru.maksonic.beresta.screen.main.presentation.core

import ru.maksonic.beresta.core.ResourceProvider
import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.notes_list.api.domain.usecase.FetchNotesUseCase
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUiMapper
import ru.maksonic.beresta.feature.notes_list.api.ui.NotesCollection

/**
 * @Author maksonic on 21.02.2023
 */
class MainProgram(
    private val fetchingUseCase: FetchNotesUseCase,
    private val mapper: NoteUiMapper,
) : ElmProgram<Msg, Cmd> {

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.RunFetchingNotesCollection -> fetchNotes(consumer)
        }
    }

    private suspend fun fetchNotes(consumer: (Msg) -> Unit) {
        runCatching {
            fetchingUseCase().collect { notesDomain ->
                val notes = mapper.mapListTo(notesDomain)
                val sorted = notes.sortedWith(comparator = compareByDescending<NoteUi> { note ->
                    note.isPinned
                }.thenBy { it.id })
                consumer(Msg.Inner.FetchedNotesCollection(NotesCollection(sorted)))
            }
        }.onFailure { fail ->
          //  val message =
         //       fail.localizedMessage ?: resourceProvider.getString(R.string.error_fetching_notes)
            consumer(Msg.Inner.FetchedError("Error"))
        }
    }
}