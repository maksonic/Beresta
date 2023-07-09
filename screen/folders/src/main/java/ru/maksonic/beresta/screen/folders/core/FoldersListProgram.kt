package ru.maksonic.beresta.screen.folders.core

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import ru.maksonic.beresta.elm.core.ElmProgram
import ru.maksonic.beresta.feature.folders_chips.api.domain.FolderDomain
import ru.maksonic.beresta.feature.folders_chips.api.domain.FoldersInteractor
import ru.maksonic.beresta.feature.folders_chips.api.domain.usecase.FetchFoldersListUseCase
import ru.maksonic.beresta.feature.folders_chips.api.ui.FolderToUiMapper
import ru.maksonic.beresta.feature.folders_chips.api.ui.FolderUi
import ru.maksonic.beresta.feature.folders_chips.api.ui.StickyItemsTitleFormatter
import ru.maksonic.beresta.feature.folders_chips.api.ui.sortStickyThenDescendingByPinTimeThenByDate
import ru.maksonic.beresta.feature.notes.api.domain.NoteDomain
import ru.maksonic.beresta.feature.notes.api.domain.NotesInteractor
import ru.maksonic.beresta.feature.notes.api.domain.usecase.FetchNotesUseCase
import ru.maksonic.beresta.feature.notes.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes.api.ui.NoteUiMapper
import ru.maksonic.beresta.language_engine.shell.LanguageEngineApi
import ru.maksonic.beresta.language_engine.shell.provider.AppLanguage
import ru.maksonic.beresta.navigation.router.AbstractNavigator
import ru.maksonic.beresta.navigation.router.Destination
import java.time.LocalDateTime

/**
 * @Author maksonic on 03.04.2023
 */
class FoldersListProgram(
    private val fetchFoldersUseCase: FetchFoldersListUseCase,
    private val fetchNotesUseCase: FetchNotesUseCase,
    private val foldersMapper: FolderToUiMapper,
    private val foldersInteractor: FoldersInteractor,
    private val notesInteractor: NotesInteractor,
    private val notesMapper: NoteUiMapper,
    private val navigator: AbstractNavigator,
    private val appLanguageEngineApi: LanguageEngineApi,
    private val stickyItemsTitleFormatter: StickyItemsTitleFormatter,
    private val currentSelectedFolderId: MutableState<Long>,
    private val ioDispatcher: CoroutineDispatcher
) : ElmProgram<Msg, Cmd> {

    private companion object {
        private const val SNACK_BAR_VISIBILITY_TIME = 5000L
    }

    private val _notes = MutableStateFlow(emptyList<NoteUi>())
    private val notes = _notes.asStateFlow()
    private val _removedNotes = MutableStateFlow(emptyList<NoteUi>())
    private val removedNotes = _notes.asStateFlow()

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchFoldersWithNotes -> fetchFoldersWithNotes(consumer)
            is Cmd.UpdatePinnedFoldersInCache -> updatePinnedFolders(cmd.pinned)
            is Cmd.ChangeNoteFolderId -> changeNotesFolderId(cmd.folderId)
            is Cmd.RemoveSelected -> moveSelectedFoldersToTrash(cmd.selectedFolders, consumer)
            is Cmd.UndoRemovedFolders -> undoRemovedFoldersFromTrash(cmd.folders, consumer)
        }
    }

    private suspend fun fetchFoldersWithNotes(consumer: (Msg) -> Unit) = withContext(ioDispatcher) {
        runCatching {
            val passedNotesIds = navigator.getListLongFromString(Destination.Folders.passedKey)
            val isMoveNotesState = passedNotesIds.isNotEmpty()

            combine(
                fetchFoldersUseCase(),
                fetchNotesUseCase(),
                appLanguageEngineApi.current
            ) { folders, notes, lang ->
                val foldersUi = folders.mapToUi(isMoveNotesState, lang, notes)

                saveFetchedNotesToIntermediateCache(notes)
                consumer(
                    Msg.Inner.FetchedFoldersData(
                        isMoveNotesToFolderState = isMoveNotesState,
                        currentSelectedFolderId = currentSelectedFolderId.value,
                        folders = FolderUi.Collection(foldersUi)
                    )
                )
            }.collect()

        }.onFailure { consumer(Msg.Inner.FetchedDataError(it.localizedMessage ?: "Error")) }
    }

    private fun saveFetchedNotesToIntermediateCache(notes: List<NoteDomain>) {
        val notesUi = notesMapper.mapListTo(notes)
        _notes.update { notesUi }
    }

    private suspend fun moveSelectedFoldersToTrash(
        selectedFolders: List<FolderUi>,
        consumer: (Msg) -> Unit
    ) {
        val foldersUi = selectedFolders.map { folder ->
            folder.copy(isMovedToTrash = true, dateMovedToTrashRaw = LocalDateTime.now())
        }
        val foldersDomain = foldersMapper.mapListFrom(foldersUi)

        foldersInteractor.updateAll(foldersDomain)
        updateNotesForRemovedFolders(foldersUi, isTrashed = true)
        delay(SNACK_BAR_VISIBILITY_TIME)
        consumer(Msg.Inner.HideRemovedFoldersSnackBar)
    }

    private suspend fun undoRemovedFoldersFromTrash(
        folders: List<FolderUi>,
        consumer: (Msg) -> Unit
    ) {
        val restoredFolders = folders.map { it.copy(dateMovedToTrashRaw = null) }

        foldersInteractor.updateAll(foldersMapper.mapListFrom(restoredFolders))
        updateNotesForRemovedFolders(restoredFolders, isTrashed = false)
        consumer(Msg.Inner.HideRemovedFoldersSnackBar)
    }

    private suspend fun updatePinnedFolders(folders: Set<FolderUi>) {
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
        foldersInteractor.updateAll(foldersDomain)
    }

    private suspend fun changeNotesFolderId(folderId: Long) {
        val passedNotesIds = navigator.getListLongFromString(Destination.Folders.passedKey)

        updateCurrentSelectedFolderState(folderId)

        fetchNotesUseCase().collect { notesDomain ->
            val passedNotes = notesDomain.filter { it.id in passedNotesIds }
            val updated = passedNotes.map { it.copy(folderId = folderId) }
            notesInteractor.updateAll(updated)
        }
    }

    private suspend fun updateNotesForRemovedFolders(folders: List<FolderUi>, isTrashed: Boolean) {
        _removedNotes.value = notes.value.filter { note ->
            folders.find { it.id == note.folderId }?.id == note.folderId
        }.map { it.copy(isMovedToTrash = isTrashed) }

        val notesDomain = notesMapper.mapListFrom(removedNotes.value)
        notesInteractor.updateAll(notesDomain)
    }

    private fun List<FolderDomain>.mapToUi(
        isMoveNotesState: Boolean,
        lang: AppLanguage,
        notes: List<NoteDomain>
    ): List<FolderUi> {
        val domain = if (isMoveNotesState) this.filter { !it.isStickyToStart } else this
        return foldersMapper.mapListTo(domain)
            .map { folder ->
                val count = notes.count { it.folderId == folder.id }
                folder.copy(
                    title = stickyItemsTitleFormatter.format(folder, lang),
                    isCurrent = folder.id == currentSelectedFolderId.value,
                    notesCount = if (folder.isStickyToStart) notes.count() else count
                )
            }.sortStickyThenDescendingByPinTimeThenByDate()
    }

    private fun updateCurrentSelectedFolderState(id: Long) {
        currentSelectedFolderId.value = id
    }
}