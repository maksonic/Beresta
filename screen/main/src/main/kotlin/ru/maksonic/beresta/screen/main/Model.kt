package ru.maksonic.beresta.screen.main

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.BaseModel
import ru.maksonic.beresta.elm.ElmCommand
import ru.maksonic.beresta.elm.ElmEffect
import ru.maksonic.beresta.elm.ElmMessage
import ru.maksonic.beresta.elm.ElmModel
import ru.maksonic.beresta.feature.notes.folders.api.ui.NoteFolderUi
import ru.maksonic.beresta.feature.notes.list.api.ui.MainBottomBarState
import ru.maksonic.beresta.feature.notes.list.api.ui.SortedNotes
import ru.maksonic.beresta.language_engine.shell.provider.AppLanguage

/**
 * @Author maksonic on 25.04.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Stable
@Immutable
data class Model(
    val base: BaseModel,
    val errorMsg: String,
    val isVisibleBottomBar: Boolean,
    val bottomBarState: MainBottomBarState,
    val isShowBottomBarUnpinBtn: Boolean,
    val filters: NoteFolderUi.Collection,
    val currentSelectedFolderId: Long,
    val isVisibleModalSheet: Boolean,
    val modalBottomSheetState: SheetState,
    val currentLang: AppLanguage
) : ElmModel {
    companion object {
        val Initial = Model(
            base = BaseModel.InitialWithLoading,
            errorMsg = "",
            isVisibleBottomBar = true,
            bottomBarState = MainBottomBarState.IDLE,
            isShowBottomBarUnpinBtn = false,
            filters = NoteFolderUi.Collection.Empty,
            currentSelectedFolderId = 1L,
            isVisibleModalSheet = false,
            modalBottomSheetState = SheetState(
                initialValue = SheetValue.Hidden,
                skipPartiallyExpanded = true
            ),
            currentLang = AppLanguage.ENGLISH
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        object OnCreateNewNoteClicked : Ui()
        data class OnChipFilterClicked(val id: Long) : Ui()
        object OnBottomBarSettingsClicked : Ui()
        object OnBottomBarTrashClicked : Ui()
        object OnBottomBarFoldersClicked : Ui()
        object OnBottomBarSortNotesByClicked : Ui()
        object OnSwitchViewClicked : Ui()
        object OnShareSelectedNotes : Ui()
        object OnHideModalBottomSheet : Ui()
    }

    sealed class Inner : Msg() {
        data class UpdateBottomPanelState(val isSelectionState: Boolean) : Inner()
        data class FetchedChipsResult(val chips: List<NoteFolderUi>) : Inner()
        data class UpdateCurrentSelectedFolder(val id: Long) : Inner()
        data class UpdatedModalSheetState(val isVisible: Boolean) : Inner()
        data class FetchedCurrentAppLang(val language: AppLanguage): Inner()
    }
}

sealed class Cmd : ElmCommand {
    object FetchCurrentAppLang : Cmd()
    object FetchFoldersChips : Cmd()
}

sealed class Eff : ElmEffect {
    object NavigateToAddNewNote : Eff()
    object NavigateToSettings : Eff()
    object NavigateToTrash : Eff()
    data class ShowNoteForEdit(val id: Long) : Eff()
    data class UpdateCurrentSelectedFolderInSharedState(val id: Long) : Eff()
    data class NavigateToFoldersList(val currentSelectedFolderId: Long) : Eff()
    object HideModalSheet : Eff()
}