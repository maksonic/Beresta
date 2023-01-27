package ru.maksonic.beresta.feature.edit_note.ui.core.core

import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel

/**
 * @Author maksonic on 26.01.2023
 */
private typealias UpdateResult = UpdatedModel<Model, Set<Cmd>, Set<Eff>>

class EditNoteSandbox(program: EditNoteProgram) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model(),
    initialCmd = setOf(Cmd.FetchNote),
    subscriptions = listOf(program)
) {
    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Ui.OnSaveNoteClicked -> onSaveNoteClicked(model)
        is Msg.Ui.OnTopBarBackClicked -> onTopBarBackPressed(model)
        is Msg.Inner.UpdateCurrentTitle -> updateTitle(model, msg)
        is Msg.Inner.UpdateCurrentMessage -> updateMessage(model, msg)
        is Msg.Inner.FetchingNoteResult -> {
            UpdatedModel(model.copy(note = msg.note))
        }
    }

    private fun onSaveNoteClicked(model: Model): UpdateResult {
        val eff = if (model.note.id == 0L) Eff.NavigateBack else Eff.Nothing
        val cmd = if (model.note.id == 0L)
            Cmd.SaveNote(model.note) else Cmd.UpdateCurrentNote(model.note)
        return UpdatedModel(model, commands = setOf(cmd), effects = setOf(eff))
    }

    private fun onTopBarBackPressed(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.NavigateBack))

    private fun updateTitle(model: Model, msg: Msg.Inner.UpdateCurrentTitle): UpdateResult =
        UpdatedModel(model.copy(note = model.note.copy(title = msg.text)))

    private fun updateMessage(model: Model, msg: Msg.Inner.UpdateCurrentMessage): UpdateResult =
        UpdatedModel(model.copy(note = model.note.copy(message = msg.text)))

}