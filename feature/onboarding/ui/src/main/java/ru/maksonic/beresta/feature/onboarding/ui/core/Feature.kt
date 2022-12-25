package ru.maksonic.beresta.feature.onboarding.ui.core

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.ElmCommand
import ru.maksonic.beresta.elm.ElmEffect
import ru.maksonic.beresta.elm.ElmMessage
import ru.maksonic.beresta.elm.ElmModel
import ru.maksonic.beresta.feature.onboarding.domain.OnboardingEntity

/**
 * @Author maksonic on 24.12.2022
 */
object Feature {

    @Stable
    @Immutable
    data class Model(
        val onboardings: Array<OnboardingEntity> = emptyArray()
    ) : ElmModel {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Model

            if (!onboardings.contentEquals(other.onboardings)) return false

            return true
        }

        override fun hashCode(): Int {
            return onboardings.contentHashCode()
        }
    }

    sealed class Msg : ElmMessage {
        sealed class Ui : Msg() {
            object OnPrimaryBtnClicked : Ui()
            object OnSkipSyncBtnClicked : Ui()
            object OnGoogleAuthClicked : Ui()
        }

        sealed class Inner : Msg() {
            data class Onboardings(val data: Array<OnboardingEntity>) : Inner()
        }
    }

    sealed class Cmd : ElmCommand {
        object NavigateToMainScreen : Cmd()
        object FetchOnboardings : Cmd()
    }

    sealed class Eff : ElmEffect {
        object SlideNextPage : Eff()
    }


}