package ru.maksonic.beresta.screen.main.core.programs

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import ru.maksonic.beresta.elm.core.ElmProgram
import ru.maksonic.beresta.feature.notes.api.NotesApi
import ru.maksonic.beresta.feature.sorting_sheet.api.SortDataKey
import ru.maksonic.beresta.feature.sorting_sheet.api.SortingSheetApi
import ru.maksonic.beresta.screen.main.core.Cmd
import ru.maksonic.beresta.screen.main.core.Msg

/**
 * @Author maksonic on 03.07.2023
 */
class NotesSortProgram(
    private val listSortStateUiApi: SortingSheetApi.Ui,
    private val listSortStateFeatureState: SortingSheetApi.Feature.State,
    private val noteCardUiState: NotesApi.Ui.Card,
    private val noteCardFeatureState: NotesApi.Feature.NoteCardState,
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchNotesListFeatureState -> fetchNotesState()
            is Cmd.UpdateNotesGridDatastoreState -> updateGridViewState(cmd.count)
            else -> {}
        }
    }

    private suspend fun fetchNotesState() = combine(
        listSortStateFeatureState.current(SortDataKey.NOTES), noteCardFeatureState.current
    ) { sortState, cardState ->
        listSortStateUiApi.state.update(sortState)
        noteCardUiState.state.update(cardState)

    }.collect()

    private suspend fun updateGridViewState(count: Int) = listSortStateUiApi.state
        .update { it.copy(gridCount = count) }
        .let { listSortStateFeatureState.setGridCount(count) }
}