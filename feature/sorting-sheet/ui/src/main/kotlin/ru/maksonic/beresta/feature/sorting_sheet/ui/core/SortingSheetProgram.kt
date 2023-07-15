package ru.maksonic.beresta.feature.sorting_sheet.ui.core

import ru.maksonic.beresta.elm.core.ElmProgram
import ru.maksonic.beresta.feature.sorting_sheet.api.Order
import ru.maksonic.beresta.feature.sorting_sheet.api.Sort
import ru.maksonic.beresta.feature.sorting_sheet.api.SortDataKey
import ru.maksonic.beresta.feature.sorting_sheet.api.SortingSheetApi
import ru.maksonic.beresta.feature.sorting_sheet.api.setInitialSortState
import ru.maksonic.beresta.feature.sorting_sheet.api.updateCheckbox
import ru.maksonic.beresta.feature.sorting_sheet.api.updateOrder
import ru.maksonic.beresta.feature.sorting_sheet.api.updateSort

/**
 * @Author maksonic on 09.07.2023
 */
class SortingSheetProgram(
    private val listSortStateUiApi: SortingSheetApi.Ui,
    private val listSortStateFeatureState: SortingSheetApi.Feature.State
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.UpdateOrderState -> updateOrderState(cmd.value)
            is Cmd.UpdateSortState -> updateSortState(cmd.value)
            is Cmd.UpdateCheckboxState -> updateSortPinnedCheckboxState(cmd.value)
            is Cmd.SetDefaultSortState -> setDefaultSortState(cmd.key)
        }
    }

    private suspend fun updateOrderState(value: Pair<SortDataKey, Order>) = listSortStateUiApi.state
        .updateOrder(value)
        .let { listSortStateFeatureState.setOrderState(value) }

    private suspend fun updateSortState(value: Pair<SortDataKey, Sort>) = listSortStateUiApi.state
        .updateSort(value)
        .let { listSortStateFeatureState.setSortState(value) }

    private suspend fun updateSortPinnedCheckboxState(value: Pair<SortDataKey, Boolean>) =
        listSortStateUiApi.state
            .updateCheckbox(value)
            .let { listSortStateFeatureState.setCheckboxState(value) }

    private suspend fun setDefaultSortState(key: SortDataKey) = listSortStateUiApi.state
        .setInitialSortState(key)
        .let { listSortStateFeatureState.resetSortState(key) }

}