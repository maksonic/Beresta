package ru.maksonic.beresta.screen.settings.core

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.*
import ru.maksonic.beresta.screen.settings.ui.widget.ModalSheetContent
import ru.maksonic.beresta.ui.theme.AppTheme

/**
 * @Author maksonic on 23.01.2023
 */
@Stable
data class Model @OptIn(ExperimentalMaterialApi::class) constructor(
    val base: BaseModel = BaseModel(),
    val currentSheetContent: ModalSheetContent = ModalSheetContent.NOTHING,
    val modalBottomSheetState: ModalBottomSheetState = ModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        isSkipHalfExpanded = true
    ),
    val currentTheme: AppTheme = AppTheme.SYSTEM,
) : ElmModel

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        object OnTopBarBackPressed : Ui()
        object OnShowSelectLanguageSheetClicked : Ui()
        object OnShowSelectThemeSheetClicked : Ui()
        object OnHideModalSheetClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedTheme(val theme: AppTheme): Inner()
    }
}

sealed class Cmd : ElmCommand {
    object FetchCurrentTheme: Cmd()
}

sealed class Eff : ElmEffect {
    object NavigateBack : Eff()
    object ShowModalSheet : Eff()
    object HideModalSheet : Eff()
}