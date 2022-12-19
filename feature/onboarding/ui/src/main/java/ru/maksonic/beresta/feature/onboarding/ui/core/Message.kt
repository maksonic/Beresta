package ru.maksonic.beresta.feature.onboarding.ui.core

import ru.maksonic.beresta.elm.ElmMessage
import ru.maksonic.beresta.feature.onboarding.domain.OnboardingEntity

/**
 * @Author maksonic on 15.12.2022
 */
sealed class Message : ElmMessage {
    sealed class Ui : Message() {
        object OnPrimaryBtnClicked : Ui()
        object OnSkipSyncBtnClicked : Ui()
        object OnGoogleAuthClicked : Ui()
    }

    sealed class Inner : Message() {
        data class Onboardings(val data: Array<OnboardingEntity>) : Inner()
    }
}