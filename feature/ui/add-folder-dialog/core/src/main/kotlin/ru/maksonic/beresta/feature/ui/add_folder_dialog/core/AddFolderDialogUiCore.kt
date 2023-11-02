package ru.maksonic.beresta.feature.ui.add_folder_dialog.core

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.maksonic.beresta.common.core.ui.SharedState
import ru.maksonic.beresta.feature.ui.add_folder_dialog.api.AddFolderDialogUiApi
import ru.maksonic.beresta.feature.ui.add_folder_dialog.api.FolderDialogUiState

/**
 * @Author maksonic on 03.07.2023
 */
class AddFolderDialogUiCore : AddFolderDialogUiApi {
    override val sharedState: SharedState<FolderDialogUiState> = FolderDialogUiState.Initial

    override fun addFolder() =
        sharedState.update { it.copy(isNewFolder = true, editableFolderId = 0L) }

    override fun updateFolder(id: Long) =
        sharedState.update { it.copy(isNewFolder = false, editableFolderId = id) }


    @Composable
    override fun Widget(isVisible: Boolean, hideDialog: () -> Unit) {
        val state = sharedState.state.collectAsStateWithLifecycle()
        Container(isVisible, state, hideDialog)
    }
}