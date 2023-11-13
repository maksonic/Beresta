package ru.maksonic.beresta.screen.settings.core

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.common.ui_theme.AppThemeUi
import ru.maksonic.beresta.platform.elm.core.ElmBaseModel
import ru.maksonic.beresta.platform.elm.core.ElmCommand
import ru.maksonic.beresta.platform.elm.core.ElmEffect
import ru.maksonic.beresta.platform.elm.core.ElmMessage
import ru.maksonic.beresta.platform.elm.core.ElmModel

/**
 * @Author maksonic on 23.01.2023
 */
enum class ModalSheetContent {
    NOTHING, LANGUAGE_PICKER, THEME_PICKER
}


@Stable
@Immutable
data class ModalSheet(
    val isVisible: Boolean,
    val skipPartiallyExpanded: Boolean,
    val content: ModalSheetContent
) {
    companion object {
        val Initial = ModalSheet(
            isVisible = false,
            skipPartiallyExpanded = true,
            content = ModalSheetContent.NOTHING
        )
    }
}

@Stable
@Immutable
data class Model(
    val base: ElmBaseModel,
    val modalSheet: ModalSheet,
    val currentTheme: AppThemeUi,
    val isDarkTheme: Boolean,
) : ElmModel {
    companion object {
        val Initial = Model(
            base = ElmBaseModel.Initial,
            modalSheet = ModalSheet.Initial,
            currentTheme = AppThemeUi.SYSTEM,
            isDarkTheme = false,
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
        data object OnNotificationsClicked : Ui()
        data object OnSecurityClicked : Ui()
        data object OnUserAccountClicked : Ui()
        data object OnTagsManagementClicked : Ui()
        data object OnWriteEmailClicked : Ui()
        data object OnPrivacyPolicyClicked : Ui()
        data object OnUserAgreementClicked : Ui()
        data object OnAboutAppClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedTheme(val theme: AppThemeUi, val isDark: Boolean) : Inner()
        data object HiddenModalBottomSheet : Inner()
    }
}

sealed class Cmd : ElmCommand {
    data object FetchCurrentTheme : Cmd()
}

sealed class Eff : ElmEffect {
    data object NavigateBack : Eff()
    data object NavigateToAppearance : Eff()
    data object NavigateToNotifications : Eff()
    data object NavigateToSecurity : Eff()
    data object NavigateToTags : Eff()
    data object HideModalSheet : Eff()
}