package ru.maksonic.beresta.feature.notes.folders.core.screen.core

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.notes.folders.api.domain.FetchFoldersListUseCase
import ru.maksonic.beresta.feature.notes.folders.api.domain.NotesFoldersInteractor
import ru.maksonic.beresta.feature.notes.folders.api.ui.NoteFolderToUiMapper
import ru.maksonic.beresta.feature.notes.folders.api.ui.NoteFolderUi
import ru.maksonic.beresta.feature.notes.folders.api.ui.sortStickyThenDescendingByPinTimeThenByDate
import ru.maksonic.beresta.feature.notes.list.api.domain.RefactorNoteInteractor
import ru.maksonic.beresta.feature.notes.list.api.domain.usecase.FetchNotesUseCase
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteUiMapper
import ru.maksonic.beresta.navigation.router.Destination
import ru.maksonic.beresta.navigation.router.navigator.AppNavigator
import java.time.LocalDateTime

/**
 * @Author maksonic on 03.04.2023
 */
class FoldersListProgram(
    private val fetchFoldersUseCase: FetchFoldersListUseCase,
    private val fetchNotesUseCase: FetchNotesUseCase,
    private val foldersMapper: NoteFolderToUiMapper,
    private val foldersInteractor: NotesFoldersInteractor,
    private val notesInteractor: RefactorNoteInteractor,
    private val notesMapper: NoteUiMapper,
    private val navigator: AppNavigator
) : ElmProgram<Msg, Cmd> {

    private companion object {
        private const val SNACK_BAR_VISIBILITY_TIME = 5000L
    }

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchFolders -> fetchFolders(consumer)
            is Cmd.FetchPassedFromMainScreenArgs -> fetchedPassedNotesMoveState(consumer)
            is Cmd.RemoveSelected -> moveSelectedNotesToTrash(cmd.folders, consumer)
            is Cmd.UndoRemoveNotes -> undoRemovedFromTrash(cmd.folders, consumer)
            is Cmd.UpdatePinnedFoldersInCache -> updatePinnedFolders(cmd.pinned)
            is Cmd.ChangeNoteFolderId -> changeNotesFolderId(cmd.folderId, cmd.notes)
        }
    }

    private suspend fun fetchFolders(consumer: (Msg) -> Unit) {

        runCatching {
            combine(fetchFoldersUseCase(), fetchNotesUseCase()) { folders, notes ->
                val result =
                    foldersMapper.mapListTo(folders).sortStickyThenDescendingByPinTimeThenByDate()
                val foldersUi = result.map { folder ->
                    val count = notes.count { it.folderId == folder.id }
                    val notesCount = if (folder.isStickyToStart) notes.count() else count
                    folder.copy(notesCount = notesCount)
                }
                consumer(Msg.Inner.FetchedFoldersResult(foldersUi))
            }.collect()

        }.onFailure {
            consumer(Msg.Inner.FetchedFoldersResult(emptyList()))
        }
    }

    /* private suspend fun fetchFolders(consumer: (Msg) -> Unit) {

        runCatching {
            fetchFoldersUseCase().collect { data ->
                val folders =
                    foldersMapper.mapListTo(data).sortStickyThenDescendingByPinTimeThenByDate()
                consumer(Msg.Inner.FetchedFoldersResult(folders))
            }
        }.onFailure {
            consumer(Msg.Inner.FetchedFoldersResult(emptyList()))
        }
    }*/

    private fun fetchedPassedNotesMoveState(consumer: (Msg) -> Unit) {
        val keyList = Destination.NotesFoldersList.passedListKey
        val isMoveNotesState = navigator.getBoolean(keyList.first())
        val currentSelectedFolderId = navigator.getLong(keyList.last())
        consumer(Msg.Inner.FetchedPassedArgsFromMain(isMoveNotesState, currentSelectedFolderId))
    }

    private suspend fun moveSelectedNotesToTrash(
        folders: List<NoteFolderUi>,
        consumer: (Msg) -> Unit
    ) {
        consumer(Msg.Inner.ShowRemovedNotesSnackBar)
        val notesDomain = foldersMapper.mapListFrom(folders)
        foldersInteractor.updateAll(notesDomain)
        delay(SNACK_BAR_VISIBILITY_TIME)
        consumer(Msg.Inner.HideRemovedNotesSnackBar)
    }

    private suspend fun undoRemovedFromTrash(folders: List<NoteFolderUi>, consumer: (Msg) -> Unit) {
        val notesDomain = foldersMapper.mapListFrom(folders)
        foldersInteractor.updateAll(notesDomain)
        consumer(Msg.Inner.HideRemovedNotesSnackBar)
    }

    private suspend fun updatePinnedFolders(folders: Set<NoteFolderUi>) {
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

    private suspend fun changeNotesFolderId(folderId: Long, notes: List<NoteUi>) {
       // val folderId = if (folderId == 2L) 0L else folderId
        val updated = notes.map { it.copy(folderId = folderId) }
        val notesDomain = notesMapper.mapListFrom(updated)
        notesInteractor.updateAll(notesDomain)
    }
}
