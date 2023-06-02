package ru.maksonic.beresta.feature.onboarding.core

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
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
@OptIn(ExperimentalMaterial3Api::class)
@Stable
@Immutable
data class Model (
    val currentSheetContent: ModalSheetContent,
    val modalBottomSheetState: SheetState,
    val isVisibleModalSheet: Boolean
) : ElmModel {
    companion object {
        @ExperimentalMaterial3Api
        val Initial = Model(
            currentSheetContent = ModalSheetContent.NOTHING,
            modalBottomSheetState = SheetState(
                initialValue = SheetValue.Hidden,
                skipPartiallyExpanded = true
            ),
            isVisibleModalSheet = false
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
        data class UpdatedModalSheetState(val isVisible: Boolean): Inner()
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