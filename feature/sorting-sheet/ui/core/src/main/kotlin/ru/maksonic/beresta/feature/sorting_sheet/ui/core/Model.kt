package ru.maksonic.beresta.feature.sorting_sheet.ui.core

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.common.core.ui.sorting.Order
import ru.maksonic.beresta.common.core.ui.sorting.Sort
import ru.maksonic.beresta.feature.sorting_sheet.domain.SortDataKey
import ru.maksonic.beresta.platform.elm.core.ElmCommand
import ru.maksonic.beresta.platform.elm.core.ElmEffect
import ru.maksonic.beresta.platform.elm.core.ElmMessage
import ru.maksonic.beresta.platform.elm.core.ElmModel

/**
 * @Author maksonic on 09.07.2023
 */
@Stable
@Immutable
data class Model(
    val order: Order,
    val sort: Sort,
    val isSortPinned: Boolean
) : ElmModel {
    companion object {
        val Initial = Model(
            order = Order.DESCENDING,
            sort = Sort.DATE_CREATION,
            isSortPinned = false
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        data class OnOrderClicked(val value: Pair<SortDataKey, Order>) : Ui()
        data class OnSortSelected(val value: Pair<SortDataKey, Sort>) : Ui()
        data class OnCheckboxClicked(val value: Pair<SortDataKey, Boolean>) : Ui()
        data class OnDefaultBtnClicked(val key: SortDataKey) : Ui()
    }
}

sealed class Cmd : ElmCommand {
    data class UpdateOrderState(val value: Pair<SortDataKey, Order>) : Cmd()
    data class UpdateSortState(val value: Pair<SortDataKey, Sort>) : Cmd()
    data class UpdateCheckboxState(val value: Pair<SortDataKey, Boolean>) : Cmd()
    data class SetDefaultSortState(val key: SortDataKey) : Cmd()
}

sealed class Eff : ElmEffect