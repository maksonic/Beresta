package ru.maksonic.beresta.feature.edit_note.core.screen.core

import ru.maksonic.beresta.elm.*

/**
 * @Author maksonic on 04.03.2023
 */
private typealias Result = UpdatedModel<Model, Set<Cmd>, Set<Eff>>

class EditNoteSandbox : Sandbox<Model, Msg, Cmd, Eff>(initialModel = Model()) {
    companion object {
        private const val MAX_NOTE_LENGTH = 15000
    }

    override fun update(msg: Msg, model: Model): Result = when (msg) {

        is Msg.Ui.OnTopBarBackPressed -> topBarBackPressed(model)
        is Msg.Inner.UpdatedInputTitle -> updatedNoteTitle(model, msg)
        is Msg.Inner.UpdatedInputMessage -> updatedNoteMessage(model, msg)
        is Msg.Inner.FetchedFabStateValue -> afterFetchedFabState(model, msg)
        is Msg.Inner.UpdatedEditorPanelVisibility -> updatedEditorPanelVisibility(model, msg)
        is Msg.Inner.ShowedMaxLengthNoteInputWarning -> showedSnackMaxLengthNoteExceed(model)
    }

    private fun afterFetchedFabState(model: Model, msg: Msg.Inner.FetchedFabStateValue): Result =
        UpdatedModel(model.copy(isExpandedFabState = msg.isExpanded))

    private fun topBarBackPressed(model: Model): Result =
        UpdatedModel(model, effects = setOf(Eff.NavigateBack))

    private fun updatedNoteTitle(model: Model, msg: Msg.Inner.UpdatedInputTitle): Result {
        val croppedInput = msg.textField.text.take(MAX_NOTE_LENGTH)
        val isShowWarning = if (msg.textField.text.length > MAX_NOTE_LENGTH)
            setOf(Eff.ShowSnackMaxLengthNoteExceed)
        else
            emptySet()

        return UpdatedModel(
            model = model.copy(titleField = msg.textField.copy(croppedInput)),
            effects = isShowWarning
        )
    }

    private fun updatedNoteMessage(model: Model, msg: Msg.Inner.UpdatedInputMessage): Result {
        val croppedInput = msg.msgField.text.take(MAX_NOTE_LENGTH)
        val isShowWarning = if (msg.msgField.text.length > MAX_NOTE_LENGTH)
            setOf(Eff.ShowSnackMaxLengthNoteExceed)
        else
            emptySet()

        return UpdatedModel(
            model = model.copy(messageField = msg.msgField.copy(croppedInput)),
            effects = isShowWarning
        )
    }

    private fun updatedEditorPanelVisibility(
        model: Model, msg: Msg.Inner.UpdatedEditorPanelVisibility
    ): Result =
        UpdatedModel(model.copy(isVisibleEditorPanel = msg.isVisible))

    private fun showedSnackMaxLengthNoteExceed(model: Model): Result =
        UpdatedModel(model, effects = setOf(Eff.ShowSnackMaxLengthNoteExceed))
}

