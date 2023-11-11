package ru.maksonic.beresta.feature.tags_list.ui.core

import androidx.compose.ui.text.input.TextFieldValue
import ru.maksonic.beresta.feature.tags_list.ui.api.NoteTagUi
import ru.maksonic.beresta.platform.elm.core.ElmBaseModel.Companion.Loading
import ru.maksonic.beresta.platform.elm.core.ElmBaseModel.Companion.loadedSuccess
import ru.maksonic.beresta.platform.elm.core.ElmUpdate
import ru.maksonic.beresta.platform.elm.core.Sandbox

/**
 * @Author maksonic on 05.11.2023
 */
private typealias Update = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

class TagsListSandbox(program: TagsListProgram) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    subscriptions = listOf(program)
) {
    companion object {
        private const val TAG_NAME_MAX_LENGTH = 50
    }

    override fun update(msg: Msg, model: Model): Update = when (msg) {
        is Msg.Inner.FetchedTags -> fetchedTags(model, msg)
        is Msg.Inner.FetchedTagsResult -> fetchedTagsResult(model, msg)
        //  is Msg.Inner.FetchedNoteTagsResult -> fetchedNoteTagsResult(model, msg)
        is Msg.Ui.OnTagClicked -> onTagClicked(model, msg)
        is Msg.Inner.UpdatedAddTagDialogVisibility -> updatedAddTagDialogVisibility(model, msg)
        is Msg.Inner.UpdatedInputField -> updatedInputField(model, msg)
        is Msg.Ui.OnAcceptTagCreationClicked -> onAcceptTagCreationClicked(model)
    }

    private fun fetchedTags(model: Model, msg: Msg.Inner.FetchedTags): Update = ElmUpdate(
        model = model.copy(base = model.base.Loading),
        commands = setOf(Cmd.FetchTags(msg.tagsIds))
    )

    private fun fetchedTagsResult(model: Model, msg: Msg.Inner.FetchedTagsResult): Update =
        ElmUpdate(model.copy(base = model.base.loadedSuccess, tags = msg.tags))

    private fun onTagClicked(model: Model, msg: Msg.Ui.OnTagClicked): Update {
        val tags = model.tags.data.map { tag ->
            tag.copy(isSelected = if (tag.id == msg.id) !tag.isSelected else tag.isSelected)
        }

        return ElmUpdate(
            model = model.copy(tags = model.tags.copy(tags)),
            effects = setOf(Eff.UpdateNoteTags(tags.filter { it.isSelected }))
        )
    }

    private fun onTagLongClicked(model: Model, msg: Msg.Ui.OnTagClicked): Update {
        val tags = model.tags.data.map { tag ->
            val isSelected = if (tag.id == msg.id) !tag.isSelected else tag.isSelected

            tag.copy(isSelected = isSelected)
        }

        val noteTags = tags.filter { it.isSelected }

        return ElmUpdate(
            model = model.copy(tags = model.tags.copy(tags)),
            effects = setOf(Eff.UpdateNoteTags(noteTags))
        )
    }

    private fun updatedAddTagDialogVisibility(
        model: Model,
        msg: Msg.Inner.UpdatedAddTagDialogVisibility
    ): Update =
        ElmUpdate(model.copy(isVisibleAddTagDialog = msg.isVisible))


    private fun updatedInputField(model: Model, msg: Msg.Inner.UpdatedInputField): Update {
        val croppedInput = msg.value.copy(msg.value.text.take(TAG_NAME_MAX_LENGTH))

        return ElmUpdate(
            model.copy(
                newTagInputField = croppedInput,
                hintSymbolsCount = updatedHintCounter(croppedInput.text.count()),
                isEmptyFieldError = false
            )
        )
    }

    private fun onAcceptTagCreationClicked(model: Model): Update =
        if (model.newTagInputField.text.isBlank()) {
            ElmUpdate(model.copy(isEmptyFieldError = true))
        } else {
            val tag =
                NoteTagUi.Default.copy(title = model.newTagInputField.text.take(TAG_NAME_MAX_LENGTH))

            ElmUpdate(
                model = model.copy(
                    newTagInputField = TextFieldValue(),
                    isVisibleAddTagDialog = false
                ),
                commands = setOf(Cmd.SaveNewTag(tag)),
            )
        }

    private fun updatedHintCounter(count: Int) = "${count}/$TAG_NAME_MAX_LENGTH"
}