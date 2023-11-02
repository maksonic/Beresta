package ru.maksonic.beresta.screen.trash.folders.core

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import ru.maksonic.beresta.common.core.ext.RETRY_REQUEST_DELAY
import ru.maksonic.beresta.feature.folders_list.domain.FoldersInteractor
import ru.maksonic.beresta.feature.folders_list.domain.usecase.FetchFoldersTrashListUseCase
import ru.maksonic.beresta.feature.folders_list.ui.api.FolderUi
import ru.maksonic.beresta.feature.folders_list.ui.api.FolderUiMapper
import ru.maksonic.beresta.feature.notes_list.domain.NoteDomain
import ru.maksonic.beresta.feature.notes_list.domain.list.NotesInteractor
import ru.maksonic.beresta.feature.notes_list.domain.list.usecase.FetchNotesByFolderTrashListUseCase
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUi
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUiMapper
import ru.maksonic.beresta.platform.elm.core.ElmProgram

/**
 * @Author maksonic on 30.05.2023
 */
class FoldersTrashProgram(
    private val fetchFoldersTrashListUseCase: FetchFoldersTrashListUseCase,
    private val fetchNotesByFolderTrashListUseCase: FetchNotesByFolderTrashListUseCase,
    private val notesFoldersInteractor: FoldersInteractor,
    private val notesInteractor: NotesInteractor,
    private val foldersMapper: FolderUiMapper,
    private val notesMapper: NoteUiMapper
) : ElmProgram<Msg, Cmd> {

    private val _removedNotes = MutableStateFlow(emptyList<NoteUi>())
    private val removedNotes = _removedNotes.asStateFlow()

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchData -> fetchData(consumer)
            is Cmd.RetryFetchData -> retryFetchData(consumer)
            is Cmd.RestoreFolders -> restoreFolders(cmd.folders)
            is Cmd.DeleteFolders -> deleteFolders(cmd.folders)
        }
    }

    private suspend fun fetchData(consumer: (Msg) -> Unit) = runCatching {
        combine(
            fetchFoldersTrashListUseCase(),
            fetchNotesByFolderTrashListUseCase()
        ) { folders, notes ->
            val foldersUi = foldersMapper.mapListTo(folders)
                .map { folder ->
                    val count = notes.count { it.folderId == folder.id }

                    folder.copy(notesCount = if (folder.isStickyToStart) notes.count() else count)
                }.sortedByDescending { it.dateMovedToTrashRaw }

            saveFetchedNotesToIntermediateCache(notes)

            consumer(Msg.Inner.FetchedDataResult(foldersUi))
        }.collect()

    }.onFailure {
        consumer(Msg.Inner.FetchedDataFail(it.localizedMessage ?: "Error"))
    }

    private suspend fun retryFetchData(consumer: (Msg) -> Unit) {
        delay(RETRY_REQUEST_DELAY)
        fetchData(consumer)
    }

    private fun saveFetchedNotesToIntermediateCache(notes: List<NoteDomain>) {
        val notesUi = notesMapper.mapListTo(notes)
        _removedNotes.update { notesUi }
    }

    private suspend fun restoreFolders(folders: List<FolderUi>) = runCatching {
        val restoredFolders = foldersMapper.mapListFrom(folders).map { folder ->
            folder.copy(isMovedToTrash = false, dateMovedToTrash = null)
        }

        val restoredNotes = notesMapper.mapListFrom(removedNotes.value)
            .filter { note -> restoredFolders.any { folder -> note.folderId == folder.id } }
            .map { note -> note.copy(isMovedToTrash = false, dateMovedToTrashRaw = null) }

        notesFoldersInteractor.updateList(restoredFolders)
        notesInteractor.updateList(restoredNotes)
    }

    private suspend fun deleteFolders(folders: List<FolderUi>) = runCatching {
        val deleteFolders = foldersMapper.mapListFrom(folders)
        val deleteNotes = notesMapper.mapListFrom(removedNotes.value)
            .filter { note -> deleteFolders.any { folder -> note.folderId == folder.id } }

        notesFoldersInteractor.deleteList(deleteFolders)
        notesInteractor.deleteList(deleteNotes)
    }
}