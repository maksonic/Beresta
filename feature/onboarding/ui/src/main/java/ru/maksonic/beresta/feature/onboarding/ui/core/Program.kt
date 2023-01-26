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
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchOnboardings -> fetchOnboardings(consumer)
            is Cmd.NotShowAgain -> onboardingVisibility.notShowAgain()
        }
    }

    private fun fetchOnboardings(consumer: (Msg) -> Unit) {
        consumer(Msg.Inner.Onboardings(repository.onboardings))
    }
}