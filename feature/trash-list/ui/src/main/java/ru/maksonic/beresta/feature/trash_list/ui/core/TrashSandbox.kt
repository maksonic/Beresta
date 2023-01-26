package ru.maksonic.beresta.feature.trash_list.ui.core

import ru.maksonic.beresta.elm.BaseModel
import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel

/**
 * @Author maksonic on 23.01.2023
 */
private typealias UpdateResult = UpdatedModel<Model, Set<Cmd>, Set<Eff>>

class TrashSandbox(trashProgram: TrashProgram) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model(base = BaseModel(isLoading = true)),
    initialCmd = setOf(Cmd.FetchRemovedNotes),
    subscriptions = listOf(trashProgram)
) {

    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Inner.FetchingResult -> fetchRemovedNotes(model, msg)
        is Msg.Ui.TopBarBackPressed -> onTopBarBackPressed(model)
        is Msg.Ui.OnNoteClicked -> UpdatedModel(model)
        is Msg.Ui.OnNoteLongClicked -> UpdatedModel(model)
    }

    private fun fetchRemovedNotes(model: Model, msg: Msg.Inner.FetchingResult): UpdateResult =
        UpdatedModel(
            model.copy(
                base = model.base.copy(isLoading = false),
                removedNotes = msg.notes
            )
        )

    private fun onTopBarBackPressed(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.NavigateBack))
}