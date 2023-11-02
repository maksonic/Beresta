package ru.maksonic.beresta.feature.onboarding.ui.core

import ru.maksonic.beresta.feature.onboarding.domain.OnboardingRepository
import ru.maksonic.beresta.feature.onboarding.domain.OnboardingVisibilityFeatureCase
import ru.maksonic.beresta.platform.elm.core.ElmProgram

/**
 * @Author maksonic on 19.06.2023
 */
class OnboardingProgram(
    private val repository: OnboardingRepository,
    private val onboardingVisibilityFeatureCase: OnboardingVisibilityFeatureCase
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.NotShowAgain -> onboardingVisibilityFeatureCase.notShowOnboardingAgain()
            is Cmd.FetchOnboardingsProvider -> fetchOnboardingsProvider(consumer)
        }
    }

    private suspend fun fetchOnboardingsProvider(consumer: (Msg) -> Unit) =
        repository.fetchOnboardings().collect { result ->
            result.onSuccess { onboardings ->
                consumer(Msg.Inner.FetchedOnboardingsData(onboardings))
            }

            result.onFailure { consumer(Msg.Inner.FetchedOnboardingsFailure) }
        }
}