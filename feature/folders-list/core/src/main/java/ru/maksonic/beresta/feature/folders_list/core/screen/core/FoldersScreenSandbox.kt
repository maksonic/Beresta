package ru.maksonic.beresta.feature.folders_list.core.screen.core

import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel
import ru.maksonic.beresta.feature.folders_list.api.ui.FilterChipUi

/**
 * @Author maksonic on 03.04.2023
 */
private typealias UpdateResult = UpdatedModel<Model, Set<Cmd>, Set<Eff>>

class FoldersScreenSandbox(program: FoldersListProgram) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(Cmd.FetchFolders),
    subscriptions = listOf(program)
) {

    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Inner.FetchedFoldersResult -> fetchedData(model, msg)
        is Msg.Inner.UpdatedNewFolderDialogVisibility -> updatedDialogVisibilityState(model, msg)
        is Msg.Ui.OnTopBarBackPressed -> UpdatedModel(model, effects = setOf(Eff.NavigateBack))
        is Msg.Ui.OnFolderClicked -> onFolderClicked(model, msg)
        is Msg.Ui.OnFolderLongPressed -> onFolderLongClicked(model, msg)
        is Msg.Inner.UpdateCurrentSelectedFolder -> updatedFolderSelection(model, msg)
        is Msg.Ui.OnCancelSelectionClicked -> cancelFoldersSelection(model)
        is Msg.Ui.OnPinSelectedFoldersClicked -> onPinSelectedFoldersClicked(model)
        is Msg.Ui.OnSelectAllFoldersClicked -> onSelectAllFoldersClicked(model)
        is Msg.Inner.HideRemovedFoldersSnack -> onHideSnack(model)
        is Msg.Ui.OnRemoveSelectedFoldersClicked -> onRemoveSelectedFoldersClicked(model)
        // is Msg.Ui.OnRemoveFoldersUndoClicked -> onRemoveSelectedFoldersUndoClicked(model)
        is Msg.Ui.OnEditFolderClicked -> onEditFolderClicked(model)
        is Msg.Ui.OnAddFolderClicked -> onAddFolderClicked(model)
    }

    private fun fetchedData(model: Model, msg: Msg.Inner.FetchedFoldersResult): UpdateResult =
        UpdatedModel(
            model = model.copy(
                base = model.base.copy(isLoading = false, isSuccessLoading = true, isError = false),
                folders = FilterChipUi.Collection(msg.folders)
            )
        )

    private fun onAddFolderClicked(model: Model): UpdateResult =
        UpdatedModel(
            model.copy(currentSelectedFolderId = 0),
            effects = setOf(Eff.ShowFolderDialog, Eff.UpdatePassedEditableFolderId(0))
        )

    private fun onFolderClicked(model: Model, msg: Msg.Ui.OnFolderClicked): UpdateResult =
        if (model.isSelectionState)
            baseOnFolderAction(model, msg.id)
        else
            UpdatedModel(
                model = model.copy(isVisibleSelectionPanel = false),
                effects = setOf(Eff.NavigateBack, Eff.UpdateFolderSelection(msg.id))
            )

    private fun onFolderLongClicked(model: Model, msg: Msg.Ui.OnFolderLongPressed): UpdateResult =
        baseOnFolderAction(model, msg.id)

    private fun updatedDialogVisibilityState(
        model: Model,
        msg: Msg.Inner.UpdatedNewFolderDialogVisibility
    ): UpdateResult {
        val effect = if (model.selectedFolders.isNotEmpty()) setOf(
            Eff.UpdatePassedEditableFolderId(model.selectedFolders.first().id)
        ) else emptySet()

        return UpdatedModel(
            model.copy(isVisibleNewFolderDialog = msg.isVisible),
            effects = effect
        )
    }

    private fun onEditFolderClicked(model: Model): UpdateResult {
        val effect = if (model.selectedFolders.isNotEmpty()) setOf(
            Eff.UpdatePassedEditableFolderId(model.selectedFolders.first().id), Eff.ShowFolderDialog
        ) else emptySet()

        return UpdatedModel(
            model.copy(isVisibleNewFolderDialog = true),
            effects = effect
        )
    }

    private fun updatedFolderSelection(
        model: Model,
        msg: Msg.Inner.UpdateCurrentSelectedFolder
    ): UpdateResult = UpdatedModel(model.copy(currentSelectedFolderId = msg.id))

    private fun baseOnFolderAction(model: Model, folderId: Long): UpdateResult {
        val selectedList = model.selectedFolders.toMutableSet().also { list ->
            model.folders.data.forEach { folder ->
                if (folderId == folder.id && folderId != FilterChipUi.InitialSelected.id) {
                    if (list.contains(folder)) list.remove(folder) else list.add(folder)
                }
            }
        }.toSet()
        val isSelected = selectedList.isNotEmpty()

        return UpdatedModel(
            model.copy(
                selectedFolders = selectedList,
                isSelectionState = isSelected,
                isVisibleSelectionPanel = isSelected,
                selectedFoldersCount = selectedList.count()
            )
        )
    }

    private fun onSelectAllFoldersClicked(model: Model): UpdateResult {
        val selectedList = model.selectedFolders.toMutableSet().also { list ->
            if (list.containsAll(model.folders.data)) list.clear()
            else list.addAll(model.folders.data.filter { it.id != FilterChipUi.InitialSelected.id })
        }.toSet()

        val isSelected = selectedList.isNotEmpty()
        val isShowUnpinButton = !selectedList.map { it.isPinned }.contains(false)

        return UpdatedModel(
            model = model.copy(
                selectedFolders = selectedList,
                isSelectionState = isSelected,
                isShowBottomBarUnpinBtn = isShowUnpinButton,
                selectedFoldersCount = selectedList.count(),
                isVisibleSelectionPanel = isSelected
            ),
        )
    }

    private fun cancelFoldersSelection(model: Model): UpdateResult = UpdatedModel(
        model = model.copy(
            isSelectionState = false,
            selectedFolders = emptySet(),
            selectedFoldersCount = 0,
            isVisibleSelectionPanel = false
        )
    )
}

