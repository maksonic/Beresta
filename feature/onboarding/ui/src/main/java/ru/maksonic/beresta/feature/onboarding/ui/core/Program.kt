package ru.maksonic.beresta.feature.onboarding.ui.core

import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.onboarding.domain.OnboardingVisibility
import ru.maksonic.beresta.feature.onboarding.domain.Repository

/**
 * @Author maksonic on 15.12.2022
 */
class Program(
    private val repository: Repository,
    private val onboardingVisibility: OnboardingVisibility,
) : ElmProgram<Feature.Msg, Feature.Cmd> {
    override suspend fun executeProgram(cmd: Feature.Cmd, consumer: (Feature.Msg) -> Unit) {
        when (cmd) {
            is Feature.Cmd.FetchOnboardings -> fetchOnboardings(consumer)
            is Feature.Cmd.NotShowAgain -> onboardingVisibility.notShowAgain()
        }
    }

    private fun fetchOnboardings(consumer: (Feature.Msg) -> Unit) {
        consumer(Feature.Msg.Inner.Onboardings(repository.onboardings))
    }
}