package ru.maksonic.beresta.feature.edit_note.core.screen.core

import ru.maksonic.beresta.elm.*

/**
 * @Author maksonic on 04.03.2023
 */
private typealias UpdateResult = UpdatedModel<Model, Set<Cmd>, Set<Eff>>

class EditNoteSandbox : Sandbox<Model, Msg, Cmd, Eff>(initialModel = Model()) {

    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {

        is Msg.Ui.OnTopBarBackPressed -> topBarBackPressed(model)
        is Msg.Inner.UpdatedInputTitle -> updatedNoteTitle(model, msg)
        is Msg.Inner.UpdatedInputMessage -> updatedNoteMessage(model, msg)
        is Msg.Inner.FetchedFabStateValue -> afterFetchedFabState(model, msg)
        is Msg.Inner.UpdatedEditorPanelVisibility -> updatedEditorPanelVisibility(model, msg)
    }

    private fun afterFetchedFabState(
        model: Model,
        msg: Msg.Inner.FetchedFabStateValue
    ): UpdateResult =
        UpdatedModel(model.copy(isExpandedFabState = msg.isExpanded))

    private fun topBarBackPressed(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.NavigateBack))

    private fun updatedNoteTitle(model: Model, msg: Msg.Inner.UpdatedInputTitle): UpdateResult =
        UpdatedModel(model.copy(titleField = msg.textField))

    private fun updatedNoteMessage(model: Model, msg: Msg.Inner.UpdatedInputMessage): UpdateResult =
        UpdatedModel(model.copy(messageField = msg.msgField))

    private fun updatedEditorPanelVisibility(
        model: Model, msg: Msg.Inner.UpdatedEditorPanelVisibility
    ): UpdateResult =
        UpdatedModel(model.copy(isVisibleEditorPanel = msg.isVisible))
}

