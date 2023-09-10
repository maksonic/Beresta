package ru.maksonic.beresta.feature.sorting_sheet.ui.core

import ru.maksonic.beresta.elm.core.ElmProgram
import ru.maksonic.beresta.feature.sorting_sheet.api.Order
import ru.maksonic.beresta.feature.sorting_sheet.api.Sort
import ru.maksonic.beresta.feature.sorting_sheet.api.SortDataKey
import ru.maksonic.beresta.feature.sorting_sheet.api.SortingSheetApi

/**
 * @Author maksonic on 09.07.2023
 */
class SortingSheetProgram(
    private val listSortFeatureApi: SortingSheetApi.Ui,
    private val listSortFeatureStorage: SortingSheetApi.Storage
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.UpdateOrderState -> updateOrderState(cmd.value)
            is Cmd.UpdateSortState -> updateSortState(cmd.value)
            is Cmd.UpdateCheckboxState -> updateSortPinnedCheckboxState(cmd.value)
            is Cmd.SetDefaultSortState -> setDefaultSortState(cmd.key)
        }
    }

    private suspend fun updateOrderState(value: Pair<SortDataKey, Order>) = listSortFeatureApi
        .updateOrder(value)
        .let { listSortFeatureStorage.setOrderState(value) }

    private suspend fun updateSortState(value: Pair<SortDataKey, Sort>) = listSortFeatureApi
        .updateSort(value)
        .let { listSortFeatureStorage.setSortState(value) }

    private suspend fun updateSortPinnedCheckboxState(value: Pair<SortDataKey, Boolean>) =
        listSortFeatureApi
            .updateCheckbox(value)
            .let { listSortFeatureStorage.setCheckboxState(value) }

    private suspend fun setDefaultSortState(key: SortDataKey) = listSortFeatureApi
        .setInitialSortState(key)
        .let { listSortFeatureStorage.resetSortState(key) }

}