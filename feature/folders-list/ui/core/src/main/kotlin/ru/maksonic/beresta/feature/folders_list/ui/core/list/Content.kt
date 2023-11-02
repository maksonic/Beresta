package ru.maksonic.beresta.feature.folders_list.ui.core.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.common.core.ui.sorting.FilterDataSorter
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.PhonelinkLock
import ru.maksonic.beresta.feature.folders_list.ui.api.FolderUi
import ru.maksonic.beresta.feature.folders_list.ui.api.FoldersFeature
import ru.maksonic.beresta.feature.folders_list.ui.api.FoldersListUiState
import ru.maksonic.beresta.feature.folders_list.ui.core.widget.ButtonHiddenNotes
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 20.10.2023
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun ContentSuccess(
    state: FoldersListUiState,
    sorter: State<FilterDataSorter<FolderUi>>,
    onFolderClicked: (Long) -> Unit,
    onFolderLongClicked: (Long) -> Unit,
    onToHiddenNotesClicked: () -> Unit,
    contentPadding: PaddingValues,
    isTrashPlacement: Boolean,
    modifier: Modifier
) {
    val scrollState = rememberLazyStaggeredGridState()

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(1),
        state = scrollState,
        contentPadding = contentPadding,
        modifier = modifier.fillMaxSize()
    ) {
        if (!state.isNotesMoving && !isTrashPlacement) {
            item {
                ButtonHiddenNotes(
                    isEnabled = !state.isSelection,
                    title = text.hiddenNotes.topBarTitle,
                    prefixIcon = AppIcon.PhonelinkLock,
                    onNavigateToFoldersClicked = onToHiddenNotesClicked
                )
            }
        }

        items(items = sorter.value.sortedByFilterList, key = { item -> item.id }) { folder ->
            FolderItem(
                isSelected = folder.isSelected && folder.isSelectable,
                isCurrent = FoldersFeature.currentSelected == folder.id,
                folder = folder,
                isTrashPlacement = isTrashPlacement,
                onFolderClicked = { onFolderClicked(folder.id) },
                onFolderLongPressed = { onFolderLongClicked(folder.id) },
                modifier = Modifier.animateItemPlacement()
            )
        }
    }
}