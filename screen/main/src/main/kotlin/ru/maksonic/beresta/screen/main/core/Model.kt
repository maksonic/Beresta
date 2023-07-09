package ru.maksonic.beresta.screen.main.core

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
import ru.maksonic.beresta.feature.folders_chips.api.ui.FoldersListUiState
import ru.maksonic.beresta.feature.notes.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes.api.ui.NotesListUiState
import ru.maksonic.beresta.feature.search_bar.api.SearchBarUiState
import ru.maksonic.beresta.screen.main.ui.widget.bottombar.MainBottomBarState

/**
 * @Author maksonic on 22.06.2023
 */
enum class CurrentMainSheetContent {
    NOTHING, SORT_NOTES
}

@OptIn(ExperimentalMaterial3Api::class)
@Stable
data class ModalSheet(
    val isVisible: Boolean,
    val state: SheetState,
    val content: CurrentMainSheetContent
) {
    companion object {
        val Initial = ModalSheet(
            isVisible = false,
            state = SheetState(
                initialValue = SheetValue.Hidden,
                skipPartiallyExpanded = true
            ),
            content = CurrentMainSheetContent.NOTHING
        )
    }
}

@Stable
@Immutable
data class Model(
    val base: ElmBaseModel,
    val notes: NotesListUiState,
    val chips: FoldersListUiState,
    val modalSheet: ModalSheet,
    val bottomBarState: MainBottomBarState,
    val searchBarState: SearchBarUiState,
    val isVisibleEditFab: Boolean,
) : ElmModel {
    companion object {
        val Initial = Model(
            base = ElmBaseModel.Initial,
            notes = NotesListUiState.Initial,
            chips = FoldersListUiState.Initial,
            modalSheet = ModalSheet.Initial,
            bottomBarState = MainBottomBarState.IDLE,
            searchBarState = SearchBarUiState.Initial,
            isVisibleEditFab = true,
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        //notes
        data class OnNoteClicked(val id: Long) : Ui()
        data class OnNoteLongClicked(val id: Long) : Ui()
        object CancelNotesSelection : Ui()
        //idle bottom bar actions
        object OnBottomBarSettingsClicked : Ui()
        object OnBottomBarTrashClicked : Ui()
        object OnBottomBarFoldersClicked : Ui()
        object OnBottomBarSortNotesClicked : Ui()
        //selected bottom bar actions
        object OnBottomBarHideSelectedNotesClicked : Ui()
        object OnBottomBarPinSelectedNotesClicked : Ui()
        object OnBottomBarMoveSelectedNotesClicked : Ui()
        object OnBottomBarRemoveSelectedNotesClicked : Ui()
        //search bar
        object OnCollapseSearchBar : Ui()
        object OnExpandSearchBar : Ui()
        object OnCounterBarShareClicked : Ui()
        object OnCounterBarSelectAllClicked : Ui()
        data class OnChangeGridViewClicked(val count: Int) : Ui()
        //all
        object OnHideModalBottomSheet : Ui()
        object OnAddNewChipClicked : Ui()
        //snack bar
        object OnSnackUndoRemoveNotesClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedNotesData(val notes: NoteUi.Collection) : Inner()
        data class FetchedNotesError(val errorMsg: String = "") : Inner()
        object HiddenModalBottomSheet : Inner()
        object HideRemovedNotesSnackBar : Inner()
        data class FetchedChipsData(val chips: FolderUi.Collection) : Inner()
        data class UpdatedCurrentSelectedChipId(val id: Long) : Inner()
    }
}

sealed class Cmd : ElmCommand {
    object FetchNotesListFeatureState : Cmd()
    object FetchNotesData : Cmd()
    data class UpdateNotesGridDatastoreState(val count: Int) : Cmd()
    data class RemoveSelectedNotes(val notes: List<NoteUi>) : Cmd()
    data class UndoRemoveNotes(val notes: List<NoteUi>) : Cmd()
    data class UpdatePinnedNotesInCache(val pinned: Set<NoteUi>) : Cmd()
    object FetchChipsData : Cmd()
}

sealed class Eff : ElmEffect {
    data class NavigateToEditNote(val id: Long) : Eff()
    object HideModalSheet : Eff()
    object NavigateToSettings : Eff()
    data class NavigateToFolders(val ids: List<Long>) : Eff()
    object ShowAddNewChipDialog : Eff()

}