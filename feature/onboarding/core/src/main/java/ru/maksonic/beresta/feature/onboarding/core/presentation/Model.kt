package ru.maksonic.beresta.feature.onboarding.core.presentation

import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.ElmCommand
import ru.maksonic.beresta.elm.ElmEffect
import ru.maksonic.beresta.elm.ElmMessage
import ru.maksonic.beresta.elm.ElmModel
import ru.maksonic.beresta.feature.language_selector.api.components.OnboardingDataItem
import ru.maksonic.beresta.feature.onboarding.core.presentation.ui.OnboardingUi

/**
 * @Author maksonic on 24.12.2022
 */
@Stable
data class Model(val onboardings: Array<OnboardingUi> = emptyArray()) : ElmModel

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        object OnPrimaryBtnClicked : Ui()
        object OnSkipSyncBtnClicked : Ui()
        object OnGoogleAuthClicked : Ui()
        object OnSelectLanguageBtnClicked : Ui()
        object OnHideLanguageBtnClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class Onboardings(val data: Array<OnboardingUi>) : Inner()
        data class FetchOnboardingTextData(val data: Array<OnboardingDataItem>): Inner()
    }
}

sealed class Cmd : ElmCommand {
    data class FetchOnboardings(val textData: Array<OnboardingDataItem>) : Cmd()
    object NotShowAgain : Cmd()
}

sealed class Eff : ElmEffect {
    object SlideNextPage : Eff()
    object ShowLanguageSheet : Eff()
    object HideLanguageSheet: Eff()
    object NavigateToMain : Eff()
}