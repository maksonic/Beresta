package ru.maksonic.beresta.feature.onboarding.core

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.ElmCommand
import ru.maksonic.beresta.elm.ElmEffect
import ru.maksonic.beresta.elm.ElmMessage
import ru.maksonic.beresta.elm.ElmModel
import ru.maksonic.beresta.feature.onboarding.core.widget.ModalSheetContent

/**
 * @Author maksonic on 24.12.2022
 */
@Stable
@Immutable
data class Model @OptIn(ExperimentalMaterialApi::class) constructor(
    val currentSheetContent: ModalSheetContent,
    val modalBottomSheetState: ModalBottomSheetState
) : ElmModel {
    companion object {
        @OptIn(ExperimentalMaterialApi::class)
        val Initial = Model(
            currentSheetContent = ModalSheetContent.NOTHING,
            modalBottomSheetState = ModalBottomSheetState(
                initialValue = ModalBottomSheetValue.Hidden,
                isSkipHalfExpanded = true
            ),
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        object OnNextPageBtnClicked : Ui()
        object OnSkipSyncBtnClicked : Ui()
        object OnGoogleAuthClicked : Ui()
        object OnShowLangPickerClicked : Ui()
        object OnShowThemePickerClicked : Ui()
        object OnHideModalBottomSheet : Ui()
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