package ru.maksonic.beresta.feature.folders_list.core.screen.core

import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.folders_list.api.domain.FetchFoldersListUseCase
import ru.maksonic.beresta.feature.folders_list.api.domain.NotesFoldersInteractor
import ru.maksonic.beresta.feature.folders_list.api.ui.FilterChipUi
import ru.maksonic.beresta.feature.folders_list.api.ui.NoteFolderToChipMapper

/**
 * @Author maksonic on 03.04.2023
 */
class FoldersListProgram(
    private val foldersListUseCase: FetchFoldersListUseCase,
    private val mapper: NoteFolderToChipMapper,
    private val foldersInteractor: NotesFoldersInteractor,
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchFolders -> fetchFolders(consumer)
        }
    }

    private suspend fun fetchFolders(consumer: (Msg) -> Unit) {
        runCatching {
            foldersListUseCase().collect { foldersData ->
                val folders = mapper.mapListTo(foldersData).sortedByDescending { it.id }
                val pinnedFolder = FilterChipUi(title = "All", isPinned = true)
                val pinnedFolders = mutableListOf(pinnedFolder).also { it.addAll(folders) }.toList()

                consumer(Msg.Inner.FetchedFoldersResult(FilterChipUi.Collection(pinnedFolders)))
            }
        }.onFailure {
            consumer(Msg.Inner.FetchedFoldersResult(FilterChipUi.Collection(emptyList())))
        }
    }
}
