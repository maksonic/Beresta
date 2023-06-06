package ru.maksonic.beresta.feature.notes.folders.core.screen.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.withContext
import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.notes.folders.api.domain.NotesFoldersInteractor
import ru.maksonic.beresta.feature.notes.folders.api.domain.usecase.FetchFoldersListUseCase
import ru.maksonic.beresta.feature.notes.folders.api.ui.NoteFolderToUiMapper
import ru.maksonic.beresta.feature.notes.folders.api.ui.NoteFolderUi
import ru.maksonic.beresta.feature.notes.folders.api.ui.StickyItemsTitleFormatter
import ru.maksonic.beresta.feature.notes.folders.api.ui.sortStickyThenDescendingByPinTimeThenByDate
import ru.maksonic.beresta.feature.notes.list.api.domain.NotesInteractor
import ru.maksonic.beresta.feature.notes.list.api.domain.usecase.FetchNotesUseCase
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteUiMapper
import ru.maksonic.beresta.language_engine.shell.LanguageEngineApi
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
    private val notesInteractor: NotesInteractor,
    private val notesMapper: NoteUiMapper,
    private val navigator: AppNavigator,
    private val appLanguageEngineApi: LanguageEngineApi,
    private val stickyItemsTitleFormatter: StickyItemsTitleFormatter,
    private val ioDispatcher: CoroutineDispatcher
) : ElmProgram<Msg, Cmd> {

    private companion object {
        private const val SNACK_BAR_VISIBILITY_TIME = 5000L
    }

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchCurrentAppLang -> fetchCurrentAppLang(consumer)
            is Cmd.FetchFolders -> fetchFolders(consumer)
            is Cmd.FetchPassedFromMainScreenArgs -> fetchedPassedNotesMoveState(consumer)
            is Cmd.UpdatePinnedFoldersInCache -> updatePinnedFolders(cmd.pinned)
            is Cmd.UndoRemovedFolders -> {
                undoRemovedFoldersFromTrash(
                    folders = cmd.folders,
                    removedFolders = cmd.removedFolders,
                    removedNotes = cmd.removedNotes,
                    notes = cmd.notes,
                    consumer = consumer
                )
            }

            is Cmd.ChangeNoteFolderId -> changeNotesFolderId(cmd.folderId, cmd.notes)
            is Cmd.RemoveSelected -> {
                moveSelectedFoldersToTrash(
                    folders = cmd.folders,
                    notes = cmd.notes,
                    consumer = consumer
                )
            }
        }
    }

    private suspend fun fetchFolders(consumer: (Msg) -> Unit) = withContext(ioDispatcher) {
        runCatching {
            combine(
                fetchFoldersUseCase(),
                fetchNotesUseCase(),
                appLanguageEngineApi.current
            ) { folders, notes, lang ->
                val foldersUi = foldersMapper.mapListTo(folders)
                    .map { folder ->
                        val count = notes.count { it.folderId == folder.id }
                        folder.copy(
                            notesCount = if (folder.isStickyToStart) notes.count() else count
                        )
                    }.sortStickyThenDescendingByPinTimeThenByDate()
                    .also { stickyItemsTitleFormatter.format(it, lang) }

                val notesUi = notesMapper.mapListTo(notes)
                consumer(Msg.Inner.FetchedFoldersResult(foldersUi, notesUi))
            }.collect()

        }.onFailure {
            consumer(Msg.Inner.FetchedFoldersResult(emptyList(), emptyList()))
        }
    }

    private fun fetchedPassedNotesMoveState(consumer: (Msg) -> Unit) {
        val keyList = Destination.NotesFoldersList.passedKeysList
        val isMoveNotesState = navigator.getBoolean(keyList.first())
        val currentSelectedFolderId = navigator.getLong(keyList.last())
        consumer(Msg.Inner.FetchedPassedArgsFromMain(isMoveNotesState, currentSelectedFolderId))
    }

    private suspend fun moveSelectedFoldersToTrash(
        folders: List<NoteFolderUi>,
        notes: List<NoteUi>,
        consumer: (Msg) -> Unit
    ) = withContext(ioDispatcher) {
        val removedFolders = folders.map {
            it.copy(
                isMovedToTrash = true,
                dateMovedToTrashRaw = LocalDateTime.now()
            )
        }
        val removedNotes = notes.filter { note ->
            removedFolders.find { it.id == note.folderId }?.id == note.folderId
        }.map { it.copy(isMovedToTrash = true) }

        foldersInteractor.updateAll(foldersMapper.mapListFrom(removedFolders))
        updateNotes(removedNotes)
        consumer(Msg.Inner.UpdatedRemovedNotes(removedNotes))
        delay(SNACK_BAR_VISIBILITY_TIME)
        consumer(Msg.Inner.HideRemovedNotesSnackBar)
    }

    private suspend fun undoRemovedFoldersFromTrash(
        folders: List<NoteFolderUi>,
        removedFolders: List<NoteFolderUi>,
        removedNotes: List<NoteUi>,
        notes: List<NoteUi>,
        consumer: (Msg) -> Unit
    ) = withContext(ioDispatcher) {
        val restoredRemovedFolders =
            removedFolders.map { it.copy(isMovedToTrash = false, dateMovedToTrashRaw = null) }
        val foldersUi = folders.toMutableList().also { it.addAll(restoredRemovedFolders) }
        val restoredRemovedNotes = removedNotes.map { it.copy(isMovedToTrash = false) }
        val restoredNotes = notes.toMutableList().also { it.addAll(restoredRemovedNotes) }

        foldersInteractor.updateAll(foldersMapper.mapListFrom(foldersUi))
        updateNotes(restoredNotes)

        consumer(Msg.Inner.HideRemovedNotesSnackBar)
    }

    private suspend fun updatePinnedFolders(folders: Set<NoteFolderUi>) =
        withContext(ioDispatcher) {
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
        val updated = notes.map { it.copy(folderId = folderId) }
        val notesDomain = notesMapper.mapListFrom(updated)
        notesInteractor.updateAll(notesDomain)
    }

    private suspend fun updateNotes(notes: List<NoteUi>) {
        val notesDomain = notesMapper.mapListFrom(notes)
        notesInteractor.updateAll(notesDomain)
    }

    private suspend fun fetchCurrentAppLang(consumer: (Msg) -> Unit) {
        appLanguageEngineApi.current.collectLatest {
            consumer(Msg.Inner.FetchedCurrentAppLang(it))
        }
    }
}