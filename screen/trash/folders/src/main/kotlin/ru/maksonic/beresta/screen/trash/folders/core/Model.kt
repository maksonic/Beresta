package ru.maksonic.beresta.screen.trash.folders.core

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.feature.folders_list.ui.api.FolderUi
import ru.maksonic.beresta.platform.elm.core.ElmBaseModel
import ru.maksonic.beresta.platform.elm.core.ElmCommand
import ru.maksonic.beresta.platform.elm.core.ElmEffect
import ru.maksonic.beresta.platform.elm.core.ElmMessage
import ru.maksonic.beresta.platform.elm.core.ElmModel

/**
 * @Author maksonic on 30.05.2023
 */
@Stable
@Immutable
data class ModalSheet(
    val isVisible: Boolean,
    val skipPartiallyExpanded: Boolean,
) {
    companion object {
        val Initial = ModalSheet(isVisible = false, skipPartiallyExpanded = true)
    }
}

data class Model(
    val base: ElmBaseModel,
    val modalSheet: ModalSheet,
    val folders: FolderUi.Collection,
    val isSelection: Boolean,
    val currentClickedFolderId: Long?,
    val isVisibleAcceptDeleteFoldersDialog: Boolean,
    val isSingleSelection: Boolean
) : ElmModel {
    companion object {
        val Initial = Model(
            base = ElmBaseModel.Loading,
            modalSheet = ModalSheet.Initial,
            folders = FolderUi.Collection.Empty,
            isSelection = false,
            currentClickedFolderId = null,
            isVisibleAcceptDeleteFoldersDialog = false,
            isSingleSelection = true,
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        data object OnRetryFetchDataClicked : Ui()
        data object OnTopBarBackPressed : Ui()
        data class OnFolderClicked(val id: Long) : Ui()
        data class OnFolderLongClicked(val id: Long) : Ui()
        data object CancelSelectionState : Ui()
        data object OnSelectAllFoldersClicked : Ui()
        data object HideModalBottomSheet : Ui()
        data object OnModalSheetRestoreClicked : Ui()
        data object OnModalSheetDeleteClicked : Ui()
        data object OnBottomBarRestoreFoldersClicked : Ui()
        data object OnBottomBarDeleteFoldersClicked : Ui()
        data object OnAcceptDeleteWarningDialogClicked : Ui()
        data object OnCancelDeleteWarningDialogClicked : Ui()
        data object OnDeleteAllFoldersClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedDataResult(val folders: List<FolderUi>) : Inner()
        data class FetchedDataFail(val message: String = "Error") : Inner()
        data class UpdatedModalSheetState(val isVisible: Boolean) : Inner()
    }
}

sealed class Cmd : ElmCommand {
    data object FetchData : Cmd()
    data object RetryFetchData : Cmd()
    data class DeleteFolders(val folders: List<FolderUi>) : Cmd()
    data class RestoreFolders(val folders: List<FolderUi>) : Cmd()
}

sealed class Eff : ElmEffect {
    data object NavigateBack : Eff()
    data object HideModalSheet : Eff()
}