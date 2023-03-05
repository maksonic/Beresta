package ru.maksonic.beresta.feature.edit_note.core.screen.core

import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel

/**
 * @Author maksonic on 04.03.2023
 */
private typealias UpdateResult = UpdatedModel<Model, Set<Cmd>, Set<Eff>>

class EditNoteSandbox : Sandbox<Model, Msg, Cmd, Eff>(initialModel = Model()) {

    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {

        is Msg.Ui.OnTopBarBackPressed -> topBarBackPressed(model)
        is Msg.Inner.UpdateInputTitle -> updatedNoteTitle(model, msg)
    }

    private fun topBarBackPressed(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.NavigateBack))

    private fun updatedNoteTitle(model: Model, msg: Msg.Inner.UpdateInputTitle): UpdateResult =
        UpdatedModel(model.copy(inputTitle = msg.text) )
}