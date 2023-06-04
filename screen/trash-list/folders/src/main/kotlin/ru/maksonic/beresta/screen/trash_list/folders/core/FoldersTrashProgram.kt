package ru.maksonic.beresta.screen.trash_list.folders.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.withContext
import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.notes.folders.api.domain.NotesFoldersInteractor
import ru.maksonic.beresta.feature.notes.folders.api.domain.usecase.FetchFoldersTrashListUseCase
import ru.maksonic.beresta.feature.notes.folders.api.ui.NoteFolderToUiMapper
import ru.maksonic.beresta.feature.notes.folders.api.ui.NoteFolderUi
import ru.maksonic.beresta.feature.notes.list.api.domain.NotesInteractor
import ru.maksonic.beresta.feature.notes.list.api.domain.usecase.FetchNotesByFolderTrashListUseCase
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteUiMapper
import ru.maksonic.beresta.language_engine.shell.LanguageEngineApi

/**
 * @Author maksonic on 30.05.2023
 */
class FoldersTrashProgram(
    private val fetchFoldersTrashListUseCase: FetchFoldersTrashListUseCase,
    private val fetchNotesByFolderTrashListUseCase: FetchNotesByFolderTrashListUseCase,
    private val notesFoldersInteractor: NotesFoldersInteractor,
    private val notesInteractor: NotesInteractor,
    private val foldersMapper: NoteFolderToUiMapper,
    private val notesMapper: NoteUiMapper,
    private val appLanguageEngineApi: LanguageEngineApi,
    private val ioDispatcher: CoroutineDispatcher,
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.ReadLanguageFromDataStore -> readLanguageFromDatastore(consumer)
            is Cmd.FetchRemovedData -> fetchData(consumer)
            is Cmd.DeleteOrRestoreFolders -> {
                deleteOrRestoreFolders(cmd.isRestore, cmd.folders, cmd.notes)
            }
        }
    }

    private suspend fun fetchData(consumer: (Msg) -> Unit) = withContext(ioDispatcher) {
        runCatching {
            combine(
                fetchFoldersTrashListUseCase(),
                fetchNotesByFolderTrashListUseCase()
            ) { folders, notes ->
                val foldersUi = foldersMapper.mapListTo(folders)
                    .map { folder ->
                        val count = notes.count { it.folderId == folder.id }
                        folder.copy(
                            notesCount = if (folder.isStickyToStart) notes.count() else count
                        )
                    }

                val notesUi = notesMapper.mapListTo(notes)

                consumer(Msg.Inner.FetchedTrashDataResult(foldersUi, notesUi))
            }.collect()

        }.onFailure {
            consumer(Msg.Inner.FetchedTrashDataResult(emptyList(), emptyList()))
        }
    }

    private suspend fun deleteOrRestoreFolders(
        isRestore: Boolean,
        folders: List<NoteFolderUi>,
        notes: List<NoteUi>
    ) = withContext(ioDispatcher) {
        runCatching {
            val foldersDomain = foldersMapper.mapListFrom(folders)
            val restoredFolders = foldersDomain.map { folder ->
                folder.copy(isMovedToTrash = false, dateMovedToTrash = null)
            }
            val restoredNotes = notesMapper.mapListFrom(notes)
                .filter { note -> restoredFolders.any { folder -> note.folderId == folder.id } }
                .map { note -> note.copy(isMovedToTrash = false, dateMovedToTrash = null) }

            notesFoldersInteractor.also {
                if (isRestore) it.updateAll(restoredFolders) else it.deleteAll(foldersDomain)
            }

            notesInteractor.also {
                if (isRestore) it.updateAll(restoredNotes) else it.deleteAll(restoredNotes)
            }
        }
    }

    private suspend fun readLanguageFromDatastore(consumer: (Msg) -> Unit) {
        appLanguageEngineApi.current.collectLatest { savedAppLanguage ->
            consumer(Msg.Inner.FetchedCurrentAppLang(savedAppLanguage))
        }
    }
}