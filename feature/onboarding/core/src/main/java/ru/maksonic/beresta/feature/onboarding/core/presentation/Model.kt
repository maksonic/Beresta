package ru.maksonic.beresta.feature.onboarding.core.presentation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.ElmCommand
import ru.maksonic.beresta.elm.ElmEffect
import ru.maksonic.beresta.elm.ElmMessage
import ru.maksonic.beresta.elm.ElmModel
import ru.maksonic.beresta.feature.language_selector.api.components.OnboardingDataItem
import ru.maksonic.beresta.feature.onboarding.core.presentation.ui.ModalSheetContent
import ru.maksonic.beresta.feature.onboarding.core.presentation.ui.OnboardingUi

/**
 * @Author maksonic on 24.12.2022
 */
@Stable
data class Model @OptIn(ExperimentalMaterialApi::class) constructor(
    val onboardings: Array<OnboardingUi> = emptyArray(),
    val currentSheetContent: ModalSheetContent = ModalSheetContent.NOTHING,
    val modalBottomSheetState: ModalBottomSheetState = ModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        isSkipHalfExpanded = true
    ),
) : ElmModel

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        object OnPrimaryBtnClicked : Ui()
        object OnSkipSyncBtnClicked : Ui()
        object OnGoogleAuthClicked : Ui()
        object OnShowSelectLanguageSheetClicked : Ui()
        object OnShowSelectThemeSheetClicked : Ui()
        object OnHideLanguageBtnClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class Onboardings(val data: Array<OnboardingUi>) : Inner()
        data class FetchOnboardingTextData(val data: Array<OnboardingDataItem>) : Inner()
    }
}

sealed class Cmd : ElmCommand {
    data class FetchOnboardings(val textData: Array<OnboardingDataItem>) : Cmd()
    object NotShowAgain : Cmd()
}

sealed class Eff : ElmEffect {
    object SlideNextPage : Eff()
    object ShowModalSheet : Eff()
    object HideModalSheet : Eff()
    object NavigateToMain : Eff()
}