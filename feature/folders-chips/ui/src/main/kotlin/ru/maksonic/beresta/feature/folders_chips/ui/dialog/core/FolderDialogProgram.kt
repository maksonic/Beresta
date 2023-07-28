package ru.maksonic.beresta.feature.folders_chips.ui.dialog.core

import ru.maksonic.beresta.elm.core.ElmProgram
import ru.maksonic.beresta.feature.folders_chips.api.FoldersApi
import ru.maksonic.beresta.feature.folders_chips.api.domain.FoldersInteractor
import ru.maksonic.beresta.feature.folders_chips.api.ui.FolderUi
import ru.maksonic.beresta.feature.folders_chips.api.ui.FolderUiMapper
import ru.maksonic.beresta.feature.folders_chips.api.ui.isDefaultId
import java.time.LocalDateTime

/**
 * @Author maksonic on 30.03.2023
 */
class FolderDialogProgram(
    private val interactor: FoldersInteractor,
    private val mapper: FolderUiMapper,
    private val chipsRowApi: FoldersApi.Ui.ChipsRow
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.SaveOrUpdateCurrentFolder -> saveOrUpdateCurrentFolder(cmd.folder)
            is Cmd.FetchFolderById -> fetchFolderById(cmd.id, consumer)
        }
    }

    private suspend fun fetchFolderById(id: Long, consumer: (Msg) -> Unit) {
        runCatching {
            interactor.fetchById(id).collect { folderDomain ->
                val folder = mapper.mapTo(folderDomain)
                consumer(Msg.Inner.FetchFolderData(folder))
            }
        }.onFailure {
            consumer(Msg.Inner.FetchFolderData(FolderUi.Empty))
        }
    }

    private suspend fun saveOrUpdateCurrentFolder(folder: FolderUi) {
        val folderDomain = mapper.mapFrom(folder)
        interactor.let {
            if (folder.isDefaultId()) {
                it.add(folderDomain.copy(dateCreation = LocalDateTime.now()))
                updateCurrentFolder()
            } else {
                it.update(
                    folderDomain.copy(
                        dateCreation = folder.dateCreationRaw,
                        dateLastUpdateRaw = LocalDateTime.now()
                    )
                )
            }
        }
    }

    private suspend fun updateCurrentFolder() = interactor.fetchList().collect { list ->
        val lastAddedFolder = list.maxBy { folder -> folder.id }
        chipsRowApi.currentSelectedId.update(lastAddedFolder.id)
    }
}