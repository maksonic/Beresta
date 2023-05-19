package ru.maksonic.beresta.feature.notes.folders.core.add_folder_dialog.core

import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.notes.folders.api.domain.FetchFolderByIdUseCase
import ru.maksonic.beresta.feature.notes.folders.api.domain.NotesFoldersInteractor
import ru.maksonic.beresta.feature.notes.folders.api.ui.NoteFolderToUiMapper
import ru.maksonic.beresta.feature.notes.folders.api.ui.NoteFolderUi
import ru.maksonic.beresta.feature.notes.folders.api.ui.isDefaultId
import java.time.LocalDateTime

/**
 * @Author maksonic on 30.03.2023
 */
class FolderDialogProgram(
    private val fetchFolderByIdUseCase: FetchFolderByIdUseCase,
    private val interactor: NotesFoldersInteractor,
    private val mapper: NoteFolderToUiMapper,
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.SaveOrUpdateCurrentFolder -> saveOrUpdateCurrentFolder(cmd.folder)
            is Cmd.FetchFolderById -> fetchFolderById(cmd.id, consumer)
        }
    }

    private suspend fun fetchFolderById(id: Long, consumer: (Msg) -> Unit) {
        runCatching {
            fetchFolderByIdUseCase(id).collect { folderDomain ->
                val folder = mapper.mapTo(folderDomain)
                consumer(Msg.Inner.FetchFolderResult(folder))
            }
        }.onFailure {
            consumer(Msg.Inner.FetchFolderResult(NoteFolderUi.Empty))
        }
    }

    private suspend fun saveOrUpdateCurrentFolder(folder: NoteFolderUi) {
        val folderDomain = mapper.mapFrom(folder)
        interactor.let {
            if (folder.isDefaultId())
                it.addFolder(folderDomain.copy(dateCreation = LocalDateTime.now()))
            else
                it.updateFolder(folderDomain.copy(dateCreation = folder.dateCreationRaw))
        }
    }
}