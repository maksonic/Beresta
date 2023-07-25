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
                initialValue = SheetValue.Hidden,
                skipPartiallyExpanded = true
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
        data class OnBottomBarRemoveSelectedClicked(val currentSelectedFolderId: Long) : Ui()
        object OnBottomBarEditSelectedClicked : Ui()
        object OnSnackUndoRemoveFoldersClicked : Ui()
        object OnHideModalBottomSheet : Ui()
        object OnToHiddenNotesClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedFoldersData(
            val isMoveNotesToFolderState: Boolean,
            val folders: FolderUi.Collection
        ) : Inner()

        data class FetchedDataError(val errorMsg: String = "") : Inner()
        object HideRemovedFoldersSnackBar : Inner()
        object HiddenModalBottomSheet : Inner()
        object NavigatedToHiddenNotes : Inner()
    }
}

sealed class Cmd : ElmCommand {
    object FetchFoldersWithNotes : Cmd()
    object RetryFetchFoldersWithNotes : Cmd()
    data class RemoveSelected(val removed: List<FolderUi>) : Cmd()
    data class UndoRemovedFolders(val removed: List<FolderUi>) : Cmd()
    data class UpdatePinnedFoldersInCache(val pinned: Set<FolderUi>) : Cmd()
    data class ChangeNoteFolderId(val folderId: Long) : Cmd()
    data class UpdateCurrentSelectedFolder(val id: Long) : Cmd()
}

sealed class Eff : ElmEffect {
    object NavigateBack : Eff()
    object ShowedHiddenNotesEnterPasswordDialog : Eff()
    object NavigateToHiddenNotes : Eff()
    data class ShowFolderDialog(val id: Long = 0L) : Eff()
    object HideModalSheet : Eff()
}