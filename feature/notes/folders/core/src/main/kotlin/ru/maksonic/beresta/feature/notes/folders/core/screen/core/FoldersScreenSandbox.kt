package ru.maksonic.beresta.feature.notes.folders.core.screen.core

import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel
import ru.maksonic.beresta.feature.notes.folders.api.ui.NoteFolderUi
import ru.maksonic.beresta.feature.notes.folders.api.ui.isDefaultId

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
        is Msg.Ui.OnTopBarBackPressed -> onTopBarBackPressed(model)
        is Msg.Ui.OnFolderClicked -> onFolderClicked(model, msg)
        is Msg.Ui.OnFolderLongPressed -> onFolderLongClicked(model, msg)
        is Msg.Ui.CancelSelectionState -> onCancelSelectionClicked(model)
        is Msg.Ui.OnSelectAllFoldersClicked -> onSelectAllFoldersClicked(model)
        is Msg.Ui.OnAddNewFolderClicked -> onAddNewFolderClicked(model)
        is Msg.Inner.UpdateCurrentSelectedFolder -> updatedCurrentSelectedFolder(model, msg)
        is Msg.Ui.OnBarPinClicked -> onBarPinSelectedClicked(model)
        is Msg.Ui.OnBarRemoveClicked -> onBarMoveSelectedToTrashClicked(model)
        is Msg.Ui.OnBarEditClicked -> onBarEditClicked(model)
        is Msg.Inner.ShowRemovedNotesSnackBar -> showRemoveNotesSnackBar(model)
        is Msg.Inner.HideRemovedNotesSnackBar -> hideRemoveNotesSnackBar(model)
        is Msg.Ui.OnSnackUndoRemoveFoldersClicked -> onSnackBarUndoRemoveClicked(model)

    }

    private fun fetchedData(model: Model, msg: Msg.Inner.FetchedFoldersResult): UpdateResult =
        UpdatedModel(
            model = model.copy(
                base = model.base.copy(isLoading = false, isSuccessLoading = true, isError = false),
                folders = NoteFolderUi.Collection(msg.folders)
            )
        )

    private fun onTopBarBackPressed(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.NavigateBack))

    private fun onFolderClicked(model: Model, msg: Msg.Ui.OnFolderClicked): UpdateResult =
        if (model.isSelectionState)
            baseOnFolderAction(model, msg.id)
        else
            UpdatedModel(
                model = model.copy(),
                effects = setOf(
                    Eff.NavigateBack,
                    Eff.UpdateCurrentSelectedFolderInSharedState(msg.id)
                )
            )

    private fun onFolderLongClicked(model: Model, msg: Msg.Ui.OnFolderLongPressed): UpdateResult =
        baseOnFolderAction(model, msg.id)


    private fun baseOnFolderAction(model: Model, folderId: Long): UpdateResult {
        val selectedList = model.selectedFolders.toMutableSet().also { list ->
            model.folders.data.forEach { folder ->
                if (folderId == folder.id && folderId != NoteFolderUi.InitialSelected.id) {
                    if (list.contains(folder)) list.remove(folder) else list.add(folder)
                }
            }
        }.toSet()
        val isSelected = selectedList.isNotEmpty()
        val isShowUnpinButton = !selectedList.map { it.isPinned }.contains(false)

        return UpdatedModel(
            model.copy(
                selectedFolders = selectedList,
                isSelectionState = isSelected,
                isShowUnpinBottomBarIcon = isShowUnpinButton
            )
        )
    }

    private fun onCancelSelectionClicked(model: Model): UpdateResult =
        UpdatedModel(model.copy(selectedFolders = emptySet(), isSelectionState = false))

    private fun onSelectAllFoldersClicked(model: Model): UpdateResult {
        val selected = model.selectedFolders.toMutableSet().also { list ->
            if (list.containsAll(model.folders.data)) list.clear()
            else
                list.addAll(model.folders.data.filter { !it.isSticky })
        }.toSet()
        val isShowUnpinButton = !selected.map { it.isPinned }.contains(false)

        return UpdatedModel(
            model.copy(
                selectedFolders = selected,
                isSelectionState = selected.isNotEmpty(),
                isShowUnpinBottomBarIcon = isShowUnpinButton
            )
        )
    }

    private fun onAddNewFolderClicked(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.ShowFolderDialog(isNewFolder = true)))

    private fun updatedCurrentSelectedFolder(
        model: Model,
        msg: Msg.Inner.UpdateCurrentSelectedFolder
    ): UpdateResult = UpdatedModel(model.copy(currentSelectedFolderId = msg.id))

    private fun onBarPinSelectedClicked(model: Model): UpdateResult {
        return UpdatedModel(
            model = model.copy(selectedFolders = emptySet()),
            commands = setOf(Cmd.UpdatePinnedFoldersInCache(model.selectedFolders))
        )
    }

    private fun onBarMoveSelectedToTrashClicked(model: Model): UpdateResult {
        val removed = model.removedFolders.toMutableSet().also { list ->
            val isTrashItems =
                model.selectedFolders.map { folder -> folder.copy(isMovedToTrash = true) }
            list.addAll(isTrashItems)
        }.toSet()
        val notes = model.folders.copy(data = model.folders.data.filterNot { note ->
            removed.any { it.id == note.id }
        })
        val isRemoveCurrentFolder =
            removed.map { folder -> folder.id == model.currentSelectedFolderId }.contains(true)
        val currentSelectedId = if (isRemoveCurrentFolder) 0L else model.currentSelectedFolderId

        return UpdatedModel(
            model = model.copy(
                folders = notes,
                currentSelectedFolderId = currentSelectedId,
                selectedFolders = emptySet(),
                removedFolders = removed,
                isSelectionState = false,
            ),
            commands = setOf(Cmd.RemoveSelected(removed.toList())),
            effects = setOf(Eff.UpdateCurrentSelectedFolderInSharedState(currentSelectedId))
        )
    }

    private fun onBarEditClicked(model: Model): UpdateResult {
        val effect = if (model.selectedFolders.isEmpty()) emptySet()
        else setOf(Eff.ShowFolderDialog(isNewFolder = false, id = model.selectedFolders.first().id))

        return UpdatedModel(model, effects = effect)
    }

    private fun showRemoveNotesSnackBar(model: Model): UpdateResult =
        UpdatedModel(model.copy(isVisibleRemovedSnackBar = true))

    private fun hideRemoveNotesSnackBar(model: Model): UpdateResult =
        UpdatedModel(model.copy(isVisibleRemovedSnackBar = false, removedFolders = emptySet()))

    private fun onSnackBarUndoRemoveClicked(model: Model): UpdateResult {
        val removed = model.folders.copy(data = model.folders.data.toMutableList().also { folders ->
            folders.addAll(model.removedFolders.map { it.copy(isMovedToTrash = false) })
        }.toList())

        return UpdatedModel(
            model = model.copy(
                folders = removed,
                removedFolders = emptySet(),
            ),
            commands = setOf(Cmd.UndoRemoveNotes(removed.data)),
        )
    }
}