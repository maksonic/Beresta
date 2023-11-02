package ru.maksonic.beresta.screen.main.core

import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUi
import ru.maksonic.beresta.feature.notes_list.ui.api.unselectAll
import ru.maksonic.beresta.feature.ui.edit_note.api.EditNoteFabState
import ru.maksonic.beresta.feature.ui.edit_note.api.isExpanded
import ru.maksonic.beresta.feature.ui.search_bar.api.SearchBarState
import ru.maksonic.beresta.platform.elm.core.ElmBaseModel.Companion.Loading
import ru.maksonic.beresta.platform.elm.core.ElmBaseModel.Companion.loadedFailure
import ru.maksonic.beresta.platform.elm.core.ElmBaseModel.Companion.loadedSuccess
import ru.maksonic.beresta.platform.elm.core.ElmUpdate
import ru.maksonic.beresta.platform.elm.core.Sandbox
import ru.maksonic.beresta.screen.main.core.program.ChipsDataProgram
import ru.maksonic.beresta.screen.main.core.program.ChipsSortProgram
import ru.maksonic.beresta.screen.main.core.program.NotesDataProgram
import ru.maksonic.beresta.screen.main.core.program.NotesSortProgram
import ru.maksonic.beresta.screen.main.ui.SnackBarKey

/**
 * @Author maksonic on 15.10.2023
 */
private typealias Update = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

