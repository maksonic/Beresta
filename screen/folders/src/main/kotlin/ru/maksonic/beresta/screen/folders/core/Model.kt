package ru.maksonic.beresta.screen.folders.core

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
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
enum class ModalSheetContent {
    NOTHING, SORT_FOLDERS
}

@OptIn(ExperimentalMaterial3Api::class)
@Stable
data class ModalSheet(
    val isVisible: Boolean,
    val state: SheetState,
    val content: ModalSheetContent
) {
    companion object {
        val Initial = ModalSheet(
            isVisible = false,
            state = SheetState(
                skipPartiallyExpanded = true,
                initialValue = SheetValue.Hidden
            ),
            content = ModalSheetContent.NOTHING
        )
    }
}

@Stable
@Immutable
data class Model(
    val base: ElmBaseModel,
    val modalSheet: ModalSheet,
    val folders: FolderUi.Collection,
    val selectedList: Set<FolderUi>,
    val removedList: Set<FolderUi> = emptySet(),
    val isSelectionState: Boolean,
    val isVisibleUnpinBottomBarIcon: Boolean,
    val isVisibleRemovedSnackBar: Boolean,
    val isMoveNotesToFolder: Boolean,
    val isVisibleHiddenNotesDialog: Boolean
) : ElmModel {

    companion object {
        val Initial = Model(
            base = ElmBaseModel.Loading,
            modalSheet = ModalSheet.Initial,
            folders = FolderUi.Collection.Empty,
            selectedList = emptySet(),
            isSelectionState = false,
            isVisibleUnpinBottomBarIcon = false,
            isVisibleRemovedSnackBar = false,
            isMoveNotesToFolder = false,
            isVisibleHiddenNotesDialog = false
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        data object RetryFetchData : Ui()
        data object OnTopBarBackPressed : Ui()
        data object OnTopBarSortFolderClicked : Ui()
        data class OnFolderClicked(val id: Long) : Ui()
        data class OnFolderLongPressed(val id: Long) : Ui()
        data object CancelSelectionState : Ui()
        data object OnTopBarSelectAllClicked : Ui()
        data object OnAddNewFolderClicked : Ui()
        data object OnBottomBarPinSelectedClicked : Ui()
        data class OnBottomBarRemoveSelectedClicked(val currentSelectedFolderId: Long) : Ui()
        data object OnBottomBarEditSelectedClicked : Ui()
        data object OnSnackUndoRemoveFoldersClicked : Ui()
        data object OnHideModalBottomSheet : Ui()
        data object OnToHiddenNotesClicked : Ui()
        data object OnHideHiddenNotesDialogClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedFoldersData(
            val isMoveNotesToFolderState: Boolean,
            val folders: FolderUi.Collection
        ) : Inner()

        data class FetchedDataError(val errorMsg: String = "") : Inner()
        data object HideRemovedFoldersSnackBar : Inner()
        data object HiddenModalBottomSheet : Inner()
        data object NavigatedToHiddenNotes : Inner()
    }
}

sealed class Cmd : ElmCommand {
    data object FetchFoldersWithNotes : Cmd()
    data object RetryFetchFoldersWithNotes : Cmd()
    data class RemoveSelected(val removed: List<FolderUi>) : Cmd()
    data class UndoRemovedFolders(val removed: List<FolderUi>) : Cmd()
    data class UpdatePinnedFoldersInCache(val pinned: Set<FolderUi>) : Cmd()
    data class ChangeNoteFolderId(val folderId: Long) : Cmd()
    data class UpdateCurrentSelectedFolder(val id: Long) : Cmd()
}

sealed class Eff : ElmEffect {
    data object NavigateBack : Eff()
    data object NavigateToHiddenNotes : Eff()
    data object AddNewFolder : Eff()
    data class UpdateFolder(val id: Long): Eff()
    data object HideModalSheet : Eff()
}