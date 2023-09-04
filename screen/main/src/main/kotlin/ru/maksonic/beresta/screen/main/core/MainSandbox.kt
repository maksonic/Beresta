package ru.maksonic.beresta.screen.main.core

import androidx.compose.material3.ExperimentalMaterial3Api
import ru.maksonic.beresta.elm.core.ElmBaseModel.Companion.Loading
import ru.maksonic.beresta.elm.core.ElmBaseModel.Companion.loadedFailure
import ru.maksonic.beresta.elm.core.ElmBaseModel.Companion.loadedSuccess
import ru.maksonic.beresta.elm.core.ElmUpdate
import ru.maksonic.beresta.elm.core.Sandbox
import ru.maksonic.beresta.feature.edit_note.api.EditNoteFabState
import ru.maksonic.beresta.feature.edit_note.api.isExpanded
import ru.maksonic.beresta.feature.search_bar.api.SearchBarState
import ru.maksonic.beresta.screen.main.core.programs.ChipsDataProgram
import ru.maksonic.beresta.screen.main.core.programs.ChipsSortProgram
import ru.maksonic.beresta.screen.main.core.programs.NotesDataProgram
import ru.maksonic.beresta.screen.main.core.programs.NotesSortProgram

/**
 * @Author maksonic on 22.06.2023
 */
private typealias UpdateResult = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

