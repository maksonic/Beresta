package ru.maksonic.beresta.feature.sorting_sheet.ui.core

import ru.maksonic.beresta.elm.core.ElmProgram
import ru.maksonic.beresta.feature.sorting_sheet.api.Order
import ru.maksonic.beresta.feature.sorting_sheet.api.Sort
import ru.maksonic.beresta.feature.sorting_sheet.api.SortingSheetApi
import ru.maksonic.beresta.feature.sorting_sheet.api.setInitialSortState

/**
 * @Author maksonic on 09.07.2023
 */
class SortingSheetProgram(
    private val listSortStateUiApi: SortingSheetApi.Ui,
    private val listSortStateFeatureState: SortingSheetApi.Feature.State
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.UpdateOrderState -> updateOrderState(cmd.order)
            is Cmd.UpdateSortState -> updateSortState(cmd.sort)
            is Cmd.UpdateCheckboxState -> updateSortPinnedCheckboxState(cmd.isChecked)
            is Cmd.SetDefaultSortState -> setDefaultSortState()
        }
    }

    private suspend fun updateOrderState(order: Order) = listSortStateUiApi.state
        .update { it.copy(order = order) }
        .let { listSortStateFeatureState.setOrderState(order) }

    private suspend fun updateSortState(sort: Sort) = listSortStateUiApi.state
        .update { it.copy(sort = sort) }
        .let { listSortStateFeatureState.setSortState(sort) }

    private suspend fun updateSortPinnedCheckboxState(isChecked: Boolean) = listSortStateUiApi.state
        .update { it.copy(isSortPinned = isChecked) }
        .let { listSortStateFeatureState.setCheckboxState(isChecked) }

    private suspend fun setDefaultSortState() = listSortStateUiApi.state.setInitialSortState()
        .let { listSortStateFeatureState.resetSortState() }

}