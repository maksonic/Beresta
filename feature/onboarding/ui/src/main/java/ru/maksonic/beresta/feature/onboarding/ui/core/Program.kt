package ru.maksonic.beresta.feature.onboarding.ui.core

import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.onboarding.domain.OnboardingVisibility
import ru.maksonic.beresta.feature.onboarding.domain.Repository
import ru.maksonic.beresta.navigation.router.AppNavigator

/**
 * @Author maksonic on 15.12.2022
 */
class Program(
    private val repository: Repository,
    private val onboardingVisibility: OnboardingVisibility,
    private val navigator: AppNavigator
) : ElmProgram<Message, Command> {
    override suspend fun executeProgram(cmd: Command, consumer: (Message) -> Unit) {
        when (cmd) {
            is Command.FetchOnboardings -> fetchOnboardings(consumer)
            is Command.NavigateToMainScreen -> navigateToMain()
        }
    }

    private fun fetchOnboardings(consumer: (Message) -> Unit) {
        consumer(Message.Inner.Onboardings(repository.onboardings))
    }

    private suspend fun navigateToMain() {
        onboardingVisibility.notShowAgain().run { navigator.onboardingToMain() }
    }
}