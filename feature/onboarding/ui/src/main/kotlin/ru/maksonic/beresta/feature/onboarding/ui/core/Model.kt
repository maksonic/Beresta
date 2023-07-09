package ru.maksonic.beresta.feature.onboarding.ui.core

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.core.ElmCommand
import ru.maksonic.beresta.elm.core.ElmEffect
import ru.maksonic.beresta.elm.core.ElmMessage
import ru.maksonic.beresta.elm.core.ElmModel
import ru.maksonic.beresta.feature.onboarding.api.OnboardingApi

/**
 * @Author maksonic on 19.06.2023
 */
@Stable
@Immutable
@OptIn(ExperimentalMaterial3Api::class)
data class Model(
    val currentPage: Int,
    val isVisibleModalSheet: Boolean,
    val modalBottomSheetState: SheetState,
    val currentSheetContent: OnboardingApi.Ui.BottomSheetContent,
) : ElmModel {
    companion object {
        val Initial = Model(
            currentPage = 0,
            isVisibleModalSheet = false,
            modalBottomSheetState = SheetState(
                initialValue = SheetValue.Hidden,
                skipPartiallyExpanded = true
            ),
            currentSheetContent = OnboardingApi.Ui.BottomSheetContent.NOTHING
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

    sealed class Inner : Msg() {
        data class UpdatedModalSheetVisibility(val isVisible: Boolean) : Inner()
    }
}

sealed class Cmd : ElmCommand {
    object NotShowAgain : Cmd()
}

sealed class Eff : ElmEffect {
    object SlideNextPage : Eff()
    object HideModalSheet : Eff()
    object NavigateToMain : Eff()
}