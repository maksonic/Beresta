package ru.maksonic.beresta.screen.main

import android.util.Log
import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.notes.folders.api.domain.FetchFoldersListUseCase
import ru.maksonic.beresta.feature.notes.folders.api.ui.NoteFolderToUiMapper
import ru.maksonic.beresta.feature.notes.folders.api.ui.NoteFolderUi
import ru.maksonic.beresta.feature.notes.folders.api.ui.sortStickyThenDescendingByPinTimeThenByDate

/**
 * @Author maksonic on 21.02.2023
 */
class MainProgram(
    private val foldersListUseCase: FetchFoldersListUseCase,
    private val foldersMapper: NoteFolderToUiMapper
) : ElmProgram<Msg, Cmd> {

    private companion object {
        private const val PINNED_END_FOLDER_DEFAULT_ID = 1L
    }

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchFoldersChips -> fetchNotesFolders(consumer)
        }
    }

    private suspend fun fetchNotesFolders(consumer: (Msg) -> Unit) {
        val stickyStartFolder = NoteFolderUi.StartListFolder
        val stickyEndFolder = NoteFolderUi.EndListFolder

        runCatching {
            foldersListUseCase().collect { data ->
                val folders =
                    foldersMapper.mapListTo(data).sortStickyThenDescendingByPinTimeThenByDate()
                // Adding sticky folders to start and end position in data list.
                val lastItemId = if (folders.isNotEmpty()) folders.maxBy { it.id }.id + 2
                else PINNED_END_FOLDER_DEFAULT_ID

                val result = mutableListOf(stickyStartFolder, stickyEndFolder.copy(lastItemId))
                    .also { it.addAll(1, folders) }

                consumer(Msg.Inner.FetchedChipsResult(result))
            }
        }.onFailure {
            val pinnedEndFolder = stickyEndFolder.copy(id = PINNED_END_FOLDER_DEFAULT_ID)
            val folders = mutableListOf(stickyStartFolder, pinnedEndFolder).toList()

            consumer(Msg.Inner.FetchedChipsResult(folders))
        }
    }
}