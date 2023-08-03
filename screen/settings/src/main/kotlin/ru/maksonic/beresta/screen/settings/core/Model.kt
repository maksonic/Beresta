package ru.maksonic.beresta.screen.settings.core

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.core.ElmBaseModel
import ru.maksonic.beresta.elm.core.ElmCommand
import ru.maksonic.beresta.elm.core.ElmEffect
import ru.maksonic.beresta.elm.core.ElmMessage
import ru.maksonic.beresta.elm.core.ElmModel
import ru.maksonic.beresta.screen.settings.ui.widget.ModalSheetContent
import ru.maksonic.beresta.ui.theme.AppTheme

/**
 * @Author maksonic on 23.01.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Stable
@Immutable
data class Model(
    val base: ElmBaseModel,
    val currentSheetContent: ModalSheetContent,
    val modalBottomSheetState: SheetState,
    val currentTheme: AppTheme,
    val isDarkTheme: Boolean,
    val isVisibleModalSheet: Boolean
) : ElmModel {
    companion object {
        val Initial = Model(
            base = ElmBaseModel.Initial,
            currentSheetContent = ModalSheetContent.NOTHING,
            modalBottomSheetState = SheetState(
                initialValue = SheetValue.Hidden,
                skipPartiallyExpanded = true
            ),
            currentTheme = AppTheme.SYSTEM,
            isDarkTheme = false,
            isVisibleModalSheet = false
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        data object OnTopBarBackPressed : Ui()
        data object OnHideModalSheetClicked : Ui()
        data object OnPickLanguageClicked : Ui()
        data object OnPickThemeClicked : Ui()
        data object OnAppearanceClicked : Ui()
        data object OnSecurityClicked : Ui()
        data object OnUserAccountClicked : Ui()
        data object OnWriteEmailClicked : Ui()
        data object OnPrivacyPolicyClicked : Ui()
        data object OnUserAgreementClicked : Ui()
        data object OnAboutAppClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedTheme(val theme: AppTheme, val isDark: Boolean) : Inner()
        data object HiddenModalBottomSheet : Inner()
    }
}

sealed class Cmd : ElmCommand {
    data object FetchCurrentTheme : Cmd()
}

sealed class Eff : ElmEffect {
    data object NavigateBack : Eff()
    data object NavigateToAppearance : Eff()
    data object NavigateToSecurity : Eff()
    data object HideModalSheet : Eff()
}