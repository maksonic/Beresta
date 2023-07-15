package ru.maksonic.beresta.screen.trash_list.folders.core

import androidx.compose.material3.ExperimentalMaterial3Api
import ru.maksonic.beresta.elm.core.ElmBaseModel.Companion.loadedSuccess
import ru.maksonic.beresta.elm.core.ElmUpdate
import ru.maksonic.beresta.elm.core.Sandbox

/**
 * @Author maksonic on 30.05.2023
 */
private typealias UpdateResult = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

@OptIn(ExperimentalMaterial3Api::class)
class FoldersTrashSandbox(program: FoldersTrashProgram) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(Cmd.FetchRemovedData),
    subscriptions = listOf(program)
) {
    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Inner.FetchedTrashDataResult -> fetchedDataResult(model, msg)
        is Msg.Inner.FetchedError -> ElmUpdate(model)
        is Msg.Ui.OnTopBarBackPressed -> onTopBarBackPressed(model)
        is Msg.Ui.OnFolderClicked -> onFolderClicked(model, msg)
        is Msg.Ui.OnFolderLongClicked -> onFolderLongClicked(model, msg)
        is Msg.Ui.OnSelectAllFoldersClicked -> onSelectAllFoldersClicked(model)
        is Msg.Ui.CancelSelectionState -> onCancelSelectionClicked(model)
        is Msg.Inner.UpdatedModalSheetState -> updatedModalSheetState(model, msg)
        is Msg.Ui.HideModalBottomSheet -> hideModalBottomSheet(model)
        is Msg.Ui.OnModalSheetRestoreClicked -> onModalSheetRestoreFolderClicked(model)
        is Msg.Ui.OnModalSheetDeleteClicked -> showedAcceptDeleteWarningDialog(model)
        is Msg.Ui.OnBottomBarDeleteSelectedFoldersClicked -> showedAcceptDeleteWarningDialog(model)
        is Msg.Ui.OnBottomBarRestoreSelectedFoldersClicked -> {
            onBottomBarRestoreSelectedClicked(model)
        }

        is Msg.Ui.OnCancelDeleteWarningDialogClicked -> onCancelDeleteWarningDialogClicked(model)
        is Msg.Ui.OnAcceptDeleteWarningDialogClicked -> onAcceptDeleteWarningDialogClicked(model)
    }

    private fun fetchedDataResult(
        model: Model,
        msg: Msg.Inner.FetchedTrashDataResult
    ): UpdateResult = ElmUpdate(
        model.copy(
            base = model.base.loadedSuccess,
            folders = model.folders.copy(msg.folders),
        )
    )

    private fun onTopBarBackPressed(model: Model): UpdateResult {
        val onClickEffect =
            if (model.modalBottomSheetState.isVisible) Eff.HideModalSheet else Eff.NavigateBack
        return ElmUpdate(model, effects = setOf(onClickEffect))
    }

    private fun onFolderClicked(model: Model, msg: Msg.Ui.OnFolderClicked): UpdateResult =
        if (model.isSelectionState)
            baseOnFolderAction(model, msg.id)
        else
            ElmUpdate(
                model = model.copy(
                    isVisibleModalSheet = !model.isVisibleModalSheet,
                    currentClickedFolderId = msg.id
                ),
            )

    private fun onFolderLongClicked(model: Model, msg: Msg.Ui.OnFolderLongClicked): UpdateResult =
        baseOnFolderAction(model, msg.id)

    private fun baseOnFolderAction(model: Model, folderId: Long): UpdateResult {
        val selected = model.selectedList.toMutableSet().also { list ->
            model.folders.data.forEach { folder ->
                if (folderId == folder.id) {
                    if (list.contains(folder)) list.remove(folder) else list.add(folder)
                }
            }
        }.toSet()

        return ElmUpdate(
            model.copy(
                selectedList = selected,
                isSelectionState = true,
            )
        )
    }

    private fun onSelectAllFoldersClicked(model: Model): UpdateResult {
        val selected = model.selectedList.toMutableSet().also { list ->
            if (list.containsAll(model.folders.data)) list.clear()
            else list.addAll(model.folders.data)
        }.toSet()

        return ElmUpdate(model.copy(selectedList = selected))
    }

    private fun onCancelSelectionClicked(model: Model): UpdateResult =
        ElmUpdate(model.copy(selectedList = emptySet(), isSelectionState = false))

    private fun updatedModalSheetState(
        model: Model,
        msg: Msg.Inner.UpdatedModalSheetState
    ): UpdateResult =
        ElmUpdate(model.copy(isVisibleModalSheet = msg.isVisible))

    private fun hideModalBottomSheet(model: Model): UpdateResult =
        ElmUpdate(model, effects = setOf(Eff.HideModalSheet))

    private fun onModalSheetRestoreFolderClicked(model: Model): UpdateResult = ElmUpdate(
        model = model.copy(isVisibleAcceptDeleteFoldersDialog = false),
        commands = setOf(
            Cmd.DeleteOrRestoreFolders(
                isRestore = true,
                folders = model.folders.data.filter { it.id == model.currentClickedFolderId },
            )
        ),
        effects = setOf(Eff.HideModalSheet)
    )

    private fun showedAcceptDeleteWarningDialog(model: Model): UpdateResult =
        ElmUpdate(
            model = model.copy(
                isVisibleAcceptDeleteFoldersDialog = true,
                isSingleItemAction = model.selectedList.count() <= 1
            ),
            effects = setOf(Eff.HideModalSheet)
        )

    private fun onBottomBarRestoreSelectedClicked(model: Model): UpdateResult = ElmUpdate(
        model = model.copy(selectedList = emptySet(), isSelectionState = false),
        commands = setOf(Cmd.DeleteOrRestoreFolders(isRestore = true, model.selectedList.toList()))
    )

    private fun onCancelDeleteWarningDialogClicked(model: Model): UpdateResult = ElmUpdate(
        model = model.copy(
            isVisibleAcceptDeleteFoldersDialog = false,
            currentClickedFolderId = null,
        )
    )

    private fun onAcceptDeleteWarningDialogClicked(model: Model): UpdateResult {
        val currentClickedFolder =
            model.folders.data.filter { it.id == model.currentClickedFolderId }
        val delete = if (model.selectedList.isEmpty() && model.currentClickedFolderId != null)
            currentClickedFolder else model.selectedList.toList()

        return ElmUpdate(
            model = model.copy(
                isVisibleAcceptDeleteFoldersDialog = false,
                currentClickedFolderId = null,
                isSelectionState = false,
                selectedList = emptySet()
            ),
            commands = setOf(Cmd.DeleteOrRestoreFolders(isRestore = false, delete))
        )
    }
}