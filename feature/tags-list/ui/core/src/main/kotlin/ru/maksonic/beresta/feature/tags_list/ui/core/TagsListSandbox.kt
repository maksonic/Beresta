package ru.maksonic.beresta.feature.tags_list.ui.core

import ru.maksonic.beresta.feature.ui.add_tag_dialog.api.AddTagDialogState
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
    override fun update(msg: Msg, model: Model): Update = when (msg) {
        is Msg.Inner.FetchedTags -> fetchedTags(model, msg)
        is Msg.Inner.FetchedTagsResult -> fetchedTagsResult(model, msg)
        is Msg.Ui.OnTagClicked -> onTagClicked(model, msg)
        is Msg.Ui.OnCreateNewTagClicked -> onCreateNewTagClicked(model)
        is Msg.Inner.HiddenAddTagDialog -> hiddenAddTagDialog(model)
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

    private fun onCreateNewTagClicked(model: Model): Update = ElmUpdate(
        model.copy(addTagDialogState = AddTagDialogState.Initial.copy(isVisible = true))
    )

    private fun hiddenAddTagDialog(model: Model): Update =
        ElmUpdate(model.copy(addTagDialogState = model.addTagDialogState.copy(isVisible = false)))
}