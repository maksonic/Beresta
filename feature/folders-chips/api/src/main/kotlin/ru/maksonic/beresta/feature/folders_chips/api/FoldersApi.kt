package ru.maksonic.beresta.feature.folders_chips.api

import androidx.compose.runtime.Composable
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
            val currentSelectedId: SharedUiState<Long>

            @Composable
            fun Widget(
                isLoading: State<Boolean>,
                isColoredBackground: State<Boolean>,
                chips: FolderUi.Collection,
                chipsRowOffsetHeightPx: State<Float>,
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
        interface Placeholder {
            @Composable
            fun List(modifier: Modifier)

            @Composable
            fun TrashList(modifier: Modifier)
        }
    }
}