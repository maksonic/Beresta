package ru.maksonic.beresta.feature.folders_chips.api.ui

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.core.ElmBaseModel

/**
 * @Author maksonic on 03.07.2023
 */
@Stable
@Immutable
data class FoldersListUiState(
    val state: ElmBaseModel,
    val collection: FolderUi.Collection,
    val selectedList: Set<FolderUi>,
    val removedFolders: Set<FolderUi>,
) {
    companion object {
        val Initial = FoldersListUiState(
            state = ElmBaseModel.Loading,
            collection = FolderUi.Collection.Empty,
            selectedList = emptySet(),
            removedFolders = emptySet(),
        )
    }
}