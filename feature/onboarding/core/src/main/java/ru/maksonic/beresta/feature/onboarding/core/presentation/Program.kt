package ru.maksonic.beresta.feature.onboarding.core.presentation

import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.language_selector.api.components.OnboardingDataItem
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
            is Cmd.FetchOnboardings -> fetchOnboardings(cmd.textData, consumer)
            is Cmd.NotShowAgain -> onboardingVisibility.notShowAgain()
        }
    }

    private fun fetchOnboardings(textData: Array<OnboardingDataItem>, consumer: (Msg) -> Unit) {
        consumer(Msg.Inner.Onboardings(repository.onboardings(textData)))
    }
}