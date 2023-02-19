package ru.maksonic.beresta.feature.onboarding.core.presentation

import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel

/**
 * @Author maksonic on 15.12.2022
 */
private typealias UpdateResult = UpdatedModel<Model, Set<Cmd>, Set<Eff>>

class OnboardingSandbox(
    program: Program
) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model(),
    initialCmd = setOf(Cmd.FetchOnboardings),
    initialEff = setOf(),
    subscriptions = listOf(program)
) {
    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Ui.OnGoogleAuthClicked -> UpdatedModel(model)
        is Msg.Ui.OnPrimaryBtnClicked -> onPrimaryBtnClicked(model)
        is Msg.Ui.OnSkipSyncBtnClicked -> onSkipBtnClicked(model)
        is Msg.Inner.OnboardingImages -> fetchedData(model, msg)
        is Msg.Ui.OnHideLanguageBtnClicked -> UpdatedModel(model, effects = setOf(Eff.HideLanguageSheet))
        is Msg.Ui.OnSelectLanguageBtnClicked -> UpdatedModel(model, effects = setOf(Eff.ShowLanguageSheet))
    }

    private fun fetchedData(model: Model, msg: Msg.Inner.OnboardingImages): UpdateResult =
        UpdatedModel(model.copy(onboardingImages = msg.data))

    private fun onPrimaryBtnClicked(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.SlideNextPage))

    private fun onSkipBtnClicked(model: Model): UpdateResult = UpdatedModel(
        model = model,
        commands = setOf(Cmd.NotShowAgain),
        effects = setOf(Eff.NavigateToMain)
    )
}