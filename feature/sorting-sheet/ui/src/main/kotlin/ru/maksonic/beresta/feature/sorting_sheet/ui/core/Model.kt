package ru.maksonic.beresta.feature.sorting_sheet.ui.core

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.core.ElmCommand
import ru.maksonic.beresta.elm.core.ElmEffect
import ru.maksonic.beresta.elm.core.ElmMessage
import ru.maksonic.beresta.elm.core.ElmModel
import ru.maksonic.beresta.feature.sorting_sheet.api.Order
import ru.maksonic.beresta.feature.sorting_sheet.api.Sort

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
        data class OnOrderClicked(val order: Order) : Ui()
        data class OnSortSelected(val sort: Sort) : Ui()
        data class OnCheckboxClicked(val isChecked: Boolean) : Ui()
        object OnDefaultBtnClicked : Ui()
    }
}

sealed class Cmd : ElmCommand {
    data class UpdateOrderState(val order: Order) : Cmd()
    data class UpdateSortState(val sort: Sort) : Cmd()
    data class UpdateCheckboxState(val isChecked: Boolean) : Cmd()
    object SetDefaultSortState : Cmd()
}

sealed class Eff : ElmEffect {

}