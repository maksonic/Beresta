package ru.maksonic.beresta.feature.folders_list.core.screen.core

import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.*
import ru.maksonic.beresta.feature.folders_list.api.ui.FilterChipUi

/**
 * @Author maksonic on 03.04.2023
 */
@Stable
data class Model(
    val base: BaseModel,
    val folders: FilterChipUi.Collection,
    val selectedFolders: Set<FilterChipUi>,
    val removedFolders: Set<FilterChipUi>,
    val isVisibleNewFolderDialog: Boolean,
    val currentSelectedFolderId: Long,
    val isSelectionState: Boolean,
    val isVisibleSelectionPanel: Boolean,
    val selectedFoldersCount: Int,
    val isShowBottomBarUnpinBtn: Boolean,
    val isVisibleUndoRemoveNotesSnack: Boolean,
) : ElmModel {

    companion object {
        val Initial = Model(
            base = BaseModel.InitialWithLoading,
            folders = FilterChipUi.Collection.Empty,
            selectedFolders = emptySet(),
            removedFolders = emptySet(),
            isVisibleNewFolderDialog = false,
            currentSelectedFolderId = FilterChipUi.InitialSelected.id,
            isSelectionState = false,
            isVisibleSelectionPanel = false,
            selectedFoldersCount = 0,
            isShowBottomBarUnpinBtn = false,
            isVisibleUndoRemoveNotesSnack = false,
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        object OnTopBarBackPressed: Ui()
        data class OnFolderClicked(val id: Long) : Ui()
        data class OnFolderLongPressed(val id: Long) : Ui()
        object OnSelectAllFoldersClicked : Ui()
        object OnCancelSelectionClicked : Ui()
        object OnPinSelectedFoldersClicked : Ui()
        object OnEditFolderClicked: Ui()
        object OnRemoveSelectedFoldersClicked : Ui()
        object OnAddFolderClicked : Ui()
     //   object OnRemoveFoldersUndoClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedFoldersResult(val folders: List<FilterChipUi>) : Inner()
        data class UpdatedNewFolderDialogVisibility(val isVisible: Boolean): Inner()
        data class UpdateCurrentSelectedFolder(val id: Long): Inner()
        object HideRemovedFoldersSnack : Inner()
    }
}

sealed class Cmd : ElmCommand {
    object FetchFolders : Cmd()
    data class UpdatePinnedFoldersInCache(val pinned: List<FilterChipUi>) : Cmd()
    data class RemoveSelectedFolders(val folders: List<FilterChipUi>) : Cmd()
    data class UndoRemoved(val folders: List<FilterChipUi>) : Cmd()
}

sealed class Eff : ElmEffect {
    object NavigateBack : Eff()
    data class UpdateFolderSelection(val id: Long): Eff()
    data class UpdatePassedEditableFolderId(val id: Long): Eff()
    object ShowFolderDialog : Eff()
}