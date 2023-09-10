package ru.maksonic.beresta.feature.folders_chips.ui.dialog.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import ru.maksonic.beresta.feature.folders_chips.api.FoldersApi
import ru.maksonic.beresta.feature.folders_chips.api.ui.SharedNewFolderDialogUiState

/**
 * @Author maksonic on 03.07.2023
 */
class FoldersAddNewChipDialog : FoldersApi.AddChipDialog.Ui {

    override val sharedState = mutableStateOf(SharedNewFolderDialogUiState.Initial)

    override fun hideDialog() {
        sharedState.value = sharedState.value.copy(isVisible = false)
    }

    override fun addFolder() {
        sharedState.value =
            sharedState.value.copy(isVisible = true, isNewFolder = true, editableFolderId = 0L)
    }

    override fun updateFolder(id: Long) {
        sharedState.value =
            sharedState.value.copy(isVisible = true, isNewFolder = false, editableFolderId = id)
    }

    @Composable
    override fun Widget() {
        val state = remember { sharedState }

        Container(state, hideDialog = { hideDialog() })
    }
}