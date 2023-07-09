package ru.maksonic.beresta.feature.folders_chips.api.ui

import ru.maksonic.beresta.core.SharedUiState

/**
 * @Author maksonic on 04.07.2023
 */
data class SharedNewFolderDialogUiState(
    val isVisible: Boolean,
    val isNewFolder: Boolean,
    val editableFolderId: Long
) {
    companion object {
        val Default = SharedNewFolderDialogUiState(
            isVisible = false,
            isNewFolder = true,
            editableFolderId = 0L
        )
        val Initial = object : SharedUiState<SharedNewFolderDialogUiState>(Default) {}
    }
}

fun SharedUiState<SharedNewFolderDialogUiState>.showForEdit(id: Long) =
    this.update { it.copy(isVisible = true, editableFolderId = id) }