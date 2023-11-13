package ru.maksonic.beresta.screen.settings.tags.core

import ru.maksonic.beresta.feature.ui.add_tag_dialog.api.AddTagDialogState
import ru.maksonic.beresta.platform.elm.core.ElmBaseModel.Companion.loadedSuccess
import ru.maksonic.beresta.platform.elm.core.ElmUpdate
import ru.maksonic.beresta.platform.elm.core.Sandbox

/**
 * @Author maksonic on 12.11.2023
 */
private typealias Update = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

class SettingsTagsSandbox(program: SettingsTagsProgram) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(Cmd.FetchData),
    subscriptions = listOf(program)
) {

    override fun update(msg: Msg, model: Model): Update = when (msg) {
        is Msg.Ui.OnTopBarBackPressed -> onTopBarBackPressed(model)
        is Msg.Inner.FetchedDataResult -> fetchedDataResult(model, msg)
        is Msg.Ui.OnTagClicked -> onTagClicked(model, msg)
        is Msg.Ui.OnSelectAllTagsClicked -> onSelectAllTagsClicked(model)
        is Msg.Ui.CancelSelectionState -> onCancelSelectionClicked(model)
        is Msg.Ui.HideModalBottomSheet -> hideModalBottomSheet(model)
        is Msg.Inner.UpdatedModalSheetState -> updatedModalSheetState(model, msg)
        is Msg.Ui.OnModalSheetRenameTagClicked -> onModalSheetRenameTagClicked(model)
        is Msg.Ui.OnModalSheetDeleteTagClicked -> onModalSheetDeleteTagClicked(model)
        is Msg.Inner.HiddenAddTagDialog -> hiddenAddTagDialog(model)
        is Msg.Ui.OnCreateNewTagClicked -> onCreateNewTagClicked(model)
    }

    private fun onTopBarBackPressed(model: Model): Update =
        ElmUpdate(model, effects = setOf(Eff.NavigateBack))

    private fun fetchedDataResult(model: Model, msg: Msg.Inner.FetchedDataResult): Update =
        ElmUpdate(model.copy(base = model.base.loadedSuccess, tags = msg.tags))

    private fun onTagClicked(model: Model, msg: Msg.Ui.OnTagClicked): Update =
        if (model.isSelection)
            baseOnTagAction(model, msg.tag.id)
        else
            ElmUpdate(
                model = model.copy(
                    modalSheet = model.modalSheet.copy(isVisible = true),
                    currentClickedTag = msg.tag
                ),
            )


    private fun baseOnTagAction(model: Model, tagId: Long): Update {
        val tags = model.tags.copy(model.tags.data.map { note ->
            val isSelected = if (note.id == tagId) !note.isSelected else note.isSelected
            note.copy(isSelected = isSelected)
        })

        return ElmUpdate(model.copy(tags = tags, isSelection = true))
    }

    private fun onSelectAllTagsClicked(model: Model): Update {
        val tags = model.tags.data.map {
            it.copy(isSelected = !model.tags.data.all { item -> item.isSelected })
        }

        return ElmUpdate(model.copy(tags = model.tags.copy(tags)))
    }

    private fun onCancelSelectionClicked(model: Model): Update = ElmUpdate(
        model.copy(
            tags = model.tags.copy(model.tags.data.map { it.copy(isSelected = false) }),
            isSelection = false
        )
    )

    private fun hideModalBottomSheet(model: Model): Update = ElmUpdate(
        model = model.copy(currentClickedTag = null),
        effects = setOf(Eff.HideModalSheet)
    )

    private fun updatedModalSheetState(
        model: Model,
        msg: Msg.Inner.UpdatedModalSheetState
    ): Update =
        ElmUpdate(model.copy(modalSheet = model.modalSheet.copy(isVisible = msg.isVisible)))

    private fun onModalSheetRenameTagClicked(model: Model): Update {
        val passedState = model.addTagDialogState.copy(
            isVisible = true,
            isNewTag = false,
            passedTagId = model.currentClickedTag?.id ?: 0L
        )
        return ElmUpdate(
            model = model.copy(addTagDialogState = passedState),
            effects = setOf(Eff.HideModalSheet)
        )
    }

    private fun onModalSheetDeleteTagClicked(model: Model): Update {
        val delete = model.tags.data.find { it.id == model.currentClickedTag?.id }
        val cmd = if (delete == null) emptySet() else setOf(Cmd.DeleteTag(delete))

        return ElmUpdate(model.copy(currentClickedTag = null),
            commands = cmd,
            effects = setOf(Eff.HideModalSheet)
        )
    }

    private fun hiddenAddTagDialog(model: Model): Update =
        ElmUpdate(model.copy(addTagDialogState = model.addTagDialogState.copy(isVisible = false)))

    private fun onCreateNewTagClicked(model: Model): Update = ElmUpdate(
        model.copy(addTagDialogState = AddTagDialogState.Initial.copy(isVisible = true))
    )
}