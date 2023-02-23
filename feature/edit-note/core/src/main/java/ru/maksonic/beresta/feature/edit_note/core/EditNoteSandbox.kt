package ru.maksonic.beresta.feature.edit_note.core

import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel

/**
 * @Author maksonic on 23.02.2023
 */
private typealias UpdateResult = UpdatedModel<Model, Set<Cmd>, Set<Eff>>

class EditNoteSandbox : Sandbox<Model, Msg, Cmd, Eff>(initialModel = Model()) {

    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {

        is Msg.Ui.OnCreateNewNoteClicked -> onCreateNoteClicked(model)
        is Msg.Ui.OnBackTopBarClicked -> onTopBarBackPressed(model)
    }

    private fun onCreateNoteClicked(model: Model): UpdateResult =
        UpdatedModel(model.copy(isExpandedEdit = true), effects = setOf(Eff.HideSearchBar))

    private fun onTopBarBackPressed(model: Model): UpdateResult =
        UpdatedModel(model.copy(isExpandedEdit = false), effects = setOf(Eff.ShowSearchBar))
}