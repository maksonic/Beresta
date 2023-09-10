package ru.maksonic.beresta.feature.folders_chips.api.ui

/**
 * @Author maksonic on 04.07.2023
 */
data class SharedNewFolderDialogUiState(
    val isVisible: Boolean,
    val isNewFolder: Boolean,
    val editableFolderId: Long
) {
    companion object {
        val Initial = SharedNewFolderDialogUiState(
            isVisible = false,
            isNewFolder = true,
            editableFolderId = 0L
        )
    }
}