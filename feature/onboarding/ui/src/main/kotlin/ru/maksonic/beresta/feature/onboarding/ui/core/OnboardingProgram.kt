package ru.maksonic.beresta.feature.onboarding.ui.core

import ru.maksonic.beresta.elm.core.ElmProgram
import ru.maksonic.beresta.feature.onboarding.api.OnboardingApi

/**
 * @Author maksonic on 19.06.2023
 */
class OnboardingProgram(private val onboardingApi: OnboardingApi.Feature) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.NotShowAgain -> onboardingApi.notShowAgain()
        }
    }
}