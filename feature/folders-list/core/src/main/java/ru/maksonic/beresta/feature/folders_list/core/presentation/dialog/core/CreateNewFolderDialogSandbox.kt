package ru.maksonic.beresta.feature.folders_list.core.presentation.dialog.core

import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel
import ru.maksonic.beresta.feature.folders_list.api.ui.FilterChipUi

/**
 * @Author maksonic on 30.03.2023
 */
private typealias UpdateResult = UpdatedModel<DialogModel, Set<Cmd>, Set<Eff>>

class CreateNewFolderDialogSandbox(
    program: NewFolderDialogProgram
) : Sandbox<DialogModel, Msg, Cmd, Eff>(
    initialModel = DialogModel(),
    subscriptions = listOf(program)
) {
    companion object {
        private const val MAX_FOLDER_LENGTH = 50
    }

    override fun update(msg: Msg, model: DialogModel): UpdateResult = when (msg) {
        is Msg.Inner.UpdateNewFolderNameInput -> updatedNewFolderInputField(model, msg)
        is Msg.Ui.AddNewNotesFolder -> onAddNewFolderClicked(model)
        is Msg.Ui.OnCreateNewNotesFolderClicked -> onCreateNewFolderClicked(model)
        is Msg.Ui.OnDismissFolderCreationDialogClicked -> onDismissCreateFolderDialogClicked(model)
    }

    private fun updatedNewFolderInputField(
        model: DialogModel,
        msg: Msg.Inner.UpdateNewFolderNameInput
    ): UpdateResult {
        val croppedInput = msg.value.take(MAX_FOLDER_LENGTH)

        return UpdatedModel(model.copy(folderInputName = croppedInput))
    }

    private fun onAddNewFolderClicked(model: DialogModel): UpdateResult =
        UpdatedModel(
            model.copy(folderInputName = ""), effects = setOf(Eff.ShowNewFolderDialog)
        )

    private fun onCreateNewFolderClicked(model: DialogModel): UpdateResult =
        UpdatedModel(
            model.copy(folderInputName = ""),
            commands = setOf(Cmd.AddNewFolderToCache(FilterChipUi(title = model.folderInputName))),
            effects = setOf(Eff.HideNewFolderDialog)
        )

    private fun onDismissCreateFolderDialogClicked(model: DialogModel): UpdateResult =
        UpdatedModel(
            model.copy(folderInputName = ""), effects = setOf(Eff.HideNewFolderDialog)
        )
}