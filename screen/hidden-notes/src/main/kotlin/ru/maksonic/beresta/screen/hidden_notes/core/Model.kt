package ru.maksonic.beresta.screen.hidden_notes.core

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
import ru.maksonic.beresta.feature.notes.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes.api.ui.NotesListUiState
import ru.maksonic.beresta.feature.search_bar.api.SearchBarUiState

/**
 * @Author maksonic on 21.07.2023
 */
enum class CurrentSheetContent {
    NOTHING, SORT_HIDDEN_NOTES
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
                initialValue = SheetValue.Hidden,
                skipPartiallyExpanded = true
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
    val modalSheet: ModalSheet,
    val searchBarState: SearchBarUiState,
    val editNoteFabState: EditNoteUiFabState,
    val isVisibleStonewall: Boolean
) : ElmModel {
    companion object {
        val Initial = Model(
            base = ElmBaseModel.Initial,
            notes = NotesListUiState.InitialHidden,
            modalSheet = ModalSheet.Initial,
            searchBarState = SearchBarUiState.InitialHiddenNotes,
            editNoteFabState = EditNoteUiFabState.Initial,
            isVisibleStonewall = false
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        data object OnTopBarBackPressed : Ui()
        //notes
        data class OnNoteClicked(val id: Long) : Ui()
        data class OnNoteLongClicked(val id: Long) : Ui()
        data object CancelNotesSelection : Ui()

        data object OnTopBarSortNotesClicked : Ui()

        //selected bottom bar actions
        data object OnBottomBarUnhideSelectedNotesClicked : Ui()
        data object OnBottomBarPinSelectedNotesClicked : Ui()
        data object OnBottomBarRemoveSelectedNotesClicked : Ui()

        //search bar
        data object OnCollapseSearchBar : Ui()
        data object OnExpandSearchBar : Ui()
        data object OnCounterBarShareClicked : Ui()
        data object OnCounterBarSelectAllClicked : Ui()
        data class OnTopBarGridViewClicked(val count: Int) : Ui()

        //all
        data object OnHideModalBottomSheet : Ui()

        //snack bar
        data object OnSnackUndoRemoveNotesClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedNotesData(val notes: NoteUi.Collection) : Inner()
        data class FetchedNotesError(val errorMsg: String = "") : Inner()
        data object HiddenModalBottomSheet : Inner()
        data object HiddenRemovedNotesSnackBar : Inner()
        data object HiddenLoadingPlaceholder : Inner()
        data class UpdatedEditNoteFabState(val state: EditNoteFabState) : Inner()
        data class UpdateStonewallVisibility(val isVisible: Boolean): Inner()
    }
}

sealed class Cmd : ElmCommand {
    data object FetchNotesData : Cmd()
    data object FetchMovedForHideNotesData : Cmd()
    data object AllowScreenCapture : Cmd()
    data class UpdateNotesGridDatastoreState(val count: Int) : Cmd()
    data class RemoveSelectedNotes(val notes: List<NoteUi>) : Cmd()
    data class UnhideSelectedNotes(val notes: List<NoteUi>) : Cmd()
    data class UndoRemoveNotes(val notes: List<NoteUi>) : Cmd()
    data class UpdatePinnedNotesInCache(val pinned: Set<NoteUi>) : Cmd()
}

sealed class Eff : ElmEffect {
    data object NavigateBack : Eff()
    data class NavigateToEditNote(val id: Long) : Eff()
    data object HideModalSheet : Eff()
}