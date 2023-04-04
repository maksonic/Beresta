package ru.maksonic.beresta.feature.folders_list.core.screen.core

import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel

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
        is Msg.Inner.UpdateCurrentSelectedFolder -> updatedFolderSelection(model, msg)
    }

    private fun fetchedData(model: Model, msg: Msg.Inner.FetchedFoldersResult): UpdateResult =
        UpdatedModel(
            model = model.copy(
                base = model.base.copy(isLoading = false, isSuccessLoading = true, isError = false),
                folders = msg.folders
            )
        )

    private fun onFolderClicked(model: Model, msg: Msg.Ui.OnFolderClicked): UpdateResult =
        UpdatedModel(
            model = model.copy(currentSelectedFolderId = msg.id),
            effects = setOf(Eff.NavigateBack, Eff.UpdateFolderSelection(msg.id))
        )

    private fun updatedDialogVisibilityState(
        model: Model,
        msg: Msg.Inner.UpdatedNewFolderDialogVisibility
    ): UpdateResult =
        UpdatedModel(model.copy(isVisibleNewFolderDialog = msg.isVisible))

    private fun updatedFolderSelection(
        model: Model,
        msg: Msg.Inner.UpdateCurrentSelectedFolder
    ): UpdateResult = UpdatedModel(model.copy(currentSelectedFolderId = msg.id))
}