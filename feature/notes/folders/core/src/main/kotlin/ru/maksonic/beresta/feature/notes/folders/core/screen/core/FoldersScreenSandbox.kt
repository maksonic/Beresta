package ru.maksonic.beresta.feature.notes.folders.core.screen.core

import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel

/**
 * @Author maksonic on 03.04.2023
 */
private typealias UpdateResult = UpdatedModel<Model, Set<Cmd>, Set<Eff>>

class FoldersScreenSandbox(program: FoldersListProgram) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(
        Cmd.FetchFolders,
        Cmd.FetchPassedFromMainScreenArgs,
        Cmd.FetchCurrentAppLang
    ),
    subscriptions = listOf(program)
) {
    private companion object {
        private const val STICKY_START_FOLDER_ID = 1L
        private const val STICKY_END_FOLDER_ID = 2L
    }

    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Inner.FetchedFoldersResult -> fetchedData(model, msg)
        is Msg.Inner.FetchedPassedArgsFromMain -> fetchedPassedArgsFromMain(model, msg)
        is Msg.Inner.FetchedPassedReplaceNotesState -> fetchedPassedNotesMoveState(model, msg)
        is Msg.Ui.OnTopBarBackPressed -> onTopBarBackPressed(model)
        is Msg.Ui.OnFolderClicked -> onFolderClicked(model, msg)
        is Msg.Ui.OnFolderLongPressed -> onFolderLongClicked(model, msg)
        is Msg.Ui.CancelSelectionState -> onCancelSelectionClicked(model)
        is Msg.Ui.OnSelectAllFoldersClicked -> onSelectAllFoldersClicked(model)
        is Msg.Ui.OnAddNewFolderClicked -> onAddNewFolderClicked(model)
        is Msg.Ui.OnBarPinClicked -> onBarPinSelectedClicked(model)
        is Msg.Ui.OnBarRemoveClicked -> onBarMoveSelectedToTrashClicked(model)
        is Msg.Ui.OnBarEditClicked -> onBarEditClicked(model)
        is Msg.Inner.HideRemovedNotesSnackBar -> hideRemoveNotesSnackBar(model)
        is Msg.Ui.OnSnackUndoRemoveFoldersClicked -> onSnackBarUndoRemoveClicked(model)
        is Msg.Inner.UpdatedRemovedNotes -> updatedRemovedNotes(model, msg)
        is Msg.Inner.FetchedCurrentAppLang -> fetchedAppLang(model, msg)
    }

    private fun fetchedData(model: Model, msg: Msg.Inner.FetchedFoldersResult): UpdateResult {
        val folders = if (model.isMoveNotesToFolder) msg.folders.filter { !it.isStickyToStart }
        else msg.folders

        return UpdatedModel(
            model = model.copy(
                base = model.base.copy(
                    isLoading = false,
                    isSuccessLoading = true,
                    isError = false
                ),
                folders = model.folders.copy(folders), notes = msg.notes,
            )
        )
    }

    private fun fetchedPassedArgsFromMain(
        model: Model,
        msg: Msg.Inner.FetchedPassedArgsFromMain
    ): UpdateResult = UpdatedModel(
        model = model.copy(isMoveNotesToFolder = msg.isMove, currentSelectedFolderId = msg.id)
    )

    private fun fetchedPassedNotesMoveState(
        model: Model,
        msg: Msg.Inner.FetchedPassedReplaceNotesState
    ): UpdateResult = UpdatedModel(model.copy(moveNotesList = msg.notes))

    private fun onTopBarBackPressed(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.NavigateBack))

    private fun onFolderClicked(model: Model, msg: Msg.Ui.OnFolderClicked): UpdateResult =
        if (model.isSelectionState)
            baseOnFolderAction(model, msg.id)
        else {
            if (model.isMoveNotesToFolder) {
                UpdatedModel(
                    model = model,
                    commands = setOf(Cmd.ChangeNoteFolderId(msg.id, model.moveNotesList)),
                    effects = setOf(Eff.NavigateBack)
                )
            } else {
                UpdatedModel(
                    model = model,
                    effects = setOf(
                        Eff.UpdateCurrentSelectedFolderInSharedState(msg.id), Eff.NavigateBack
                    )
                )
            }
        }

    private fun onFolderLongClicked(model: Model, msg: Msg.Ui.OnFolderLongPressed): UpdateResult =
        baseOnFolderAction(model, msg.id)

    private fun baseOnFolderAction(model: Model, folderId: Long): UpdateResult {
        val selectedList = model.selectedFolders.toMutableSet().also { list ->
            model.folders.data.forEach { folder ->
                val isCanSelected = folderId == folder.id && folder.isSelectable
                if (isCanSelected) {
                    if (list.contains(folder)) list.remove(folder) else list.add(folder)
                }
            }
        }.toSet()

        val isVisibleUnpinButton = selectedList.isNotEmpty().run {
            if (this) !selectedList.map { it.isPinned }.contains(false) else false
        }
        val isSticky = folderId == STICKY_START_FOLDER_ID || folderId == STICKY_END_FOLDER_ID
        val updateResult = if (model.isMoveNotesToFolder || isSticky) model else model.copy(
            selectedFolders = selectedList,
            isSelectionState = true,
            isVisibleUnpinBottomBarIcon = isVisibleUnpinButton
        )
        return UpdatedModel(updateResult)
    }

    private fun onCancelSelectionClicked(model: Model): UpdateResult =
        UpdatedModel(model.copy(selectedFolders = emptySet(), isSelectionState = false))

    private fun onSelectAllFoldersClicked(model: Model): UpdateResult {
        val withoutStickyItemsList = model.folders.data.filter { it.isSelectable }
        val selectedList = model.selectedFolders.toMutableSet().also { list ->
            if (list.containsAll(withoutStickyItemsList)) list.clear()
            else list.addAll(withoutStickyItemsList)
        }.toSet()

        val isVisibleUnpinButton = selectedList.isNotEmpty().run {
            if (this) !selectedList.map { it.isPinned }.contains(false) else false
        }

        return UpdatedModel(
            model.copy(
                selectedFolders = selectedList,
                isVisibleUnpinBottomBarIcon = isVisibleUnpinButton
            )
        )
    }

    private fun onAddNewFolderClicked(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.ShowFolderDialog(isNewFolder = true)))

    private fun onBarPinSelectedClicked(model: Model): UpdateResult {
        return UpdatedModel(
            model = model.copy(selectedFolders = emptySet()),
            commands = setOf(Cmd.UpdatePinnedFoldersInCache(model.selectedFolders))
        )
    }

    private fun onBarMoveSelectedToTrashClicked(model: Model): UpdateResult {
        val folders =
            model.folders.copy(model.folders.data.filter { !model.selectedFolders.contains(it) })

        // Set initial sticky folder to current if previous current folder was moved to trash.
        val isRemoveCurrentFolder =
            model.selectedFolders.map { folder -> folder.id == model.currentSelectedFolderId }
                .contains(true)
        val currentSelectedId = if (isRemoveCurrentFolder) 1L else model.currentSelectedFolderId

        return UpdatedModel(
            model = model.copy(
                base = model.base.copy(isLoading = true),
                folders = folders,
                currentSelectedFolderId = currentSelectedId,
                selectedFolders = emptySet(),
                removedFolders = model.selectedFolders,
                isSelectionState = false,
                isVisibleRemovedSnackBar = true
            ),
            commands = setOf(Cmd.RemoveSelected(model.selectedFolders.toList(), model.notes)),
            effects = setOf(Eff.UpdateCurrentSelectedFolderInSharedState(currentSelectedId))
        )
    }

    private fun onBarEditClicked(model: Model): UpdateResult {
        val effect = if (model.selectedFolders.isEmpty()) emptySet()
        else setOf(Eff.ShowFolderDialog(isNewFolder = false, id = model.selectedFolders.first().id))

        return UpdatedModel(model, effects = effect)
    }

    private fun hideRemoveNotesSnackBar(model: Model): UpdateResult = UpdatedModel(
        model = model.copy(
            base = model.base.copy(isLoading = false),
            isVisibleRemovedSnackBar = false,
            removedFolders = emptySet()
        )
    )

    private fun updatedRemovedNotes(
        model: Model,
        msg: Msg.Inner.UpdatedRemovedNotes
    ): UpdateResult = UpdatedModel(model = model.copy(removedNotes = msg.removedNotes.toSet()))


    private fun onSnackBarUndoRemoveClicked(model: Model): UpdateResult = UpdatedModel(
        model = model.copy(
            base = model.base.copy(isLoading = true),
            removedFolders = emptySet(),
            removedNotes = emptySet(),
        ),
        commands = setOf(
            Cmd.UndoRemovedFolders(
                folders = model.folders.data,
                removedFolders = model.removedFolders.toList(),
                notes = model.notes,
                removedNotes = model.removedNotes.toList()
            )
        )
    )

    private fun fetchedAppLang(model: Model, msg: Msg.Inner.FetchedCurrentAppLang): UpdateResult =
        UpdatedModel(model.copy(currentLang = msg.language))
}