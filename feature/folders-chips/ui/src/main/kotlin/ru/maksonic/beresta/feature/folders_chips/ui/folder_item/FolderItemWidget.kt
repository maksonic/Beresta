package ru.maksonic.beresta.feature.folders_chips.ui.folder_item

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.folders_chips.api.FoldersApi
import ru.maksonic.beresta.feature.folders_chips.api.ui.FolderUi

/**
 * @Author maksonic on 04.07.2023
 */
class FolderItemWidget : FoldersApi.FolderItem.Ui {

    @Composable
    override fun Widget(
        folder: FolderUi,
        isSelected: Boolean,
        isCurrent: Boolean,
        isTrashPlacement: Boolean,
        onFolderClicked: (id: Long) -> Unit,
        onFolderLongPressed: (id: Long) -> Unit,
        modifier: Modifier
    ) {
        Container(
            isSelected = isSelected,
            isCurrent = isCurrent,
            folder = folder,
            isTrashPlacement = isTrashPlacement,
            onFolderClicked = onFolderClicked,
            onFolderLongPressed = onFolderLongPressed
        )
    }
}