@OptIn(ExperimentalMaterial3Api::class)
class MainSandbox(
    notesDataProgram: NotesDataProgram,
    notesSortProgram: NotesSortProgram,
    chipsDataProgram: ChipsDataProgram,
    chipsSortProgram: ChipsSortProgram,
) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(Cmd.FetchNotesListFeatureState, Cmd.FetchNotesData, Cmd.FetchChipsData),
    subscriptions = listOf(notesDataProgram, notesSortProgram, chipsDataProgram, chipsSortProgram)
) {
    companion object {
        private const val STICKY_START_FOLDER_ID = 1L
        private const val STICKY_END_FOLDER_ID = 2L
        private const val DEFAULT_NOTE_FOLDER_ID = 2L
        private const val MINIMAL_FOR_LOADING_ITEMS_COUNT = 2000
    }

    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        //notes
        is Msg.Inner.FetchedNotesData -> fetchedNotesData(model, msg)
        is Msg.Inner.FetchedNotesError -> ElmUpdate(model)
        is Msg.Ui.OnNoteClicked -> onNoteClicked(model, msg)
        is Msg.Ui.OnNoteLongClicked -> onNoteLongClicked(model, msg)
        is Msg.Ui.CancelNotesSelection -> onCancelSelectionClicked(model)
        //chips
        is Msg.Inner.FetchedChipsData -> fetchedChipsData(model, msg)
        is Msg.Inner.FetchedChipsError -> fetchedChipsError(model)
        is Msg.Ui.OnRetryFetchChipsClicked -> onRetryFetchChipsClicked(model)
        //idle bottom bar actions
        is Msg.Ui.OnBottomBarSettingsClicked -> onBottomBarSettingsClicked(model)
        is Msg.Ui.OnBottomBarFoldersClicked -> onBottomBarFoldersClicked(model)
        is Msg.Ui.OnChangeGridViewClicked -> onSearchBarGridViewClicked(model, msg)
        is Msg.Ui.OnBottomBarSortNotesClicked -> onBottomBarSortNotesClicked(model)
        is Msg.Ui.OnBottomBarTrashClicked -> onBottomBarTrashClicked(model)
        //selected bottom bar actions
        is Msg.Ui.OnBottomBarHideSelectedNotesClicked -> onBottomBarHideSelectedClicked(model)
        is Msg.Ui.OnBottomBarPinSelectedNotesClicked -> onBottomBarPinSelectedClicked(model)
        is Msg.Ui.OnBottomBarMoveSelectedNotesClicked -> onBottomBarMoveSelectedClicked(model)
        is Msg.Ui.OnBottomBarRemoveSelectedNotesClicked -> onBottomBarRemoveSelectedClicked(model)
        //modal sheet
        is Msg.Ui.OnHideModalBottomSheet -> onHideModalBottomSheet(model)
        is Msg.Inner.HiddenModalBottomSheet -> hiddenModalBottomSheet(model)
        //search bar
        is Msg.Ui.OnCollapseSearchBar -> onCollapseSearchBar(model)
        is Msg.Ui.OnExpandSearchBar -> onExpandSearchBar(model)
        is Msg.Ui.OnCounterBarSelectAllClicked -> onCounterBarSelectAllClicked(model, msg)
        is Msg.Ui.OnCounterBarShareClicked -> onCounterBarShareClicked(model)
        //other
        //snack bar
        is Msg.Inner.HiddenRemovedNotesSnackBar -> hideRemoveNotesSnackBar(model)
        is Msg.Ui.OnSnackUndoRemoveNotesClicked -> onSnackBarUndoRemoveClicked(model)
        is Msg.Ui.OnAddNewChipClicked -> onAddNewChipClicked(model)
        is Msg.Inner.NavigatedToHiddenNotes -> navigatedToHiddenNotes(model)
        is Msg.Inner.UpdatedEditNoteFabState -> updatedEditNoteFabState(model, msg)
        is Msg.Inner.ResetCurrentSelectedFolder -> resetCurrentSelectedFolder(model)
        is Msg.Ui.OnHideHiddenNotesDialogClicked -> onHideHiddenNotesDialogClicked(model)
    }

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
        else ElmUpdate(
            model.copy(
                editNoteFabState = model.editNoteFabState.copy(state = EditNoteFabState.COLLAPSED)
            ),
            effects = setOf(Eff.NavigateToEditNote(msg.id))
        )

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
                ),
            ),
        )
    }

    private fun onCancelSelectionClicked(model: Model): UpdateResult = ElmUpdate(
        model.copy(
            notes = model.notes.copy(selectedList = emptySet(), isSelection = false),
            searchBarState = model.searchBarState.copy(barState = SearchBarState.Collapsed),
            editNoteFabState = model.editNoteFabState.copy(isVisible = true),
        )
    )

    private fun fetchedChipsData(model: Model, msg: Msg.Inner.FetchedChipsData): UpdateResult =
        ElmUpdate(
            model.copy(
                chips = model.chips.copy(
                    state = model.chips.state.loadedSuccess, collection = msg.chips
                ),
            ),
        )

    private fun fetchedChipsError(model: Model): UpdateResult = ElmUpdate(
        model.copy(
            chips = model.chips.copy(
                state = model.chips.state.loadedFailure(""),
                collection = model.chips.collection.copy(emptyList())
            )
        )
    )

    private fun onRetryFetchChipsClicked(model: Model): UpdateResult = ElmUpdate(
        model.copy(chips = model.chips.copy(state = model.chips.state.Loading)),
        commands = setOf(Cmd.FetchChipsData)
    )

    private fun onBottomBarSettingsClicked(model: Model): UpdateResult =
        ElmUpdate(model, effects = setOf(Eff.NavigateToSettings))

    private fun onBottomBarFoldersClicked(model: Model): UpdateResult {
        val args = if (model.notes.selectedList.isNotEmpty() && model.notes.isSelection)
            model.notes.selectedList.map { it.id } else emptyList()

        return ElmUpdate(model, effects = setOf(Eff.NavigateToFolders(args)))
    }

    private fun onSearchBarGridViewClicked(
        model: Model,
        msg: Msg.Ui.OnChangeGridViewClicked
    ): UpdateResult = ElmUpdate(
        model, commands = setOf(Cmd.UpdateNotesGridDatastoreState(msg.count)),
    )

    private fun onBottomBarSortNotesClicked(model: Model): UpdateResult = ElmUpdate(
        model.copy(
            modalSheet = model.modalSheet.copy(
                isVisible = true, content = CurrentSheetContent.SORT_NOTES
            )
        )
    )

    private fun onBottomBarTrashClicked(model: Model): UpdateResult =
        ElmUpdate(model, effects = setOf(Eff.NavigateToTrash))

    private fun onBottomBarHideSelectedClicked(model: Model): UpdateResult =
        ElmUpdate(model.copy(isVisibleHiddenNotesDialog = true))

    private fun onHideHiddenNotesDialogClicked(model: Model): UpdateResult =
        ElmUpdate(model.copy(isVisibleHiddenNotesDialog = false))

    private fun onBottomBarPinSelectedClicked(model: Model): UpdateResult = ElmUpdate(
        model.copy(notes = model.notes.copy(selectedList = emptySet())),
        commands = setOf(Cmd.UpdatePinnedNotesInCache(model.notes.selectedList))
    )

    private fun onBottomBarMoveSelectedClicked(model: Model): UpdateResult {
        val args = if (model.notes.selectedList.isNotEmpty() && model.notes.isSelection)
            model.notes.selectedList.map { it.id } else emptyList()

        return ElmUpdate(
            model.copy(
                notes = model.notes.copy(isSelection = false, selectedList = emptySet()),
                searchBarState = model.searchBarState.copy(barState = SearchBarState.Collapsed),
                editNoteFabState = model.editNoteFabState.copy(isVisible = true),
            ),
            effects = setOf(Eff.NavigateToFolders(args))
        )
    }

    private fun onBottomBarRemoveSelectedClicked(model: Model): UpdateResult {
        val isShowLoading = model.notes.selectedList.count() >= MINIMAL_FOR_LOADING_ITEMS_COUNT

        return ElmUpdate(
            model.copy(
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
            ),
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

    private fun onCounterBarSelectAllClicked(
        model: Model,
        msg: Msg.Ui.OnCounterBarSelectAllClicked
    ): UpdateResult {
        val filteredNotes = model.notes.collection.data.filter { note ->
            when (msg.currentFolderId) {
                STICKY_START_FOLDER_ID -> true
                STICKY_END_FOLDER_ID -> note.folderId == DEFAULT_NOTE_FOLDER_ID
                else -> note.folderId == msg.currentFolderId
            }
        }

        val selectedList = model.notes.selectedList.toMutableSet().also { list ->
            if (msg.currentFolderId == STICKY_START_FOLDER_ID) {
                if (list.containsAll(model.notes.collection.data)) list.clear()
                else list.addAll(model.notes.collection.data)
            } else {
                if (list.containsAll(filteredNotes)) list.removeAll(filteredNotes.toSet())
                else list.addAll(filteredNotes)
            }
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
        model.copy(
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
            model.copy(
                notes = model.notes.copy(state = model.notes.state.copy(isLoading = isShowLoading))
            ),
            commands = setOf(Cmd.UndoRemoveNotes(restored))
        )
    }

    private fun onAddNewChipClicked(model: Model): UpdateResult =
        ElmUpdate(model, effects = setOf(Eff.ShowAddNewChipDialog))

    private fun navigatedToHiddenNotes(model: Model): UpdateResult {
        val args = if (model.notes.selectedList.isNotEmpty() && model.notes.isSelection)
            model.notes.selectedList.map { it.id } else emptyList()

        return ElmUpdate(
            model.copy(
                notes = model.notes.copy(isSelection = false, selectedList = emptySet()),
                searchBarState = model.searchBarState.copy(barState = SearchBarState.Collapsed),
                editNoteFabState = model.editNoteFabState.copy(isVisible = true),
            ), effects = setOf(Eff.NavigateToHiddenNotes(args))
        )
    }

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
            ),
        )
    }

    private fun resetCurrentSelectedFolder(model: Model): UpdateResult =
        ElmUpdate(model, commands = setOf(Cmd.ResetCurrentSelectedFolder))
}