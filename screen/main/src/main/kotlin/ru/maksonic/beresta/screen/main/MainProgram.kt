package ru.maksonic.beresta.screen.main

import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.notes.folders.api.domain.FetchFoldersListUseCase
import ru.maksonic.beresta.feature.notes.folders.api.ui.NoteFolderToUiMapper
import ru.maksonic.beresta.feature.notes.folders.api.ui.sortStickyThenDescendingByPinTimeThenByDate

/**
 * @Author maksonic on 21.02.2023
 */
class MainProgram(
    private val foldersListUseCase: FetchFoldersListUseCase,
    private val foldersMapper: NoteFolderToUiMapper
) : ElmProgram<Msg, Cmd> {

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchFoldersChips -> fetchNotesFolders(consumer)
        }
    }

    private suspend fun fetchNotesFolders(consumer: (Msg) -> Unit) {

        runCatching {
            foldersListUseCase().collect { data ->
                val folders =
                    foldersMapper.mapListTo(data).sortStickyThenDescendingByPinTimeThenByDate()

                consumer(Msg.Inner.FetchedChipsResult(folders))
            }
        }.onFailure {
            consumer(Msg.Inner.FetchedChipsResult(emptyList()))
        }
    }
}