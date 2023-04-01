package ru.maksonic.beresta.screen.main.presentation.core

import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel
import ru.maksonic.beresta.feature.notes_list.api.ui.MainBottomBarState

/**
 * @Author maksonic on 16.01.2023
 */
private typealias UpdateResult = UpdatedModel<Model, Set<Cmd>, Set<Eff>>

class MainSandbox(
    mainProgram: MainProgram
) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model(),
    initialCmd = setOf(Cmd.RunFetchingNotesCollection),
    subscriptions = listOf(mainProgram)
) {

    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Inner.FetchedDataResult -> fetchedData(model, msg)
        is Msg.Inner.FetchedError -> fetchedError(model, msg)
        is Msg.Ui.OnCreateNewNoteClicked -> onAddNoteClicked(model)
        is Msg.Ui.OnShareSelectedNotes -> onShareSelectedNotesClicked(model)
        is Msg.Ui.OnBottomBarSettingsClicked -> onSettingsClicked(model)
        is Msg.Ui.OnBottomBarTrashClicked -> onTrashClicked(model)
        is Msg.Ui.OnBottomBarOpenFoldersClicked -> UpdatedModel(model)
        is Msg.Ui.OnBottomBarSortNotesByClicked -> UpdatedModel(model)
        is Msg.Ui.OnSwitchViewClicked -> onSwitchGridCountClicked(model)
        is Msg.Ui.OnChipFilterClicked -> onFilterChipClicked(model, msg)
        is Msg.Ui.OnNoteClicked -> onNoteClicked(model, msg)
        is Msg.Ui.OnNoteLongPressed -> onNoteLongClicked(model, msg)
        is Msg.Ui.OnHideSelectedNotesBottomBarClicked -> UpdatedModel(model)
        is Msg.Ui.OnPinSelectedNotesBottomBarClicked -> onPinSelectedNotesClicked(model)
        is Msg.Ui.OnRemoveSelectedNotesClicked -> onRemoveSelectedNotesClicked(model)
        is Msg.Ui.OnRemoveNotesUndoClicked -> onRemoveSelectedNotesUndoClicked(model)
        is Msg.Ui.OnReplaceNoteToFolderClicked -> replaceSelectedNotesToFolder(model, msg)
        is Msg.Ui.OnCancelSelectionClicked -> cancelNotesSelection(model)
        is Msg.Ui.OnSelectAllNotesClicked -> onSelectAllNotesClicked(model)
        is Msg.Inner.UpdatedNewFolderDialogVisibility -> updatedDialogVisibility(model, msg)
        is Msg.Inner.HideRemovedNotesSnack -> hiddenSnack(model)
        //is Msg.Inner.ShowRemovedNotesSnack -> showedSnack(model)
    }

    private fun fetchedData(model: Model, msg: Msg.Inner.FetchedDataResult): UpdateResult {
        val notes = msg.notes.copy(data = msg.notes.data.filter { !it.isMovedToTrash })

        return UpdatedModel(
            model = model.copy(
                base = model.base.copy(isLoading = false, isSuccessLoading = true, isError = false),
                notes = notes,
                allNotes = msg.notes.data,
                filters = msg.folders,
            )
        )
    }

    private fun fetchedError(model: Model, msg: Msg.Inner.FetchedError): UpdateResult =
        UpdatedModel(
            model.copy(
                base = model.base.copy(
                    isLoading = false,
                    isSuccessLoading = false,
                    isError = true,
                    errorMsg = msg.message
                )
            )
        )

    private fun onAddNoteClicked(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.NavigateToAddNewNote))

    private fun onSettingsClicked(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.NavigateToSettings))

    private fun onShareSelectedNotesClicked(model: Model): UpdateResult = UpdatedModel(model)

    private fun onTrashClicked(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.NavigateToTrash))

    private fun onSwitchGridCountClicked(model: Model): UpdateResult {
        val count = if (model.notesGridCount == 1) 2 else 1
        return UpdatedModel(model.copy(notesGridCount = count))
    }

    private fun onFilterChipClicked(model: Model, msg: Msg.Ui.OnChipFilterClicked): UpdateResult {
        val afterSelect = model.filters.copy(data = model.filters.data.map { note ->
            note.copy(isSelected = note.id == msg.id)
        })
        return UpdatedModel(model.copy(filters = afterSelect))
    }

    private fun onNoteClicked(model: Model, msg: Msg.Ui.OnNoteClicked): UpdateResult =
        if (model.isSelectionState)
            baseOnNoteAction(model, msg.id)
        else
            UpdatedModel(
                model = model.copy(bottomBarState = MainBottomBarState.IDLE),
                effects = setOf(Eff.ShowNoteForEdit(msg.id))
            )


    private fun onNoteLongClicked(model: Model, msg: Msg.Ui.OnNoteLongPressed): UpdateResult =
        baseOnNoteAction(model, msg.id)

    private fun baseOnNoteAction(model: Model, noteId: Long): UpdateResult {
        val selectedList = model.selectedNotes.toMutableSet().also { list ->
            model.notes.data.forEach { note ->
                if (noteId == note.id) {
                    if (list.contains(note)) list.remove(note) else list.add(note)
                }
            }
        }.toSet()

        val isSelected = selectedList.isNotEmpty()
        val isShowUnpinButton = !selectedList.map { it.isPinned }.contains(false)
        val bottomBarState =
            if (isSelected) MainBottomBarState.SELECTION else MainBottomBarState.IDLE

        return UpdatedModel(
            model.copy(
                selectedNotes = selectedList,
                isSelectionState = isSelected,
                bottomBarState = bottomBarState,
                isShowBottomBarUnpinBtn = isShowUnpinButton,
                selectedNotesCount = selectedList.count()
            )
        )
    }

    private fun onSelectAllNotesClicked(model: Model): UpdateResult {
        val selectedList = model.selectedNotes.toMutableSet().also { list ->
            if (list.containsAll(model.notes.data)) list.clear()
            else list.addAll(model.notes.data)
        }.toSet()

        val isSelected = selectedList.isNotEmpty()
        val isShowUnpinButton = !selectedList.map { it.isPinned }.contains(false)
        val bottomBarState =
            if (isSelected) MainBottomBarState.SELECTION else MainBottomBarState.IDLE

        return UpdatedModel(
            model = model.copy(
                selectedNotes = selectedList,
                isSelectionState = isSelected,
                isShowBottomBarUnpinBtn = isShowUnpinButton,
                selectedNotesCount = selectedList.count(),
                bottomBarState = bottomBarState
            ),
        )
    }

    private fun cancelNotesSelection(model: Model): UpdateResult {
        return UpdatedModel(
            model = model.copy(
                isSelectionState = false,
                selectedNotes = emptySet(),
                selectedNotesCount = 0,
                bottomBarState = MainBottomBarState.IDLE
            )
        )
    }

    private fun onPinSelectedNotesClicked(model: Model): UpdateResult {
        val selectedNotes = model.selectedNotes
        val isSelectedContainsUnpinnedNotes =
            selectedNotes.map { note -> note.isPinned }.contains(false)
        val notes = model.notes.copy(data = model.notes.data.map { note ->
            val isPinned = if (isSelectedContainsUnpinnedNotes) true else !note.isPinned
            return@map if (selectedNotes.contains(note)) note.copy(isPinned = isPinned) else note
        })

        return UpdatedModel(
            model = model.copy(notes = notes),
            commands = setOf(Cmd.UpdatePinnedNotesInCache(notes.data))
        )
    }

    private fun replaceSelectedNotesToFolder(
        model: Model, msg: Msg.Ui.OnReplaceNoteToFolderClicked
    ): UpdateResult = UpdatedModel(model)

    private fun onRemoveSelectedNotesClicked(model: Model): UpdateResult {
        val selectedNotes = model.selectedNotes
        val removedNotes = model.removedNotes.toMutableSet().also { list ->
            if (list.containsAll(selectedNotes)) list.removeAll(selectedNotes) else list.addAll(
                selectedNotes
            )
        }.toSet()

        val remove = model.notes.copy(data = model.notes.data.map { note ->
            val isRemoved = removedNotes.contains(note)
            return@map note.copy(isMovedToTrash = isRemoved)
        })
        val notes = remove.copy(data = remove.data.filter { !it.isMovedToTrash })

        return UpdatedModel(
            model.copy(
                notes = notes,
                selectedNotes = emptySet(),
                removedNotes = removedNotes,
                isSelectionState = false,
                bottomBarState = MainBottomBarState.IDLE,
                selectedNotesCount = 0,
                isVisibleUndoRemoveNotesSnack = true
            ),
            commands = setOf(Cmd.RemoveSelected(remove.data)),
        )
    }

    private fun onRemoveSelectedNotesUndoClicked(model: Model): UpdateResult {
        val undoRemoved = model.notes.copy(data = model.notes.data.toMutableList().also { notes ->
            notes.addAll(model.removedNotes.map { it.copy(isMovedToTrash = false) })
        }.toList())

        return UpdatedModel(
            model = model.copy(
                notes = undoRemoved,
                removedNotes = emptySet(),
                isVisibleUndoRemoveNotesSnack = false
            ),
            commands = setOf(Cmd.UndoRemoved(undoRemoved.data)),
        )
    }

    private fun updatedDialogVisibility(
        model: Model,
        msg: Msg.Inner.UpdatedNewFolderDialogVisibility
    ): UpdateResult =
        UpdatedModel(model.copy(isVisibleNewFolderDialog = msg.isVisible))

    private fun showedSnack(model: Model): UpdateResult =
        UpdatedModel(model.copy(isVisibleUndoRemoveNotesSnack = true))


    private fun hiddenSnack(model: Model): UpdateResult =
        UpdatedModel(model.copy(isVisibleUndoRemoveNotesSnack = false, removedNotes = emptySet()))
}