class MainSandbox(
    notesDataProgram: NotesDataProgram,
    notesSortProgram: NotesSortProgram,
    chipsDataProgram: ChipsDataProgram,
    chipsSortProgram: ChipsSortProgram
) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(
        Cmd.FetchNotesList,
        Cmd.FetchNotesSortState,
        Cmd.FetchChipsList,
        Cmd.FetchChipsSortState
    ),
    subscriptions = listOf(notesDataProgram, notesSortProgram, chipsDataProgram, chipsSortProgram)
) {
    companion object {
        private const val STICKY_START_FOLDER_ID = 1L
        private const val MINIMAL_FOR_LOADING_ITEMS_COUNT = 2000
    }

    override fun update(msg: Msg, model: Model): Update = when (msg) {
        // Notes
        is Msg.Inner.FetchedNotesSuccess -> fetchedNotesSuccess(model, msg)
        is Msg.Inner.FetchedNotesFail -> fetchedNotesFail(model, msg)
        is Msg.Inner.FetchedNotesSort -> fetchedNotesSort(model, msg)
        is Msg.Ui.OnNoteClicked -> onNoteClicked(model, msg)
        is Msg.Ui.OnNoteLongClicked -> onNoteLongClicked(model, msg)
        is Msg.Ui.CancelNotesSelection -> onCancelSelectionClicked(model)
        // Folders
        is Msg.Inner.FetchedChipsSuccess -> fetchedChipsSuccess(model, msg)
        is Msg.Inner.FetchedChipsFail -> fetchedChipsFail(model)
        is Msg.Inner.FetchedChipsSort -> fetchedChipsSort(model, msg)
        is Msg.Ui.OnRetryFetchChipsClicked -> onRetryFetchChipsClicked(model)
        is Msg.Ui.OnChipClicked -> onChipClicked(model, msg)
        is Msg.Inner.ResetCurrentSelectedFolder -> resetCurrentSelectedFolder(model)
        is Msg.Ui.OnAddNewChipClicked -> onAddNewChipClicked(model)
        is Msg.Ui.OnCloseAddChipDialogClicked -> onCloseAddChipDialogClicked(model)
        // Idle bottom bar actions
        is Msg.Ui.OnBottomBarSettingsClicked -> onBottomBarSettingsClicked(model)
        is Msg.Ui.OnBottomBarTrashClicked -> onBottomBarTrashClicked(model)
        is Msg.Ui.OnBottomBarFoldersClicked -> onBottomBarFoldersClicked(model)
        is Msg.Ui.OnBottomBarSortNotesClicked -> onBottomBarSortNotesClicked(model)
        // Selected bottom bar actions
        is Msg.Ui.OnBottomBarHideSelectedNotesClicked -> onBottomBarHideSelectedClicked(model)
        is Msg.Ui.OnBottomBarPinSelectedNotesClicked -> onBottomBarPinSelectedClicked(model)
        is Msg.Ui.OnBottomBarMoveSelectedNotesClicked -> onBottomBarMoveSelectedClicked(model)
        is Msg.Ui.OnBottomBarRemoveSelectedNotesClicked -> onBottomBarRemoveSelectedClicked(model)
        is Msg.Inner.UpdatedEditNoteFabState -> updatedEditNoteFabState(model, msg)
        // Search bar
        is Msg.Ui.OnCollapseSearchBar -> onCollapseSearchBar(model)
        is Msg.Ui.OnExpandSearchBar -> onExpandSearchBar(model)
        is Msg.Ui.OnCounterBarSelectAllClicked -> onCounterBarSelectAllClicked(model, msg)
        is Msg.Ui.OnCounterBarShareClicked -> onCounterBarShareClicked(model)
        is Msg.Ui.OnChangeGridViewClicked -> onSearchBarGridViewClicked(model, msg)
        is Msg.Ui.OnCancelSelectionClicked -> onCancelSelectionClicked(model)
        // Snack bar
        is Msg.Ui.OnSnackUndoRemoveNotesClicked -> onSnackBarUndoRemoveClicked(model)
        //
        is Msg.Inner.HiddenModalBottomSheet -> hiddenModalBottomSheet(model)
        // Hidden notes
        is Msg.Inner.NavigatedToHiddenNotes -> navigatedToHiddenNotes(model)
        is Msg.Inner.UpdatedHiddenNotesDialogVisibility -> {
            updatedHiddenNotesDialogVisibility(model, msg)
        }
    }

    private fun fetchedNotesSuccess(model: Model, msg: Msg.Inner.FetchedNotesSuccess): Update =
        ElmUpdate(
            model.copy(
                notes = model.notes.copy(
                    state = model.notes.state.loadedSuccess, collection = msg.notes
                ),
                searchBarState = model.searchBarState.copy(searchList = msg.notes)
            )
        )

    private fun fetchedNotesFail(model: Model, msg: Msg.Inner.FetchedNotesFail): Update =
        ElmUpdate(
            model.copy(
                notes = model.notes.copy(
                    state = model.notes.state.loadedFailure(msg.failInfo),
                    collection = NoteUi.Collection.Empty
                ),
                searchBarState = model.searchBarState.copy(searchList = NoteUi.Collection.Empty)
            )
        )

    private fun fetchedNotesSort(model: Model, msg: Msg.Inner.FetchedNotesSort): Update = ElmUpdate(
        model.copy(sortState = model.sortState.copy(notes = msg.sort))
    )

    private fun onNoteClicked(model: Model, msg: Msg.Ui.OnNoteClicked): Update =
        if (model.notes.isSelection) baseOnNoteAction(model, msg.id)
        else ElmUpdate(
            model.copy(
                editNoteFabState = model.editNoteFabState.copy(state = EditNoteFabState.COLLAPSED)
            ),
            effects = setOf(Eff.NavigateToEditNote(msg.id))
        )

    private fun onNoteLongClicked(model: Model, msg: Msg.Ui.OnNoteLongClicked): Update =
        baseOnNoteAction(model, msg.id)

    private fun baseOnNoteAction(model: Model, noteId: Long): Update {
        val notes = model.notes.collection.copy(model.notes.collection.data.map { note ->
            val isSelected = if (note.id == noteId) !note.isSelected else note.isSelected
            note.copy(isSelected = isSelected)
        })
        val selectedList = notes.data.filter { it.isSelected }
        val isVisibleUnpinButton = selectedList.isNotEmpty().run {
            if (this) !selectedList.map { it.style.isPinned }.contains(false) else false
        }

        return ElmUpdate(
            model.copy(
                notes = model.notes.copy(
                    collection = notes,
                    isSelection = true,
                    isVisibleUnpinBottomBarIcon = isVisibleUnpinButton
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

    private fun onCancelSelectionClicked(model: Model): Update = ElmUpdate(
        model.copy(
            notes = model.notes.copy(
                collection = model.notes.collection.unselectAll(), isSelection = false
            ),
            searchBarState = model.searchBarState.copy(barState = SearchBarState.Collapsed),
            editNoteFabState = model.editNoteFabState.copy(isVisible = true),
        )
    )

    private fun fetchedChipsSuccess(model: Model, msg: Msg.Inner.FetchedChipsSuccess): Update =
        ElmUpdate(
            model.copy(
                chips = model.chips.copy(
                    state = model.chips.state.loadedSuccess, collection = msg.chips
                ),
            ),
        )

    private fun fetchedChipsFail(model: Model): Update = ElmUpdate(
        model.copy(
            chips = model.chips.copy(
                state = model.chips.state.loadedFailure(""),
                collection = model.chips.collection.copy(emptyList())
            )
        )
    )

    private fun fetchedChipsSort(model: Model, msg: Msg.Inner.FetchedChipsSort): Update =
        ElmUpdate(model.copy(sortState = model.sortState.copy(chips = msg.sort)))

    private fun onRetryFetchChipsClicked(model: Model): Update = ElmUpdate(
        model.copy(chips = model.chips.copy(state = model.chips.state.Loading)),
        commands = setOf(Cmd.FetchChipsList)
    )

    private fun onChipClicked(model: Model, msg: Msg.Ui.OnChipClicked): Update =
        ElmUpdate(model, effects = setOf(Eff.UpdateCurrentSelectedFolder(msg.id)))

    private fun onAddNewChipClicked(model: Model): Update = ElmUpdate(
        model.copy(isVisibleAddChipDialog = true), effects = setOf(Eff.ShowAddNewChipDialog)
    )

    private fun onCloseAddChipDialogClicked(model: Model): Update =
        ElmUpdate(model = model.copy(isVisibleAddChipDialog = false))

    private fun onBottomBarSettingsClicked(model: Model): Update =
        ElmUpdate(model, effects = setOf(Eff.NavigateToSettings))

    private fun onBottomBarFoldersClicked(model: Model): Update {
        val selectedList = model.notes.collection.data.filter { it.isSelected }
        val passedNotesIds = if (selectedList.isNotEmpty() && model.notes.isSelection)
            selectedList.map { it.id } else emptyList()

        return ElmUpdate(model, effects = setOf(Eff.NavigateToFolders(passedNotesIds)))
    }

    private fun onSearchBarGridViewClicked(
        model: Model,
        msg: Msg.Ui.OnChangeGridViewClicked
    ): Update = ElmUpdate(
        model.copy(
            sortState = model.sortState.copy(
                notes = model.sortState.notes.copy(gridCount = msg.count)
            )
        ),
        commands = setOf(Cmd.UpdateNotesGrid(msg.count))
    )

    private fun onBottomBarSortNotesClicked(model: Model): Update = ElmUpdate(
        model.copy(
            modalSheet = model.modalSheet.copy(
                isVisible = true, content = ModalSheetContent.SORT_NOTES
            )
        )
    )

    private fun onBottomBarTrashClicked(model: Model): Update =
        ElmUpdate(model, effects = setOf(Eff.NavigateToTrash))

    private fun onBottomBarPinSelectedClicked(model: Model): Update = ElmUpdate(
        model = model.copy(
            notes = model.notes.copy(collection = model.notes.collection.unselectAll())
        ),
        commands = setOf(
            Cmd.UpdatePinnedNotes(model.notes.collection.data.filter { it.isSelected })
        )
    )

    private fun onBottomBarMoveSelectedClicked(model: Model): Update {
        val selectedList = model.notes.collection.data.filter { it.isSelected }
        val passedNotesIds = if (selectedList.isNotEmpty() && model.notes.isSelection)
            selectedList.map { it.id } else emptyList()

        return ElmUpdate(
            model.copy(
                notes = model.notes.copy(
                    isSelection = false, collection = model.notes.collection.unselectAll()
                ),
                searchBarState = model.searchBarState.copy(barState = SearchBarState.Collapsed),
                editNoteFabState = model.editNoteFabState.copy(isVisible = true),
            ),
            effects = setOf(Eff.NavigateToFolders(passedNotesIds))
        )
    }

    private fun onBottomBarRemoveSelectedClicked(model: Model): Update {
        val selectedList = model.notes.collection.data.filter { it.isSelected }

        return ElmUpdate(
            model.copy(
                notes = model.notes.copy(
                    state = model.notes.state.copy(
                        isLoading = selectedList.count() >= MINIMAL_FOR_LOADING_ITEMS_COUNT
                    ),
                    collection = model.notes.collection.unselectAll(),
                    removedList = selectedList,
                    isSelection = false,
                ),
                searchBarState = model.searchBarState.copy(barState = SearchBarState.Collapsed),
                editNoteFabState = model.editNoteFabState.copy(isVisible = true)
            ),
            commands = setOf(Cmd.RemoveSelectedNotes(selectedList.toList())),
            effects = setOf(
                Eff.ShowSnackBar(
                    SnackBarKey.REMOVED_NOTES, message = "${selectedList.count()}"
                )
            )
        )
    }

    private fun hiddenModalBottomSheet(model: Model): Update = ElmUpdate(
        model.copy(
            modalSheet = model.modalSheet.copy(
                isVisible = false, content = ModalSheetContent.NOTHING
            )
        )
    )

    private fun onCollapseSearchBar(model: Model): Update = ElmUpdate(
        model.copy(
            searchBarState = model.searchBarState.copy(barState = SearchBarState.Collapsed),
            editNoteFabState = model.editNoteFabState.copy(
                isVisible = !model.notes.isSelection, state = EditNoteFabState.COLLAPSED
            )
        )
    )

    private fun onExpandSearchBar(model: Model): Update = ElmUpdate(
        model.copy(
            searchBarState = model.searchBarState.copy(barState = SearchBarState.Expanded),
            editNoteFabState = model.editNoteFabState.copy(
                isVisible = false, state = EditNoteFabState.COLLAPSED
            ),
        )
    )

    private fun onCounterBarShareClicked(model: Model): Update = ElmUpdate(model)

    private fun onCounterBarSelectAllClicked(
        model: Model,
        msg: Msg.Ui.OnCounterBarSelectAllClicked
    ): Update {
        val byFolder = model.notes.collection.data.filter { it.folderId == msg.currentFolderId }
        val allSelected = byFolder.all { it.isSelected }
        val notes = model.notes.collection.data.map { item ->
            if (msg.currentFolderId == STICKY_START_FOLDER_ID) {
                item.copy(isSelected = !model.notes.collection.data.all { it.isSelected })
            } else {
                val isSelected = if (byFolder.contains(item)) !allSelected else item.isSelected
                item.copy(isSelected = isSelected)
            }
        }

        val selectedList = notes.filter { it.isSelected }
        val isVisibleUnpinButton = selectedList.isNotEmpty().run {
            if (this) !selectedList.map { it.style.isPinned }.contains(false) else false
        }

        return ElmUpdate(
            model.copy(
                notes = model.notes.copy(
                    collection = model.notes.collection.copy(notes),
                    isVisibleUnpinBottomBarIcon = isVisibleUnpinButton
                ),
                searchBarState = model.searchBarState.copy(
                    counterValue = notes.count { it.isSelected }
                )
            )
        )
    }

    private fun onSnackBarUndoRemoveClicked(model: Model): Update {
        val restored = model.notes.removedList.map { note -> note.copy(isMovedToTrash = false) }
        val isShowLoading = restored.count() >= MINIMAL_FOR_LOADING_ITEMS_COUNT

        return ElmUpdate(
            model.copy(
                notes = model.notes.copy(
                    state = model.notes.state.copy(isLoading = isShowLoading),
                    isSelection = false,
                    collection = model.notes.collection.copy(
                        model.notes.collection.data.map { it.copy(isSelected = false) }
                    )
                ),
                searchBarState = model.searchBarState.copy(barState = SearchBarState.Collapsed)
            ),
            commands = setOf(Cmd.UndoRemoveNotes(restored))
        )
    }

    private fun updatedEditNoteFabState(
        model: Model,
        msg: Msg.Inner.UpdatedEditNoteFabState
    ): Update {

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

    private fun resetCurrentSelectedFolder(model: Model): Update =
        ElmUpdate(model, effects = setOf(Eff.UpdateCurrentSelectedFolder(1L)))

    private fun navigatedToHiddenNotes(model: Model): Update {
        val selectedList = model.notes.collection.data.filter { it.isSelected }
        val hiddenNotes =
            if (selectedList.isNotEmpty() && model.notes.isSelection) selectedList else emptyList()

        return ElmUpdate(
            model.copy(
                notes = model.notes.copy(
                    isSelection = false, collection = model.notes.collection.unselectAll()
                ),
                searchBarState = model.searchBarState.copy(barState = SearchBarState.Collapsed),
                editNoteFabState = model.editNoteFabState.copy(isVisible = true),
                isVisibleHiddenNotesDialog = false,
                isVisibleAddChipDialog = false
            ),
            commands = setOf(Cmd.HideSelectedNotes(hiddenNotes)),
            effects = setOf(Eff.NavigateToHiddenNotes)
        )
    }

    private fun onBottomBarHideSelectedClicked(model: Model): Update {
        val unselectedAll = model.notes.collection.copy(
            model.notes.collection.data.map { it.copy(isSelected = false) }
        )
        val selectedList = model.notes.collection.data.filter { it.isSelected }
        val hiddenNotes =
            if (selectedList.isNotEmpty() && model.notes.isSelection) selectedList else emptyList()

        return ElmUpdate(
            model = model.copy(
                notes = model.notes.copy(isSelection = false, collection = unselectedAll),
                searchBarState = model.searchBarState.copy(barState = SearchBarState.Collapsed),
                editNoteFabState = model.editNoteFabState.copy(isVisible = true),
            ),
            commands = setOf(Cmd.TryHideSelectedNotes(hiddenNotes))
        )
    }

    private fun updatedHiddenNotesDialogVisibility(
        model: Model,
        msg: Msg.Inner.UpdatedHiddenNotesDialogVisibility
    ): Update = ElmUpdate(model.copy(isVisibleHiddenNotesDialog = msg.isVisible))
}