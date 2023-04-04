package ru.maksonic.beresta.feature.folders_list.api.ui

/**
 * @Author maksonic on 04.04.2023
 */
data class FoldersSharedUiState(
    val isVisibleDialog: Boolean,
    val currentFolderId: Long
) {
    companion object {

        val Initial = FoldersSharedUiState(
            isVisibleDialog = false,
            currentFolderId = 0L
        )
    }
}