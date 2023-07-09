package ru.maksonic.beresta.screen.folders.core

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.core.ElmBaseModel
import ru.maksonic.beresta.elm.core.ElmCommand
import ru.maksonic.beresta.elm.core.ElmEffect
import ru.maksonic.beresta.elm.core.ElmMessage
import ru.maksonic.beresta.elm.core.ElmModel
import ru.maksonic.beresta.feature.folders_chips.api.ui.FolderUi

/**
 * @Author maksonic on 03.04.2023
 */
@Stable
@Immutable
data class Model(
    val base: ElmBaseModel,
    val folders: FolderUi.Collection,
    val selectedList: Set<FolderUi>,
    val removedList: Set<FolderUi> = emptySet(),
    val isSelectionState: Boolean,
    val currentSelectedFolderId: Long,
    val isVisibleUnpinBottomBarIcon: Boolean,
    val isVisibleRemovedSnackBar: Boolean,
    val isMoveNotesToFolder: Boolean,
) : ElmModel {

    companion object {
        val Initial = Model(
            base = ElmBaseModel.Loading,
            folders = FolderUi.Collection.Empty,
            selectedList = emptySet(),
            isSelectionState = false,
            currentSelectedFolderId = 1L,
            isVisibleUnpinBottomBarIcon = false,
            isVisibleRemovedSnackBar = false,
            isMoveNotesToFolder = false,
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        object RetryFetchData : Ui()
        object OnTopBarBackPressed : Ui()
        object OnTopBarSortFolderClicked : Ui()
        data class OnFolderClicked(val id: Long) : Ui()
        data class OnFolderLongPressed(val id: Long) : Ui()
        object CancelSelectionState : Ui()
        object OnTopBarSelectAllClicked : Ui()
        object OnAddNewFolderClicked : Ui()
        object OnBottomBarPinSelectedClicked : Ui()
        object OnBottomBarRemoveSelectedClicked : Ui()
        object OnBottomBarEditSelectedClicked : Ui()
        object OnSnackUndoRemoveFoldersClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedFoldersData(
            val currentSelectedFolderId: Long,
            val isMoveNotesToFolderState: Boolean,
            val folders: FolderUi.Collection
        ) : Inner()

        data class FetchedDataError(val errorMsg: String = "") : Inner()
        object HideRemovedFoldersSnackBar : Inner()
    }
}

sealed class Cmd : ElmCommand {
    object FetchFoldersWithNotes : Cmd()
    data class RemoveSelected(val selectedFolders: List<FolderUi>) : Cmd()
    data class UndoRemovedFolders(val folders: List<FolderUi>) : Cmd()
    data class UpdatePinnedFoldersInCache(val pinned: Set<FolderUi>) : Cmd()
    data class ChangeNoteFolderId(val folderId: Long) : Cmd()
}

sealed class Eff : ElmEffect {
    object NavigateBack : Eff()
    data class ShowFolderDialog(val id: Long = 0L) : Eff()
    data class UpdateCurrentSelectedFolderInSharedState(val id: Long) : Eff()
}