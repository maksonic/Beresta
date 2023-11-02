package ru.maksonic.beresta.feature.sorting_sheet.ui.core

import ru.maksonic.beresta.common.core.ui.sorting.Order
import ru.maksonic.beresta.common.core.ui.sorting.Sort
import ru.maksonic.beresta.feature.sorting_sheet.domain.SortDataKey
import ru.maksonic.beresta.feature.sorting_sheet.domain.SortInteractor
import ru.maksonic.beresta.platform.elm.core.ElmProgram

/**
 * @Author maksonic on 09.07.2023
 */
class SortingSheetProgram(private val sortInteractor: SortInteractor) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.UpdateOrderState -> updateOrderState(cmd.value)
            is Cmd.UpdateSortState -> updateSortState(cmd.value)
            is Cmd.UpdateCheckboxState -> updateSortPinnedCheckboxState(cmd.value)
            is Cmd.SetDefaultSortState -> setDefaultSortState(cmd.key)
        }
    }

    private suspend fun updateOrderState(value: Pair<SortDataKey, Order>) {
        sortInteractor.setOrderState(value)
    }

    private suspend fun updateSortState(value: Pair<SortDataKey, Sort>) {
        sortInteractor.setSortState(value)
    }

    private suspend fun updateSortPinnedCheckboxState(value: Pair<SortDataKey, Boolean>) {
        sortInteractor.setCheckboxState(value)
    }

    private suspend fun setDefaultSortState(key: SortDataKey) {
        sortInteractor.resetSortState(key)
    }
}