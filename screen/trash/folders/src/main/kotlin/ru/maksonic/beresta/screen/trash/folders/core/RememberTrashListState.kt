package ru.maksonic.beresta.screen.trash.folders.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import ru.maksonic.beresta.feature.folders_list.ui.api.FoldersListUiState

/**
 * @Author maksonic on 20.10.2023
 */
@Composable
internal fun rememberTrashListState(model: Model) = rememberUpdatedState(
    FoldersListUiState.Initial.copy(
        state = model.base,
        collection = model.folders,
        isSelection = model.isSelection
    )
)