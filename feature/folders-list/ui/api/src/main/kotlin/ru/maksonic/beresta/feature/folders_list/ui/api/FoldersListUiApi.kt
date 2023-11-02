package ru.maksonic.beresta.feature.folders_list.ui.api

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.common.core.ui.sorting.FilterDataSorter

/**
 * @Author maksonic on 20.10.2023
 */
interface FoldersListUiApi {
    @Composable
    fun List(
        state: FoldersListUiState,
        sorter: State<FilterDataSorter<FolderUi>>,
        onFolderClicked: (Long) -> Unit,
        onFolderLongClicked: (Long) -> Unit,
        onErrorRetryClicked: () -> Unit,
        onToHiddenNotesClicked: () -> Unit,
        isTrashPlacement: Boolean,
        contentPadding: PaddingValues,
        modifier: Modifier,
        loadingModifier: Modifier,
        emptyListPlaceholder: @Composable () -> Unit
    )

    @Composable
    fun LoadingPlaceholder(modifier: Modifier)

    @Composable
    fun LoadingTrashPlaceholder(modifier: Modifier)
}