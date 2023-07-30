package ru.maksonic.beresta.screen.hidden_notes.core.programs

import ru.maksonic.beresta.elm.core.ElmProgram
import ru.maksonic.beresta.feature.sorting_sheet.api.GridCountKey
import ru.maksonic.beresta.feature.sorting_sheet.api.SortingSheetApi
import ru.maksonic.beresta.screen.hidden_notes.core.Cmd
import ru.maksonic.beresta.screen.hidden_notes.core.Msg

/**
 * @Author maksonic on 21.07.2023
 */
class HiddenNotesSortProgram(
    private val listSortStateUiApi: SortingSheetApi.Ui,
    private val listSortStateFeatureState: SortingSheetApi.Feature.State,
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.UpdateNotesGridDatastoreState -> updateGridViewState(cmd.count)
            else -> {}
        }
    }

    private suspend fun updateGridViewState(count: Int) = listSortStateUiApi.state
        .update { it.copy(gridHiddenNotesCount = count) }
        .let { listSortStateFeatureState.setGridCount(Pair(GridCountKey.HIDDEN_NOTES, count)) }
}