package ru.maksonic.beresta.feature.folders_list.ui.api

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.platform.elm.core.ElmBaseModel

/**
 * @Author maksonic on 03.07.2023
 */
@Stable
@Immutable
data class FoldersListUiState(
    val state: ElmBaseModel,
    val collection: FolderUi.Collection,
    val removedList: List<FolderUi>,
    val isSelection: Boolean,
    val isNotesMoving: Boolean
) {
    companion object {
        val Initial = FoldersListUiState(
            state = ElmBaseModel.Loading,
            collection = FolderUi.Collection.Empty,
            removedList = emptyList(),
            isSelection = false,
            isNotesMoving = false
        )
    }
}