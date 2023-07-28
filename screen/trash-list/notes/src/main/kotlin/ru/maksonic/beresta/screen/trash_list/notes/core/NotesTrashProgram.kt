package ru.maksonic.beresta.screen.trash_list.notes.core

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import ru.maksonic.beresta.core.DateFormatter
import ru.maksonic.beresta.elm.core.ElmProgram
import ru.maksonic.beresta.feature.notes.api.domain.NotesInteractor
import ru.maksonic.beresta.feature.notes.api.domain.usecase.FetchNotesWithoutFolderTrashListUseCase
import ru.maksonic.beresta.feature.notes.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes.api.ui.NoteUiMapper
import ru.maksonic.beresta.language_engine.shell.LanguageEngineApi

/**
 * @Author maksonic on 30.05.2023
 */
class NotesTrashProgram(
    private val fetchRemovedNotes: FetchNotesWithoutFolderTrashListUseCase,
    private val notesInteractor: NotesInteractor,
    private val notesMapper: NoteUiMapper,
    private val appLanguageEngineApi: LanguageEngineApi,
    private val dateFormatter: DateFormatter,

    ) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchRemovedData -> fetchData(consumer)
            is Cmd.DeleteOrRestoreNotes -> deleteOrRestoreNotes(cmd.isRestore, cmd.notes)
        }
    }

    private suspend fun fetchData(consumer: (Msg) -> Unit) {
        runCatching {
            combine(fetchRemovedNotes(), appLanguageEngineApi.current) { notesDomain, lang ->
                val notes = notesMapper.mapListTo(notesDomain).map { note ->
                    val dateCreation = dateFormatter.fetchFormattedUiDate(
                        note.dateCreationRaw, lang
                    )
                    val dateMovedToTrash = note.dateMovedToTrashRaw?.let { date ->
                        dateFormatter.fetchFormattedUiDate(date, lang)
                    } ?: ""

                    note.copy(
                        dateCreation = dateCreation, dateMovedToTrash = dateMovedToTrash
                    )
                }.sortedByDescending { it.dateMovedToTrashRaw }

                consumer(Msg.Inner.FetchedRemovedNotesResult(notes))
            }.collect()

        }.onFailure {
            consumer(Msg.Inner.FetchedRemovedNotesResult(emptyList()))
        }
    }

    private suspend fun deleteOrRestoreNotes(isRestore: Boolean, notes: List<NoteUi>) {
        runCatching {
            val notesDomain = notesMapper.mapListFrom(notes)
            notesInteractor.also {
                if (isRestore) {
                    val restored = notesDomain.map { note ->
                        note.copy(isMovedToTrash = false, dateMovedToTrash = null)
                    }
                    it.updateList(restored)
                } else
                    it.deleteList(notesDomain)
            }
        }
    }
}
