package ru.maksonic.beresta.feature.onboarding.core.presentation

import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.ElmCommand
import ru.maksonic.beresta.elm.ElmEffect
import ru.maksonic.beresta.elm.ElmMessage
import ru.maksonic.beresta.elm.ElmModel

/**
 * @Author maksonic on 24.12.2022
 */
@Stable
data class Model(val onboardingImages: Array<Int> = emptyArray()) : ElmModel

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        object OnPrimaryBtnClicked : Ui()
        object OnSkipSyncBtnClicked : Ui()
        object OnGoogleAuthClicked : Ui()
        object OnSelectLanguageBtnClicked : Ui()
        object OnHideLanguageBtnClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class OnboardingImages(val data: Array<Int>) : Inner()
    }
}

sealed class Cmd : ElmCommand {
    object FetchOnboardings : Cmd()
    object NotShowAgain : Cmd()
}

sealed class Eff : ElmEffect {
    object SlideNextPage : Eff()
    object ShowLanguageSheet : Eff()
    object HideLanguageSheet: Eff()
    object NavigateToMain : Eff()
}