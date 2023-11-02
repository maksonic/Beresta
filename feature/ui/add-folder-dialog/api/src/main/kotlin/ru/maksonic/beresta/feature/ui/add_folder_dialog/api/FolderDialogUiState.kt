package ru.maksonic.beresta.feature.ui.add_folder_dialog.api

import ru.maksonic.beresta.common.core.ui.SharedState

/**
 * @Author maksonic on 04.07.2023
 */
data class FolderDialogUiState(
    val isNewFolder: Boolean,
    val editableFolderId: Long
) {
    companion object {
        private val _Initial = FolderDialogUiState(
            isNewFolder = true,
            editableFolderId = 0L
        )
        val Initial = object : SharedState<FolderDialogUiState>(_Initial) {}
    }
}