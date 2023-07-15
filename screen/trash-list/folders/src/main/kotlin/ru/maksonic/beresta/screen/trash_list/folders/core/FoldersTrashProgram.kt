package ru.maksonic.beresta.screen.trash_list.folders.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import ru.maksonic.beresta.core.DateFormatter
import ru.maksonic.beresta.elm.core.ElmProgram
import ru.maksonic.beresta.feature.folders_chips.api.domain.FoldersInteractor
import ru.maksonic.beresta.feature.folders_chips.api.domain.usecase.FetchFoldersTrashListUseCase
import ru.maksonic.beresta.feature.folders_chips.api.ui.FolderUi
import ru.maksonic.beresta.feature.folders_chips.api.ui.FolderUiMapper
import ru.maksonic.beresta.feature.notes.api.domain.NoteDomain
import ru.maksonic.beresta.feature.notes.api.domain.NotesInteractor
import ru.maksonic.beresta.feature.notes.api.domain.usecase.FetchNotesByFolderTrashListUseCase
import ru.maksonic.beresta.feature.notes.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes.api.ui.NoteUiMapper
import ru.maksonic.beresta.language_engine.shell.LanguageEngineApi

/**
 * @Author maksonic on 30.05.2023
 */
class FoldersTrashProgram(
    private val fetchFoldersTrashListUseCase: FetchFoldersTrashListUseCase,
    private val fetchNotesByFolderTrashListUseCase: FetchNotesByFolderTrashListUseCase,
    private val notesFoldersInteractor: FoldersInteractor,
    private val notesInteractor: NotesInteractor,
    private val foldersMapper: FolderUiMapper,
    private val notesMapper: NoteUiMapper,
    private val appLanguageEngineApi: LanguageEngineApi,
    private val dateFormatter: DateFormatter,
    private val ioDispatcher: CoroutineDispatcher,
) : ElmProgram<Msg, Cmd> {

    private val _removedNotes = MutableStateFlow(emptyList<NoteUi>())
    private val removedNotes = _removedNotes.asStateFlow()

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchRemovedData -> fetchData(consumer)
            is Cmd.DeleteOrRestoreFolders -> {
                deleteOrRestoreFolders(cmd.isRestore, cmd.folders)
            }
        }
    }

    private suspend fun fetchData(consumer: (Msg) -> Unit) = withContext(ioDispatcher) {
        runCatching {
            combine(
                fetchFoldersTrashListUseCase(),
                fetchNotesByFolderTrashListUseCase(),
                appLanguageEngineApi.current
            ) { folders, notes, lang ->
                val foldersUi = foldersMapper.mapListTo(folders)
                    .map { folder ->
                        val count = notes.count { it.folderId == folder.id }
                        val dateMovedToTrash = folder.dateMovedToTrashRaw?.let {
                            dateFormatter.fetchFormattedUiDate(it, lang)
                        }

                        folder.copy(
                            dateMovedToTrash = dateMovedToTrash,
                            notesCount = if (folder.isStickyToStart) notes.count() else count
                        )
                    }.sortedByDescending { it.dateMovedToTrashRaw }

                saveFetchedNotesToIntermediateCache(notes)

                consumer(Msg.Inner.FetchedTrashDataResult(foldersUi))
            }.collect()

        }.onFailure {
            consumer(Msg.Inner.FetchedTrashDataResult(emptyList()))
        }
    }

    private fun saveFetchedNotesToIntermediateCache(notes: List<NoteDomain>) {
        val notesUi = notesMapper.mapListTo(notes)
        _removedNotes.update { notesUi }
    }

    private suspend fun deleteOrRestoreFolders(
        isRestore: Boolean,
        folders: List<FolderUi>
    ) = withContext(ioDispatcher) {
        runCatching {
            val foldersDomain = foldersMapper.mapListFrom(folders)
            val restoredFolders = foldersDomain.map { folder ->
                folder.copy(isMovedToTrash = false, dateMovedToTrash = null)
            }
            val restoredNotes = notesMapper.mapListFrom(removedNotes.value)
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
}