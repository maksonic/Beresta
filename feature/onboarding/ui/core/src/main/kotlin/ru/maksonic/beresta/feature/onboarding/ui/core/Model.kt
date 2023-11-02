package ru.maksonic.beresta.feature.onboarding.ui.core

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.feature.onboarding.domain.Onboarding
import ru.maksonic.beresta.platform.elm.core.ElmCommand
import ru.maksonic.beresta.platform.elm.core.ElmEffect
import ru.maksonic.beresta.platform.elm.core.ElmMessage
import ru.maksonic.beresta.platform.elm.core.ElmModel

/**
 * @Author maksonic on 19.06.2023
 */
enum class ModalSheetContent {
    NOTHING, LANGUAGE_PICKER, THEME_PICKER
}

@Stable
@Immutable
data class ModalSheet(
    val isVisible: Boolean,
    val skipPartiallyExpanded: Boolean,
    val content: ModalSheetContent,
) {
    companion object {
        val Initial = ModalSheet(
            isVisible = false,
            skipPartiallyExpanded = true,
            content = ModalSheetContent.NOTHING,
        )
    }
}

@Stable
@Immutable
data class Model(
    val onboardings: List<Onboarding>,
    val currentPage: Int,
    val modalSheet: ModalSheet,
    val isFailFetched: Boolean

) : ElmModel {
    companion object {
        val Initial = Model(
            onboardings = emptyList(),
            currentPage = 0,
            modalSheet = ModalSheet.Initial,
            isFailFetched = false
        )

        private val onboardingsPreview = List(4) {
            Onboarding(
                id = it.toLong(),
                title = "Title $it onboarding",
                description = "Decription $it onboarding",
                image = ru.maksonic.beresta.common.ui_theme.R.drawable.maksonic_logo_low
            )
        }

        val Preview = Model(
            onboardings = onboardingsPreview,
            currentPage = 0,
            modalSheet = ModalSheet.Initial,
            isFailFetched = false
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        data object OnNextPageBtnClicked : Ui()
        data object OnSkipSyncBtnClicked : Ui()
        data object OnGoogleAuthClicked : Ui()
        data object OnShowLangPickerClicked : Ui()
        data object OnShowThemePickerClicked : Ui()
    }

    sealed class Inner : Msg() {
        data object HiddenModalBottomSheet : Inner()
        data class FetchedOnboardingsData(val onboardings: List<Onboarding>) : Inner()
        data object FetchedOnboardingsFailure : Inner()
    }
}

sealed class Cmd : ElmCommand {
    data object NotShowAgain : Cmd()
    data object FetchOnboardingsProvider : Cmd()
}

sealed class Eff : ElmEffect {
    data object SlideNextPage : Eff()
    data object HideModalSheet : Eff()
    data object NavigateToMain : Eff()
}