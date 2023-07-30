package ru.maksonic.beresta.feature.folders_chips.ui.dialog.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.feature.folders_chips.api.FoldersApi
import ru.maksonic.beresta.feature.folders_chips.api.ui.SharedNewFolderDialogUiState
import ru.maksonic.beresta.feature.folders_chips.api.ui.hide

/**
 * @Author maksonic on 03.07.2023
 */
class AddNewChipDialog : FoldersApi.Ui.AddChipDialog {
    override val sharedUiState: SharedUiState<SharedNewFolderDialogUiState>
        get() = SharedNewFolderDialogUiState.Initial

    @Composable
    override fun Widget() {
        val uiState = sharedUiState.state.collectAsStateWithLifecycle()

        Container(uiState) { sharedUiState.hide() }
    }
}