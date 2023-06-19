package ru.maksonic.beresta.feature.search_bar.core

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.notes.list.api.domain.DateFormatter
import ru.maksonic.beresta.feature.notes.list.api.domain.usecase.FetchNotesUseCase
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteUiMapper
import ru.maksonic.beresta.language_engine.shell.LanguageEngineApi

/**
 * @Author maksonic on 08.06.2023
 */
class SearchProgram(
    private val fetchNotesUseCase: FetchNotesUseCase,
    private val mapper: NoteUiMapper,
    private val appLanguageEngineApi: LanguageEngineApi,
    private val dateFormatter: DateFormatter
) :
    ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchNotesList -> fetchNotesList(consumer)
        }
    }

    private suspend fun fetchNotesList(consumer: (Msg) -> Unit) {
        runCatching {
            combine(fetchNotesUseCase(), appLanguageEngineApi.current) { notesDomain, lang ->
                val notes = mapper.mapListTo(notesDomain).map { note ->
                    note.copy(
                        dateCreation = dateFormatter.fetchFormattedUiDate(
                            note.dateCreationRaw, lang
                        )
                    )
                }
                consumer(Msg.Inner.FetchedResultData(NoteUi.Collection(notes)))
            }.collect()

        }.onFailure { throwable ->
            consumer(Msg.Inner.FetchedResultError(throwable.localizedMessage.toString()))
        }
    }
}