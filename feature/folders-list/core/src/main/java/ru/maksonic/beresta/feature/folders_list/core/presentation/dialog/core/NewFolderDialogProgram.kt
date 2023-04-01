package ru.maksonic.beresta.feature.folders_list.core.presentation.dialog.core

import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.folders_list.api.domain.NotesFoldersInteractor
import ru.maksonic.beresta.feature.folders_list.api.ui.FilterChipUi
import ru.maksonic.beresta.feature.folders_list.api.ui.NoteFolderToChipMapper

/**
 * @Author maksonic on 30.03.2023
 */
class NewFolderDialogProgram(
    private val interactor: NotesFoldersInteractor,
    private val mapper: NoteFolderToChipMapper,
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.SaveNewFolderToCache -> createFolder(cmd.folder)
        }
    }

    private suspend fun createFolder(newFolder: FilterChipUi) {
        val folder = mapper.mapFrom(newFolder)
        interactor.addFolder(folder)
    }
}