package ru.maksonic.beresta.feature.folders_list.core.dialog.core

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
    initialModel = DialogModel.Initial,
    subscriptions = listOf(program)
) {
    companion object {
        private const val FOLDER_NAME_MAX_LENGTH = 50
        private const val INITIAL_SUPPORTING_TEXT = "0/$FOLDER_NAME_MAX_LENGTH"
    }

    override fun update(msg: Msg, model: DialogModel): UpdateResult = when (msg) {
        is Msg.Inner.UpdateNewFolderNameInput -> updatedNewFolderInputField(model, msg)
        is Msg.Ui.AddNewNotesFolder -> onAddNewFolderClicked(model)
        is Msg.Ui.OnCreateNewNotesFolderClicked -> onCreateNewFolderClicked(model)
        is Msg.Ui.OnDismissFolderCreationDialogClicked -> onDismissCreateFolderDialogClicked(model)
        is Msg.Inner.FillTextFieldByFolderName -> updatedAfterFetchPassedFolderId(model, msg)
        is Msg.Inner.FetchedEditableFolderId -> afterFetchEditableFolderId(model, msg)
    }

    private fun afterFetchEditableFolderId(
        model: DialogModel,
        msg: Msg.Inner.FetchedEditableFolderId
    ): UpdateResult =
        UpdatedModel(model, commands = setOf(Cmd.FetchFolderById(msg.id)))

    private fun updatedNewFolderInputField(
        model: DialogModel,
        msg: Msg.Inner.UpdateNewFolderNameInput
    ): UpdateResult {
        val croppedInput = msg.value.take(FOLDER_NAME_MAX_LENGTH)
        val supportingText = "${croppedInput.count()}/$FOLDER_NAME_MAX_LENGTH"

        return UpdatedModel(
            model.copy(
                currentEditableFolder = model.currentEditableFolder.copy(title = croppedInput),
                supportingText = supportingText,
                isEmptyFieldError = false
            )
        )
    }

    private fun updatedAfterFetchPassedFolderId(
        model: DialogModel,
        msg: Msg.Inner.FillTextFieldByFolderName
    ): UpdateResult {
        val supportingText = "${msg.folder.title.count()}/$FOLDER_NAME_MAX_LENGTH"

        return UpdatedModel(
            model.copy(
                currentEditableFolder = msg.folder,
                supportingText = supportingText,
                isEmptyFieldError = false
            )
        )
    }

    private fun onAddNewFolderClicked(model: DialogModel): UpdateResult =
        UpdatedModel(
            model = model.copy(currentEditableFolder = FilterChipUi.Empty),
            effects = setOf(Eff.ShowNewFolderDialog)
        )

    private fun onCreateNewFolderClicked(model: DialogModel): UpdateResult {
        val isError = model.currentEditableFolder.title.isBlank()
        val isSaveOrUpdateCommand =
            if (model.currentEditableFolder.id != FilterChipUi.Empty.id)
                Cmd.UpdateFolder(model.currentEditableFolder)
            else Cmd.SaveNewFolderToCache(model.currentEditableFolder)

        val command = if (isError) emptySet() else setOf(isSaveOrUpdateCommand)
        val effect = if (isError) emptySet() else setOf(Eff.HideNewFolderDialog)

        return UpdatedModel(
            model.copy(supportingText = INITIAL_SUPPORTING_TEXT, isEmptyFieldError = isError),
            commands = command,
            effects = effect
        )
    }

    private fun onDismissCreateFolderDialogClicked(model: DialogModel): UpdateResult =
        UpdatedModel(
            model = model.copy(isEmptyFieldError = false, supportingText = INITIAL_SUPPORTING_TEXT),
            effects = setOf(Eff.HideNewFolderDialog)
        )
}