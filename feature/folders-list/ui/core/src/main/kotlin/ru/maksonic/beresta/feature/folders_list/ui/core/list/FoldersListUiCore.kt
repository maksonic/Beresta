package ru.maksonic.beresta.feature.folders_list.ui.core.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.common.core.ui.sorting.FilterDataSorter
import ru.maksonic.beresta.feature.folders_list.ui.api.FolderUi
import ru.maksonic.beresta.feature.folders_list.ui.api.FoldersListUiApi
import ru.maksonic.beresta.feature.folders_list.ui.api.FoldersListUiState

/**
 * @Author maksonic on 20.10.2023
 */

class FoldersListUiCore : FoldersListUiApi {
    @Composable
    override fun List(
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
    ) {
        Container(
            state = state,
            sorter = sorter,
            onFolderClicked = onFolderClicked,
            onFolderLongClicked = onFolderLongClicked,
            onErrorRetryClicked = onErrorRetryClicked,
            onToHiddenNotesClicked = onToHiddenNotesClicked,
            isTrashPlacement = isTrashPlacement,
            contentPadding = contentPadding,
            modifier = modifier,
            loadingModifier = loadingModifier,
            emptyListPlaceholder = emptyListPlaceholder
        )
    }

    @Composable
    override fun LoadingPlaceholder(modifier: Modifier) {
        LoadingPlaceholderContent(modifier)
    }

    @Composable
    override fun LoadingTrashPlaceholder(modifier: Modifier) {
        LoadingTrashPlaceholderContent(modifier)
    }
}