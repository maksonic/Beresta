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
    private val listSortStateUiApi: SortingSheetApi.Ui,
    private val listSortStateFeatureState: SortingSheetApi.Feature.State,
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchChipsSortState -> fetchChipsSortState()
            else -> {}
        }
    }

    private suspend fun fetchChipsSortState() =
        listSortStateFeatureState.current(SortDataKey.FOLDERS)
            .collect { listSortStateUiApi.state.update(it) }
}