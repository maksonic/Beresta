package ru.maksonic.beresta.feature.folders_chips.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.feature.folders_chips.api.ui.FolderUi
import ru.maksonic.beresta.feature.folders_chips.api.ui.SharedNewFolderDialogUiState


/**
 * @Author maksonic on 03.07.2023
 */
interface FoldersApi {

    interface Ui {
        interface ChipsRow {
            val currentSelectedChipId: MutableState<Long>

            @Composable
            fun Widget(
                isLoading: State<Boolean>,
                chips: FolderUi.Collection,
                chipsRowOffsetHeightPx: MutableState<Float>,
                onAddNewChipClicked: () -> Unit,
            )
        }

        interface AddChipDialog {
            val sharedUiState: SharedUiState<SharedNewFolderDialogUiState>

            @Composable
            fun Widget()
        }

        interface FolderItem {
            @Composable
            fun Widget(
                isSelected: Boolean,
                folder: FolderUi,
                isTrashPlacement: Boolean,
                onFolderClicked: (id: Long) -> Unit,
                onFolderLongPressed: (id: Long) -> Unit,
                modifier: Modifier
            )
        }
    }
    object CurrentFolderKey {
        const val VALUE = "passedCurrentFolderId"
    }
}