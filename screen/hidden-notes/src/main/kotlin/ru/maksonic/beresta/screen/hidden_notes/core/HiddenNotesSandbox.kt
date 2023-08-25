package ru.maksonic.beresta.screen.hidden_notes.core

import androidx.compose.material3.ExperimentalMaterial3Api
import ru.maksonic.beresta.elm.core.ElmBaseModel.Companion.loadedSuccess
import ru.maksonic.beresta.elm.core.ElmUpdate
import ru.maksonic.beresta.elm.core.Sandbox
import ru.maksonic.beresta.feature.edit_note.api.EditNoteFabState
import ru.maksonic.beresta.feature.edit_note.api.isExpanded
import ru.maksonic.beresta.feature.search_bar.api.SearchBarState
import ru.maksonic.beresta.screen.hidden_notes.core.programs.HiddenNotesDataProgram
import ru.maksonic.beresta.screen.hidden_notes.core.programs.HiddenNotesScreenCaptureProgram
import ru.maksonic.beresta.screen.hidden_notes.core.programs.HiddenNotesSortProgram

/**
 * @Author maksonic on 21.07.2023
 */
private typealias UpdateResult = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

@OptIn(ExperimentalMaterial3Api::class)
class HiddenNotesSandbox(
    hiddenNotesDataProgram: HiddenNotesDataProgram,
    hiddenNotesSortProgram: HiddenNotesSortProgram,
    hiddenNotesScreenCaptureProgram: HiddenNotesScreenCaptureProgram,
) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(Cmd.FetchNotesData, Cmd.FetchMovedForHideNotesData),
    subscriptions = listOf(
        hiddenNotesDataProgram,
        hiddenNotesSortProgram,
        hiddenNotesScreenCaptureProgram
    )
) {
    companion object {
        private const val MINIMAL_FOR_LOADING_ITEMS_COUNT = 2000
    }

    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Ui.OnTopBarBackPressed -> onTopBarBackPressed(model)
        //notes
        is Msg.Inner.FetchedNotesData -> fetchedNotesData(model, msg)
        is Msg.Inner.FetchedNotesError -> ElmUpdate(model)
        is Msg.Ui.OnNoteClicked -> onNoteClicked(model, msg)
        is Msg.Ui.OnNoteLongClicked -> onNoteLongClicked(model, msg)
        is Msg.Ui.CancelNotesSelection -> onCancelSelectionClicked(model)
        //idle bottom bar actions
        is Msg.Ui.OnTopBarGridViewClicked -> onTopBarGridViewClicked(model, msg)
        is Msg.Ui.OnTopBarSortNotesClicked -> onTopBarSortNotesClicked(model)
        //selected bottom bar actions
        is Msg.Ui.OnBottomBarUnhideSelectedNotesClicked -> onBottomBarUnhideSelectedClicked(model)
        is Msg.Ui.OnBottomBarPinSelectedNotesClicked -> onBottomBarPinSelectedClicked(model)
        is Msg.Ui.OnBottomBarRemoveSelectedNotesClicked -> onBottomBarRemoveSelectedClicked(model)
        //modal sheet
        is Msg.Ui.OnHideModalBottomSheet -> onHideModalBottomSheet(model)
        is Msg.Inner.HiddenModalBottomSheet -> hiddenModalBottomSheet(model)
        //search bar
        is Msg.Ui.OnCollapseSearchBar -> onCollapseSearchBar(model)
        is Msg.Ui.OnExpandSearchBar -> onExpandSearchBar(model)
        is Msg.Ui.OnCounterBarSelectAllClicked -> onCounterBarSelectAllClicked(model)
        is Msg.Ui.OnCounterBarShareClicked -> onCounterBarShareClicked(model)
        //snack bar
        is Msg.Inner.HiddenRemovedNotesSnackBar -> hideRemoveNotesSnackBar(model)
        is Msg.Ui.OnSnackUndoRemoveNotesClicked -> onSnackBarUndoRemoveClicked(model)
        //other
        is Msg.Inner.HiddenLoadingPlaceholder -> hiddenLoadingPlaceholder(model)
        is Msg.Inner.UpdatedEditNoteFabState -> updatedEditNoteFabState(model, msg)
        is Msg.Inner.UpdateStonewallVisibility -> updateStonewallVisibility(model, msg)
    }

    private fun onTopBarBackPressed(model: Model): UpdateResult = ElmUpdate(
        model,
        effects = setOf(Eff.NavigateBack),
        commands = setOf(Cmd.AllowScreenCapture)
    )

    private fun fetchedNotesData(model: Model, msg: Msg.Inner.FetchedNotesData): UpdateResult =
        ElmUpdate(
            model.copy(
                notes = model.notes.copy(
                    state = model.notes.state.loadedSuccess, collection = msg.notes
                ),
                searchBarState = model.searchBarState.copy(searchList = msg.notes)
            )
        )

    private fun onNoteClicked(model: Model, msg: Msg.Ui.OnNoteClicked): UpdateResult =
        if (model.notes.isSelection) baseOnNoteAction(model, msg.id)
        else ElmUpdate(model, effects = setOf(Eff.NavigateToEditNote(msg.id)))

    private fun onNoteLongClicked(model: Model, msg: Msg.Ui.OnNoteLongClicked): UpdateResult =
        baseOnNoteAction(model, msg.id)

    private fun baseOnNoteAction(model: Model, noteId: Long): UpdateResult {
        val selectedList = model.notes.selectedList.toMutableSet().also { list ->
            model.notes.collection.data.forEach { note ->
                if (noteId == note.id) {
                    if (list.contains(note)) list.remove(note) else list.add(note)
                }
            }
        }.toSet()

        val isVisibleUnpinButton = selectedList.isNotEmpty().run {
            if (this) !selectedList.map { it.isPinned }.contains(false) else false
        }

        return ElmUpdate(
            model.copy(
                notes = model.notes.copy(
                    selectedList = selectedList,
                    isSelection = true,
                    isVisibleUnpinMainBarIcon = isVisibleUnpinButton
                ),
                searchBarState = model.searchBarState.copy(
                    barState = SearchBarState.Selected,
                    counterValue = selectedList.count()
                ),
                editNoteFabState = model.editNoteFabState.copy(
                    isVisible = false, state = EditNoteFabState.COLLAPSED
                )
            )
        )
    }

    private fun onCancelSelectionClicked(model: Model): UpdateResult = ElmUpdate(
        model = model.copy(
            notes = model.notes.copy(selectedList = emptySet(), isSelection = false),
            searchBarState = model.searchBarState.copy(barState = SearchBarState.Collapsed),
            editNoteFabState = model.editNoteFabState.copy(isVisible = true),
        )
    )

    private fun onTopBarGridViewClicked(
        model: Model,
        msg: Msg.Ui.OnTopBarGridViewClicked
    ): UpdateResult = ElmUpdate(
        model, commands = setOf(Cmd.UpdateNotesGridDatastoreState(msg.count)),
    )

    private fun onTopBarSortNotesClicked(model: Model): UpdateResult = ElmUpdate(
        model.copy(
            modalSheet = model.modalSheet.copy(
                isVisible = true, content = CurrentSheetContent.SORT_HIDDEN_NOTES
            )
        ),
        commands = setOf(Cmd.AllowScreenCapture)
    )

    private fun onBottomBarUnhideSelectedClicked(model: Model): UpdateResult {
        val unhidden = model.notes.selectedList
        val isShowLoading = unhidden.count() >= MINIMAL_FOR_LOADING_ITEMS_COUNT

        return ElmUpdate(
            model = model.copy(
                notes = model.notes.copy(
                    state = model.notes.state.copy(isLoading = isShowLoading),
                    selectedList = emptySet(),
                    removedList = model.notes.selectedList,
                    isSelection = false,
                ),
                searchBarState = model.searchBarState.copy(barState = SearchBarState.Collapsed),
                editNoteFabState = model.editNoteFabState.copy(isVisible = true),
            ),
            commands = setOf(Cmd.UnhideSelectedNotes(unhidden.toList())),
        )
    }

    private fun onBottomBarPinSelectedClicked(model: Model): UpdateResult = ElmUpdate(
        model = model.copy(notes = model.notes.copy(selectedList = emptySet())),
        commands = setOf(Cmd.UpdatePinnedNotesInCache(model.notes.selectedList))
    )

    private fun onBottomBarRemoveSelectedClicked(model: Model): UpdateResult {
        val isShowLoading = model.notes.selectedList.count() >= MINIMAL_FOR_LOADING_ITEMS_COUNT

        return ElmUpdate(
            model = model.copy(
                notes = model.notes.copy(
                    state = model.notes.state.copy(isLoading = isShowLoading),
                    selectedList = emptySet(),
                    removedList = model.notes.selectedList,
                    isSelection = false,
                    isVisibleRemovedSnackBar = true
                ),
                searchBarState = model.searchBarState.copy(barState = SearchBarState.Collapsed),
                editNoteFabState = model.editNoteFabState.copy(isVisible = true),
            ),
            commands = setOf(Cmd.RemoveSelectedNotes(model.notes.selectedList.toList())),
        )
    }

    private fun onHideModalBottomSheet(model: Model): UpdateResult =
        ElmUpdate(model, effects = setOf(Eff.HideModalSheet))

    @OptIn(ExperimentalMaterial3Api::class)
    private fun hiddenModalBottomSheet(model: Model): UpdateResult = ElmUpdate(
        model.copy(
            modalSheet = model.modalSheet.copy(
                isVisible = false, content = CurrentSheetContent.NOTHING
            )
        )
    )

    private fun onCollapseSearchBar(model: Model): UpdateResult = ElmUpdate(
        model.copy(
            searchBarState = model.searchBarState.copy(barState = SearchBarState.Collapsed),
            editNoteFabState = model.editNoteFabState.copy(
                isVisible = !model.notes.isSelection, state = EditNoteFabState.COLLAPSED
            )
        )
    )

    private fun onExpandSearchBar(model: Model): UpdateResult = ElmUpdate(
        model.copy(
            searchBarState = model.searchBarState.copy(barState = SearchBarState.Expanded),
            editNoteFabState = model.editNoteFabState.copy(
                isVisible = false, state = EditNoteFabState.COLLAPSED
            ),
        )
    )

    private fun onCounterBarShareClicked(model: Model): UpdateResult = ElmUpdate(model)

    private fun onCounterBarSelectAllClicked(model: Model): UpdateResult {
        val notes = model.notes.collection.data
        val selectedList = model.notes.selectedList.toMutableSet().also { list ->
            if (list.containsAll(notes)) list.removeAll(notes.toSet())
            else list.addAll(notes)
        }.toSet()

        val isVisibleUnpinButton = selectedList.isNotEmpty().run {
            if (this) !selectedList.map { it.isPinned }.contains(false) else false
        }

        return ElmUpdate(
            model.copy(
                notes = model.notes.copy(
                    isVisibleUnpinMainBarIcon = isVisibleUnpinButton, selectedList = selectedList
                ),
                searchBarState = model.searchBarState.copy(counterValue = selectedList.count())
            )
        )
    }

    private fun hideRemoveNotesSnackBar(model: Model): UpdateResult = ElmUpdate(
        model = model.copy(
            base = model.base.copy(isLoading = false),
            notes = model.notes.copy(
                removedList = emptySet(),
                isVisibleRemovedSnackBar = false
            )
        )
    )

    private fun onSnackBarUndoRemoveClicked(model: Model): UpdateResult {
        val restored = model.notes.removedList.map { note -> note.copy(isMovedToTrash = false) }
        val isShowLoading = restored.count() >= MINIMAL_FOR_LOADING_ITEMS_COUNT

        return ElmUpdate(
            model = model.copy(
                notes = model.notes.copy(state = model.notes.state.copy(isLoading = isShowLoading))
            ),
            commands = setOf(Cmd.UndoRemoveNotes(restored))
        )
    }

    private fun hiddenLoadingPlaceholder(model: Model): UpdateResult = ElmUpdate(
        model = model.copy(
            notes = model.notes.copy(state = model.notes.state.copy(isLoading = false))
        )
    )

    private fun updatedEditNoteFabState(
        model: Model,
        msg: Msg.Inner.UpdatedEditNoteFabState
    ): UpdateResult {

        val searchBarState = if (msg.state.isExpanded)
            model.searchBarState.copy(barState = SearchBarState.Collapsed)
        else model.searchBarState

        return ElmUpdate(
            model.copy(
                searchBarState = searchBarState,
                editNoteFabState = model.editNoteFabState.copy(
                    isVisible = !model.notes.isSelection, state = msg.state
                )
            )
        )
    }

    private fun updateStonewallVisibility(
        model: Model,
        msg: Msg.Inner.UpdateStonewallVisibility
    ): UpdateResult = ElmUpdate(model.copy(isVisibleStonewall = msg.isVisible))
}