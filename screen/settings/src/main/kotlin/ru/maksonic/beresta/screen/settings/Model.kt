@file:OptIn(ExperimentalMaterialApi::class)

package ru.maksonic.beresta.screen.settings

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
    val base: BaseModel,
    val currentSheetContent: ModalSheetContent,
    val modalBottomSheetState: ModalBottomSheetState,
    val currentTheme: AppTheme,
    val isDarkTheme: Boolean,
) : ElmModel {
    companion object {
        @OptIn(ExperimentalMaterialApi::class)
        val Initial = Model(
            base = BaseModel.Initial,
            currentSheetContent = ModalSheetContent.NOTHING,
            modalBottomSheetState = ModalBottomSheetState(
                initialValue = ModalBottomSheetValue.Hidden,
                isSkipHalfExpanded = true
            ),
            currentTheme = AppTheme.SYSTEM,
            isDarkTheme = false,
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        object OnTopBarBackPressed : Ui()
        object OnHideModalSheetClicked : Ui()
        object OnPickLanguageClicked : Ui()
        object OnPickThemeClicked : Ui()
        object OnAppearanceClicked : Ui()
        object OnUserAccountClicked : Ui()
        object OnWriteEmailClicked : Ui()
        object OnPrivacyPolicyClicked : Ui()
        object OnUserAgreementClicked : Ui()
        object OnAboutAppClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedTheme(val theme: AppTheme, val isDark: Boolean) : Inner()
    }
}

sealed class Cmd : ElmCommand {
    object FetchCurrentTheme : Cmd()
}

sealed class Eff : ElmEffect {
    object NavigateBack : Eff()
    object ShowModalSheet : Eff()
    object HideModalSheet : Eff()
}