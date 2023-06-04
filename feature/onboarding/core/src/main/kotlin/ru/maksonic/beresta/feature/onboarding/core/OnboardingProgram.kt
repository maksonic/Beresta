package ru.maksonic.beresta.feature.onboarding.core

import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.onboarding.api.OnboardingApi

/**
 * @Author maksonic on 15.12.2022
 */
class OnboardingProgram(
    private val onboardingVisibility: OnboardingApi.VisibilityBehavior
) : ElmProgram<Msg, Cmd> {

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.NotShowAgain -> onboardingVisibility.notShowAgain()
        }
    }
}