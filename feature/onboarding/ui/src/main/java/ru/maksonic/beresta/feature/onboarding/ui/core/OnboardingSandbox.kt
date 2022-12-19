package ru.maksonic.beresta.feature.onboarding.ui.core

import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel

/**
 * @Author maksonic on 15.12.2022
 */
private typealias UpdateResult = UpdatedModel<Model, Set<Command>, Set<Effect>>

class OnboardingSandbox(program: Program) : Sandbox<Model, Message, Command, Effect>(
    initialModel = Model(),
    initialCmd = setOf(Command.FetchOnboardings),
    initialEff = setOf(),
    subscriptions = listOf(program)
) {
    override fun update(msg: Message, model: Model): UpdateResult =
        when (msg) {
            is Message.Ui.OnGoogleAuthClicked -> UpdatedModel(model)
            is Message.Ui.OnPrimaryBtnClicked -> onPrimaryBtnClicked(model)
            is Message.Ui.OnSkipSyncBtnClicked -> onSkipBtnClicked(model)
            is Message.Inner.Onboardings -> fetchedData(model, msg)
        }

    private fun fetchedData(model: Model, msg: Message.Inner.Onboardings): UpdateResult =
        UpdatedModel(model.copy(onboardings = msg.data))

    private fun onPrimaryBtnClicked(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Effect.SlideNextPage))

    private fun onSkipBtnClicked(model: Model): UpdateResult =
        UpdatedModel(model, commands = setOf(Command.NavigateToMainScreen))
}