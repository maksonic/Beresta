package ru.maksonic.beresta.screen.main.core

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.common.ui_kit.bar.snackbar.SnackBarHostState
import ru.maksonic.beresta.feature.folders_list.ui.api.FolderUi
import ru.maksonic.beresta.feature.folders_list.ui.api.FoldersListUiState
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUi
import ru.maksonic.beresta.feature.notes_list.ui.api.list.NotesListUiState
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.FoldersSortUi
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.NotesSortUi
import ru.maksonic.beresta.feature.ui.edit_note.api.EditNoteFabState
import ru.maksonic.beresta.feature.ui.edit_note.api.EditNoteUiFabState
import ru.maksonic.beresta.feature.ui.search_bar.api.SearchBarUiState
import ru.maksonic.beresta.platform.elm.core.ElmCommand
import ru.maksonic.beresta.platform.elm.core.ElmEffect
import ru.maksonic.beresta.platform.elm.core.ElmMessage
import ru.maksonic.beresta.platform.elm.core.ElmModel
import ru.maksonic.beresta.screen.main.ui.SnackBarKey

/**
 * @Author maksonic on 15.10.2023
 */
enum class ModalSheetContent {
    NOTHING, SORT_NOTES
}

@Stable
@Immutable
data class ModalSheet(
    val isVisible: Boolean,
    val skipPartiallyExpanded: Boolean,
    val content: ModalSheetContent
) {
    companion object {
        val Initial = ModalSheet(
            isVisible = false,
            skipPartiallyExpanded = true,
            content = ModalSheetContent.NOTHING
        )
    }
}

data class SortState(
    val notes: NotesSortUi,
    val chips: FoldersSortUi
) {
    companion object {
        val Initial = SortState(notes = NotesSortUi.Default, chips = FoldersSortUi.Default)
    }
}

@Stable
@Immutable
data class Model(
    val notes: NotesListUiState,
    val chips: FoldersListUiState,
    val modalSheet: ModalSheet,
    val editNoteFabState: EditNoteUiFabState,
    val searchBarState: SearchBarUiState,
    val snackNotesState: SnackBarHostState,
    val sortState: SortState,
    val isVisibleAddChipDialog: Boolean,
    val isVisibleHiddenNotesDialog: Boolean,
) : ElmModel {

    companion object {
        val Initial = Model(
            notes = NotesListUiState.Initial,
            chips = FoldersListUiState.Initial,
            modalSheet = ModalSheet.Initial,
            editNoteFabState = EditNoteUiFabState.Initial,
            searchBarState = SearchBarUiState.InitialMainNotes,
            snackNotesState = SnackBarHostState(),
            sortState = SortState.Initial,
            isVisibleAddChipDialog = false,
            isVisibleHiddenNotesDialog = false
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        // Notes
        data class OnNoteClicked(val id: Long) : Ui()
        data class OnNoteLongClicked(val id: Long) : Ui()
        data object CancelNotesSelection : Ui()

        // Folders
        data class OnChipClicked(val id: Long) : Ui()
        data object OnRetryFetchChipsClicked : Ui()

        // Idle bottom bar actions
        data object OnBottomBarSettingsClicked : Ui()
        data object OnBottomBarTrashClicked : Ui()
        data object OnBottomBarFoldersClicked : Ui()
        data object OnBottomBarSortNotesClicked : Ui()

        // Selected bottom bar actions
        data object OnBottomBarHideSelectedNotesClicked : Ui()
        data object OnBottomBarPinSelectedNotesClicked : Ui()
        data object OnBottomBarMoveSelectedNotesClicked : Ui()
        data object OnBottomBarRemoveSelectedNotesClicked : Ui()

        // Search bar
        data object OnCollapseSearchBar : Ui()
        data object OnExpandSearchBar : Ui()
        data object OnCounterBarShareClicked : Ui()
        data class OnCounterBarSelectAllClicked(val currentFolderId: Long) : Ui()
        data class OnChangeGridViewClicked(val count: Int) : Ui()
        data object OnAddNewChipClicked : Ui()
        data object OnCloseAddChipDialogClicked : Ui()

        data object OnCancelSelectionClicked : Ui()
        // Snack bar
        data object OnSnackUndoRemoveNotesClicked : Ui()
    }

    sealed class Inner : Msg() {
        // Notes
        data class FetchedNotesSuccess(val notes: NoteUi.Collection) : Inner()
        data class FetchedNotesFail(val failInfo: String) : Inner()
        data class FetchedNotesSort(val sort: NotesSortUi) : Inner()

        // Folders
        data class FetchedChipsSuccess(val chips: FolderUi.Collection) : Inner()
        data class FetchedChipsFail(val failInfo: String) : Inner()
        data class FetchedChipsSort(val sort: FoldersSortUi) : Inner()
        data class UpdatedEditNoteFabState(val state: EditNoteFabState) : Inner()
        data object HiddenModalBottomSheet : Inner()
        data object ResetCurrentSelectedFolder : Inner()

        // Hidden notes
        data class UpdatedHiddenNotesDialogVisibility(val isVisible: Boolean) : Inner()
        data object NavigatedToHiddenNotes : Inner()
    }
}

sealed class Cmd : ElmCommand {
    data object FetchNotesList : Cmd()
    data object FetchChipsList : Cmd()
    data object FetchNotesSortState : Cmd()
    data object FetchChipsSortState : Cmd()
    data class RemoveSelectedNotes(val notes: List<NoteUi>) : Cmd()
    data class UndoRemoveNotes(val notes: List<NoteUi>) : Cmd()
    data class HideSelectedNotes(val notes: List<NoteUi>) : Cmd()
    data class TryHideSelectedNotes(val notes: List<NoteUi>) : Cmd()
    data class UpdatePinnedNotes(val pinned: List<NoteUi>) : Cmd()
    data class UpdateNotesGrid(val count: Int) : Cmd()
}

sealed class Eff : ElmEffect {
    data class NavigateToEditNote(val id: Long) : Eff()
    data object NavigateToSettings : Eff()
    data class NavigateToFolders(val ids: List<Long>) : Eff()
    data object NavigateToHiddenNotes : Eff()
    data object NavigateToTrash : Eff()
    data object HideModalSheet : Eff()
    data object ShowAddNewChipDialog : Eff()
    data class UpdateCurrentSelectedFolder(val id: Long) : Eff()
    data class ShowSnackBar(val key: SnackBarKey, val message: String) : Eff()
}