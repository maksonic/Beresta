package ru.maksonic.beresta.feature.onboarding.core.presentation

import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.onboarding.api.OnboardingApi
import ru.maksonic.beresta.feature.onboarding.core.data.OnboardingRepository

/**
 * @Author maksonic on 15.12.2022
 */
class Program(
    private val repository: OnboardingRepository,
    private val onboardingVisibility: OnboardingApi.Visibility,
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchOnboardings -> fetchOnboardings(consumer)
            is Cmd.NotShowAgain -> onboardingVisibility.notShowAgain()
        }
    }

    private fun fetchOnboardings(consumer: (Msg) -> Unit) {
        consumer(Msg.Inner.OnboardingImages(repository.onboardingImages))
    }
}