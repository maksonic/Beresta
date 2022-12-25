package ru.maksonic.beresta.feature.onboarding.ui.core

import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel

/**
 * @Author maksonic on 15.12.2022
 */
private typealias UpdateResult = UpdatedModel<Feature.Model, Set<Feature.Cmd>, Set<Feature.Eff>>

class OnboardingSandbox(
    program: Program
) : Sandbox<Feature.Model, Feature.Msg, Feature.Cmd, Feature.Eff>(
    initialModel = Feature.Model(),
    initialCmd = setOf(Feature.Cmd.FetchOnboardings),
    initialEff = setOf(),
    subscriptions = listOf(program)
) {
    override fun update(msg: Feature.Msg, model: Feature.Model): UpdateResult =
        when (msg) {
            is Feature.Msg.Ui.OnGoogleAuthClicked -> UpdatedModel(model)
            is Feature.Msg.Ui.OnPrimaryBtnClicked -> onPrimaryBtnClicked(model)
            is Feature.Msg.Ui.OnSkipSyncBtnClicked -> onSkipBtnClicked(model)
            is Feature.Msg.Inner.Onboardings -> fetchedData(model, msg)
        }

    private fun fetchedData(
        model: Feature.Model,
        msg: Feature.Msg.Inner.Onboardings
    ): UpdateResult =
        UpdatedModel(model.copy(onboardings = msg.data))

    private fun onPrimaryBtnClicked(model: Feature.Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Feature.Eff.SlideNextPage))

    private fun onSkipBtnClicked(model: Feature.Model): UpdateResult =
        UpdatedModel(model, commands = setOf(Feature.Cmd.NavigateToMainScreen))
}