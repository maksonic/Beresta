package ru.maksonic.beresta.feature.folders_chips.ui.dialog.core

import android.util.Log
import ru.maksonic.beresta.elm.core.ElmProgram
import ru.maksonic.beresta.feature.folders_chips.api.FoldersApi
import ru.maksonic.beresta.feature.folders_chips.api.domain.FolderDomain
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

    private suspend fun fetchFolderById(id: Long, consumer: (Msg) -> Unit) = runCatching {
        if (id == 0L) {
            consumer(Msg.Inner.FetchFolderData(FolderUi.Empty))
        } else {
            interactor.fetchById(id).collect { folderDomain ->
                val folder = mapper.mapTo(folderDomain)
                consumer(Msg.Inner.FetchFolderData(folder))
            }
        }
    }.onFailure {
        consumer(Msg.Inner.FetchFolderData(FolderUi.Empty))
    }

    private suspend fun saveOrUpdateCurrentFolder(folder: FolderUi) {

        Log.e("AAA", "$folder")

        val folderDomain = mapper.mapFrom(folder)
        val currentRawTime = LocalDateTime.now()

        if (folder.isDefaultId()) {
            addNewFolder(folderDomain, currentRawTime)
        } else {
            updateOldFolder(folderDomain, currentRawTime)
        }
    }

    private suspend fun addNewFolder(folder: FolderDomain, time: LocalDateTime) {
        interactor.add(folder.copy(dateCreation = time)).also { id -> updateCurrentFolder(id) }
    }

    private suspend fun updateOldFolder(folder: FolderDomain, time: LocalDateTime) {
        interactor.update(folder.copy(dateCreation = folder.dateCreation, dateLastUpdateRaw = time))
    }

    private fun updateCurrentFolder(id: Long) {
        chipsRowApi.currentSelectedId.update(id)
    }
}