package ru.maksonic.beresta.feature.notes.folders.core.add_folder_dialog.core

import androidx.compose.ui.text.TextRange
import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel
import ru.maksonic.beresta.feature.notes.folders.api.ui.NoteFolderUi

/**
 * @Author maksonic on 30.03.2023
 */
private typealias UpdateResult = UpdatedModel<DialogModel, Set<Cmd>, Set<Eff>>

class FolderDialogSandbox(
    program: FolderDialogProgram
) : Sandbox<DialogModel, Msg, Cmd, Eff>(
    initialModel = DialogModel.Initial,
    subscriptions = listOf(program)
) {
    companion object {
        private const val FOLDER_NAME_MAX_LENGTH = 50
        private const val INITIAL_SUPPORTING_TEXT = "0/$FOLDER_NAME_MAX_LENGTH"
    }

    override fun update(msg: Msg, model: DialogModel): UpdateResult = when (msg) {
        is Msg.Inner.UpdatedFolderStatus -> updatedFolderStatus(model, msg)
        is Msg.Inner.FetchedEditableFolderId -> afterFetchEditableFolderId(model, msg)
        is Msg.Inner.UpdateNewFolderNameInput -> updatedNewFolderInputField(model, msg)
        is Msg.Ui.OnAcceptClicked -> onAcceptBtnClicked(model)
        is Msg.Ui.OnDismissClicked -> onDismissBtnClicked(model)
        is Msg.Inner.FetchFolderResult -> fetchedFolderResult(model, msg)
    }

    private fun updatedFolderStatus(
        model: DialogModel,
        msg: Msg.Inner.UpdatedFolderStatus
    ): UpdateResult = UpdatedModel(model.copy(isNewFolder = msg.isNew))

    private fun afterFetchEditableFolderId(
        model: DialogModel,
        msg: Msg.Inner.FetchedEditableFolderId
    ): UpdateResult {
        val cmd = if (msg.id == model.currentEditableFolder.id) emptySet()
        else setOf(Cmd.FetchFolderById(msg.id))

        return UpdatedModel(model.copy(supportingText = INITIAL_SUPPORTING_TEXT), commands = cmd)
    }

    private fun updatedNewFolderInputField(
        model: DialogModel,
        msg: Msg.Inner.UpdateNewFolderNameInput
    ): UpdateResult {
        val croppedInput = msg.value.copy(msg.value.text.take(FOLDER_NAME_MAX_LENGTH))
        val supportingText = "${croppedInput.text.count()}/$FOLDER_NAME_MAX_LENGTH"

        return UpdatedModel(
            model.copy(
                inputFiled = croppedInput,
                supportingText = supportingText,
                isEmptyFieldError = false
            )
        )
    }

    private fun fetchedFolderResult(
        model: DialogModel,
        msg: Msg.Inner.FetchFolderResult
    ): UpdateResult {
        val supportingText = "${msg.folder.title.count()}/$FOLDER_NAME_MAX_LENGTH"
        return UpdatedModel(
            model.copy(
                inputFiled = model.inputFiled.copy(
                    text = msg.folder.title,
                    selection = TextRange(msg.folder.title.length)
                ),
                currentEditableFolder = msg.folder,
                supportingText = supportingText,
                isEmptyFieldError = false
            )
        )
    }

    private fun onAcceptBtnClicked(model: DialogModel): UpdateResult {
        val isError = model.inputFiled.text.isBlank()
        val folder = model.currentEditableFolder.copy(title = model.inputFiled.text)
        val effect = if (isError) emptySet() else setOf(Eff.HideDialog)
        val command =
            if (isError) emptySet()
            else setOf(Cmd.SaveOrUpdateCurrentFolder(folder))

        return UpdatedModel(
            model = model.copy(
                inputFiled = model.inputFiled.copy("", selection = TextRange.Zero),
                currentEditableFolder = NoteFolderUi.Empty,
                supportingText = INITIAL_SUPPORTING_TEXT,
                isEmptyFieldError = isError
            ),
            commands = command,
            effects = effect
        )
    }

    private fun onDismissBtnClicked(model: DialogModel): UpdateResult = UpdatedModel(
        model = model.copy(
            inputFiled = model.inputFiled.copy("", selection = TextRange.Zero),
            currentEditableFolder = NoteFolderUi.Empty,
            isEmptyFieldError = false,
            supportingText = INITIAL_SUPPORTING_TEXT
        ),
        effects = setOf(Eff.HideDialog)
    )
}