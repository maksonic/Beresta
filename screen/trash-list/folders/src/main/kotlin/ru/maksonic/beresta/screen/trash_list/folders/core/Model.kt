package ru.maksonic.beresta.screen.trash_list.folders.core

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import ru.maksonic.beresta.elm.core.ElmBaseModel
import ru.maksonic.beresta.elm.core.ElmCommand
import ru.maksonic.beresta.elm.core.ElmEffect
import ru.maksonic.beresta.elm.core.ElmMessage
import ru.maksonic.beresta.elm.core.ElmModel
import ru.maksonic.beresta.feature.folders_chips.api.ui.FolderUi
import ru.maksonic.beresta.language_engine.shell.provider.AppLanguage

/**
 * @Author maksonic on 30.05.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
data class Model(
    val base: ElmBaseModel,
    val folders: FolderUi.Collection,
    val selectedList: Set<FolderUi>,
    val isSelectionState: Boolean,
    val modalBottomSheetState: SheetState,
    val currentClickedFolderId: Long?,
    val isVisibleAcceptDeleteFoldersDialog: Boolean,
    val isVisibleModalSheet: Boolean,
    val isSingleItemAction: Boolean,
    val currentAppLang: AppLanguage

) : ElmModel {
    companion object {
        val Initial = Model(
            base = ElmBaseModel.Loading,
            folders = FolderUi.Collection.Empty,
            selectedList = emptySet(),
            isSelectionState = false,
            modalBottomSheetState = SheetState(
                initialValue = SheetValue.Hidden,
                skipPartiallyExpanded = true
            ),
            currentClickedFolderId = null,
            isVisibleAcceptDeleteFoldersDialog = false,
            isVisibleModalSheet = false,
            isSingleItemAction = true,
            currentAppLang = AppLanguage.ENGLISH
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
        data class FetchedTrashDataResult(val folders: List<FolderUi>) : Inner()
        data class FetchedError(val message: String) : Inner()
        data class UpdatedModalSheetState(val isVisible: Boolean) : Inner()

    }
}

sealed class Cmd : ElmCommand {
    object FetchRemovedData : Cmd()
    data class DeleteOrRestoreFolders(
        val isRestore: Boolean,
        val folders: List<FolderUi>,
    ) :
        Cmd()
}

sealed class Eff : ElmEffect {
    object NavigateBack : Eff()
    object HideModalSheet : Eff()
}