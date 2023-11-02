package ru.maksonic.beresta.feature.folders_list.ui.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.common.core.ui.sorting.FilterDataSorter
import ru.maksonic.beresta.platform.elm.core.ElmBaseModel

/**
 * @Author maksonic on 17.10.2023
 */
interface FoldersChipsRowUiApi  {

    interface CurrentSelectedFolderStore {
        val id: State<Long>
        fun updateId(value: Long)
    }

    @Composable
    fun Widget(
        state: ElmBaseModel,
        sorter: State<FilterDataSorter<FolderUi>>,
        isColoredBackground: State<Boolean>,
        onAddNewChipClicked: () -> Unit,
        onRetryFetchChipsClicked: () -> Unit,
        onChipClicked: (id: Long) -> Unit,
        modifier: Modifier
    )
}