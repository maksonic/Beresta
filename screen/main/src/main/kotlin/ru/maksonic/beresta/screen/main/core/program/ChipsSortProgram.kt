package ru.maksonic.beresta.screen.main.core.program

import ru.maksonic.beresta.feature.sorting_sheet.domain.usecase.FetchFoldersSortUseCase
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.FoldersSortUiMapper
import ru.maksonic.beresta.platform.elm.core.ElmProgram
import ru.maksonic.beresta.screen.main.core.Cmd
import ru.maksonic.beresta.screen.main.core.Msg

/**
 * @Author maksonic on 16.10.2023
 */
class ChipsSortProgram(
    private val fetchFoldersSortUseCase: FetchFoldersSortUseCase,
    private val mapper: FoldersSortUiMapper
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchChipsSortState -> fetchChipsSortState(consumer)
            else -> {}
        }
    }

    private suspend fun fetchChipsSortState(consumer: (Msg) -> Unit) =
        fetchFoldersSortUseCase().collect { sort ->
            consumer(Msg.Inner.FetchedChipsSort(mapper.mapTo(sort)))
        }
}