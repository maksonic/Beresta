package ru.maksonic.beresta.feature.folders_chips.ui.dialog.core

import androidx.compose.ui.text.TextRange
import ru.maksonic.beresta.elm.core.ElmUpdate
import ru.maksonic.beresta.elm.core.Sandbox

/**
 * @Author maksonic on 30.03.2023
 */
private typealias UpdateResult = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

class FolderDialogSandbox(
    program: FolderDialogProgram
) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    subscriptions = listOf(program)
) {
    companion object {
        private const val FOLDER_NAME_MAX_LENGTH = 50
        private const val INITIAL_SUPPORTING_TEXT = "0/$FOLDER_NAME_MAX_LENGTH"
    }

    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Inner.FetchedPassedFolderId -> fetchedEditableFolderId(model, msg)
        is Msg.Inner.UpdateNewFolderNameInput -> updatedNewFolderInputField(model, msg)
        is Msg.Ui.OnAcceptClicked -> onAcceptBtnClicked(model)
        is Msg.Ui.OnDismissClicked -> onDismissBtnClicked(model)
        is Msg.Inner.FetchFolderData -> fetchedFolderResult(model, msg)
    }

    private fun fetchedEditableFolderId(
        model: Model,
        msg: Msg.Inner.FetchedPassedFolderId
    ): UpdateResult =
        ElmUpdate(
            model.copy(inputFiled = model.resetInput(), supportingText = INITIAL_SUPPORTING_TEXT),
            commands = setOf(Cmd.FetchFolderById(msg.id))
        )

    private fun updatedNewFolderInputField(
        model: Model,
        msg: Msg.Inner.UpdateNewFolderNameInput
    ): UpdateResult {
        val croppedInput = msg.value.copy(msg.value.text.take(FOLDER_NAME_MAX_LENGTH))
        val supportingText = "${croppedInput.text.count()}/$FOLDER_NAME_MAX_LENGTH"

        return ElmUpdate(
            model.copy(
                inputFiled = croppedInput,
                supportingText = supportingText,
                isEmptyFieldError = false
            )
        )
    }

    private fun fetchedFolderResult(
        model: Model,
        msg: Msg.Inner.FetchFolderData
    ): UpdateResult {
        val supportingText = "${msg.folder.title.count()}/$FOLDER_NAME_MAX_LENGTH"
        return ElmUpdate(
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

    private fun onAcceptBtnClicked(model: Model): UpdateResult {
        val isError = model.inputFiled.text.isBlank()
        val folder = model.currentEditableFolder.copy(title = model.inputFiled.text)
        val effect = if (isError) emptySet() else setOf(Eff.HideDialog)
        val command = if (isError) emptySet() else setOf(Cmd.SaveOrUpdateCurrentFolder(folder))

        return ElmUpdate(
            model = model.copy(
                supportingText = INITIAL_SUPPORTING_TEXT,
                isEmptyFieldError = isError
            ),
            commands = command, effects = effect
        )
    }

    private fun onDismissBtnClicked(model: Model): UpdateResult = ElmUpdate(
        model = model.copy(isEmptyFieldError = false, supportingText = INITIAL_SUPPORTING_TEXT),
        effects = setOf(Eff.HideDialog)
    )
}