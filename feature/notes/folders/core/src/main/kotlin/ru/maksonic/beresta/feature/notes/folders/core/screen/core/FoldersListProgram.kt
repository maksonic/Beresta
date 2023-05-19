package ru.maksonic.beresta.feature.notes.folders.core.screen.core

import kotlinx.coroutines.delay
import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.notes.folders.api.domain.FetchFoldersListUseCase
import ru.maksonic.beresta.feature.notes.folders.api.domain.NotesFoldersInteractor
import ru.maksonic.beresta.feature.notes.folders.api.ui.NoteFolderToUiMapper
import ru.maksonic.beresta.feature.notes.folders.api.ui.NoteFolderUi
import ru.maksonic.beresta.feature.notes.folders.api.ui.sortStickyThenDescendingByPinTimeThenByDate
import java.time.LocalDateTime

/**
 * @Author maksonic on 03.04.2023
 */
class FoldersListProgram(
    private val fetchFoldersUseCase: FetchFoldersListUseCase,
    private val mapper: NoteFolderToUiMapper,
    private val foldersInteractor: NotesFoldersInteractor,
) : ElmProgram<Msg, Cmd> {

    private companion object {
        private const val SNACK_BAR_VISIBILITY_TIME = 5000L
    }

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchFolders -> fetchFolders(consumer)
            is Cmd.RemoveSelected -> moveSelectedNotesToTrash(cmd.folders, consumer)
            is Cmd.UndoRemoveNotes -> undoRemovedFromTrash(cmd.folders, consumer)
            is Cmd.UpdatePinnedFoldersInCache -> updatePinnedFolders(cmd.pinned)
        }
    }

    private suspend fun fetchFolders(consumer: (Msg) -> Unit) {
        runCatching {
            fetchFoldersUseCase().collect { foldersDomain ->
                val folders = mapper.mapListTo(foldersDomain)
                val pinnedFolder = NoteFolderUi.InitialSelected
                val sortedFolders = mutableListOf(pinnedFolder).also {
                    it.addAll(folders)
                }.sortStickyThenDescendingByPinTimeThenByDate()

                consumer(Msg.Inner.FetchedFoldersResult(sortedFolders))
            }
        }.onFailure {
            consumer(Msg.Inner.FetchedFoldersResult(emptyList()))
        }
    }

    /*
    private suspend fun fetchFolders(consumer: (Msg) -> Unit) {
        runCatching {
            fetchFoldersUseCase().collect { foldersDomain ->
                val folders = mapper.mapListTo(foldersDomain)
                val pinnedFolder = NoteFolderUi.InitialSelected
                val sortedFolders = mutableListOf(pinnedFolder).also {
                    it.addAll(folders)
                }.toList().sortStickyThenDescendingByPinTimeThenByDate()

                consumer(Msg.Inner.FetchedFoldersResult(sortedFolders))
            }
        }.onFailure {
            consumer(Msg.Inner.FetchedFoldersResult(emptyList()))
        }
    }*/

    private suspend fun moveSelectedNotesToTrash(
        folders: List<NoteFolderUi>,
        consumer: (Msg) -> Unit
    ) {
        consumer(Msg.Inner.ShowRemovedNotesSnackBar)
        val notesDomain = mapper.mapListFrom(folders)
        foldersInteractor.updateAll(notesDomain)
        delay(SNACK_BAR_VISIBILITY_TIME)
        consumer(Msg.Inner.HideRemovedNotesSnackBar)
    }

    private suspend fun undoRemovedFromTrash(folders: List<NoteFolderUi>, consumer: (Msg) -> Unit) {
        val notesDomain = mapper.mapListFrom(folders)
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

        val foldersDomain = mapper.mapListFrom(selected)
        foldersInteractor.updateAll(foldersDomain)
    }
}
