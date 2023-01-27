package ru.maksonic.beresta.feature.notes_list.ui.core

import ru.maksonic.beresta.core.ResourceProvider
import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.notes_list.api.NoteUi
import ru.maksonic.beresta.feature.notes_list.api.NoteUiMapper
import ru.maksonic.beresta.feature.notes_list.domain.FetchNotesUseCase
import ru.maksonic.beresta.feature.notes_list.ui.R

/**
 * @Author maksonic on 25.12.2022
 */
class NotesListProgram(
    private val fetchingUseCase: FetchNotesUseCase,
    private val mapper: NoteUiMapper,
    private val resourceProvider: ResourceProvider
) : ElmProgram<Msg, Cmd> {

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchData -> fetchNotes(consumer)
            is Cmd.MoveSelectedToTrash -> moveSelectedToTrash(cmd.notes, consumer)
            else -> {}
        }
    }

    private suspend fun fetchNotes(consumer: (Msg) -> Unit) {
        runCatching {
            fetchingUseCase().collect { notesDomain ->
                val notes = mapper.mapListTo(notesDomain)
                consumer(Msg.Inner.FetchingSuccess(notes))
            }
        }.onFailure { fail ->
            val message =
                fail.localizedMessage ?: resourceProvider.getString(R.string.error_fetching_notes)
            consumer(Msg.Inner.FetchingError(message))
        }
    }

    private fun moveSelectedToTrash(notes: List<NoteUi>, consumer: (Msg) -> Unit) {

    }
}