package ru.maksonic.beresta.feature.edit_note.core.fab.core

import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel

/**
 * @Author maksonic on 04.03.2023
 */
private typealias UpdatedResult = UpdatedModel<WidgetModel, Set<Cmd>, Set<Eff>>

class AddNoteSandbox : Sandbox<WidgetModel, Msg, Cmd, Eff>(initialModel = WidgetModel()) {

    override fun update(msg: Msg, model: WidgetModel): UpdatedResult = when (msg) {
        is Msg.Ui.OnCreateNewNoteClicked -> onCreateNoteClicked(model)
        is Msg.Ui.OnCollapseFabClicked -> onTopBarBackPressed(model)
        is Msg.Inner.UpdatedFabState -> updatedFabState(model, msg)
    }

    private fun updatedFabState(model: WidgetModel, msg: Msg.Inner.UpdatedFabState): UpdatedResult =
        UpdatedModel(model.copy(isExpandedFab = msg.value))

    private fun onCreateNoteClicked(model: WidgetModel): UpdatedResult =
        UpdatedModel(model.copy(isExpandedFab = true), effects = setOf(Eff.SetExpandFabSharedState))

    private fun onTopBarBackPressed(model: WidgetModel): UpdatedResult =
        UpdatedModel(model.copy(isExpandedFab = false), effects = setOf(Eff.SetCollapseFabSharedState))
}