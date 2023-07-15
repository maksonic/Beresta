package ru.maksonic.beresta.screen.folders.core

import androidx.compose.material3.ExperimentalMaterial3Api
import ru.maksonic.beresta.elm.core.ElmBaseModel.Companion.Loading
import ru.maksonic.beresta.elm.core.ElmBaseModel.Companion.loadedFailure
import ru.maksonic.beresta.elm.core.ElmBaseModel.Companion.loadedSuccess
import ru.maksonic.beresta.elm.core.ElmUpdate
import ru.maksonic.beresta.elm.core.Sandbox

/**
 * @Author maksonic on 03.04.2023
 */
private typealias UpdateResult = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

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

    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Ui.RetryFetchData -> retryFetchData(model)
        is Msg.Inner.FetchedFoldersData -> fetchedFoldersWithNotes(model, msg)
        is Msg.Inner.FetchedDataError -> fetchedDataError(model, msg)
        is Msg.Ui.OnTopBarBackPressed -> onTopBarBackPressed(model)
        is Msg.Ui.OnTopBarSortFolderClicked -> onTopBarSortFoldersClicked(model)
        is Msg.Ui.OnFolderClicked -> onFolderClicked(model, msg)
        is Msg.Ui.OnFolderLongPressed -> onFolderLongClicked(model, msg)
        is Msg.Ui.CancelSelectionState -> onCancelSelectionClicked(model)
        is Msg.Ui.OnTopBarSelectAllClicked -> onSelectAllFoldersClicked(model)
        is Msg.Ui.OnAddNewFolderClicked -> onAddNewFolderClicked(model)
        is Msg.Ui.OnBottomBarPinSelectedClicked -> onBarPinSelectedClicked(model)
        is Msg.Ui.OnBottomBarRemoveSelectedClicked -> onBarMoveSelectedToTrashClicked(model, msg)
        is Msg.Ui.OnBottomBarEditSelectedClicked -> onBarEditClicked(model)
        is Msg.Inner.HideRemovedFoldersSnackBar -> hideRemoveNotesSnackBar(model)
        is Msg.Ui.OnSnackUndoRemoveFoldersClicked -> onSnackBarUndoRemoveClicked(model)
        //modal sheet
        is Msg.Ui.OnHideModalBottomSheet -> onHideModalBottomSheet(model)
        is Msg.Inner.HiddenModalBottomSheet -> hiddenModalBottomSheet(model)
    }

    private fun retryFetchData(model: Model): UpdateResult = ElmUpdate(
        model.copy(base = model.base.Loading), commands = setOf(Cmd.RetryFetchFoldersWithNotes)
    )

    private fun fetchedFoldersWithNotes(
        model: Model,
        msg: Msg.Inner.FetchedFoldersData
    ): UpdateResult =
        ElmUpdate(
            model = model.copy(
                base = model.base.loadedSuccess,
                isMoveNotesToFolder = msg.isMoveNotesToFolderState,
                folders = msg.folders
            )
        )

    private fun fetchedDataError(model: Model, msg: Msg.Inner.FetchedDataError): UpdateResult =
        ElmUpdate(model.copy(base = model.base.loadedFailure(msg.errorMsg)))

    private fun onTopBarBackPressed(model: Model): UpdateResult =
        ElmUpdate(model, effects = setOf(Eff.NavigateBack))

    @OptIn(ExperimentalMaterial3Api::class)
    private fun onTopBarSortFoldersClicked(model: Model): UpdateResult = ElmUpdate(
        model.copy(
            modalSheet = model.modalSheet.copy(
                isVisible = true,
                content = ModalSheetContent.SORT_FOLDERS
            )
        )
    )

    private fun onFolderClicked(model: Model, msg: Msg.Ui.OnFolderClicked): UpdateResult =
        if (model.isSelectionState)
            baseOnFolderAction(model, msg.id)
        else {
            ElmUpdate(
                model = model,
                commands = setOf(Cmd.ChangeNoteFolderId(msg.id)),
                effects = setOf(Eff.NavigateBack)
            )
        }

    private fun onFolderLongClicked(model: Model, msg: Msg.Ui.OnFolderLongPressed): UpdateResult =
        baseOnFolderAction(model, msg.id)

    private fun baseOnFolderAction(model: Model, folderId: Long): UpdateResult {
        val selectedList = model.selectedList.toMutableSet().also { list ->
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
            selectedList = selectedList,
            isSelectionState = true,
            isVisibleUnpinBottomBarIcon = isVisibleUnpinButton
        )
        return ElmUpdate(updateResult)
    }

    private fun onCancelSelectionClicked(model: Model): UpdateResult =
        ElmUpdate(model.copy(selectedList = emptySet(), isSelectionState = false))

    private fun onSelectAllFoldersClicked(model: Model): UpdateResult {
        val withoutStickyItemsList = model.folders.data.filter { it.isSelectable }
        val selectedList = model.selectedList.toMutableSet().also { list ->
            if (list.containsAll(withoutStickyItemsList)) list.clear()
            else list.addAll(withoutStickyItemsList)
        }.toSet()

        val isVisibleUnpinButton = selectedList.isNotEmpty().run {
            if (this) !selectedList.map { it.isPinned }.contains(false) else false
        }

        return ElmUpdate(
            model.copy(
                selectedList = selectedList, isVisibleUnpinBottomBarIcon = isVisibleUnpinButton
            )
        )
    }

    private fun onAddNewFolderClicked(model: Model): UpdateResult =
        ElmUpdate(model, effects = setOf(Eff.ShowFolderDialog()))

    private fun onBarPinSelectedClicked(model: Model): UpdateResult {
        return ElmUpdate(
            model = model.copy(selectedList = emptySet()),
            commands = setOf(Cmd.UpdatePinnedFoldersInCache(model.selectedList))
        )
    }

    private fun onBarMoveSelectedToTrashClicked(model: Model, msg: Msg.Ui.OnBottomBarRemoveSelectedClicked): UpdateResult {
        val isShowLoading = model.selectedList.count() >= MINIMAL_FOR_LOADING_ITEMS_COUNT
        // Set initial sticky folder to current if previous current folder was moved to trash.
        val isWasRemovedCurrentFolder =
            model.selectedList.map { folder -> folder.id == msg.currentSelectedFolderId }
                .contains(true)

        val currentSelectedId = if (isWasRemovedCurrentFolder) STICKY_START_FOLDER_ID
        else msg.currentSelectedFolderId

        return ElmUpdate(
            model = model.copy(
                base = model.base.copy(isLoading = isShowLoading),
                selectedList = emptySet(),
                removedList = model.selectedList,
                isSelectionState = false,
                isVisibleRemovedSnackBar = true
            ),
            commands = setOf(
                Cmd.RemoveSelected(model.selectedList.toList()),
                Cmd.UpdateCurrentSelectedFolder(currentSelectedId)
            ),
        )
    }

    private fun onBarEditClicked(model: Model): UpdateResult {
        val effect = if (model.selectedList.isEmpty()) emptySet()
        else setOf(Eff.ShowFolderDialog(model.selectedList.first().id))

        return ElmUpdate(model, effects = effect)
    }

    private fun hideRemoveNotesSnackBar(model: Model): UpdateResult = ElmUpdate(
        model = model.copy(
            base = model.base.copy(isLoading = false),
            isVisibleRemovedSnackBar = false,
            removedList = emptySet()
        )
    )

    private fun onSnackBarUndoRemoveClicked(model: Model): UpdateResult {
        val restored = model.removedList.map { folder -> folder.copy(isMovedToTrash = false) }
        val isShowLoading = restored.count() >= MINIMAL_FOR_LOADING_ITEMS_COUNT

        return ElmUpdate(
            model = model.copy(base = model.base.copy(isLoading = isShowLoading)),
            commands = setOf(Cmd.UndoRemovedFolders(restored))
        )
    }

    private fun onHideModalBottomSheet(model: Model): UpdateResult =
        ElmUpdate(model, effects = setOf(Eff.HideModalSheet))

    @OptIn(ExperimentalMaterial3Api::class)
    private fun hiddenModalBottomSheet(model: Model): UpdateResult = ElmUpdate(
        model.copy(
            modalSheet = model.modalSheet.copy(
                isVisible = false, content = ModalSheetContent.NOTHING
            )
        )
    )

}