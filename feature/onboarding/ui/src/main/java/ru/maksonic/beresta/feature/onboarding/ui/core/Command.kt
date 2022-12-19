package ru.maksonic.beresta.feature.onboarding.ui.core

import ru.maksonic.beresta.elm.ElmCommand

/**
 * @Author maksonic on 15.12.2022
 */
sealed class Command: ElmCommand {
    object NavigateToMainScreen : Command()
    object FetchOnboardings : Command()
}