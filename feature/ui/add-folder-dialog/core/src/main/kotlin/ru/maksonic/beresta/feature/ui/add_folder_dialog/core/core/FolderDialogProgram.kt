package ru.maksonic.beresta.feature.ui.add_folder_dialog.core.core

import kotlinx.coroutines.flow.collectLatest
import ru.maksonic.beresta.feature.folders_list.domain.FolderDomain
import ru.maksonic.beresta.feature.folders_list.domain.FoldersInteractor
import ru.maksonic.beresta.feature.folders_list.domain.usecase.FetchFolderByIdUseCase
import ru.maksonic.beresta.feature.folders_list.ui.api.FolderUi
import ru.maksonic.beresta.feature.folders_list.ui.api.FolderUiMapper
import ru.maksonic.beresta.feature.folders_list.ui.api.FoldersChipsRowUiApi
import ru.maksonic.beresta.feature.folders_list.ui.api.isDefaultId
import ru.maksonic.beresta.feature.ui.add_folder_dialog.api.AddFolderDialogUiApi
import ru.maksonic.beresta.platform.elm.core.ElmProgram
import java.time.LocalDateTime

/**
 * @Author maksonic on 30.03.2023
 */
class FolderDialogProgram(
    private val fetchFolderByIdUseCase: FetchFolderByIdUseCase,
    private val interactor: FoldersInteractor,
    private val mapper: FolderUiMapper,
    private val currentFolderStoreUiApi: FoldersChipsRowUiApi.CurrentSelectedFolderStore,
    private val addChipDialogApi: AddFolderDialogUiApi,
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.SaveOrUpdateCurrentFolder -> saveOrUpdateCurrentFolder(cmd.folder)
            is Cmd.FetchFolderById -> fetchFolderById(consumer)
        }
    }

    private suspend fun fetchFolderById(consumer: (Msg) -> Unit) = runCatching {
        addChipDialogApi.sharedState.state.collectLatest { state ->
            if (state.editableFolderId == 0L) {
                consumer(Msg.Inner.FetchedFolderResult(FolderUi.Empty))
            } else {
                fetchFolderByIdUseCase(state.editableFolderId).collect { folderDomain ->
                    val folder = mapper.mapTo(folderDomain)
                    consumer(Msg.Inner.FetchedFolderResult(folder))
                }
            }
        }
    }.onFailure {
        consumer(Msg.Inner.FetchedFolderResult(FolderUi.Empty))
    }

    private suspend fun saveOrUpdateCurrentFolder(folder: FolderUi) {
        val folderDomain = mapper.mapFrom(folder)
        val currentRawTime = LocalDateTime.now()

        if (folder.isDefaultId()) {
            addNewFolder(folderDomain, currentRawTime)
        } else {
            updateOldFolder(folderDomain, currentRawTime)
        }
    }

    private suspend fun addNewFolder(folder: FolderDomain, time: LocalDateTime) = interactor
        .add(folder.copy(dateCreation = time))
        .also { id -> currentFolderStoreUiApi.updateId(id) }


    private suspend fun updateOldFolder(folder: FolderDomain, time: LocalDateTime) = interactor
        .update(folder.copy(dateCreation = folder.dateCreation, dateLastUpdateRaw = time))
}