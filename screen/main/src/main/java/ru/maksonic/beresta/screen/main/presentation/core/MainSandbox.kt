package ru.maksonic.beresta.screen.main.presentation.core

import android.util.Log
import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel
import ru.maksonic.beresta.feature.notes_list.api.ui.MainBottomBarState
import ru.maksonic.beresta.feature.notes_list.api.ui.sortDescendingPinnedByTimeThenByDate

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
        is Msg.Ui.OnBottomBarFoldersClicked -> onFoldersListClicked(model)
        is Msg.Ui.OnBottomBarSortNotesByClicked -> UpdatedModel(model)
        is Msg.Ui.OnSwitchViewClicked -> onSwitchGridCountClicked(model)
        is Msg.Ui.OnChipFilterClicked -> onFilterChipClicked(model, msg)
        is Msg.Ui.OnNoteClicked -> onNoteClicked(model, msg)
        is Msg.Ui.OnNoteLongPressed -> onNoteLongClicked(model, msg)
        is Msg.Ui.OnHideSelectedNotesBottomBarClicked -> UpdatedModel(model)
        is Msg.Ui.OnPinSelectedNotesBottomBarClicked -> onPinSelectedNotesClicked(model)
        is Msg.Ui.OnRemoveSelectedNotesClicked -> onRemoveSelectedNotesClicked(model)
        is Msg.Ui.OnRemoveNotesUndoClicked -> onRemoveSelectedNotesUndoClicked(model)
        is Msg.Ui.OnReplaceNoteToFolderClicked -> replaceSelectedNotesToFolder(model)
        is Msg.Ui.OnCancelSelectionClicked -> cancelNotesSelection(model)
        is Msg.Ui.OnSelectAllNotesClicked -> onSelectAllNotesClicked(model)
        is Msg.Inner.UpdatedNewFolderDialogVisibility -> updatedDialogVisibility(model, msg)
        is Msg.Inner.HideRemovedNotesSnack -> onHideSnack(model)
        is Msg.Inner.UpdateCurrentSelectedFolder -> updatedFolderSelection(model, msg)
    }

    private fun fetchedData(model: Model, msg: Msg.Inner.FetchedDataResult): UpdateResult {
        val initialCurrentFolderId =
            msg.chips.data.find { folder -> folder.id == 0L }?.id ?: model.currentSelectedFolderId

        return UpdatedModel(
            model = model.copy(
                base = model.base.copy(isLoading = false, isSuccessLoading = true, isError = false),
                notes = msg.notes,
                filters = msg.chips,
                currentSelectedFolderId = initialCurrentFolderId
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

    private fun onFoldersListClicked(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.NavigateToFoldersList))

    private fun onSwitchGridCountClicked(model: Model): UpdateResult {
        val count = if (model.notesGridCount == 1) 2 else 1
        return UpdatedModel(model.copy(notesGridCount = count))
    }

    private fun onFilterChipClicked(model: Model, msg: Msg.Ui.OnChipFilterClicked): UpdateResult =
        UpdatedModel(
            model = model.copy(currentSelectedFolderId = msg.id),
            effects = setOf(Eff.UpdateFolderSelection(msg.id))
        )

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
        }.sortDescendingPinnedByTimeThenByDate())

        return UpdatedModel(
            model = model.copy(notes = notes),
            commands = setOf(Cmd.UpdatePinnedNotesInCache(notes.data))
        )
    }
    /* private fun onPinSelectedNotesClicked(model: Model): UpdateResult {
        val selectedNotes = model.selectedNotes
        val isSelectedContainsUnpinnedNotes =
            selectedNotes.map { note -> note.isPinned }.contains(false)
        val notes = model.notes.copy(data = model.notes.data.map { note ->
            val isPinned = if (isSelectedContainsUnpinnedNotes) true else !note.isPinned
            return@map if (selectedNotes.contains(note)) note.copy(isPinned = isPinned) else note
        })
        val pinned = notes.data.filter { it.isPinned }
        val other = notes.data.filter { !it.isPinned }.sortedByDescending { it.id }
        val result = other.toMutableList().also { it -> it.addAll(0, pinned.sortedByDescending { it.isPinned }) }
        return UpdatedModel(
            model = model.copy(notes = notes.copy(result)),
            commands = setOf(Cmd.UpdatePinnedNotesInCache(notes.data))
        )
    }*/

    /* private fun onPinSelectedNotesClicked(model: Model): UpdateResult {
        val selectedNotes = model.selectedNotes
        val isSelectedContainsUnpinnedNotes =
            selectedNotes.map { note -> note.isPinned }.contains(false)
        val notes = model.notes.copy(data = model.notes.data.map { note ->
            val isPinned = if (isSelectedContainsUnpinnedNotes) true else !note.isPinned
            return@map if (selectedNotes.contains(note)) note.copy(isPinned = isPinned) else note
        })
        val pinned = notes.data.filter { it.isPinned }
        val other = notes.data.filter { !it.isPinned }.sortedByDescending { it.id }
        val result = other.toMutableList().also { it.addAll(0, pinned) }

        return UpdatedModel(
            model = model.copy(notes = notes.copy(result)),
            commands = setOf(Cmd.UpdatePinnedNotesInCache(notes.data))
        )
    }*/

    private fun replaceSelectedNotesToFolder(model: Model): UpdateResult = UpdatedModel(model)

    private fun onRemoveSelectedNotesClicked(model: Model): UpdateResult {
        val selectedNotes = model.selectedNotes
        val removedNotes = model.removedNotes.toMutableSet().also { list ->
            val isTrashItems = selectedNotes.map { note -> note.copy(isMovedToTrash = true) }
            list.addAll(isTrashItems)
        }.toSet()
        val notes = model.notes.copy(data = model.notes.data.filterNot { note ->
            removedNotes.any { it.id == note.id }
        })

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
            commands = setOf(Cmd.RemoveSelected(removedNotes.toList())),
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

    private fun onHideSnack(model: Model): UpdateResult =
        UpdatedModel(model.copy(isVisibleUndoRemoveNotesSnack = false, removedNotes = emptySet()))

    private fun updatedFolderSelection(
        model: Model,
        msg: Msg.Inner.UpdateCurrentSelectedFolder
    ): UpdateResult = UpdatedModel(model.copy(currentSelectedFolderId = msg.id))
}