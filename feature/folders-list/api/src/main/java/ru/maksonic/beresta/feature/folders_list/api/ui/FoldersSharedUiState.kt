package ru.maksonic.beresta.feature.folders_list.api.ui

import ru.maksonic.beresta.core.SharedUiState

/**
 * @Author maksonic on 04.04.2023
 */
data class FoldersSharedUiState(
    val isUpdateFolder: Boolean,
    val isVisibleDialog: Boolean,
    val currentFolderId: Long,
    val passedForEditFolderId: Long?,
) {
    companion object {
        private val DefaultState = FoldersSharedUiState(
            isUpdateFolder = false,
            isVisibleDialog = false,
            currentFolderId = 0L,
            passedForEditFolderId = null
        )

        val Initial = object : SharedUiState<FoldersSharedUiState>(DefaultState) {}
    }
}

fun SharedUiState<FoldersSharedUiState>.updateDialogVisibility(isVisible: Boolean) =
    this.updateState { it.copy(isVisibleDialog = isVisible) }