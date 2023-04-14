package ru.maksonic.beresta.feature.onboarding.core.presentation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.ElmCommand
import ru.maksonic.beresta.elm.ElmEffect
import ru.maksonic.beresta.elm.ElmMessage
import ru.maksonic.beresta.elm.ElmModel
import ru.maksonic.beresta.feature.onboarding.core.presentation.ui.ModalSheetContent

/**
 * @Author maksonic on 24.12.2022
 */
@Stable
@Immutable
data class Model @OptIn(ExperimentalMaterialApi::class) constructor(
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
}

sealed class Cmd : ElmCommand {
    object NotShowAgain : Cmd()
}

sealed class Eff : ElmEffect {
    object SlideNextPage : Eff()
    object ShowModalSheet : Eff()
    object HideModalSheet : Eff()
    object NavigateToMain : Eff()
}