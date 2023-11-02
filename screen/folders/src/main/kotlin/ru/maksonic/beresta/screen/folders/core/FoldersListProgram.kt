package ru.maksonic.beresta.screen.folders.core

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import ru.maksonic.beresta.common.core.ext.RETRY_REQUEST_DELAY
import ru.maksonic.beresta.feature.folders_list.domain.FolderDomain
import ru.maksonic.beresta.feature.folders_list.domain.FoldersInteractor
import ru.maksonic.beresta.feature.folders_list.domain.usecase.FetchFoldersUseCase
import ru.maksonic.beresta.feature.folders_list.ui.api.FolderUi
import ru.maksonic.beresta.feature.folders_list.ui.api.FolderUiMapper
import ru.maksonic.beresta.feature.folders_list.ui.api.FoldersChipsRowUiApi
import ru.maksonic.beresta.feature.notes_list.domain.NoteDomain
import ru.maksonic.beresta.feature.notes_list.domain.list.NotesInteractor
import ru.maksonic.beresta.feature.notes_list.domain.list.usecase.FetchNotesUseCase
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUi
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUiMapper
import ru.maksonic.beresta.feature.notes_list.ui.api.findNotesByFoldersId
import ru.maksonic.beresta.feature.sorting_sheet.domain.usecase.FetchFoldersSortUseCase
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.FoldersSortUiMapper
import ru.maksonic.beresta.navigation.router.core.AbstractNavigator
import ru.maksonic.beresta.navigation.router.core.Destination
import ru.maksonic.beresta.platform.elm.core.ElmProgram
import java.time.LocalDateTime

/**
 * @Author maksonic on 03.04.2023
 */
class FoldersListProgram(
    private val fetchFoldersUseCase: FetchFoldersUseCase,
    private val fetchFoldersSortUseCase: FetchFoldersSortUseCase,
    private val foldersInteractor: FoldersInteractor,
    private val foldersMapper: FolderUiMapper,
    private val fetchNotesUseCase: FetchNotesUseCase,
    private val notesInteractor: NotesInteractor,
    private val notesMapper: NoteUiMapper,
    private val foldersSortUiMapper: FoldersSortUiMapper,
    private val navigator: AbstractNavigator,
    private val currentFolderStoreUiApi: FoldersChipsRowUiApi.CurrentSelectedFolderStore,
) : ElmProgram<Msg, Cmd> {
    private val _notes = MutableStateFlow(emptyList<NoteUi>())
    private val notes = _notes.asStateFlow()
    private val _removedNotes = MutableStateFlow(emptyList<NoteUi>())
    private val removedNotes = _removedNotes.asStateFlow()

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchFoldersWithNotes -> fetchFoldersWithNotes(consumer)
            is Cmd.RetryFetchData -> retryFetchFoldersWithNotes(consumer)
            is Cmd.UpdatePinnedFolders -> updatePinnedFolders(cmd.pinned)
            is Cmd.ChangeNoteFolderId -> changeNotesFolderId(cmd.folderId)
            is Cmd.RemoveSelected -> moveSelectedFoldersToTrash(cmd.removed)
            is Cmd.UndoRemovedFolders -> undoRemovedFromTrash(cmd.removed)
            is Cmd.UpdateCurrentSelectedFolder -> updateCurrentSelectedFolderState(cmd.id)
        }
    }

    private suspend fun fetchFoldersWithNotes(consumer: (Msg) -> Unit) = runCatching {
        combine(
            fetchFoldersUseCase(),
            fetchNotesUseCase(),
            fetchFoldersSortUseCase()
        ) { folders, notes, sort ->
            val passedNotesIds = navigator.getListLongFromString(Destination.Folders.passedKey)
            val isMoveNotesState = passedNotesIds.isNotEmpty()
            val foldersUi = folders.mapToUi(isMoveNotesState, notes)

            saveFetchedNotesToIntermediateCache(notes)

            consumer(
                Msg.Inner.FetchedDataSuccess(
                    isNotesMoving = isMoveNotesState,
                    folders = FolderUi.Collection(foldersUi),
                    sortState = foldersSortUiMapper.mapTo(sort)
                )
            )
        }.collect()

    }.onFailure {
        consumer(Msg.Inner.FetchedDataFail(it.localizedMessage ?: "Error"))
    }

    private suspend fun retryFetchFoldersWithNotes(consumer: (Msg) -> Unit) {
        delay(RETRY_REQUEST_DELAY)
        fetchFoldersWithNotes(consumer)
    }

    private fun saveFetchedNotesToIntermediateCache(notes: List<NoteDomain>) {
        val notesUi = notesMapper.mapListTo(notes)
        _notes.update { notesUi }
    }

    private suspend fun moveSelectedFoldersToTrash(folders: List<FolderUi>) {
        val foldersUi = folders.map { folder ->
            folder.copy(isMovedToTrash = true, dateMovedToTrashRaw = LocalDateTime.now())
        }
        updateFolderNotes(foldersUi.map { it.id }, true)

        val foldersDomain = foldersMapper.mapListFrom(foldersUi)
        foldersInteractor.updateList(foldersDomain)
    }

    private suspend fun undoRemovedFromTrash(folders: List<FolderUi>) {
        val restore = foldersMapper.mapListFrom(folders.map { it.copy(dateMovedToTrashRaw = null) })

        updateFolderNotes(restore.map { it.id }, false)

        foldersInteractor.updateList(restore)
    }

    private suspend fun updatePinnedFolders(folders: List<FolderUi>) {
        val currentDate = LocalDateTime.now()
        val isSelectedContainsUnpinned = folders.map { !it.isPinned }.contains(true)
        val selected = folders.map { note ->
            return@map note.copy(
                isPinned = isSelectedContainsUnpinned,
                pinTime = if (isSelectedContainsUnpinned) currentDate else null,
                dateCreationRaw = note.dateCreationRaw
            )
        }
        val foldersDomain = foldersMapper.mapListFrom(selected)
        foldersInteractor.updateList(foldersDomain)
    }

    private suspend fun changeNotesFolderId(folderId: Long) {
        val passedNotesIds = navigator.getListLongFromString(Destination.Folders.passedKey)

        updateCurrentSelectedFolderState(folderId)

        fetchNotesUseCase().collect { notesDomain ->
            val passedNotes = notesDomain.filter { it.id in passedNotesIds }
            val updated = passedNotes.map { it.copy(folderId = folderId) }
            notesInteractor.updateList(updated)
        }
    }

    private suspend fun updateFolderNotes(foldersIds: List<Long>, isTrashed: Boolean) {
        val removedUiNotes = notes.value.findNotesByFoldersId(foldersIds).map { it.trash() }
        val restoredUiNotes = removedNotes.value.map { it.restored() }
        val notes = notesMapper.mapListFrom(if (isTrashed) removedUiNotes else restoredUiNotes)

        _removedNotes.value = if (isTrashed) removedUiNotes else emptyList()
        notesInteractor.updateList(notes)
    }

    private fun List<FolderDomain>.mapToUi(
        isMoveNotesState: Boolean,
        notes: List<NoteDomain>
    ): List<FolderUi> {
        val folderDomain = if (isMoveNotesState) this.filter { !it.isStickyToStart } else this

        return foldersMapper.mapListTo(folderDomain).map { folder ->
            val count = notes.count { it.folderId == folder.id }
            folder.copy(notesCount = if (folder.isStickyToStart) notes.count() else count)
        }
    }

    private fun updateCurrentSelectedFolderState(id: Long) = currentFolderStoreUiApi.updateId(id)
}