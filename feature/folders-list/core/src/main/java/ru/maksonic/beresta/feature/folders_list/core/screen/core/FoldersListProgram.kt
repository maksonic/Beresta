package ru.maksonic.beresta.feature.folders_list.core.screen.core

import kotlinx.coroutines.delay
import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.folders_list.api.domain.FetchFoldersListUseCase
import ru.maksonic.beresta.feature.folders_list.api.domain.NotesFoldersInteractor
import ru.maksonic.beresta.feature.folders_list.api.ui.FilterChipUi
import ru.maksonic.beresta.feature.folders_list.api.ui.NoteFolderToChipMapper
import java.time.Instant

/**
 * @Author maksonic on 03.04.2023
 */
class FoldersListProgram(
    private val fetchFoldersUseCase: FetchFoldersListUseCase,
    private val mapper: NoteFolderToChipMapper,
    private val foldersInteractor: NotesFoldersInteractor,
) : ElmProgram<Msg, Cmd> {

    private companion object {
        private const val SNACK_BAR_VISIBILITY_TIME = 5000L
    }

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchFolders -> fetchFolders(consumer)
            is Cmd.UpdatePinnedFoldersInCache -> updatePinnedFolders(cmd.pinned)
            is Cmd.RemoveSelectedFolders -> moveSelectedFoldersToTrash(cmd.folders, consumer)
            is Cmd.UndoRemoved -> undoRemovedFoldersFromTrash(cmd.folders)
        }
    }

    private suspend fun fetchFolders(consumer: (Msg) -> Unit) {
        runCatching {
            fetchFoldersUseCase().collect { foldersData ->
                val foldersDomain = foldersData.filter { !it.isMovedToTrash }
                val folders = mapper.mapListTo(foldersDomain)
                val pinnedFolder = FilterChipUi.InitialSelected
                val sortedFolders = mutableListOf(pinnedFolder).also {
                    it.addAll(folders)
                }.toList()
                    .sortedWith(comparator = compareByDescending<FilterChipUi> { it.isSticky }.thenByDescending { it.pinTime }
                        .thenByDescending { it.id })

                consumer(Msg.Inner.FetchedFoldersResult(sortedFolders))
            }
        }.onFailure {
            consumer(Msg.Inner.FetchedFoldersResult(emptyList()))
        }
    }

    private suspend fun moveSelectedFoldersToTrash(
        folders: List<FilterChipUi>,
        consumer: (Msg) -> Unit
    ) {
        val foldersDomain = mapper.mapListFrom(folders).map { it.copy(isMovedToTrash = true) }
        foldersInteractor.updateAll(foldersDomain)

        delay(SNACK_BAR_VISIBILITY_TIME)
        consumer(Msg.Inner.HideRemovedFoldersSnack)
    }

    private suspend fun updatePinnedFolders(folders: List<FilterChipUi>) {
        val foldersDomain = mapper.mapListFrom(folders.map { folder ->
            folder.copy(pinTime = Instant.now())
        })
        foldersInteractor.updateAll(foldersDomain)
    }

    /* private suspend fun updatePinnedFolders(folders: List<FilterChipUi>) {
        val foldersDomain = mapper.mapListFrom(folders.map { folder ->
            folder.copy(pinTime = Calendar.getInstance().timeInMillis)
        })
        foldersInteractor.updateAll(foldersDomain)
    }*/

    private suspend fun undoRemovedFoldersFromTrash(folders: List<FilterChipUi>) {
        val foldersDomain = mapper.mapListFrom(folders)
        foldersInteractor.updateAll(foldersDomain)
    }
}
