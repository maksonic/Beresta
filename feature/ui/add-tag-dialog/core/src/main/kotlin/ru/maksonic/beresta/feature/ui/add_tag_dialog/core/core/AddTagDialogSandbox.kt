package ru.maksonic.beresta.feature.ui.add_tag_dialog.core.core

import androidx.compose.ui.text.TextRange
import ru.maksonic.beresta.platform.elm.core.ElmUpdate
import ru.maksonic.beresta.platform.elm.core.Sandbox

/**
 * @Author maksonic on 13.11.2023
 */
private typealias Update = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

private const val TAG_NAME_MAX_LENGTH = 35

class AddTagDialogSandbox(program: AddTagDialogProgram) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    subscriptions = listOf(program)
) {
    override fun update(msg: Msg, model: Model): Update = when (msg) {
        is Msg.Inner.FetchedPassedTagId -> fetchedPassedTagId(model, msg)
        is Msg.Inner.FetchedTagResult -> fetchedFolderResult(model, msg)
        is Msg.Inner.UpdatedInputField -> updatedInputField(model, msg)
        is Msg.Ui.OnAcceptClicked -> onAcceptBtnClicked(model)
        is Msg.Ui.OnDismissClicked -> onDismissBtnClicked(model)
    }

    private fun fetchedPassedTagId(model: Model, msg: Msg.Inner.FetchedPassedTagId): Update =
        ElmUpdate(model, commands = setOf(Cmd.FetchTagById(msg.id)))

    private fun fetchedFolderResult(
        model: Model,
        msg: Msg.Inner.FetchedTagResult
    ): Update = ElmUpdate(
        model.copy(
            inputFiled = model.inputFiled.copy(
                text = msg.tag.title, selection = TextRange(msg.tag.title.length)
            ),
            currentEditableTag = msg.tag,
            hintSymbolsCount = updatedHintCounter(msg.tag.title.count()),
            isEmptyFieldError = false,
            isFetchedTag = true
        )
    )

    private fun updatedInputField(model: Model, msg: Msg.Inner.UpdatedInputField): Update {
        val croppedInput = msg.value.copy(msg.value.text.take(TAG_NAME_MAX_LENGTH))

        return ElmUpdate(
            model.copy(
                inputFiled = croppedInput,
                hintSymbolsCount = updatedHintCounter(croppedInput.text.count()),
                isEmptyFieldError = false
            )
        )
    }

    private fun onAcceptBtnClicked(model: Model): Update =
        if (model.inputFiled.text.isBlank()) {
            ElmUpdate(model.copy(isEmptyFieldError = true))
        } else {
            val tag = model.currentEditableTag.copy(title = model.inputFiled.text)

            ElmUpdate(
                model = model.copy(isFetchedTag = false),
                commands = setOf(Cmd.SaveOrUpdateCurrentTag(tag)),
                effects = setOf(Eff.HideDialog)
            )
        }

    private fun onDismissBtnClicked(model: Model): Update =
        ElmUpdate(model = model.copy(isFetchedTag = false), effects = setOf(Eff.HideDialog))

    private fun updatedHintCounter(count: Int) = "${count}/$TAG_NAME_MAX_LENGTH"
}