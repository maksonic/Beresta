package ru.maksonic.beresta.feature.trash_list.ui.core

import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel

/**
 * @Author maksonic on 23.01.2023
 */
private typealias UpdateResult = UpdatedModel<Feature.Model, Set<Feature.Cmd>, Set<Feature.Eff>>

class TrashSandbox(trashProgram: TrashProgram) : Sandbox<Feature.Model, Feature.Msg, Feature.Cmd, Feature.Eff>(
    initialModel = Feature.Model(),
    initialCmd = setOf(Feature.Cmd.FetchRemovedNotes),
    subscriptions = listOf(trashProgram)
) {

    override fun update(msg: Feature.Msg, model: Feature.Model): UpdateResult = when (msg) {
        is Feature.Msg.Inner.FetchingResult -> UpdatedModel(model)
        is Feature.Msg.Ui.TopBarBackPressed -> onTopBarBackPressed(model)
    }

    private fun onTopBarBackPressed(model: Feature.Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Feature.Eff.NavigateBack))
}