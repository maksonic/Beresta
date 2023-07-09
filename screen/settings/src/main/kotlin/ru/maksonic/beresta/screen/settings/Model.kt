package ru.maksonic.beresta.screen.settings

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
        object HiddenModalBottomSheet : Inner()
    }
}

sealed class Cmd : ElmCommand {
    object FetchCurrentTheme : Cmd()
}

sealed class Eff : ElmEffect {
    object NavigateBack : Eff()
    object NavigateToAppearance : Eff()
    object HideModalSheet : Eff()
}