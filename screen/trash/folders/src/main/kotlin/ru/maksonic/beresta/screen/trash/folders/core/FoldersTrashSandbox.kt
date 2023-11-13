package ru.maksonic.beresta.screen.trash.folders.core

import ru.maksonic.beresta.feature.folders_list.ui.api.FolderUi
import ru.maksonic.beresta.feature.folders_list.ui.api.unselectAll
import ru.maksonic.beresta.platform.elm.core.ElmBaseModel.Companion.Loading
import ru.maksonic.beresta.platform.elm.core.ElmBaseModel.Companion.loadedFailure
import ru.maksonic.beresta.platform.elm.core.ElmBaseModel.Companion.loadedSuccess
import ru.maksonic.beresta.platform.elm.core.ElmUpdate
import ru.maksonic.beresta.platform.elm.core.Sandbox

/**
 * @Author maksonic on 30.05.2023
 */
private typealias Update = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

class FoldersTrashSandbox(program: FoldersTrashProgram) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(Cmd.FetchData),
    subscriptions = listOf(program)
) {
    override fun update(msg: Msg, model: Model): Update = when (msg) {
        is Msg.Inner.FetchedDataResult -> fetchedDataResult(model, msg)
        is Msg.Inner.FetchedDataFail -> fetchedDataFail(model, msg)
        is Msg.Ui.OnRetryFetchDataClicked -> retryFetchData(model)
        is Msg.Ui.OnTopBarBackPressed -> onTopBarBackPressed(model)
        is Msg.Ui.OnFolderClicked -> onFolderClicked(model, msg)
        is Msg.Ui.OnFolderLongClicked -> onFolderLongClicked(model, msg)
        is Msg.Ui.OnSelectAllFoldersClicked -> onSelectAllFoldersClicked(model)
        is Msg.Ui.CancelSelectionState -> onCancelSelectionClicked(model)
        is Msg.Inner.UpdatedModalSheetState -> updatedModalSheetState(model, msg)
        is Msg.Ui.HideModalBottomSheet -> hideModalBottomSheet(model)
        is Msg.Ui.OnModalSheetRestoreClicked -> onModalSheetRestoreFolderClicked(model)
        is Msg.Ui.OnModalSheetDeleteClicked -> showedAcceptDeleteWarningDialog(model)
        is Msg.Ui.OnBottomBarDeleteFoldersClicked -> showedAcceptDeleteWarningDialog(model)
        is Msg.Ui.OnBottomBarRestoreFoldersClicked -> onBottomBarRestoreSelectedClicked(model)
        is Msg.Ui.OnCancelDeleteWarningDialogClicked -> onCancelDeleteWarningDialogClicked(model)
        is Msg.Ui.OnAcceptDeleteWarningDialogClicked -> onAcceptDeleteWarningDialogClicked(model)
        is Msg.Ui.OnDeleteAllFoldersClicked -> onDeleteAllFoldersClicked(model)
    }

    private fun fetchedDataResult(
        model: Model,
        msg: Msg.Inner.FetchedDataResult
    ): Update = ElmUpdate(
        model.copy(base = model.base.loadedSuccess, folders = model.folders.copy(msg.folders))
    )

    private fun fetchedDataFail(model: Model, msg: Msg.Inner.FetchedDataFail): Update =
        ElmUpdate(
            model.copy(
                base = model.base.loadedFailure(msg.message),
                folders = FolderUi.Collection.Empty,
            )
        )

    private fun retryFetchData(model: Model): Update = ElmUpdate(
        model = model.copy(base = model.base.Loading),
        commands = setOf(Cmd.RetryFetchData)
    )

    private fun onTopBarBackPressed(model: Model): Update = ElmUpdate(
        model = model,
        effects = setOf(if (model.modalSheet.isVisible) Eff.HideModalSheet else Eff.NavigateBack)
    )

    private fun onFolderClicked(model: Model, msg: Msg.Ui.OnFolderClicked): Update =
        if (model.isSelection)
            baseOnFolderAction(model, msg.id)
        else
            ElmUpdate(
                model = model.copy(
                    modalSheet = model.modalSheet.copy(isVisible = !model.modalSheet.isVisible),
                    currentClickedFolderId = msg.id
                ),
            )

    private fun onFolderLongClicked(model: Model, msg: Msg.Ui.OnFolderLongClicked): Update =
        baseOnFolderAction(model, msg.id)

    private fun baseOnFolderAction(model: Model, folderId: Long): Update {
        val folders = model.folders.copy(model.folders.data.map { note ->
            val isSelected = if (note.id == folderId) !note.isSelected else note.isSelected
            note.copy(isSelected = isSelected)
        })

        return ElmUpdate(model.copy(folders = folders, isSelection = true))
    }

    private fun onSelectAllFoldersClicked(model: Model): Update {
        val allSelected = model.folders.data.filter { it.isSelectable }.all { it.isSelected }
        val folders = model.folders.copy(model.folders.data.map { item ->
            if (item.isSelectable) item.copy(isSelected = !allSelected) else item
        })

        return ElmUpdate(model.copy(folders = folders))
    }

    private fun onCancelSelectionClicked(model: Model): Update =
        ElmUpdate(model.copy(folders = model.folders.unselectAll(), isSelection = false))

    private fun updatedModalSheetState(
        model: Model,
        msg: Msg.Inner.UpdatedModalSheetState
    ): Update =
        ElmUpdate(model.copy(modalSheet = model.modalSheet.copy(isVisible = msg.isVisible)))

    private fun hideModalBottomSheet(model: Model): Update =
        ElmUpdate(model, effects = setOf(Eff.HideModalSheet))

    private fun onModalSheetRestoreFolderClicked(model: Model): Update {
        val findFolder = model.folders.data.find { it.id == model.currentClickedFolderId }
        val restoredFolder = if (findFolder != null) listOf(findFolder) else emptyList()

        return ElmUpdate(
            model = model.copy(isVisibleAcceptDeleteFoldersDialog = false),
            commands = setOf(Cmd.RestoreFolders(folders = restoredFolder)),
            effects = setOf(Eff.HideModalSheet)
        )
    }

    private fun showedAcceptDeleteWarningDialog(model: Model): Update = ElmUpdate(
        model = model.copy(
            isVisibleAcceptDeleteFoldersDialog = true,
            isSingleSelection = model.folders.data.count { it.isSelected } <= 1
        ),
        effects = setOf(Eff.HideModalSheet)
    )

    private fun onBottomBarRestoreSelectedClicked(model: Model): Update = ElmUpdate(
        model = model.copy(isSelection = false),
        commands = setOf(Cmd.RestoreFolders(model.folders.data.filter { it.isSelected }))
    )

    private fun onCancelDeleteWarningDialogClicked(model: Model): Update = ElmUpdate(
        model = model.copy(
            isVisibleAcceptDeleteFoldersDialog = false, currentClickedFolderId = null
        )
    )

    private fun onAcceptDeleteWarningDialogClicked(model: Model): Update {
        val selectedList = model.folders.data.filter { it.isSelected }
        val currentClickedFolder =
            model.folders.data.filter { it.id == model.currentClickedFolderId }
        val delete = if (selectedList.isEmpty() && model.currentClickedFolderId != null)
            currentClickedFolder else selectedList

        return ElmUpdate(
            model = model.copy(
                isVisibleAcceptDeleteFoldersDialog = false,
                currentClickedFolderId = null,
                isSelection = false,
            ),
            commands = setOf(Cmd.DeleteFolders(delete))
        )
    }

    private fun onDeleteAllFoldersClicked(model: Model): Update {
        val folders = model.folders.copy(model.folders.data.map { item ->
            item.copy(isSelected = true)
        })

        return ElmUpdate(
            model = model.copy(
                folders = folders,
                isVisibleAcceptDeleteFoldersDialog = true,
                currentClickedFolderId = null,
                isSelection = true,
                isSingleSelection = false
            )
        )
    }
}