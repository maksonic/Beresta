package ru.maksonic.beresta.screen.main.core.programs

import ru.maksonic.beresta.elm.core.ElmProgram
import ru.maksonic.beresta.feature.sorting_sheet.api.SortDataKey
import ru.maksonic.beresta.feature.sorting_sheet.api.SortingSheetApi
import ru.maksonic.beresta.screen.main.core.Cmd
import ru.maksonic.beresta.screen.main.core.Msg

/**
 * @Author maksonic on 11.07.2023
 */
class ChipsSortProgram(
    private val listSortFeatureApi: SortingSheetApi.Ui,
    private val listSortFeatureStorage: SortingSheetApi.Storage,
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchChipsSortState -> fetchChipsSortState()
            else -> {}
        }
    }

    private suspend fun fetchChipsSortState() =
        listSortFeatureStorage.current(SortDataKey.FOLDERS)
            .collect { listSortFeatureApi.update(it) }
}