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
    val isVisibleNewFolderDialog: Boolean,
    val currentSelectedFolderId: Long,
    val isSelectionState: Boolean
) : ElmModel {
    companion object {
        val Initial = Model(
            base = BaseModel.InitialWithLoading,
            folders = FilterChipUi.Collection.Empty,
            isVisibleNewFolderDialog = false,
            currentSelectedFolderId = FilterChipUi.InitialSelected.id,
            isSelectionState = false
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        object OnTopBarBackPressed: Ui()
        data class OnFolderClicked(val id: Long) : Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedFoldersResult(val folders: FilterChipUi.Collection) : Inner()
        data class UpdatedNewFolderDialogVisibility(val isVisible: Boolean): Inner()
        data class UpdateCurrentSelectedFolder(val id: Long): Inner()
    }
}

sealed class Cmd : ElmCommand {
    object FetchFolders : Cmd()
}

sealed class Eff : ElmEffect {
    object NavigateBack : Eff()
    data class UpdateFolderSelection(val id: Long): Eff()
}