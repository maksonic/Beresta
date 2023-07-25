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
import ru.maksonic.beresta.feature.notes.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes.api.ui.NotesListUiState
import ru.maksonic.beresta.feature.search_bar.api.SearchBarUiState

/**
 * @Author maksonic on 21.07.2023
 */
enum class CurrentHiddenSheetContent {
    NOTHING, SORT_HIDDEN_NOTES
}

@OptIn(ExperimentalMaterial3Api::class)
@Stable
data class ModalSheet(
    val isVisible: Boolean,
    val state: SheetState,
    val content: CurrentHiddenSheetContent
) {
    companion object {
        val Initial = ModalSheet(
            isVisible = false,
            state = SheetState(
                initialValue = SheetValue.Hidden,
                skipPartiallyExpanded = true
            ),
            content = CurrentHiddenSheetContent.NOTHING
        )
    }
}

@Stable
@Immutable
data class Model(
    val base: ElmBaseModel,
    val notes: NotesListUiState,
    val modalSheet: ModalSheet,
    //  val bottomBarState: MainBottomBarState,
    val searchBarState: SearchBarUiState,
    val isVisibleEditFab: Boolean,
) : ElmModel {
    companion object {
        val Initial = Model(
            base = ElmBaseModel.Initial,
            notes = NotesListUiState.Initial,
            modalSheet = ModalSheet.Initial,
            //       bottomBarState = MainBottomBarState.IDLE,
            searchBarState = SearchBarUiState.InitialHiddenNotes,
            isVisibleEditFab = true
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        //notes
        data class OnNoteClicked(val id: Long) : Ui()
        data class OnNoteLongClicked(val id: Long) : Ui()
        object CancelNotesSelection : Ui()

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

        //snack bar
        object OnSnackUndoRemoveNotesClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedNotesData(val notes: NoteUi.Collection) : Inner()
        data class FetchedNotesError(val errorMsg: String = "") : Inner()
        object HiddenModalBottomSheet : Inner()
        object HideRemovedNotesSnackBar : Inner()
    }
}

sealed class Cmd : ElmCommand {
    object FetchNotesListFeatureState : Cmd()
    object FetchNotesData : Cmd()
    data class UpdateNotesGridDatastoreState(val count: Int) : Cmd()
    data class RemoveSelectedNotes(val notes: List<NoteUi>) : Cmd()
    data class UndoRemoveNotes(val notes: List<NoteUi>) : Cmd()
    data class UpdatePinnedNotesInCache(val pinned: Set<NoteUi>) : Cmd()
}

sealed class Eff : ElmEffect {
    data class NavigateToEditNote(val id: Long) : Eff()
    object HideModalSheet : Eff()
}