package ru.maksonic.beresta.feature.folders_list.ui.core.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.common.core.ui.sorting.FilterDataSorter
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.Wallpaper
import ru.maksonic.beresta.common.ui_kit.placeholder.PlaceholderErrorState
import ru.maksonic.beresta.feature.folders_list.ui.api.FolderUi
import ru.maksonic.beresta.feature.folders_list.ui.api.FoldersListUiState

/**
 * @Author maksonic on 20.10.2023
 */
@Composable
internal fun Container(
    state: FoldersListUiState,
    sorter: State<FilterDataSorter<FolderUi>>,
    onFolderClicked: (Long) -> Unit,
    onFolderLongClicked: (Long) -> Unit,
    onErrorRetryClicked: () -> Unit,
    onToHiddenNotesClicked: () -> Unit,
    isTrashPlacement: Boolean,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
    loadingModifier: Modifier,
    emptyListPlaceholder: @Composable () -> Unit
) {
    when {
        state.state.isLoading -> {
            if (isTrashPlacement) {
                LoadingTrashPlaceholderContent(loadingModifier)
            } else {
                LoadingPlaceholderContent(loadingModifier)
            }
        }
        state.state.successAfterLoading -> {
            if (sorter.value.isEmptyList) {
                emptyListPlaceholder()
            } else {
                ContentSuccess(
                    state = state,
                    sorter = sorter,
                    onFolderClicked = onFolderClicked,
                    onFolderLongClicked = onFolderLongClicked,
                    onToHiddenNotesClicked = onToHiddenNotesClicked,
                    contentPadding = contentPadding,
                    isTrashPlacement = isTrashPlacement,
                    modifier = modifier,
                )
            }
        }

        state.state.failAfterLoading -> {
            PlaceholderErrorState(
                imageVector = AppIcon.Wallpaper,
                message = state.state.failMessage,
                onErrorRetryClicked = onErrorRetryClicked
            )
        }
    }
}