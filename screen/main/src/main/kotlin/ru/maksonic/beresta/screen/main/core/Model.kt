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
import ru.maksonic.beresta.feature.edit_note.api.EditNoteFabState
import ru.maksonic.beresta.feature.edit_note.api.EditNoteUiFabState
import ru.maksonic.beresta.feature.folders_chips.api.ui.FolderUi
import ru.maksonic.beresta.feature.folders_chips.api.ui.FoldersListUiState
import ru.maksonic.beresta.feature.notes.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes.api.ui.NotesListUiState
import ru.maksonic.beresta.feature.search_bar.api.SearchBarUiState
import ru.maksonic.beresta.screen.main.ui.widget.bottombar.MainBottomBarState

/**
 * @Author maksonic on 22.06.2023
 */
enum class CurrentSheetContent {
    NOTHING, SORT_NOTES
}

@OptIn(ExperimentalMaterial3Api::class)
@Stable
data class ModalSheet(
    val isVisible: Boolean,
    val state: SheetState,
    val content: CurrentSheetContent
) {
    companion object {
        val Initial = ModalSheet(
            isVisible = false,
            state = SheetState(
                skipPartiallyExpanded = true,
                initialValue = SheetValue.Hidden
            ),
            content = CurrentSheetContent.NOTHING
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
    val editNoteFabState: EditNoteUiFabState,
    val isVisibleHiddenNotesDialog: Boolean,
) : ElmModel {
    companion object {
        val Initial = Model(
            base = ElmBaseModel.Initial,
            notes = NotesListUiState.Initial,
            chips = FoldersListUiState.Initial,
            modalSheet = ModalSheet.Initial,
            bottomBarState = MainBottomBarState.IDLE,
            searchBarState = SearchBarUiState.InitialMainNotes,
            editNoteFabState = EditNoteUiFabState.Initial,
            isVisibleHiddenNotesDialog = false
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        //notes
        data class OnNoteClicked(val id: Long) : Ui()
        data class OnNoteLongClicked(val id: Long) : Ui()
        data object CancelNotesSelection : Ui()
        //chips
        data object OnRetryFetchChipsClicked : Ui()

        //idle bottom bar actions
        data object OnBottomBarSettingsClicked : Ui()
        data object OnBottomBarTrashClicked : Ui()
        data object OnBottomBarFoldersClicked : Ui()
        data object OnBottomBarSortNotesClicked : Ui()

        //selected bottom bar actions
        data object OnBottomBarHideSelectedNotesClicked : Ui()
        data object OnBottomBarPinSelectedNotesClicked : Ui()
        data object OnBottomBarMoveSelectedNotesClicked : Ui()
        data object OnBottomBarRemoveSelectedNotesClicked : Ui()

        //search bar
        data object OnCollapseSearchBar : Ui()
        data object OnExpandSearchBar : Ui()
        data object OnCounterBarShareClicked : Ui()
        data class OnCounterBarSelectAllClicked(val currentFolderId: Long) : Ui()
        data class OnChangeGridViewClicked(val count: Int) : Ui()

        //all
        data object OnHideModalBottomSheet : Ui()
        data object OnAddNewChipClicked : Ui()
        data object OnHideHiddenNotesDialogClicked : Ui()

        //snack bar
        data object OnSnackUndoRemoveNotesClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedNotesData(val notes: NoteUi.Collection) : Inner()
        data class FetchedNotesError(val errorMsg: String = "") : Inner()
        data class FetchedChipsData(val chips: FolderUi.Collection) : Inner()
        data object FetchedChipsError : Inner()
        data object HiddenModalBottomSheet : Inner()
        data object HiddenRemovedNotesSnackBar : Inner()
        data object NavigatedToHiddenNotes : Inner()
        data class UpdatedEditNoteFabState(val state: EditNoteFabState) : Inner()
        data object ResetCurrentSelectedFolder : Inner()
    }
}

sealed class Cmd : ElmCommand {
    data object FetchNotesListFeatureState : Cmd()
    data object FetchNotesData : Cmd()
    data class UpdateNotesGridDatastoreState(val count: Int) : Cmd()
    data class RemoveSelectedNotes(val notes: List<NoteUi>) : Cmd()
    data class UndoRemoveNotes(val notes: List<NoteUi>) : Cmd()
    data class UpdatePinnedNotesInCache(val pinned: Set<NoteUi>) : Cmd()
    data object FetchChipsData : Cmd()
    data object FetchChipsSortState : Cmd()
    data object ResetCurrentSelectedFolder : Cmd()
}

sealed class Eff : ElmEffect {
    data class NavigateToEditNote(val id: Long) : Eff()
    data object HideModalSheet : Eff()
    data object NavigateToSettings : Eff()
    data class NavigateToFolders(val ids: List<Long>) : Eff()
    data object NavigateToTrash : Eff()
    data class NavigateToHiddenNotes(val ids: List<Long>) : Eff()
    data object ShowAddNewChipDialog : Eff()
    data object ShowedHiddenNotesEnterPasswordDialog : Eff()
}