private fun onRemoveSelectedFoldersClicked(model: Model): UpdateResult {
    val selectedFolders = model.selectedFolders
    val removedFolders = model.removedFolders.toMutableSet().also { list ->
        val isTrashItems = selectedFolders.map { folder -> folder.copy(isMovedToTrash = true) }
        list.addAll(isTrashItems)
    }.toSet()

    val folders = model.folders.copy(
        data = model.folders.data.map { folder ->
            if (selectedFolders.contains(folder)) {
                folder.copy(isMovedToTrash = true)
            } else
                folder.copy(isMovedToTrash = false)
        }.filter { !it.isMovedToTrash }
    )

    return UpdatedModel(
        model.copy(
            folders = folders,
            selectedFolders = emptySet(),
            removedFolders = removedFolders,
            isSelectionState = false,
            isVisibleSelectionPanel = false,
            selectedFoldersCount = 0,
            isVisibleUndoRemoveNotesSnack = true
        ),
        commands = setOf(Cmd.RemoveSelectedFolders(selectedFolders.toList())),
    )
}

// TODO: Update
private fun onRemoveSelectedFoldersUndoClicked(model: Model): UpdateResult {
    val undoRemoved =
        model.folders.copy(data = model.folders.data.toMutableList().also { notes ->
            notes.addAll(model.removedFolders.map { it.copy(isMovedToTrash = false) })
        }.toList())

    return UpdatedModel(
        model = model.copy(
            folders = undoRemoved,
            removedFolders = emptySet(),
            isVisibleUndoRemoveNotesSnack = false
        ),
        //  commands = setOf(Cmd.UndoRemoved(undoRemoved.data)),
    )
}

private fun onPinSelectedFoldersClicked(model: Model): UpdateResult {
    val selectedFolders = model.selectedFolders
    val isSelectedContainsUnpinnedNotes =
        selectedFolders.map { note -> note.isPinned }.contains(false)
    val folders = model.folders.copy(data = model.folders.data.map { note ->
        val isPinned = if (isSelectedContainsUnpinnedNotes) true else !note.isPinned
        return@map if (selectedFolders.contains(note)) note.copy(isPinned = isPinned) else note
    })

    return UpdatedModel(
        model = model.copy(folders = folders),
        commands = setOf(Cmd.UpdatePinnedFoldersInCache(folders.data))
    )
}

private fun onHideSnack(model: Model): UpdateResult =
    UpdatedModel(model.copy(isVisibleUndoRemoveNotesSnack = false, removedFolders = emptySet()))
