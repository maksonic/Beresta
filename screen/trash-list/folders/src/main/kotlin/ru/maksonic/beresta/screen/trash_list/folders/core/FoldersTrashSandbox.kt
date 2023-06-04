package ru.maksonic.beresta.screen.trash_list.folders.core

import androidx.compose.material3.ExperimentalMaterial3Api
import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel

/**
 * @Author maksonic on 30.05.2023
 */
private typealias UpdateResult = UpdatedModel<Model, Set<Cmd>, Set<Eff>>

@OptIn(ExperimentalMaterial3Api::class)
class FoldersTrashSandbox(program: FoldersTrashProgram) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(Cmd.FetchRemovedData, Cmd.ReadLanguageFromDataStore),
    subscriptions = listOf(program)
) {
    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Inner.FetchedTrashDataResult -> fetchedDataResult(model, msg)
        is Msg.Inner.FetchedCurrentAppLang -> afterApplyLanguage(model, msg)
        is Msg.Inner.FetchedError -> UpdatedModel(model)
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
    ): UpdateResult = UpdatedModel(
        model.copy(
            base = model.base.copy(
                isLoading = false,
                isSuccessLoading = true
            ),
            folders = model.folders.copy(msg.folders),
            notes = msg.notes
        )
    )

    private fun afterApplyLanguage(
        model: Model,
        msg: Msg.Inner.FetchedCurrentAppLang
    ): UpdateResult =
        UpdatedModel(model.copy(currentAppLang = msg.language))

    private fun onTopBarBackPressed(model: Model): UpdateResult {
        val onClickEffect =
            if (model.modalBottomSheetState.isVisible) Eff.HideModalSheet else Eff.NavigateBack
        return UpdatedModel(model, effects = setOf(onClickEffect))
    }

    private fun onFolderClicked(model: Model, msg: Msg.Ui.OnFolderClicked): UpdateResult =
        if (model.isSelectionState)
            baseOnFolderAction(model, msg.id)
        else
            UpdatedModel(
                model = model.copy(
                    isVisibleModalSheet = !model.isVisibleModalSheet,
                    currentClickedFolderId = msg.id
                ),
            )

    private fun onFolderLongClicked(model: Model, msg: Msg.Ui.OnFolderLongClicked): UpdateResult =
        baseOnFolderAction(model, msg.id)

    private fun baseOnFolderAction(model: Model, folderId: Long): UpdateResult {
        val selected = model.selectedFolders.toMutableSet().also { list ->
            model.folders.data.forEach { folder ->
                if (folderId == folder.id) {
                    if (list.contains(folder)) list.remove(folder) else list.add(folder)
                }
            }
        }.toSet()

        return UpdatedModel(
            model.copy(
                selectedFolders = selected,
                isSelectionState = true,
            )
        )
    }

    private fun onSelectAllFoldersClicked(model: Model): UpdateResult {
        val selected = model.selectedFolders.toMutableSet().also { list ->
            if (list.containsAll(model.folders.data)) list.clear()
            else list.addAll(model.folders.data)
        }.toSet()

        return UpdatedModel(model.copy(selectedFolders = selected))
    }

    private fun onCancelSelectionClicked(model: Model): UpdateResult =
        UpdatedModel(model.copy(selectedFolders = emptySet(), isSelectionState = false))

    private fun updatedModalSheetState(
        model: Model,
        msg: Msg.Inner.UpdatedModalSheetState
    ): UpdateResult =
        UpdatedModel(model.copy(isVisibleModalSheet = msg.isVisible))

    private fun hideModalBottomSheet(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.HideModalSheet))

    private fun onModalSheetRestoreFolderClicked(model: Model): UpdateResult = UpdatedModel(
        model = model.copy(isVisibleAcceptDeleteFoldersDialog = false),
        commands = setOf(
            Cmd.DeleteOrRestoreFolders(
                isRestore = true,
                folders = model.folders.data.filter { it.id == model.currentClickedFolderId },
                notes = model.notes
            )
        ),
        effects = setOf(Eff.HideModalSheet)
    )

    private fun showedAcceptDeleteWarningDialog(model: Model): UpdateResult =
        UpdatedModel(
            model = model.copy(
                isVisibleAcceptDeleteFoldersDialog = true,
                isSingleItemAction = model.selectedFolders.count() <= 1
            ),
            effects = setOf(Eff.HideModalSheet)
        )

    private fun onBottomBarRestoreSelectedClicked(model: Model): UpdateResult = UpdatedModel(
        model = model.copy(selectedFolders = emptySet(), isSelectionState = false),
        commands = setOf(
            Cmd.DeleteOrRestoreFolders(
                isRestore = true, model.selectedFolders.toList(), notes = model.notes
            )
        )
    )

    private fun onCancelDeleteWarningDialogClicked(model: Model): UpdateResult = UpdatedModel(
        model = model.copy(
            isVisibleAcceptDeleteFoldersDialog = false,
            currentClickedFolderId = null,
        )
    )

    private fun onAcceptDeleteWarningDialogClicked(model: Model): UpdateResult {
        val currentClickedFolder =
            model.folders.data.filter { it.id == model.currentClickedFolderId }
        val delete = if (model.selectedFolders.isEmpty() && model.currentClickedFolderId != null)
            currentClickedFolder else model.selectedFolders.toList()

        return UpdatedModel(
            model = model.copy(
                isVisibleAcceptDeleteFoldersDialog = false,
                currentClickedFolderId = null,
                isSelectionState = false,
                selectedFolders = emptySet()
            ),
            commands = setOf(
                Cmd.DeleteOrRestoreFolders(isRestore = false, delete, notes = model.notes)
            )
        )
    }
}