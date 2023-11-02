package ru.maksonic.beresta.screen.hidden_notes.core

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.common.ui_kit.bar.snackbar.SnackBarHostState
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUi
import ru.maksonic.beresta.feature.notes_list.ui.api.list.NotesListUiState
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.NotesSortUi
import ru.maksonic.beresta.feature.ui.edit_note.api.EditNoteFabState
import ru.maksonic.beresta.feature.ui.edit_note.api.EditNoteUiFabState
import ru.maksonic.beresta.feature.ui.search_bar.api.SearchBarUiState
import ru.maksonic.beresta.platform.elm.core.ElmBaseModel
import ru.maksonic.beresta.platform.elm.core.ElmCommand
import ru.maksonic.beresta.platform.elm.core.ElmEffect
import ru.maksonic.beresta.platform.elm.core.ElmMessage
import ru.maksonic.beresta.platform.elm.core.ElmModel

/**
 * @Author maksonic on 21.07.2023
 */
enum class ModalSheetContent {
    NOTHING, SORT_HIDDEN_NOTES
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

@Stable
@Immutable
data class Model(
    val base: ElmBaseModel,
    val notes: NotesListUiState,
    val modalSheet: ModalSheet,
    val searchBarState: SearchBarUiState,
    val editNoteFabState: EditNoteUiFabState,
    val isVisibleStonewall: Boolean,
    val snackNotesState: SnackBarHostState,
    val sortState: NotesSortUi
    ) : ElmModel {
    companion object {
        val Initial = Model(
            base = ElmBaseModel.Initial,
            notes = NotesListUiState.Initial.copy(isHidden = true),
            modalSheet = ModalSheet.Initial,
            searchBarState = SearchBarUiState.InitialHiddenNotes,
            editNoteFabState = EditNoteUiFabState.Initial,
            isVisibleStonewall = false,
            snackNotesState = SnackBarHostState(),
            sortState = NotesSortUi.Default
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        data object OnTopBarBackPressed : Ui()

        // Notes
        data class OnNoteClicked(val id: Long) : Ui()
        data class OnNoteLongClicked(val id: Long) : Ui()
        data object CancelNotesSelection : Ui()
        data object OnTopBarSortNotesClicked : Ui()

        // Selected bottom bar actions
        data object OnBottomBarUnhideSelectedNotesClicked : Ui()
        data object OnBottomBarPinSelectedNotesClicked : Ui()
        data object OnBottomBarRemoveSelectedNotesClicked : Ui()

        // Search bar
        data object OnCollapseSearchBar : Ui()
        data object OnExpandSearchBar : Ui()
        data object OnCounterBarShareClicked : Ui()
        data object OnCounterBarSelectAllClicked : Ui()
        data class OnTopBarGridViewClicked(val count: Int) : Ui()

        //
        data object OnHideModalBottomSheet : Ui()

        // Snack bar
        data object OnSnackUndoRemoveNotesClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedNotesSuccess(val notes: NoteUi.Collection) : Inner()
        data class FetchedNotesFail(val failInfo: String) : Inner()
        data class FetchedNotesSort(val sort: NotesSortUi) : Inner()
        data object HiddenModalBottomSheet : Inner()
        data object HiddenLoadingPlaceholder : Inner()
        data class UpdatedEditNoteFabState(val state: EditNoteFabState) : Inner()
        data class UpdateStonewallVisibility(val isVisible: Boolean) : Inner()
    }
}

sealed class Cmd : ElmCommand {
    data object FetchNotesData : Cmd()
    data object FetchNotesSortState : Cmd()
    data class UpdateScreenCapturePermission(val isEnabled: Boolean) : Cmd()
    data class UpdateNotesGrid(val count: Int) : Cmd()
    data class RemoveSelectedNotes(val notes: List<NoteUi>) : Cmd()
    data class UnhideSelectedNotes(val notes: List<NoteUi>) : Cmd()
    data class UndoRemoveNotes(val notes: List<NoteUi>) : Cmd()
    data class UpdatePinnedNotes(val pinned: List<NoteUi>) : Cmd()
}

sealed class Eff : ElmEffect {
    data object NavigateBack : Eff()
    data class NavigateToEditNote(val id: Long) : Eff()
    data object HideModalSheet : Eff()
    data class ShowSnackBar(val key: SnackBarKey, val message: String) : Eff()
}