package ru.maksonic.beresta.screen.main

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.BaseModel
import ru.maksonic.beresta.elm.ElmCommand
import ru.maksonic.beresta.elm.ElmEffect
import ru.maksonic.beresta.elm.ElmMessage
import ru.maksonic.beresta.elm.ElmModel
import ru.maksonic.beresta.feature.notes.folders.api.ui.NoteFolderUi
import ru.maksonic.beresta.feature.notes.list.api.ui.MainBottomBarState

/**
 * @Author maksonic on 25.04.2023
 */
@Stable
@Immutable
data class Model(
    val base: BaseModel = BaseModel.InitialWithLoading,
    val errorMsg: String = "",
    val isVisibleBottomBar: Boolean = true,
    val bottomBarState: MainBottomBarState = MainBottomBarState.IDLE,
    val isShowBottomBarUnpinBtn: Boolean = false,
    val filters: NoteFolderUi.Collection = NoteFolderUi.Collection.Empty,
    val currentSelectedFolderId: Long = 1L,
    ) : ElmModel

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        object OnCreateNewNoteClicked : Ui()
        data class OnChipFilterClicked(val id: Long) : Ui()

        // Idle bottom panel messages
        object OnBottomBarSettingsClicked : Ui()
        object OnBottomBarTrashClicked : Ui()
        object OnBottomBarFoldersClicked : Ui()
        object OnBottomBarSortNotesByClicked : Ui()
        object OnSwitchViewClicked : Ui()
        object OnShareSelectedNotes : Ui()
    }

    sealed class Inner : Msg() {
        data class UpdateBottomPanelState(val isSelectionState: Boolean): Inner()
        data class FetchedChipsResult(val chips: List<NoteFolderUi>) : Inner()
        data class UpdateCurrentSelectedFolder(val id: Long): Inner()

    }
}

sealed class Cmd : ElmCommand {
    object FetchFoldersChips : Cmd()
}

sealed class Eff : ElmEffect {
    object NavigateToAddNewNote : Eff()
    object NavigateToSettings : Eff()
    object NavigateToTrash : Eff()
    data class ShowNoteForEdit(val id: Long) : Eff()
    data class UpdateCurrentSelectedFolderInSharedState(val id: Long) : Eff()
    data class NavigateToFoldersList(val currentSelectedFolderId: Long) : Eff()
}