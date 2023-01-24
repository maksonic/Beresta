package ru.maksonic.beresta.feature.trash_list.ui.core

import ru.maksonic.beresta.elm.BaseModel
import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel

/**
 * @Author maksonic on 23.01.2023
 */
private typealias UpdateResult = UpdatedModel<Feature.Model, Set<Feature.Cmd>, Set<Feature.Eff>>

class TrashSandbox(trashProgram: TrashProgram) :
    Sandbox<Feature.Model, Feature.Msg, Feature.Cmd, Feature.Eff>(
        initialModel = Feature.Model(base = BaseModel(isLoading = true)),
        initialCmd = setOf(Feature.Cmd.FetchRemovedNotes),
        subscriptions = listOf(trashProgram)
    ) {

    override fun update(msg: Feature.Msg, model: Feature.Model): UpdateResult = when (msg) {
        is Feature.Msg.Inner.FetchingResult -> fetchRemovedNotes(model, msg)
        is Feature.Msg.Ui.TopBarBackPressed -> onTopBarBackPressed(model)
        is Feature.Msg.Ui.OnNoteClicked -> UpdatedModel(model)
        is Feature.Msg.Ui.OnNoteLongClicked -> UpdatedModel(model)
    }

    private fun fetchRemovedNotes(
        model: Feature.Model,
        msg: Feature.Msg.Inner.FetchingResult
    ): UpdateResult = UpdatedModel(
        model.copy(
            base = model.base.copy(isLoading = false),
            removedNotes = msg.notes
        )
    )

    private fun onTopBarBackPressed(model: Feature.Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Feature.Eff.NavigateBack))
}