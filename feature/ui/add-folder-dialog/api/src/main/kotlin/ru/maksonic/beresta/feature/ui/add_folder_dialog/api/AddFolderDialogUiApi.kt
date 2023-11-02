package ru.maksonic.beresta.feature.ui.add_folder_dialog.api

import androidx.compose.runtime.Composable
import ru.maksonic.beresta.common.core.ui.SharedState

/**
 * @Author maksonic on 18.10.2023
 */
interface AddFolderDialogUiApi {
    val sharedState: SharedState<FolderDialogUiState>
    fun addFolder()
    fun updateFolder(id: Long)

    @Composable
    fun Widget(isVisible: Boolean, hideDialog: () -> Unit)
}