package ru.maksonic.beresta.feature.folders_list.core.dialog.core

import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.folders_list.api.domain.FetchFolderByIdUseCase
import ru.maksonic.beresta.feature.folders_list.api.domain.NotesFoldersInteractor
import ru.maksonic.beresta.feature.folders_list.api.ui.FilterChipUi
import ru.maksonic.beresta.feature.folders_list.api.ui.NoteFolderToChipMapper

/**
 * @Author maksonic on 30.03.2023
 */
class NewFolderDialogProgram(
    private val fetchFolderByIdUseCase: FetchFolderByIdUseCase,
    private val interactor: NotesFoldersInteractor,
    private val mapper: NoteFolderToChipMapper,
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.SaveNewFolderToCache -> createFolder(cmd.folder)
            is Cmd.FetchFolderById -> fetchFolderById(cmd.id, consumer)
            is Cmd.UpdateFolder -> updateFolder(cmd.folder)
        }
    }

    private suspend fun fetchFolderById(id: Long, consumer: (Msg) -> Unit) {
        runCatching {
            fetchFolderByIdUseCase(id).collect { folderDomain ->
                val folder = mapper.mapTo(folderDomain)
                consumer(Msg.Inner.FillTextFieldByFolderName(folder))
            }
        }.onFailure {
            consumer(Msg.Inner.FillTextFieldByFolderName(FilterChipUi.Empty))
        }
    }

    private suspend fun createFolder(newFolder: FilterChipUi) {
        val folder = mapper.mapFrom(newFolder)
        interactor.addFolder(folder)
    }

    private suspend fun updateFolder(updatedFolder: FilterChipUi) {
        val folder = mapper.mapFrom(updatedFolder)
        interactor.updateFolder(folder)
    }

}