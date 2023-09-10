package ru.maksonic.beresta.screen.folders.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import ru.maksonic.beresta.elm.core.ElmProgram
import ru.maksonic.beresta.feature.folders_chips.api.FoldersApi
import ru.maksonic.beresta.feature.folders_chips.api.domain.FolderDomain
import ru.maksonic.beresta.feature.folders_chips.api.domain.FoldersInteractor
import ru.maksonic.beresta.feature.folders_chips.api.ui.FolderUi
import ru.maksonic.beresta.feature.folders_chips.api.ui.FolderUiMapper
import ru.maksonic.beresta.feature.folders_chips.api.ui.StickyFoldersTitleFormatter
import ru.maksonic.beresta.feature.notes.api.domain.NoteDomain
import ru.maksonic.beresta.feature.notes.api.domain.NotesInteractor
import ru.maksonic.beresta.feature.notes.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes.api.ui.NoteUiMapper
import ru.maksonic.beresta.feature.notes.api.ui.findNotesByFoldersId
import ru.maksonic.beresta.language_engine.shell.LanguageEngineApi
import ru.maksonic.beresta.language_engine.shell.provider.AppLanguage
import ru.maksonic.beresta.navigation.router.AbstractNavigator
import ru.maksonic.beresta.navigation.router.Destination
import java.time.LocalDateTime

/**
 * @Author maksonic on 03.04.2023
 */
class FoldersListProgram(
    private val foldersMapper: FolderUiMapper,
    private val foldersInteractor: FoldersInteractor,
    private val notesInteractor: NotesInteractor,
    private val notesMapper: NoteUiMapper,
    private val navigator: AbstractNavigator,
    private val appLanguageEngineApi: LanguageEngineApi,
    private val stickyFoldersTitleFormatter: StickyFoldersTitleFormatter,
    private val chipsRowApi: FoldersApi.Ui.ChipsRow,
    private val ioDispatcher: CoroutineDispatcher
) : ElmProgram<Msg, Cmd> {

    private companion object {
        private const val RETRY_REQUEST_DELAY = 3000L
    }

    private val _notes = MutableStateFlow(emptyList<NoteUi>())
    private val notes = _notes.asStateFlow()
    private val _removedNotes = MutableStateFlow(emptyList<NoteUi>())
    private val removedNotes = _removedNotes.asStateFlow()

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchFoldersWithNotes -> fetchFoldersWithNotes(consumer)
            is Cmd.RetryFetchFoldersWithNotes -> retryFetchFoldersWithNotes(consumer)
            is Cmd.UpdatePinnedFoldersInCache -> updatePinnedFolders(cmd.pinned)
            is Cmd.ChangeNoteFolderId -> changeNotesFolderId(cmd.folderId)
            is Cmd.RemoveSelected -> moveSelectedFoldersToTrash(cmd.removed)
            is Cmd.UndoRemovedFolders -> undoRemovedFoldersFromTrash(cmd.removed)
            is Cmd.UpdateCurrentSelectedFolder -> updateCurrentSelectedFolderState(cmd.id)
        }
    }

    private suspend fun fetchFoldersWithNotes(consumer: (Msg) -> Unit) = withContext(ioDispatcher) {
        runCatching {
            combine(
                foldersInteractor.fetchList(),
                notesInteractor.fetchList(),
                appLanguageEngineApi.current
            ) { folders, notes, lang ->
                val passedNotesIds = navigator.getListLongFromString(Destination.Folders.passedKey)
                val isMoveNotesState = passedNotesIds.isNotEmpty()
                val foldersUi = folders.mapToUi(isMoveNotesState, lang, notes)

                saveFetchedNotesToIntermediateCache(notes)

                consumer(
                    Msg.Inner.FetchedFoldersData(
                        isMoveNotesToFolderState = isMoveNotesState,
                        folders = FolderUi.Collection(foldersUi)
                    )
                )
            }.collect()

        }.onFailure { consumer(Msg.Inner.FetchedDataError(it.localizedMessage ?: "Error")) }
    }

    private suspend fun retryFetchFoldersWithNotes(consumer: (Msg) -> Unit) {
        delay(RETRY_REQUEST_DELAY)
        fetchFoldersWithNotes(consumer)
    }

    private fun saveFetchedNotesToIntermediateCache(notes: List<NoteDomain>) {
        val notesUi = notesMapper.mapListTo(notes)
        _notes.update { notesUi }
    }

    private suspend fun moveSelectedFoldersToTrash(selectedFolders: List<FolderUi>) {
        val removedUiFolders = selectedFolders.map { it.trash() }
        val removedDomainFolders = foldersMapper.mapListFrom(removedUiFolders)

        updateFolderNotes(removedDomainFolders.map { it.id }, true)
        foldersInteractor.updateList(removedDomainFolders)
    }

    private suspend fun undoRemovedFoldersFromTrash(folders: List<FolderUi>) {
        val restoredUiFolders = folders.map { it.restored() }
        val restoredDomainFolders = foldersMapper.mapListFrom(restoredUiFolders)

        updateFolderNotes(restoredUiFolders.map { it.id }, false)

        foldersInteractor.updateList(restoredDomainFolders)
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
        foldersInteractor.updateList(foldersDomain)
    }

    private suspend fun changeNotesFolderId(folderId: Long) {
        val passedNotesIds = navigator.getListLongFromString(Destination.Folders.passedKey)

        updateCurrentSelectedFolderState(folderId)

        notesInteractor.fetchList().collect { notesDomain ->
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
        lang: AppLanguage,
        notes: List<NoteDomain>
    ): List<FolderUi> {
        val folderDomain = if (isMoveNotesState) this.filter { !it.isStickyToStart } else this

        return foldersMapper.mapListTo(folderDomain)
            .map { folder ->
                val count = notes.count { it.folderId == folder.id }
                folder.copy(
                    title = stickyFoldersTitleFormatter.format(folder, lang),
                    notesCount = if (folder.isStickyToStart) notes.count() else count
                )
            }
    }

    private fun updateCurrentSelectedFolderState(id: Long) { chipsRowApi.updateCurrent(id) }
}