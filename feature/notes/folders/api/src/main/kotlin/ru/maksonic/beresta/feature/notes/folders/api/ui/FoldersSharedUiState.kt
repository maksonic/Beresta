package ru.maksonic.beresta.feature.notes.folders.api.ui

import ru.maksonic.beresta.core.SharedUiState

/**
 * @Author maksonic on 04.04.2023
 */
data class FoldersSharedUiState(
    val isNewFolderStatus: Boolean,
    val isVisibleDialog: Boolean,
    val currentFolderId: Long,
    val passedForEditFolderId: Long,
) {
    companion object {
        private val DefaultState = FoldersSharedUiState(
            isNewFolderStatus = true,
            isVisibleDialog = false,
            currentFolderId = 1L,
            passedForEditFolderId = 0L
        )
        val Initial = object : SharedUiState<FoldersSharedUiState>(DefaultState) {}
    }
}

fun SharedUiState<FoldersSharedUiState>.updateCurrentSelectedFolder(id: Long) =
    this.update { it.copy(currentFolderId = id) }

fun SharedUiState<FoldersSharedUiState>.showDialog(
    isNewFolder: Boolean,
    passedFolderId: Long = 0L
) =
    this.update {
        it.copy(
            isNewFolderStatus = isNewFolder,
            isVisibleDialog = true,
            passedForEditFolderId = passedFolderId
        )
    }

fun SharedUiState<FoldersSharedUiState>.hideDialog() =
    this.update { it.copy(isVisibleDialog = false) }