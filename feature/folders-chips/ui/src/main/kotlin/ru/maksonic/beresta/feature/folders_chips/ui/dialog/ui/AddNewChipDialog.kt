package ru.maksonic.beresta.feature.folders_chips.ui.dialog.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import ru.maksonic.beresta.feature.folders_chips.api.FoldersApi
import ru.maksonic.beresta.feature.folders_chips.api.ui.SharedNewFolderDialogUiState

/**
 * @Author maksonic on 03.07.2023
 */
class AddNewChipDialog : FoldersApi.Ui.AddChipDialog {

    override val state = mutableStateOf(SharedNewFolderDialogUiState.Initial)

    override fun hideDialog() {
        state.value = state.value.copy(isVisible = false)
    }

    override fun addFolder() {
        state.value = state.value.copy(isVisible = true, isNewFolder = true, editableFolderId = 0L)
    }

    override fun updateFolder(id: Long) {
        state.value = state.value.copy(isVisible = true, isNewFolder = false, editableFolderId = id)
    }

    @Composable
    override fun Widget() {
        val state = remember { state }

        Container(state, hideDialog = { hideDialog() })
    }
}