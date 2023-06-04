package ru.maksonic.beresta.screen.trash_list.notes.core

import kotlinx.coroutines.flow.collectLatest
import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.notes.list.api.domain.NotesInteractor
import ru.maksonic.beresta.feature.notes.list.api.domain.usecase.FetchNotesWithoutFolderTrashListUseCase
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteUiMapper
import ru.maksonic.beresta.language_engine.shell.LanguageEngineApi

/**
 * @Author maksonic on 30.05.2023
 */
class NotesTrashProgram(
    private val fetchRemovedNotes: FetchNotesWithoutFolderTrashListUseCase,
    private val notesInteractor: NotesInteractor,
    private val notesMapper: NoteUiMapper,
    private val appLanguageEngineApi: LanguageEngineApi,

    ) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchRemovedData -> fetchData(consumer)
            is Cmd.DeleteOrRestoreNotes -> deleteOrRestoreNotes(cmd.isRestore, cmd.notes)
            is Cmd.ReadLanguageFromDataStore -> readLanguageFromDatastore(consumer)
        }
    }

    private suspend fun fetchData(consumer: (Msg) -> Unit) {
        runCatching {
            fetchRemovedNotes().collect { notesDomain ->
                val notes = notesMapper.mapListTo(notesDomain)
                consumer(Msg.Inner.FetchedRemovedNotesResult(notes))
            }
        }
    }

    private suspend fun deleteOrRestoreNotes(isRestore: Boolean, notes: List<NoteUi>) {
        runCatching {
            val notesDomain = notesMapper.mapListFrom(notes)
            notesInteractor.also {
                if (isRestore) {
                    val restored = notesDomain.map { note ->
                        note.copy(
                            isMovedToTrash = false,
                            dateMovedToTrash = null
                        )
                    }
                    it.updateAll(restored)
                } else
                    it.deleteAll(notesDomain)
            }
        }
    }

    private suspend fun readLanguageFromDatastore(consumer: (Msg) -> Unit) {
        appLanguageEngineApi.current.collectLatest { savedAppLanguage ->
            consumer(Msg.Inner.FetchedCurrentAppLang(savedAppLanguage))
        }
    }
}
