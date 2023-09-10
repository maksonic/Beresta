package ru.maksonic.beresta.feature.folders_chips.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.elm.core.ElmBaseModel
import ru.maksonic.beresta.feature.folders_chips.api.ui.FolderUi
import ru.maksonic.beresta.feature.folders_chips.api.ui.SharedNewFolderDialogUiState

/**
 * @Author maksonic on 03.07.2023
 */
interface FoldersApi {
    interface ChipsRow {
        interface Ui {
            val currentSelectedId: State<Long>
            fun updateCurrent(id: Long)

            @Composable
            fun Widget(
                state: ElmBaseModel,
                isColoredBackground: State<Boolean>,
                chips: FolderUi.Collection,
                chipsRowOffsetHeightPx: State<Float>,
                onAddNewChipClicked: () -> Unit,
                onRetryFetchClicked: () -> Unit
            )
        }
    }

    interface AddChipDialog {
        interface Ui {
            val sharedState: State<SharedNewFolderDialogUiState>
            fun hideDialog()
            fun addFolder()
            fun updateFolder(id: Long)

            @Composable
            fun Widget()
        }
    }

    interface FolderItem {
        interface Ui {
            @Composable
            fun Widget(
                folder: FolderUi,
                isSelected: Boolean,
                isCurrent: Boolean,
                isTrashPlacement: Boolean,
                onFolderClicked: (id: Long) -> Unit,
                onFolderLongPressed: (id: Long) -> Unit,
                modifier: Modifier
            )
        }
    }

    interface ListPlaceholder {
        interface Ui {
            @Composable
            fun Placeholder(modifier: Modifier)

            @Composable
            fun PlaceholderTrash(modifier: Modifier)
        }
    }
}