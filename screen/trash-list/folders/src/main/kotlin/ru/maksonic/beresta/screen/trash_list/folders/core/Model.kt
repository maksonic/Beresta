package ru.maksonic.beresta.screen.trash_list.folders.core

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import ru.maksonic.beresta.elm.BaseModel
import ru.maksonic.beresta.elm.ElmCommand
import ru.maksonic.beresta.elm.ElmEffect
import ru.maksonic.beresta.elm.ElmMessage
import ru.maksonic.beresta.elm.ElmModel
import ru.maksonic.beresta.feature.notes.folders.api.ui.NoteFolderUi
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteUi

/**
 * @Author maksonic on 30.05.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
data class Model(
    val base: BaseModel,
    val folders: NoteFolderUi.Collection,
    val notes: List<NoteUi>,
    val selectedFolders: Set<NoteFolderUi>,
    val isSelectionState: Boolean,
    val modalBottomSheetState: SheetState,
    val currentClickedFolderId: Long?,
    val isVisibleAcceptDeleteFoldersDialog: Boolean,
    val isVisibleModalSheet: Boolean,
    val isSingleItemAction: Boolean,
) : ElmModel {
    companion object {
        val Initial = Model(
            base = BaseModel.InitialWithLoading,
            folders = NoteFolderUi.Collection.Empty,
            notes = emptyList(),
            selectedFolders = emptySet(),
            isSelectionState = false,
            modalBottomSheetState = SheetState(
                initialValue = SheetValue.Hidden,
                skipPartiallyExpanded = true
            ),
            currentClickedFolderId = null,
            isVisibleAcceptDeleteFoldersDialog = false,
            isVisibleModalSheet = false,
            isSingleItemAction = true
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        object OnTopBarBackPressed : Ui()
        data class OnFolderClicked(val id: Long) : Ui()
        data class OnFolderLongClicked(val id: Long) : Ui()
        object CancelSelectionState : Ui()
        object OnSelectAllFoldersClicked : Ui()
        object HideModalBottomSheet : Ui()
        object OnModalSheetRestoreClicked : Ui()
        object OnModalSheetDeleteClicked : Ui()
        object OnBottomBarRestoreSelectedFoldersClicked : Ui()
        object OnBottomBarDeleteSelectedFoldersClicked : Ui()
        object OnAcceptDeleteWarningDialogClicked : Ui()
        object OnCancelDeleteWarningDialogClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedTrashDataResult(
            val folders: List<NoteFolderUi>,
            val notes: List<NoteUi>
        ) : Inner()

        data class FetchedError(val message: String) : Inner()
        data class UpdatedModalSheetState(val isVisible: Boolean) : Inner()
    }
}

sealed class Cmd : ElmCommand {
    object FetchRemovedData : Cmd()
    data class DeleteOrRestoreFolders(
        val isRestore: Boolean,
        val folders: List<NoteFolderUi>,
        val notes: List<NoteUi>
    ) :
        Cmd()
}

sealed class Eff : ElmEffect {
    object NavigateBack : Eff()
    object HideModalSheet : Eff()
}