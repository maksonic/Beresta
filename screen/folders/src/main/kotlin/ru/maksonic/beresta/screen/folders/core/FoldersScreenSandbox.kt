package ru.maksonic.beresta.screen.folders.core

import ru.maksonic.beresta.feature.folders_list.ui.api.unselectAll
import ru.maksonic.beresta.platform.elm.core.ElmBaseModel.Companion.Loading
import ru.maksonic.beresta.platform.elm.core.ElmBaseModel.Companion.loadedFailure
import ru.maksonic.beresta.platform.elm.core.ElmBaseModel.Companion.loadedSuccess
import ru.maksonic.beresta.platform.elm.core.ElmUpdate
import ru.maksonic.beresta.platform.elm.core.Sandbox

/**
 * @Author maksonic on 03.04.2023
 */
private typealias Update = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

class FoldersScreenSandbox(program: FoldersListProgram) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(Cmd.FetchFoldersWithNotes),
    subscriptions = listOf(program)
) {
    private companion object {
        private const val STICKY_START_FOLDER_ID = 1L
        private const val STICKY_END_FOLDER_ID = 2L
        private const val MINIMAL_FOR_LOADING_ITEMS_COUNT = 2000
    }

    override fun update(msg: Msg, model: Model): Update = when (msg) {
        is Msg.Inner.FetchedDataSuccess -> fetchedDataSuccess(model, msg)
        is Msg.Inner.FetchedDataFail -> fetchedDataFail(model, msg)
        is Msg.Ui.OnRetryFetchDataClicked -> retryFetchData(model)
        is Msg.Ui.OnTopBarBackPressed -> onTopBarBackPressed(model)
        is Msg.Ui.OnTopBarSortFolderClicked -> onTopBarSortFoldersClicked(model)
        is Msg.Ui.OnFolderClicked -> onFolderClicked(model, msg)
        is Msg.Ui.OnFolderLongClicked -> onFolderLongClicked(model, msg)
        is Msg.Ui.CancelSelectionState -> onCancelSelectionClicked(model)
        is Msg.Ui.OnTopBarSelectAllClicked -> onSelectAllFoldersClicked(model)
        is Msg.Ui.OnAddNewFolderClicked -> onAddNewFolderClicked(model)
        is Msg.Ui.OnCloseEditFolderDialogClicked -> onCloseEditFolderDialogClicked(model)
        is Msg.Ui.OnBottomBarPinSelectedClicked -> onBottomBarPinSelectedClicked(model)
        is Msg.Ui.OnBottomBarRemoveSelectedClicked -> onBottomBarRemoveSelectedClicked(model, msg)
        is Msg.Ui.OnBottomBarEditSelectedClicked -> onBottomBarEditSelectedClicked(model)
        is Msg.Ui.OnSnackUndoRemoveFoldersClicked -> onSnackBarUndoRemoveClicked(model)
        is Msg.Ui.OnHideModalBottomSheet -> onHideModalBottomSheet(model)
        is Msg.Inner.HiddenModalBottomSheet -> hiddenModalBottomSheet(model)
        is Msg.Inner.UpdatedHiddenNotesDialogVisibility -> {
            updatedHiddenNotesDialogVisibility(model, msg)
        }

        is Msg.Inner.NavigatedToHiddenNotes -> navigatedToHiddenNotes(model)
    }

    private fun fetchedDataSuccess(
        model: Model,
        msg: Msg.Inner.FetchedDataSuccess
    ): Update = ElmUpdate(
        model = model.copy(
            folders = model.folders.copy(
                state = model.folders.state.loadedSuccess,
                collection = msg.folders,
                isNotesMoving = msg.isNotesMoving
            ),
            sortState = msg.sortState
        )
    )

    private fun fetchedDataFail(model: Model, msg: Msg.Inner.FetchedDataFail): Update = ElmUpdate(
        model.copy(
            folders = model.folders.copy(state = model.folders.state.loadedFailure(msg.errorMsg))
        )
    )

    private fun retryFetchData(model: Model): Update = ElmUpdate(
        model.copy(folders = model.folders.copy(state = model.folders.state.Loading)),
        commands = setOf(Cmd.RetryFetchData)
    )

    private fun onTopBarBackPressed(model: Model): Update =
        ElmUpdate(model, effects = setOf(Eff.NavigateBack))

    private fun onTopBarSortFoldersClicked(model: Model): Update = ElmUpdate(
        model.copy(
            modalSheet = model.modalSheet.copy(
                isVisible = true,
                content = ModalSheetContent.SORT_FOLDERS
            ),
            isVisibleEditFolderDialog = false,
            isVisibleHiddenNotesDialog = false
        )
    )

    private fun onFolderClicked(model: Model, msg: Msg.Ui.OnFolderClicked): Update =
        if (model.folders.isSelection)
            baseOnFolderAction(model, msg.id)
        else {
            ElmUpdate(
                model = model,
                commands = setOf(Cmd.ChangeNoteFolderId(msg.id)),
                effects = setOf(Eff.NavigateBack)
            )
        }

    private fun onFolderLongClicked(model: Model, msg: Msg.Ui.OnFolderLongClicked): Update =
        baseOnFolderAction(model, msg.id)

    private fun baseOnFolderAction(model: Model, folderId: Long): Update {
        val folders = model.folders.collection.copy(model.folders.collection.data.map { note ->
            val isSelected = if (note.id == folderId) !note.isSelected else note.isSelected
            note.copy(isSelected = isSelected)
        })
        val selectedList = folders.data.filter { it.isSelected }
        val isVisibleUnpinButton = selectedList.isNotEmpty().run {
            if (this) !selectedList.map { it.isPinned }.contains(false) else false
        }
        val isSticky = folderId == STICKY_START_FOLDER_ID || folderId == STICKY_END_FOLDER_ID
        val updateResult = if (model.folders.isNotesMoving || isSticky) model else model.copy(
            folders = model.folders.copy(collection = folders, isSelection = true),
            isVisibleUnpinBottomBarIcon = isVisibleUnpinButton,
            modalSheet = model.modalSheet.copy(
                isVisible = false, content = ModalSheetContent.NOTHING
            ),
            isVisibleEditFolderDialog = false
        )
        return ElmUpdate(updateResult)
    }

    private fun onCancelSelectionClicked(model: Model): Update = ElmUpdate(
        model.copy(
            folders = model.folders.copy(
                collection = model.folders.collection.unselectAll(), isSelection = false
            ),
            isVisibleHiddenNotesDialog = false
        )
    )

    private fun onSelectAllFoldersClicked(model: Model): Update {
        val allSelected =
            model.folders.collection.data.filter { it.isSelectable }.all { it.isSelected }
        val folders = model.folders.collection.data.map { item ->
            if (item.isSelectable) item.copy(isSelected = !allSelected) else item
        }
        val selectedList = folders.filter { it.isSelected }
        val isVisibleUnpinButton = selectedList.isNotEmpty().run {
            if (this) !selectedList.map { it.isPinned }.contains(false) else false
        }

        return ElmUpdate(
            model.copy(
                isVisibleUnpinBottomBarIcon = isVisibleUnpinButton,
                folders = model.folders.copy(collection = model.folders.collection.copy(folders))
            )
        )
    }

    private fun onAddNewFolderClicked(model: Model): Update = ElmUpdate(
        model.copy(
            isVisibleEditFolderDialog = true,
            folders = model.folders.copy(
                isSelection = false, collection = model.folders.collection.unselectAll()
            )
        ),
        effects = setOf(Eff.AddNewFolder)
    )

    private fun onCloseEditFolderDialogClicked(model: Model): Update =
        ElmUpdate(model.copy(isVisibleEditFolderDialog = false))

    private fun onBottomBarPinSelectedClicked(model: Model): Update = ElmUpdate(
        model = model.copy(
            folders = model.folders.copy(
                collection = model.folders.collection.unselectAll(),
                isSelection = false
            )
        ),
        commands = setOf(
            Cmd.UpdatePinnedFolders(model.folders.collection.data.filter { it.isSelected })
        )
    )

    private fun onBottomBarRemoveSelectedClicked(
        model: Model,
        msg: Msg.Ui.OnBottomBarRemoveSelectedClicked
    ): Update {
        val selectedList = model.folders.collection.data.filter { it.isSelected }

        val isRemovedCurrentFolder =
            selectedList.map { folder -> folder.id == msg.currentSelectedFolderId }.contains(true)

        val currentSelectedId = if (isRemovedCurrentFolder) STICKY_START_FOLDER_ID
        else msg.currentSelectedFolderId

        return ElmUpdate(
            model.copy(
                folders = model.folders.copy(
                    state = model.folders.state.copy(
                        isLoading = selectedList.count() >= MINIMAL_FOR_LOADING_ITEMS_COUNT
                    ),
                    collection = model.folders.collection.unselectAll(),
                    removedList = selectedList,
                    isSelection = false,
                ),
            ),
            commands = setOf(
                Cmd.RemoveSelected(selectedList),
                Cmd.UpdateCurrentSelectedFolder(currentSelectedId)
            ),
            effects = setOf(
                Eff.ShowSnackBar("${selectedList.count()}")
            )
        )
    }

    private fun onBottomBarEditSelectedClicked(model: Model): Update = ElmUpdate(
        model = model.copy(isVisibleEditFolderDialog = true),
        effects = setOf(Eff.UpdateFolder(model.folders.collection.data.first { it.isSelected }.id))
    )

    private fun onSnackBarUndoRemoveClicked(model: Model): Update {
        val restored = model.folders.removedList.map { note -> note.copy(isMovedToTrash = false) }
        val isShowLoading = restored.count() >= MINIMAL_FOR_LOADING_ITEMS_COUNT

        return ElmUpdate(
            model.copy(
                folders = model.folders.copy(
                    state = model.folders.state.copy(isLoading = isShowLoading),
                    isSelection = false,
                    collection = model.folders.collection.copy(
                        model.folders.collection.data.map { it.copy(isSelected = false) }
                    )
                )
            ),
            commands = setOf(Cmd.UndoRemovedFolders(restored))
        )
    }

    private fun onHideModalBottomSheet(model: Model): Update =
        ElmUpdate(model, effects = setOf(Eff.HideModalSheet))

    private fun hiddenModalBottomSheet(model: Model): Update = ElmUpdate(
        model.copy(
            modalSheet = model.modalSheet.copy(
                isVisible = false, content = ModalSheetContent.NOTHING
            )
        )
    )

    private fun updatedHiddenNotesDialogVisibility(
        model: Model,
        msg: Msg.Inner.UpdatedHiddenNotesDialogVisibility
    ): Update =
        ElmUpdate(model.copy(isVisibleHiddenNotesDialog = msg.isVisible))

    private fun navigatedToHiddenNotes(model: Model): Update =
        ElmUpdate(model, effects = setOf(Eff.NavigateToHiddenNotes))
}