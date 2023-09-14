package ru.maksonic.beresta.feature.folders_chips.ui.dialog.core

import androidx.compose.ui.text.TextRange
import ru.maksonic.beresta.elm.core.ElmUpdate
import ru.maksonic.beresta.elm.core.Sandbox

/**
 * @Author maksonic on 30.03.2023
 */
private typealias UpdateResult = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

class FolderDialogSandbox(program: FolderDialogProgram) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    subscriptions = listOf(program)
) {
    companion object {
        private const val FOLDER_NAME_MAX_LENGTH = 50
    }

    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Inner.FetchedPassedFolderId -> fetchedPassedFolderId(model)
        is Msg.Inner.UpdatedInputField -> updatedInputField(model, msg)
        is Msg.Ui.OnAcceptClicked -> onAcceptBtnClicked(model)
        is Msg.Ui.OnDismissClicked -> onDismissBtnClicked(model)
        is Msg.Inner.FetchFolderData -> fetchedFolderResult(model, msg)
    }

    private fun fetchedPassedFolderId(model: Model): UpdateResult =
        ElmUpdate(model, commands = setOf(Cmd.FetchFolderById))

    private fun updatedInputField(model: Model, msg: Msg.Inner.UpdatedInputField): UpdateResult {
        val croppedInput = msg.value.copy(msg.value.text.take(FOLDER_NAME_MAX_LENGTH))

        return ElmUpdate(
            model.copy(
                inputFiled = croppedInput,
                hintSymbolsCount = updatedHintCounter(croppedInput.text.count()),
                isEmptyFieldError = false
            )
        )
    }

    private fun fetchedFolderResult(model: Model, msg: Msg.Inner.FetchFolderData): UpdateResult =
        ElmUpdate(
            model.copy(
                inputFiled = model.inputFiled.copy(
                    text = msg.folder.title, selection = TextRange(msg.folder.title.length)
                ),
                currentEditableFolder = msg.folder,
                hintSymbolsCount = updatedHintCounter(msg.folder.title.count()),
                isEmptyFieldError = false,
                isFetchedFolder = true
            )
        )

    private fun onAcceptBtnClicked(model: Model): UpdateResult =
        if (model.inputFiled.text.isBlank()) {
            ElmUpdate(model.copy(isEmptyFieldError = true))
        } else {
            val folder = model.currentEditableFolder.copy(title = model.inputFiled.text)

            ElmUpdate(
                model = model.copy(isFetchedFolder = false),
                commands = setOf(Cmd.SaveOrUpdateCurrentFolder(folder)),
                effects = setOf(Eff.HideDialog)
            )

        }

    private fun onDismissBtnClicked(model: Model): UpdateResult =
        ElmUpdate(model = model.copy(isFetchedFolder = false), effects = setOf(Eff.HideDialog))

    private fun updatedHintCounter(count: Int) = "${count}/$FOLDER_NAME_MAX_LENGTH"
}