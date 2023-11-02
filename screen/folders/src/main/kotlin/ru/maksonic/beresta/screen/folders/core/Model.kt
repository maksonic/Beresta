package ru.maksonic.beresta.screen.folders.core

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.common.ui_kit.bar.snackbar.SnackBarHostState
import ru.maksonic.beresta.feature.folders_list.ui.api.FolderUi
import ru.maksonic.beresta.feature.folders_list.ui.api.FoldersListUiState
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.FoldersSortUi
import ru.maksonic.beresta.platform.elm.core.ElmCommand
import ru.maksonic.beresta.platform.elm.core.ElmEffect
import ru.maksonic.beresta.platform.elm.core.ElmMessage
import ru.maksonic.beresta.platform.elm.core.ElmModel

/**
 * @Author maksonic on 03.04.2023
 */
enum class ModalSheetContent {
    NOTHING, SORT_FOLDERS
}

@Stable
@Immutable
data class ModalSheet(
    val isVisible: Boolean,
    val skipPartiallyExpanded: Boolean,
    val content: ModalSheetContent
) {
    companion object {
        val Initial = ModalSheet(
            isVisible = false,
            skipPartiallyExpanded = true,
            content = ModalSheetContent.NOTHING
        )
    }
}

@Stable
@Immutable
data class Model(
    val modalSheet: ModalSheet,
    val folders: FoldersListUiState,
    val sortState: FoldersSortUi,
    val snackState: SnackBarHostState,
    val isVisibleEditFolderDialog: Boolean,
    val isVisibleHiddenNotesDialog: Boolean,
    val isVisibleUnpinBottomBarIcon: Boolean,
) : ElmModel {

    companion object {
        val Initial = Model(
            modalSheet = ModalSheet.Initial,
            folders = FoldersListUiState.Initial,
            sortState = FoldersSortUi.Default,
            snackState = SnackBarHostState(),
            isVisibleEditFolderDialog = false,
            isVisibleHiddenNotesDialog = false,
            isVisibleUnpinBottomBarIcon = false,
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        data object OnRetryFetchDataClicked : Ui()
        data object OnTopBarBackPressed : Ui()
        data object OnTopBarSortFolderClicked : Ui()
        data class OnFolderClicked(val id: Long) : Ui()
        data class OnFolderLongClicked(val id: Long) : Ui()
        data object CancelSelectionState : Ui()
        data object OnTopBarSelectAllClicked : Ui()
        data object OnAddNewFolderClicked : Ui()
        data object OnCloseEditFolderDialogClicked : Ui()
        data object OnBottomBarPinSelectedClicked : Ui()
        data class OnBottomBarRemoveSelectedClicked(val currentSelectedFolderId: Long) : Ui()
        data object OnBottomBarEditSelectedClicked : Ui()
        data object OnSnackUndoRemoveFoldersClicked : Ui()
        data object OnHideModalBottomSheet : Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedDataSuccess(
            val isNotesMoving: Boolean,
            val folders: FolderUi.Collection,
            val sortState: FoldersSortUi
        ) : Inner()

        data class FetchedDataFail(val errorMsg: String = "Error") : Inner()
        data object HiddenModalBottomSheet : Inner()
        data object NavigatedToHiddenNotes : Inner()
        data class UpdatedHiddenNotesDialogVisibility(val isVisible: Boolean): Inner()
    }
}

sealed class Cmd : ElmCommand {
    data object FetchFoldersWithNotes : Cmd()
    data object RetryFetchData : Cmd()
    data class RemoveSelected(val removed: List<FolderUi>) : Cmd()
    data class UndoRemovedFolders(val removed: List<FolderUi>) : Cmd()
    data class UpdatePinnedFolders(val pinned: List<FolderUi>) : Cmd()
    data class ChangeNoteFolderId(val folderId: Long) : Cmd()
    data class UpdateCurrentSelectedFolder(val id: Long) : Cmd()
}

sealed class Eff : ElmEffect {
    data object NavigateBack : Eff()
    data object NavigateToHiddenNotes : Eff()
    data object AddNewFolder : Eff()
    data class UpdateFolder(val id: Long) : Eff()
    data object HideModalSheet : Eff()
    data class ShowSnackBar(val message: String) : Eff()
}