package ru.maksonic.beresta.feature.notes_list.ui.core

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import ru.maksonic.beresta.core.ResourceProvider
import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.notes_list.api.NoteUi
import ru.maksonic.beresta.feature.notes_list.api.NoteUiMapper
import ru.maksonic.beresta.feature.notes_list.domain.FetchNotesUseCase
import ru.maksonic.beresta.feature.notes_list.ui.R
import java.util.*

/**
 * @Author maksonic on 25.12.2022
 */
class NotesListProgram(
    private val notesUseCase: FetchNotesUseCase,
    private val mapper: NoteUiMapper,
    private val resourceProvider: ResourceProvider
) : ElmProgram<Feature.Msg, Feature.Cmd> {

    override suspend fun executeProgram(cmd: Feature.Cmd, consumer: (Feature.Msg) -> Unit) {
        when (cmd) {
            is Feature.Cmd.FetchData -> fetchNotes(consumer)
            else -> {}
        }
    }

    private suspend fun fetchNotes(consumer: (Feature.Msg) -> Unit) {
        runCatching {
            notesUseCase().collect { notesDomain ->
                val notes = mapper.mapListTo(notesDomain)
                consumer(Feature.Msg.Inner.FetchingSuccess(notes))
            }
        }.onFailure { fail ->
            val message =
                fail.localizedMessage ?: resourceProvider.getString(R.string.error_fetching_notes)
            consumer(Feature.Msg.Inner.FetchingError(message))
        }
    }
}